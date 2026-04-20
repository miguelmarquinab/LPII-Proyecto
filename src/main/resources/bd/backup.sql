
create table logisticoradiostetra.asignacionRadios
(
    asr_id                      int                                     not null
        primary key,
    dec_id                      int                                     not null,
    rad_id                      int                                     not null,
    asr_fechaAsignacion         date                                    not null,
    asr_fechaDevolucion         date                                    null,
    asr_estado                  varchar(50) default 'ASIGNADO'          null,
    asr_observacionesAsignacion text                                    null,
    asr_observacionesDevolucion text                                    null,
    asr_filaFecha               timestamp   default current_timestamp() null,
    asr_filaOriginal            tinyint(1)  default 1                   null,
    asr_filaEliminada           tinyint(1)  default 0                   null,
    usa_id                      int                                     not null
);

create table logisticoradiostetra.clientes
(
    cli_id            int auto_increment
        primary key,
    cli_codigo        varchar(50)                            not null,
    cli_razonSocial   varchar(255)                           not null,
    cli_tipoDocumento varchar(20)                            null,
    cli_nroDocumento  varchar(20)                            not null,
    cli_direccion     text                                   null,
    cli_telefono      varchar(50)                            null,
    cli_email         varchar(100)                           null,
    cli_contacto      varchar(255)                           null,
    cli_activo        tinyint(1) default 1                   null,
    cli_filaFecha     timestamp  default current_timestamp() null,
    cli_filaOriginal  tinyint(1) default 1                   null,
    cli_filaEliminada tinyint(1) default 0                   null,
    usa_id            int                                    not null,
    constraint cli_codigo
        unique (cli_codigo)
);

create table logisticoradiostetra.contratos
(
    con_id            int auto_increment
        primary key,
    cli_id            int                          not null,
    con_numero        varchar(100)                 not null,
    con_fechaInicio   date                         not null,
    con_fechaFin      date                         not null,
    con_estado        varchar(50) default 'ACTIVO' null,
    con_tipoContrato  varchar(50)                  null,
    con_valorTotal    decimal(12, 2)               null,
    con_valorMensual  decimal(10, 2)               null,
    con_observaciones text                         null,
    con_filaEliminada tinyint(1)  default 0        null,
    usa_id            int         default 1        null,
    constraint con_numero
        unique (con_numero),
    constraint fk_contrato_cliente
        foreign key (cli_id) references clientes (cli_id)
            on update cascade on delete cascade
);

create table logisticoradiostetra.detalleContratos
(
    dec_id             int auto_increment
        primary key,
    con_id             int                  not null,
    mod_id             int                  not null,
    dec_cantidad       int                  not null,
    dec_precioUnitario decimal(10, 2)       not null,
    dec_subtotal       decimal(12, 2)       not null,
    dec_filaEliminada  tinyint(1) default 0 null,
    usa_id             int        default 1 null,
    constraint fk_detalle_contrato
        foreign key (con_id) references contratos (con_id)
            on update cascade on delete cascade
);

create table logisticoradiostetra.estadoradio
(
    esr_id            int auto_increment
        primary key,
    esr_descripcion   varchar(100) null,
    esr_sigla         varchar(100) null,
    esr_activo        tinyint(1)   null,
    esr_filafecha     datetime     null,
    esr_filaoriginal  tinyint(1)   null,
    esr_filaeliminada tinyint(1)   null,
    usa_id            int          null
);

create table logisticoradiostetra.modeloRadio
(
    mod_id            int auto_increment
        primary key,
    mod_descripcion   varchar(100)                           null,
    mod_codigo        varchar(100)                           null,
    mod_filaFecha     timestamp  default current_timestamp() null,
    mod_filaOriginal  tinyint(1) default 1                   null,
    mod_filaEliminada tinyint(1) default 0                   null,
    usa_id            int                                    null
);

create table logisticoradiostetra.proveedores
(
    pve_id           int auto_increment
        primary key,
    pve_codigo       varchar(50)  not null,
    pve_razon_social varchar(255) not null,
    pve_pais         varchar(100) null,
    pve_ruc          varchar(20)  null,
    usa_id           int          not null
);

create table logisticoradiostetra.radios
(
    rad_id            int auto_increment
        primary key,
    mod_id            int          null,
    esr_id            int          null,
    serie             varchar(100) null,
    fecha_ingreso     datetime     null,
    rad_activo        tinyint(1)   null,
    rad_filafecha     datetime     null,
    rad_filaoriginal  tinyint(1)   null,
    rad_filaeliminado tinyint(1)   null,
    usa_id            int          null
);

create table logisticoradiostetra.roles
(
    rol_id     int auto_increment
        primary key,
    rol_nombre varchar(100) null,
    rol_estado varchar(100) null,
    rol_activo tinyint(1)   null
);

create table logisticoradiostetra.usuarios
(
    usa_id              int auto_increment
        primary key,
    nombre              varchar(100) null,
    clave               varchar(100) null,
    usa_nombres         varchar(100) null,
    usa_apellidopaterno varchar(100) null,
    usa_apellidomaterno varchar(100) null,
    usa_fechanacimiento varchar(100) null,
    usa_genero          varchar(100) null,
    usa_estado          varchar(100) null,
    usa_activo          varchar(100) null,
    usa_filafecha       datetime     null,
    usa_filaoriginal    tinyint(1)   null,
    usa_filaeliminada   tinyint(1)   null,
    rol                 int          null
);

create procedure logisticoradiostetra.usp_Buscar_Contratos(IN cliente_id int,
                                                           IN estado varchar(100),
                                                           IN fecha_inicio varchar(10),
                                                           IN fecha_fin varchar(10))
begin
select c.con_id, cl.cli_id, con_numero, con_fechaInicio, con_fechaFin, con_valorMensual,
       con_estado, COALESCE(SUM(dc.dec_cantidad), 0) as nro_radios,  cl.cli_razonSocial
from contratos c join
     clientes cl on c.cli_id = cl.cli_id
                 left join detalleContratos dc
                           on c.con_id = dc.con_id
where c.con_estado like concat('%', estado, '%')
  and (cliente_id = 0 OR c.cli_id = cliente_id)
  and (fecha_inicio = '' OR con_fechaInicio = STR_TO_DATE(fecha_inicio, '%Y-%m-%d'))
  and (fecha_fin = '' OR con_fechaFin = STR_TO_DATE(fecha_fin, '%Y-%m-%d'))
GROUP BY c.con_id, cl.cli_id, con_numero, con_fechaInicio,
         con_fechaFin, con_valorMensual, con_estado, cl.cli_razonSocial
;
end;

create procedure usp_Buscar_ModeloRadio(IN texto varchar(100))
BEGIN
SELECT
    mod_id,
    mod_descripcion,
    mod_codigo
FROM
    modeloRadio
WHERE
    mod_descripcion LIKE CONCAT('%', texto, '%')
   OR mod_codigo LIKE CONCAT('%', texto, '%');
END;

create procedure usp_Buscar_Radio(IN p_texto varchar(100))
BEGIN
SELECT
    r.rad_id,
    mr.mod_codigo,
    mr.mod_descripcion AS modelo,
    e.esr_descripcion AS estado,
    r.serie,
    r.fecha_ingreso,
    r.rad_activo
FROM radios r
         INNER JOIN modeloRadio  mr ON mr.mod_id = r.mod_id
         INNER JOIN estadoradio  e  ON e.esr_id = r.esr_id
-- WHERE (p_texto IS NULL OR p_texto = '' OR r.serie LIKE CONCAT('%', p_texto, '%'));
WHERE (p_texto IS NULL OR p_texto = '' OR (
    r.serie            LIKE CONCAT('%', p_texto, '%')
        OR mr.mod_descripcion LIKE CONCAT('%', p_texto, '%')
        OR e.esr_descripcion  LIKE CONCAT('%', p_texto, '%')
    ));

END;

create  procedure usp_Buscar_Usuario(IN p_nombre varchar(100))
BEGIN
SELECT
    u.nombre,
    r.rol_nombre
FROM usuarios u
         INNER JOIN roles r
                    ON r.rol_id = u.rol
WHERE u.nombre = p_nombre;
END;

create procedure usp_radio_eliminar(IN p_rad_id int)
BEGIN
DELETE FROM radios WHERE rad_id = p_rad_id;
END;

create procedure usp_radio_estados()
BEGIN
SELECT esr_id, esr_descripcion
FROM estadoradio
ORDER BY esr_descripcion;
END;

create procedure usp_radio_guardar(IN p_rad_id int, IN p_mod_id int,
                                   IN p_esr_id int, IN p_serie varchar(50),
                                   IN p_fecha_ingreso datetime,
                                   IN p_activo tinyint)
BEGIN
  IF COALESCE(p_rad_id,0) = 0 THEN
    INSERT INTO radios (mod_id, esr_id, serie, fecha_ingreso, rad_activo)
    VALUES (p_mod_id, p_esr_id, p_serie, p_fecha_ingreso, p_activo);
SELECT LAST_INSERT_ID() AS nuevo_id;
ELSE
UPDATE radios
SET mod_id = p_mod_id,
    esr_id = p_esr_id,
    serie  = p_serie,
    fecha_ingreso = p_fecha_ingreso,
    rad_activo = p_activo
WHERE rad_id = p_rad_id;
SELECT p_rad_id AS nuevo_id;
END IF;
END;

create  procedure usp_radio_modelos()
BEGIN
SELECT mod_id, mod_codigo, mod_descripcion
FROM modeloRadio
ORDER BY mod_codigo;
END;

create procedure usp_radio_obtener(IN p_rad_id int)
BEGIN
SELECT  r.rad_id        AS radio_id,
        r.mod_id        AS mod_id,
        r.esr_id        AS esr_id,
        r.serie         AS serie,
        DATE_FORMAT(r.fecha_ingreso, '%Y-%m-%d %H:%i:%s') AS fecha_ingreso,
        r.rad_activo    AS rad_activo,
        mr.mod_codigo   AS mod_codigo,
        mr.mod_descripcion AS modelo,
        e.esr_descripcion  AS estado
FROM radios r
         LEFT JOIN modeloRadio mr ON mr.mod_id = r.mod_id
         LEFT JOIN estadoradio e  ON e.esr_id  = r.esr_id
WHERE r.rad_id = p_rad_id;
END;

INSERT INTO clientes (cli_id, cli_codigo, cli_razonSocial, cli_tipoDocumento, cli_nroDocumento, cli_direccion, cli_telefono, cli_email, cli_contacto, cli_activo, cli_filaFecha, cli_filaOriginal, cli_filaEliminada, usa_id) VALUES (1, 'COD-001', 'Empresa de Muestra S.A.C.', 'RUC', '20123456789', 'Av. Los Olivos 123, Lima', '987654322', 'contacto123@ejemplo.com', 'Juan Pérez', 1, '2025-10-24 03:54:05', 1, null, 1);
INSERT INTO clientes (cli_id, cli_codigo, cli_razonSocial, cli_tipoDocumento, cli_nroDocumento, cli_direccion, cli_telefono, cli_email, cli_contacto, cli_activo, cli_filaFecha, cli_filaOriginal, cli_filaEliminada, usa_id) VALUES (2, 'COD-002', 'Empresa Demo S.A.C.', 'RUC', '20123456799', 'Las Flores 123', '98989895', 'demo@gmail.com', 'José Flores', 0, '2025-08-16 19:10:12', 1, 0, 1);
INSERT INTO clientes (cli_id, cli_codigo, cli_razonSocial, cli_tipoDocumento, cli_nroDocumento, cli_direccion, cli_telefono, cli_email, cli_contacto, cli_activo, cli_filaFecha, cli_filaOriginal, cli_filaEliminada, usa_id) VALUES (3, 'COD-003', 'PERU SAC', 'RUC', '20123456999', 'Las Begonias 555', '985658985', 'fdfdf@gmail.com', 'José Perez', 1, '2025-08-27 22:47:40', 1, 0, 1);
INSERT INTO clientes (cli_id, cli_codigo, cli_razonSocial, cli_tipoDocumento, cli_nroDocumento, cli_direccion, cli_telefono, cli_email, cli_contacto, cli_activo, cli_filaFecha, cli_filaOriginal, cli_filaEliminada, usa_id) VALUES (4, 'COD-004', 'Empresa CIBERTEC SAC', 'RUC', '20845636957', 'Av. Grau N°456', '936587142', 'fiveril277@3dboxer.com', 'Alberto Nuñez Vega', 1, '2025-08-28 20:39:26', 1, 0, 1);

SELECT * FROM clientes;

INSERT INTO contratos (con_id, cli_id, con_numero, con_fechaInicio, con_fechaFin, con_estado, con_tipoContrato, con_valorTotal, con_valorMensual, con_observaciones, con_filaEliminada, usa_id) VALUES (1, 1, 'CON-001', '2025-08-17', '2025-10-17', 'ACTIVO', 'Mensual', 1000.00, 500.00, 'Sin observaciones', 0, 1);
INSERT INTO contratos (con_id, cli_id, con_numero, con_fechaInicio, con_fechaFin, con_estado, con_tipoContrato, con_valorTotal, con_valorMensual, con_observaciones, con_filaEliminada, usa_id) VALUES (7, 1, 'CON-007', '2025-08-27', '2025-12-27', 'Por asignar', 'Mensual', 1360.00, 340.00, 'una obs', 0, 1);
INSERT INTO contratos (con_id, cli_id, con_numero, con_fechaInicio, con_fechaFin, con_estado, con_tipoContrato, con_valorTotal, con_valorMensual, con_observaciones, con_filaEliminada, usa_id) VALUES (8, 1, 'CON-002', '2025-08-27', '2025-12-27', 'Por asignar', 'Mensual', 4000.00, 1000.00, 'ninguna', 0, 1);
INSERT INTO contratos (con_id, cli_id, con_numero, con_fechaInicio, con_fechaFin, con_estado, con_tipoContrato, con_valorTotal, con_valorMensual, con_observaciones, con_filaEliminada, usa_id) VALUES (9, 1, 'CON-004', '2025-08-27', '2025-08-30', 'Por asignar', 'Trimestral', 0.00, 23175.00, 'Ninguna.', 0, 1);
INSERT INTO contratos (con_id, cli_id, con_numero, con_fechaInicio, con_fechaFin, con_estado, con_tipoContrato, con_valorTotal, con_valorMensual, con_observaciones, con_filaEliminada, usa_id) VALUES (10, 2, 'CON-005', '2025-08-04', '2025-12-04', 'Por asignar', 'Mensual', 9600.00, 2400.00, 'Ninguna', 0, 1);
INSERT INTO contratos (con_id, cli_id, con_numero, con_fechaInicio, con_fechaFin, con_estado, con_tipoContrato, con_valorTotal, con_valorMensual, con_observaciones, con_filaEliminada, usa_id) VALUES (11, 4, '456589', '2025-08-28', '2025-09-30', 'Por asignar', 'Mensual', 0.00, 0.00, '', 0, 1);


SELECT * FROM contratos;


INSERT INTO detalleContratos (dec_id, con_id, mod_id, dec_cantidad, dec_precioUnitario, dec_subtotal, dec_filaEliminada, usa_id) VALUES (1, 1, 1, 2, 250.00, 500.00, 0, 1);
INSERT INTO detalleContratos (dec_id, con_id, mod_id, dec_cantidad, dec_precioUnitario, dec_subtotal, dec_filaEliminada, usa_id) VALUES (2, 1, 2, 2, 250.00, 500.00, 0, 1);
INSERT INTO detalleContratos (dec_id, con_id, mod_id, dec_cantidad, dec_precioUnitario, dec_subtotal, dec_filaEliminada, usa_id) VALUES (3, 8, 1, 2, 500.00, 1000.00, 0, 1);
INSERT INTO detalleContratos (dec_id, con_id, mod_id, dec_cantidad, dec_precioUnitario, dec_subtotal, dec_filaEliminada, usa_id) VALUES (4, 7, 1, 1, 100.00, 100.00, 0, 1);
INSERT INTO detalleContratos (dec_id, con_id, mod_id, dec_cantidad, dec_precioUnitario, dec_subtotal, dec_filaEliminada, usa_id) VALUES (5, 9, 1, 15, 445.00, 6675.00, 0, 1);
INSERT INTO detalleContratos (dec_id, con_id, mod_id, dec_cantidad, dec_precioUnitario, dec_subtotal, dec_filaEliminada, usa_id) VALUES (6, 9, 2, 25, 660.00, 16500.00, 0, 1);
INSERT INTO detalleContratos (dec_id, con_id, mod_id, dec_cantidad, dec_precioUnitario, dec_subtotal, dec_filaEliminada, usa_id) VALUES (7, 10, 3, 5, 120.00, 600.00, 0, 1);
INSERT INTO detalleContratos (dec_id, con_id, mod_id, dec_cantidad, dec_precioUnitario, dec_subtotal, dec_filaEliminada, usa_id) VALUES (8, 10, 1, 12, 150.00, 1800.00, 0, 1);
INSERT INTO detalleContratos (dec_id, con_id, mod_id, dec_cantidad, dec_precioUnitario, dec_subtotal, dec_filaEliminada, usa_id) VALUES (9, 7, 2, 2, 120.00, 240.00, 0, 1);
INSERT INTO detalleContratos (dec_id, con_id, mod_id, dec_cantidad, dec_precioUnitario, dec_subtotal, dec_filaEliminada, usa_id) VALUES (10, 11, 1, 2, 0.00, 0.00, 0, 1);

INSERT INTO estadoradio (esr_id, esr_descripcion, esr_sigla, esr_activo, esr_filafecha, esr_filaoriginal, esr_filaeliminada, usa_id) VALUES (1, 'Disponible', null, null, '2025-08-18 10:26:42', null, 0, null);
INSERT INTO estadoradio (esr_id, esr_descripcion, esr_sigla, esr_activo, esr_filafecha, esr_filaoriginal, esr_filaeliminada, usa_id) VALUES (2, 'Reparacion', null, 1, '2025-08-18 10:29:57', null, 0, null);
INSERT INTO estadoradio (esr_id, esr_descripcion, esr_sigla, esr_activo, esr_filafecha, esr_filaoriginal, esr_filaeliminada, usa_id) VALUES (3, 'Baja', null, 1, '2025-08-18 10:30:38', null, 0, null);
INSERT INTO estadoradio (esr_id, esr_descripcion, esr_sigla, esr_activo, esr_filafecha, esr_filaoriginal, esr_filaeliminada, usa_id) VALUES (4, 'Alta', null, 1, '2025-08-18 10:30:41', null, 0, null);


INSERT INTO modeloRadio (mod_id, mod_descripcion, mod_codigo, mod_filaFecha, mod_filaOriginal, mod_filaEliminada, usa_id) VALUES (1, 'TETRA 2', 'M00001', '2025-08-12 22:57:03', 1, 0, 0);
INSERT INTO modeloRadio (mod_id, mod_descripcion, mod_codigo, mod_filaFecha, mod_filaOriginal, mod_filaEliminada, usa_id) VALUES (2, 'TETRA MXP600', 'M00002', '2025-08-12 22:09:29', 1, 0, 0);
INSERT INTO modeloRadio (mod_id, mod_descripcion, mod_codigo, mod_filaFecha, mod_filaOriginal, mod_filaEliminada, usa_id) VALUES (3, 'TETRA MTP3100', 'M00003', '2025-08-14 16:10:54', 1, 0, null);
INSERT INTO modeloRadio (mod_id, mod_descripcion, mod_codigo, mod_filaFecha, mod_filaOriginal, mod_filaEliminada, usa_id) VALUES (8, 'TETRA MTP3900', 'M00002', '2025-08-28 23:38:51', 1, 0, null);

INSERT INTO radios (rad_id, mod_id, esr_id, serie, fecha_ingreso, rad_activo, rad_filafecha, rad_filaoriginal, rad_filaeliminado, usa_id) VALUES (3, 3, 4, '1123456', '2025-08-24 00:00:00', 1, null, null, null, null);
INSERT INTO radios (rad_id, mod_id, esr_id, serie, fecha_ingreso, rad_activo, rad_filafecha, rad_filaoriginal, rad_filaeliminado, usa_id) VALUES (4, 1, 3, '6556-678', '2025-08-29 00:00:00', 0, null, null, null, null);


INSERT INTO roles (rol_id, rol_nombre, rol_estado, rol_activo) VALUES (2, 'asistente administrativo', '1', 1);
INSERT INTO roles (rol_id, rol_nombre, rol_estado, rol_activo) VALUES (3, 'asistente de contratos', '1', 1);
INSERT INTO roles (rol_id, rol_nombre, rol_estado, rol_activo) VALUES (4, 'asistente de soporte tecnico', '1', 1);
INSERT INTO roles (rol_id, rol_nombre, rol_estado, rol_activo) VALUES (5, 'asistente logistico', '1', 1);
INSERT INTO roles (rol_id, rol_nombre, rol_estado, rol_activo) VALUES (6, 'superusuario', '1', 1);
INSERT INTO roles (rol_id, rol_nombre, rol_estado, rol_activo) VALUES (7, 'asistente de almancen', '1', 1);
INSERT INTO roles (rol_id, rol_nombre, rol_estado, rol_activo) VALUES (1, 'ROLE_ADMIN', null, null);

INSERT INTO usuarios (usa_id, nombre, clave, usa_nombres, usa_apellidopaterno, usa_apellidomaterno, usa_fechanacimiento, usa_genero, usa_estado, usa_activo, usa_filafecha, usa_filaoriginal, usa_filaeliminada, rol) VALUES (2, 'mmarquina', 'mmarquina', null, null, null, null, null, null, null, null, null, null, 7);
INSERT INTO usuarios (usa_id, nombre, clave, usa_nombres, usa_apellidopaterno, usa_apellidomaterno, usa_fechanacimiento, usa_genero, usa_estado, usa_activo, usa_filafecha, usa_filaoriginal, usa_filaeliminada, rol) VALUES (1, 'admin', 'admin123', null, null, null, null, null, null, '1', null, null, null, 1);



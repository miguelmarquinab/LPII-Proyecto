

create table asignacionRadios
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
)

create table clientes
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
)

create table contratos
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
)

create table detalleContratos
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
)

create table estadoradio
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
)

create table modeloRadio
(
    mod_id            int auto_increment
        primary key,
    mod_descripcion   varchar(100)                           null,
    mod_codigo        varchar(100)                           null,
    mod_filaFecha     timestamp  default current_timestamp() null,
    mod_filaOriginal  tinyint(1) default 1                   null,
    mod_filaEliminada tinyint(1) default 0                   null,
    usa_id            int                                    null
)

create table proveedores
(
    pve_id           int auto_increment
        primary key,
    pve_codigo       varchar(50)  not null,
    pve_razon_social varchar(255) not null,
    pve_pais         varchar(100) null,
    pve_ruc          varchar(20)  null,
    usa_id           int          not null
)

create table radios
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
)

create table roles
(
    rol_id     int auto_increment
        primary key,
    rol_nombre varchar(100) null,
    rol_estado varchar(100) null,
    rol_activo tinyint(1)   null
)

create table usuarios
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
)

create
    definer = centrowebp1_developer@`190.237.27.223` procedure usp_Buscar_Contratos(IN cliente_id int,
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

create
    definer = centrowebp1_developer@`181.176.72.41` procedure usp_Buscar_ModeloRadio(IN texto varchar(100))
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

create
    definer = centrowebp1_developer@`181.67.43.85` procedure usp_Buscar_Radio(IN p_texto varchar(100))
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

create
    definer = centrowebp1_developer@`177.91.249.141` procedure usp_Buscar_Usuario(IN p_nombre varchar(100))
BEGIN
    SELECT
        u.nombre, 
        r.rol_nombre
    FROM usuarios u
    INNER JOIN roles r 
        ON r.rol_id = u.rol
    WHERE u.nombre = p_nombre;
END;

create
    definer = centrowebp1_developer@`181.67.43.85` procedure usp_radio_eliminar(IN p_rad_id int)
BEGIN
  DELETE FROM radios WHERE rad_id = p_rad_id;
END;

create
    definer = centrowebp1_developer@`181.67.43.85` procedure usp_radio_estados()
BEGIN
  SELECT esr_id, esr_descripcion
  FROM estadoradio
  ORDER BY esr_descripcion;
END;

create
    definer = centrowebp1_developer@`181.67.43.85` procedure usp_radio_guardar(IN p_rad_id int, IN p_mod_id int,
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

create
    definer = centrowebp1_developer@`181.67.43.85` procedure usp_radio_modelos()
BEGIN
  SELECT mod_id, mod_codigo, mod_descripcion
  FROM modeloRadio
  ORDER BY mod_codigo;
END;

create
    definer = centrowebp1_developer@`181.67.43.85` procedure usp_radio_obtener(IN p_rad_id int)
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


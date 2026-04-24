package com.lp2.sisinventario.sistemainventario.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {
	public Cliente(int id, String codigo, String razonSocial, String tipoDocumento, String numeroDocumento,
			String direccion, String telefono, String email, String contacto, boolean activo) { // int usuarioId
		super();
		this.setId(id);
		this.codigo = codigo;
		this.razonSocial = razonSocial;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.contacto = contacto;
		this.fechaRegistro = LocalDateTime.now();
		this.usuarioId = 1;
		this.activo = activo;
	}
	
	public Cliente(String codigo, String razonSocial, String tipoDocumento, String numeroDocumento,
			String direccion, String telefono, String email, String contacto,  boolean activo) {
		this(0, codigo, razonSocial, tipoDocumento, numeroDocumento, direccion, telefono, email, contacto, activo);
	}
	
	public Cliente() {
		this(0, "", "", "", "", "", "", "", "", true);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
    public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	
	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cli_id")
	private Integer id;
	
	@Column(name="cli_codigo")
	private String codigo;
	
	@Column(name="cli_razonSocial")
    private String razonSocial;
	
	@Column(name="cli_tipoDocumento")
    private String tipoDocumento;
	
	@Column(name="cli_nroDocumento")
    private String numeroDocumento;
	
	@Column(name="cli_direccion")
    private String direccion;
	
	@Column(name="cli_telefono")
    private String telefono;
	
	@Column(name="cli_email")
    private String email;
	
	@Column(name="cli_contacto")
    private String contacto;
	
	@Column(name="cli_activo")
    private boolean activo;
	
	@Column(name = "\"cli_filaFecha\"")
    private LocalDateTime fechaRegistro;
    
	@Column(name="cli_filaeliminada")
    private String registroEliminado;
	
	@Column(name="usa_id")
    private int usuarioId;
}
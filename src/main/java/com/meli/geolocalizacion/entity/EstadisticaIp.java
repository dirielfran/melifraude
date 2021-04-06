package com.meli.geolocalizacion.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * The Class EstadisticaIp.
 */
@Entity
@Table(name="estadisticasip")
public class EstadisticaIp implements Serializable{

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The nombre origen. */
	@NotEmpty
	private String nombreOrigen;
	
	/** The nombre destino. */
	@NotEmpty
	private String nombreDestino;
	
	/** The ip. */
	@Pattern(regexp = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$",
			message="La ip tiene un formato incorrecto.")
	@NotNull(message = "La ip no puede ser null.")
	private String ip;
	
	/** The distancia. */
	@NotNull(message = "La distancia no puede ser null.")
	private Double distancia;
	
	/** The create at. */
	@Temporal(TemporalType.DATE)
	private Date create_at;
	
	/** The fecha modificacion. */
	@Temporal(TemporalType.DATE)
	private Date fechaModificacion;

	/**
	 * Pre persist. Crea fecha antes de persistir el objeto
	 */
	@PrePersist
	public void prePersist() {
		create_at = new Date();
	}
	
	/**
	 * Pre update.  Modifica la fecha antes de realizar update
	 */
	@PreUpdate
	public void preUpdate() {
		create_at = new Date();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the nombre origen.
	 *
	 * @return the nombre origen
	 */
	public String getNombreOrigen() {
		return nombreOrigen;
	}

	/**
	 * Sets the nombre origen.
	 *
	 * @param nombreOrigen the new nombre origen
	 */
	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
	}

	/**
	 * Gets the nombre destino.
	 *
	 * @return the nombre destino
	 */
	public String getNombreDestino() {
		return nombreDestino;
	}

	/**
	 * Sets the nombre destino.
	 *
	 * @param nombreDestino the new nombre destino
	 */
	public void setNombreDestino(String nombreDestino) {
		this.nombreDestino = nombreDestino;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Gets the distancia.
	 *
	 * @return the distancia
	 */
	public Double getDistancia() {
		return distancia;
	}

	/**
	 * Sets the distancia.
	 *
	 * @param distancia the new distancia
	 */
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	/**
	 * Gets the creates the at.
	 *
	 * @return the creates the at
	 */
	public Date getCreate_at() {
		return create_at;
	}

	/**
	 * Sets the creates the at.
	 *
	 * @param create_at the new creates the at
	 */
	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	/**
	 * Gets the fecha modificacion.
	 *
	 * @return the fecha modificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * Sets the fecha modificacion.
	 *
	 * @param fechaModificacion the new fecha modificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6498835544884301881L;

}

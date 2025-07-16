package net.bounceme.chronos.chguadalquivir.adapter.out.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "embalse")
public class Embalse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3641781709734044777L;
	
	@Id
	@Column(name = "codigo")
	private String codigo;

	@Column(name = "embalse")
	private String nombreEmbalse;
	
	@Column(name = "capacidad")
	private Float capacidad;
	
	@Column(name = "men")
	private Float men;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cod_zona", referencedColumnName = "codigo")
	private Zona zona;
}

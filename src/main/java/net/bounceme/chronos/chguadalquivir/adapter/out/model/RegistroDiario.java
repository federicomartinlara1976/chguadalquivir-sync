package net.bounceme.chronos.chguadalquivir.adapter.out.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "registro_diario")
public class RegistroDiario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3641781709734044777L;
	
	@Id
	@Column(name = "id_registro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cod_embalse", referencedColumnName = "codigo")
	private Embalse embalse;
	
	@Column(name = "porcentaje")
	private Float porcentaje;
	
	@Column(name = "volumen")
	private Float volumen;
	
	@Column(name = "nivel")
	private Float nivel;
	
	@Column(name = "fecha")
	private Date fecha;
}

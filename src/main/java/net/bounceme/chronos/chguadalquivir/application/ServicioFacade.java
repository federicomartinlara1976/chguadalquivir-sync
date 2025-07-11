package net.bounceme.chronos.chguadalquivir.application;

import net.bounceme.chronos.dto.chguadalquivir.EmbalseDTO;
import net.bounceme.chronos.dto.chguadalquivir.PuntoControlDTO;
import net.bounceme.chronos.dto.chguadalquivir.RegistroDiarioDTO;
import net.bounceme.chronos.dto.chguadalquivir.ZonaDTO;

public interface ServicioFacade {

	/**
	 * @param registroDiarioDTO
	 */
	void write(RegistroDiarioDTO registroDiarioDTO);

	/**
	 * @param embalseDTO
	 */
	void write(EmbalseDTO embalseDTO);

	/**
	 * @param zonaDTO
	 */
	void write(ZonaDTO zonaDTO);
	
	/**
	 * @param PuntoControlDTO
	 */
	void write(PuntoControlDTO zonaDTO);

}
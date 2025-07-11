package net.bounceme.chronos.chguadalquivir.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.chguadalquivir.application.ServicioFacade;
import net.bounceme.chronos.chguadalquivir.port.out.EmbalsePort;
import net.bounceme.chronos.chguadalquivir.port.out.PuntoControlPort;
import net.bounceme.chronos.chguadalquivir.port.out.RegistroDiarioPort;
import net.bounceme.chronos.chguadalquivir.port.out.ZonaPort;
import net.bounceme.chronos.dto.chguadalquivir.EmbalseDTO;
import net.bounceme.chronos.dto.chguadalquivir.PuntoControlDTO;
import net.bounceme.chronos.dto.chguadalquivir.RegistroDiarioDTO;
import net.bounceme.chronos.dto.chguadalquivir.ZonaDTO;

/**
 * 
 */
@Service
@Slf4j
public class ServicioFacadeImpl implements ServicioFacade {

	@Autowired
	private RegistroDiarioPort registroDiarioPort;

	@Autowired
	private EmbalsePort embalsePort;

	@Autowired
	private ZonaPort zonaPort;
	
	@Autowired
	private PuntoControlPort puntoControlPort;

	
	/**
	 *
	 */
	@Override
	public void write(RegistroDiarioDTO registroDiarioDTO) {
		registroDiarioPort.write(registroDiarioDTO);
	}

	/**
	 *
	 */
	@Override
	public void write(EmbalseDTO embalseDTO) {
		embalsePort.getByCode(embalseDTO.getCodigo()).ifPresentOrElse((embalse) -> {
			log.info("Embalse ya existe: {}", embalse.toString());
		}, () -> {
			embalsePort.write(embalseDTO);
		});
	}

	/**
	 *
	 */
	@Override
	public void write(ZonaDTO zonaDTO) {
		zonaPort.getByCode(zonaDTO.getCodigo()).ifPresentOrElse((zona) -> {
			log.info("Zona ya existe: {}", zona.toString());
		}, () -> {
			zonaPort.write(zonaDTO);
		});
	}

	@Override
	public void write(PuntoControlDTO puntoControlDTO) {
		puntoControlPort.getByCode(puntoControlDTO.getId()).ifPresentOrElse((puntoControl) -> {
			log.info("Punto de control ya existe: {}", puntoControl.toString());
		}, () -> {
			puntoControlPort.write(puntoControlDTO);
		});
	}
}

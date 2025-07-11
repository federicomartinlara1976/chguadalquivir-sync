package net.bounceme.chronos.chguadalquivir.port.out;

import java.util.List;
import java.util.Optional;

import net.bounceme.chronos.dto.chguadalquivir.PuntoControlDTO;

/**
 * 
 */
public interface PuntoControlPort {
	
	/**
	 * @return
	 */
	List<PuntoControlDTO> listAll();

	/**
	 * @param code
	 * @return
	 */
	Optional<PuntoControlDTO> getByCode(String code);

	/**
	 * @param embalseDTO
	 * @return
	 */
	PuntoControlDTO write(PuntoControlDTO embalseDTO);
}

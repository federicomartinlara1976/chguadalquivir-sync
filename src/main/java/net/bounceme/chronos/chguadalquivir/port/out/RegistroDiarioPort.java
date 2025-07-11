package net.bounceme.chronos.chguadalquivir.port.out;

import java.util.List;
import java.util.Optional;

import net.bounceme.chronos.dto.chguadalquivir.RegistroDiarioDTO;

public interface RegistroDiarioPort {

	List<RegistroDiarioDTO> listAll();

	Optional<RegistroDiarioDTO> getByCode(Long code);

	RegistroDiarioDTO write(RegistroDiarioDTO registroDiarioDTO);

}
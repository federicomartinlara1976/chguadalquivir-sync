package net.bounceme.chronos.chguadalquivir.port.out;

import java.util.List;
import java.util.Optional;

import net.bounceme.chronos.dto.chguadalquivir.EmbalseDTO;

public interface EmbalsePort {

	List<EmbalseDTO> listAll();

	Optional<EmbalseDTO> getByCode(String code);

	EmbalseDTO write(EmbalseDTO embalseDTO);

}
package net.bounceme.chronos.chguadalquivir.port.out;

import java.util.List;
import java.util.Optional;

import net.bounceme.chronos.dto.chguadalquivir.ZonaDTO;

public interface ZonaPort {

	List<ZonaDTO> listAll();

	Optional<ZonaDTO> getByCode(String code);

	ZonaDTO write(ZonaDTO zonaJpaDTO);

}
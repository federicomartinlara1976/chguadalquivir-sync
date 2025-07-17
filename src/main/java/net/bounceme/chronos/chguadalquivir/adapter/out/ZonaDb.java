package net.bounceme.chronos.chguadalquivir.adapter.out;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.chguadalquivir.adapter.out.model.Zona;
import net.bounceme.chronos.chguadalquivir.adapter.out.repository.ZonaRepository;
import net.bounceme.chronos.chguadalquivir.port.out.ZonaPort;
import net.bounceme.chronos.dto.chguadalquivir.ZonaDTO;

/**
 * 
 */
@Service
@Slf4j
public class ZonaDb implements ZonaPort {

	private ZonaRepository repository;

	private ModelMapper modelMapper;

	public ZonaDb(ZonaRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ZonaDTO> listAll() {
		return repository.findAll().stream().map(zona -> modelMapper.map(zona, ZonaDTO.class)).toList();
	}

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<ZonaDTO> getByCode(String code) {
		return repository.findById(code).map(zona -> modelMapper.map(zona, ZonaDTO.class));
	}

	/**
	 *
	 */
	@Override
	@Transactional
	public ZonaDTO write(ZonaDTO zonaJpaDTO) {
		Zona zona = repository.save(modelMapper.map(zonaJpaDTO, Zona.class));
		log.info("Writed: {}", zona);
		return modelMapper.map(zona, ZonaDTO.class);
	}

}

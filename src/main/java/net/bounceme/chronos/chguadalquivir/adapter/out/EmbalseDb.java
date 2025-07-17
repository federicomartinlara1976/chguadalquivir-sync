package net.bounceme.chronos.chguadalquivir.adapter.out;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.chguadalquivir.adapter.out.model.Embalse;
import net.bounceme.chronos.chguadalquivir.adapter.out.repository.EmbalseRepository;
import net.bounceme.chronos.chguadalquivir.port.out.EmbalsePort;
import net.bounceme.chronos.dto.chguadalquivir.EmbalseDTO;

/**
 * 
 */
@Service
@Slf4j
public class EmbalseDb implements EmbalsePort {

	private EmbalseRepository repository;

	private ModelMapper modelMapper;

	public EmbalseDb(EmbalseRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<EmbalseDTO> listAll() {
		return repository.findAll().stream().map(embalse -> modelMapper.map(embalse, EmbalseDTO.class)).toList();
	}

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<EmbalseDTO> getByCode(String code) {
		return repository.findById(code).map(embalse -> modelMapper.map(embalse, EmbalseDTO.class));
	}

	/**
	 *
	 */
	@Override
	@Transactional
	public EmbalseDTO write(EmbalseDTO embalseDTO) {
		Embalse embalse = repository.save(modelMapper.map(embalseDTO, Embalse.class));
		log.info("Writed: {}", embalse.toString());
		return modelMapper.map(embalse, EmbalseDTO.class);
	}

}

package net.bounceme.chronos.chguadalquivir.adapter.out;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private EmbalseRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<EmbalseDTO> listAll() {
		List<Embalse> embalses = repository.findAll();
		return CollectionUtils.isNotEmpty(embalses) ? embalses.stream()
				.map(embalse -> modelMapper.map(embalse, EmbalseDTO.class)).collect(Collectors.toList())
				: Collections.emptyList();
	}

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<EmbalseDTO> getByCode(String code) {
		return Optional
				.ofNullable(repository.findById(code).map(embalse -> modelMapper.map(embalse, EmbalseDTO.class)).orElse(null));
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

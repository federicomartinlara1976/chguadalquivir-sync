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
import net.bounceme.chronos.chguadalquivir.adapter.out.model.RegistroDiario;
import net.bounceme.chronos.chguadalquivir.adapter.out.repository.RegistroDiarioRepository;
import net.bounceme.chronos.chguadalquivir.port.out.RegistroDiarioPort;
import net.bounceme.chronos.dto.chguadalquivir.RegistroDiarioDTO;

/**
 * 
 */
@Service
@Slf4j
public class RegistroDiarioDb implements RegistroDiarioPort {
	
	@Autowired
	private RegistroDiarioRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<RegistroDiarioDTO> listAll() {
		List<RegistroDiario> registros = repository.findAll();
		return CollectionUtils.isNotEmpty(registros) ? registros.stream()
				.map(registro -> modelMapper.map(registro, RegistroDiarioDTO.class)).collect(Collectors.toList())
				: Collections.emptyList();
	}

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<RegistroDiarioDTO> getByCode(Long code) {
		return Optional.ofNullable(repository.findById(code)
                .map(registroDiario -> modelMapper.map(registroDiario, RegistroDiarioDTO.class))
                .orElse(null));
	}

	/**
	 *
	 */
	@Override
	@Transactional
	public RegistroDiarioDTO write(RegistroDiarioDTO registroDiarioDTO) {
		RegistroDiario registroDiario = repository.save(modelMapper.map(registroDiarioDTO, RegistroDiario.class));
		
		log.info("Writed: {}", registroDiario.toString());
		return modelMapper.map(registroDiario, RegistroDiarioDTO.class);
	}
}

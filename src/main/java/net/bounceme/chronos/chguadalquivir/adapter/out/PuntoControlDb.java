package net.bounceme.chronos.chguadalquivir.adapter.out;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.chguadalquivir.adapter.out.model.PuntoControl;
import net.bounceme.chronos.chguadalquivir.adapter.out.model.Zona;
import net.bounceme.chronos.chguadalquivir.adapter.out.repository.PuntoControlRepository;
import net.bounceme.chronos.chguadalquivir.adapter.out.repository.ZonaRepository;
import net.bounceme.chronos.chguadalquivir.port.out.PuntoControlPort;
import net.bounceme.chronos.dto.chguadalquivir.PuntoControlDTO;

/**
 * 
 */
@Service
@Slf4j
public class PuntoControlDb implements PuntoControlPort {

	@Autowired
	private PuntoControlRepository repository;
	
	@Autowired
	private ZonaRepository zonaRepository;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<PuntoControlDTO> listAll() {
		List<PuntoControl> puntos = repository.findAll();
		return CollectionUtils.isNotEmpty(puntos) ? puntos.stream()
				.map(punto -> modelMapper.map(punto, PuntoControlDTO.class)).collect(Collectors.toList())
				: Collections.emptyList();
	}

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<PuntoControlDTO> getByCode(String code) {
		return Optional
				.ofNullable(repository.findById(code).map(puntoControl -> modelMapper.map(puntoControl, PuntoControlDTO.class)).orElse(null));
	}

	/**
	 *
	 */
	@Override
	@Transactional
	public PuntoControlDTO write(PuntoControlDTO puntoControlDTO) {
		PuntoControl punto = modelMapper.map(puntoControlDTO, PuntoControl.class);
		
		if (StringUtils.isNotBlank(puntoControlDTO.getZona())) {
			Optional<Zona> oZona = zonaRepository.findById(puntoControlDTO.getZona());
			if (oZona.isPresent()) {
				punto.setZona(oZona.get());
			}
		}
		
		punto = repository.save(modelMapper.map(puntoControlDTO, PuntoControl.class));

		log.info("Writed: {}", punto.toString());
		return modelMapper.map(punto, PuntoControlDTO.class);
	}

}

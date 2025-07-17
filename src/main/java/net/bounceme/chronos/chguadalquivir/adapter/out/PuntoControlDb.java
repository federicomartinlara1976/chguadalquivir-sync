package net.bounceme.chronos.chguadalquivir.adapter.out;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.chguadalquivir.adapter.out.model.PuntoControl;
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

	private PuntoControlRepository repository;

	private ZonaRepository zonaRepository;

	private ModelMapper modelMapper;

	public PuntoControlDb(PuntoControlRepository repository, ZonaRepository zonaRepository, ModelMapper modelMapper) {
		this.repository = repository;
		this.zonaRepository = zonaRepository;
		this.modelMapper = modelMapper;
	}

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public List<PuntoControlDTO> listAll() {
		return repository.findAll().stream().map(punto -> modelMapper.map(punto, PuntoControlDTO.class)).toList();
	}

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<PuntoControlDTO> getByCode(String code) {
		return repository.findById(code).map(puntoControl -> modelMapper.map(puntoControl, PuntoControlDTO.class));
	}

	/**
	 *
	 */
	@Override
	@Transactional
	public PuntoControlDTO write(PuntoControlDTO puntoControlDTO) {
		PuntoControl punto = modelMapper.map(puntoControlDTO, PuntoControl.class);

		Optional.ofNullable(puntoControlDTO.getZona())
			.filter(StringUtils::isNotBlank)
			.flatMap(zonaRepository::findById)
			.ifPresent(punto::setZona);

		punto = repository.save(punto);
		log.info("Writed: {}", punto.toString());
		return modelMapper.map(punto, PuntoControlDTO.class);
	}

}

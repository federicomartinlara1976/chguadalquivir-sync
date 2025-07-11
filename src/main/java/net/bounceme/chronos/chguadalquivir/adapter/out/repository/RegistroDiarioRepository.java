package net.bounceme.chronos.chguadalquivir.adapter.out.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.bounceme.chronos.chguadalquivir.adapter.out.model.Embalse;
import net.bounceme.chronos.chguadalquivir.adapter.out.model.RegistroDiario;

@Repository
public interface RegistroDiarioRepository extends JpaRepository<RegistroDiario, Long> {
	
	List<RegistroDiario> findByEmbalseAndFecha(Embalse embalse, Date fecha);
}

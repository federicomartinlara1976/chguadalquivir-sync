package net.bounceme.chronos.chguadalquivir.adapter.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.bounceme.chronos.chguadalquivir.adapter.out.model.PuntoControl;

@Repository
public interface PuntoControlRepository extends JpaRepository<PuntoControl, String> {
}

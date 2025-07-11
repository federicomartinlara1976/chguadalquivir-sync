package net.bounceme.chronos.chguadalquivir.adapter.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.bounceme.chronos.chguadalquivir.adapter.out.model.Zona;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, String> {

}

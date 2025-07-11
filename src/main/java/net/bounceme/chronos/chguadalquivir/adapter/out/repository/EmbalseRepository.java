package net.bounceme.chronos.chguadalquivir.adapter.out.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.bounceme.chronos.chguadalquivir.adapter.out.model.Embalse;

@Repository
public interface EmbalseRepository extends JpaRepository<Embalse, String> {

	Optional<Embalse> findByCodigo(String codigo);

}

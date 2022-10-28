package it.unipv.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.repository.PageableRepository;
import it.unipv.domain.Param;



@Repository
public interface ParamRepository extends JpaRepository<Param, Integer>, PageableRepository<Param, Integer> {
}

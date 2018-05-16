package com.ml.xmen.xmenml.repository;

import com.ml.xmen.xmenml.entity.RegistroADN;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroADNRepository extends CrudRepository<RegistroADN, Long> {


    @Query("select count(1) from RegistroADN r where r.esMutante = true")
    Long cantidadDeMutantes();

    @Query("select count(1) from RegistroADN r where r.esMutante <> true")
    Long cantidadDeHumanosNoMutantes();
}

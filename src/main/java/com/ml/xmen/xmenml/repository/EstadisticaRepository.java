package com.ml.xmen.xmenml.repository;

import com.ml.xmen.xmenml.entity.Estadistica;
import com.ml.xmen.xmenml.entity.TipoEstadistica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadisticaRepository extends CrudRepository<Estadistica, Long> {

    Estadistica findByTipoEstadistica(TipoEstadistica tipoEstadistica);
}

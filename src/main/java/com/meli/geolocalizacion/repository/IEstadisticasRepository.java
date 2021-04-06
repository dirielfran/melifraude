package com.meli.geolocalizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meli.geolocalizacion.entity.EstadisticaIp;


/**
 * The Interface IEstadisticasRepository.
 */
@Repository
public interface IEstadisticasRepository extends JpaRepository<EstadisticaIp, Long> {
	
	/**
	 * Countestadisticas.
	 *
	 * @return the list con las estadisticas de distancia corresponndientes a las 
	 * ip de consulta
	 */
	@Query("Select avg(e.distancia), min(e.distancia), max(e.distancia) from EstadisticaIp e ")
	List<Object[]> countestadisticas();
}

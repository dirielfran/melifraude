package com.meli.geolocalizacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.geolocalizacion.DTO.Estadistica;
import com.meli.geolocalizacion.entity.EstadisticaIp;
import com.meli.geolocalizacion.interfaces.IEstadisticasServices;
import com.meli.geolocalizacion.repository.IEstadisticasRepository;


/**
 * The Class EstadisticasServicesImpl.
 */
@Service
public class EstadisticasServicesImpl implements IEstadisticasServices{

	/** The estadisticas repo. Se realiza ID del repositorio */
	@Autowired
	private IEstadisticasRepository estadisticasRepo;
	
	/**
	 * Gets the paises.
	 *
	 * @return the paises
	 */
	@Override
	public List<EstadisticaIp> getPaises() {
		return estadisticasRepo.findAll();
	}

	
	/**
	 * Save.
	 *
	 * @param pais the pais
	 * @return the estadistica ip
	 */
	@Override
	public EstadisticaIp save(EstadisticaIp pais) {
		return estadisticasRepo.save(pais);
	}

	/**
	 * Gets the pais id.
	 *
	 * @param id the id
	 * @return the pais id
	 */
	@Override
	public EstadisticaIp getPaisId(Long id) {
		return estadisticasRepo.findById(id).orElse(null);
	}

	/**
	 * Delete pais.
	 *
	 * @param id the id
	 */
	@Override
	public void deletePais(Long id) {
		estadisticasRepo.deleteById(id);
	};
	
	/**
	 * Save ips. Metodo que realiza la persistencia de los datos de distancia utilizados para
	 * las estadisticas
	 *
	 * @param ip the ip
	 * @param distancia the distancia
	 * @param nombreOrigen the nombre origen
	 * @param nombreDestino the nombre destino
	 */
	@Override
	public void saveIps(String ip, Double distancia, String nombreOrigen, String nombreDestino) {
		EstadisticaIp estadistica = new EstadisticaIp();
		estadistica.setIp(ip);
		estadistica.setDistancia(distancia);
		estadistica.setNombreOrigen(nombreOrigen);
		estadistica.setNombreDestino(nombreDestino);
		save(estadistica);		
	}


	/**
	 * Gets the estadisticas. Metodo que retorna las estadisticas correspondientes a 
	 * las ip de consulta
	 *
	 * @return the estadisticas
	 */
	@Override
	public Estadistica getEstadisticas() {
		Estadistica est = new Estadistica();
		List<Object[]> estadisticas= estadisticasRepo.countestadisticas();
		System.out.println(estadisticas);
		for (Object[] object : estadisticas) {
			est.setDistPromedio((Double) object[0]);
			est.setDistMinima((Double) object[1]);
			est.setDistMaxima((Double) object[2]);
		}			
		return est;
	}

}

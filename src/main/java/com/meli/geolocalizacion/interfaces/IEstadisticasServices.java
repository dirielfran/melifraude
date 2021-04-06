package com.meli.geolocalizacion.interfaces;

import java.util.List;

import com.meli.geolocalizacion.DTO.Estadistica;
import com.meli.geolocalizacion.entity.EstadisticaIp;


public interface IEstadisticasServices {
	
	public List<EstadisticaIp> getPaises();
	public EstadisticaIp save(EstadisticaIp pais);
	public EstadisticaIp getPaisId(Long id);
	public void deletePais(Long id);	
	public void saveIps(String ip, Double distancia, String nombreOrigen, String nombreDestino);
	public Estadistica getEstadisticas();

}

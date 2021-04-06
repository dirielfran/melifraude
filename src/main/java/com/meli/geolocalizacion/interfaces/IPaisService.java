package com.meli.geolocalizacion.interfaces;

import java.util.List;

import com.meli.geolocalizacion.DTO.InformacionPais;

public interface IPaisService {
	public String getCodPais( String ip );
	public InformacionPais getPaisInfo( String cod );
	public Double getCotizacionDolar(String codeBase, String codeCotizacion);
	public Double getDistancia(InformacionPais paisA, InformacionPais paisB);
	public List<String> getHoras(InformacionPais pais);
}

package com.meli.geolocalizacion.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.meli.geolocalizacion.DTO.InformacionPais;
import com.meli.geolocalizacion.exceptions.DolarException;
import com.meli.geolocalizacion.exceptions.IpException;
import com.meli.geolocalizacion.interfaces.IPaisService;
import com.meli.geolocalizacion.utils.Utils;



/**
 * The Class PaisServiceImpl.
 */
@Service
public class PaisServiceImpl implements IPaisService {
	
	/** Valores obtenidos de archivo de propiedades para  las a pi a consultar. */
	/** The ruta ip. */
	@Value("${ruta.ip}")
    private String rutaIp;
	
	/** The ruta pais. */
	@Value("${ruta.pais}")
    private String rutaPais;
	
	/** The ruta cotizacion. */
	@Value("${ruta.cotizacion}")
    private String rutaCotizacion;
	
	/** The access key. clave de acceso para api de cotizacion de dolar */
	@Value("${api.access_key}")
    private String accessKey;

	/**
	 * Gets the cod pais. Metodo que consulta api y retorna codigo pais segun ip de consulta
	 *
	 * @param ip the ip. Ip de consulta
	 * @return the cod pais. Codigo de pais obtenido
	 * @throws IpException the ip exception
	 */
	@Override
	public String getCodPais(String ip) throws IpException{
		RestTemplate restTemplate = new RestTemplate();
		String  call= restTemplate.getForObject(rutaIp.concat(ip),String.class);
		JsonObject gsonObj = JsonParser.parseString(call).getAsJsonObject();
		String codigoPais = gsonObj.get("countryCode").getAsString();
		if(codigoPais.equals("")) throw new IpException("Ip no tiene codigo pais disponible.");
		return codigoPais;
	}

	/**
	 * Gets the pais info. Metodo que consulta api y retorna informacion del pais
	 * segun codigo de consulta
	 *
	 * @param cod the cod. Codig de consulta
	 * @return the pais info. Inpormacion del Pais de consulta
	 */
	@Override
	public InformacionPais getPaisInfo(String cod) {
		RestTemplate restTemplate = new RestTemplate();
		String  call= restTemplate.getForObject(rutaPais.concat(cod),String.class);
		Gson gson = new Gson();
		InformacionPais pais = gson.fromJson(call, InformacionPais.class);
		return pais;
	}

	/**
	 * Gets the cotizacion dolar. Metodo que consulta api y retorna cotizacion en dolares 
	 *
	 * @param codeBase the code base. codigo moneda de pais consulta
	 * @param codeCotizacion the code cotizacion. Codigo de moneda a cotizar
	 * @return the cotizacion dolar
	 * @throws DolarException the dolar exception
	 */
	@Override
	public Double getCotizacionDolar(String codeBase, String codeCotizacion) throws DolarException{
		RestTemplate restTemplate = new RestTemplate();
		String access_key = "access_key=".concat(accessKey);
		String base = "&base=".concat(codeBase);
		String codeDolar = "&symbols=".concat(codeCotizacion);
		Double cotizacion = 0D;
		String url = rutaCotizacion.concat(access_key).concat(base).concat(codeDolar);
		String call= restTemplate.getForObject(url,String.class);
		JsonObject gsonObj = JsonParser.parseString(call).getAsJsonObject();
		boolean gsonCotiSucess = gsonObj.get("success").getAsBoolean();
		if (gsonCotiSucess) {
			JsonObject gsonCoti = gsonObj.get("rates").getAsJsonObject();
			 cotizacion = gsonCoti.get(codeCotizacion).getAsDouble();
		}else {
			JsonObject gsonCoti = gsonObj.get("error").getAsJsonObject();
			int codeError = gsonCoti.get("code").getAsInt();
			String mensajeError = gsonCoti.get("type").getAsString();
			throw new DolarException(codeError, mensajeError );
		}		
		return cotizacion; 
	}

	/**
	 * Gets the distancia.  Metodo de calculo de distancia entre paises por 
	 * coordendas de longitud y latitud
	 *
	 * @param paisA the pais A.
	 * @param paisB the pais B
	 * @return the distancia
	 */
	@Override
	public Double getDistancia(InformacionPais paisA, InformacionPais paisB) {
		Double distancia = Utils.distanciaCoord(paisA.getLatlng().get(0), paisA.getLatlng().get(1),
							paisB.getLatlng().get(0), paisB.getLatlng().get(1));
		return distancia;
	}	
	
	/**
	 * Gets the horas. Metodo que obtiene la hora segun zona horaria de cada pais
	 *
	 * @param pais the pais
	 * @return the horas
	 */
	@Override
	public List<String> getHoras(InformacionPais pais){
		List<String> lista = new ArrayList<>();
		for (String timezone : pais.getTimezones()) {
			lista.add(Utils.getHora(timezone));
		}
		return lista;
	}
	
}

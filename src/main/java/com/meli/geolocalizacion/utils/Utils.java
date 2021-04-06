package com.meli.geolocalizacion.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.meli.geolocalizacion.exceptions.IpException;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
public class Utils {
	
	/**
	 * Validate. Metodo que valida que la ip tenga formato correcto
	 *
	 * @param ip the ip
	 * @return the string
	 * @throws IpException the ip exception
	 */
	public static String validate(final String ip) throws IpException{
	    String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
	   if (ip.matches(PATTERN)) {
		   return ip;
	   }else {
		   throw new IpException("Ip no tiene el formato correcto");
	   }	    
	}
	
	/**
	 * Distancia coord. Metodo que realiza el calculo de la distancia entre dos paises segun sus coordenadas
	 *
	 * @param lat1 the lat 1
	 * @param lng1 the lng 1
	 * @param lat2 the lat 2
	 * @param lng2 the lng 2
	 * @return the double
	 */
	public static double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {   
        double radioTierra = 6371;  
        double dLat = Math.toRadians(lat2 - lat1);  
        double dLng = Math.toRadians(lng2 - lng1);  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
        return distancia;  
    }  
	
	/**
	 * Gets the hora. Metodo que obtiene la hoa formatedad segun zona horaria de cada pais
	 *
	 * @param Zona horaria.
	 * @return the hora
	 */
	public static String getHora(String timeZone) {
		ZoneId zone = ZoneId.of(timeZone);
		ZonedDateTime date = ZonedDateTime.now(zone);
		return date.format(DateTimeFormatter.ofPattern("hh:mm:ss ")).concat(timeZone);
	}
		

}

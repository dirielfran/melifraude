package com.meli.geolocalizacion.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.geolocalizacion.interfaces.IPaisService;

@RestController
@RequestMapping("/api")
public class IpsController {
	
	@Autowired
	private IPaisService paisService; 
	
//	@GetMapping("/geo/{ip}") 
//	public ResponseEntity<?> pruebaGeo(@PathVariable String ip ){
//		Map<String, Object> response = new HashMap<>();
//		if(Utils.validate(ip)) {
//			String cod = paisService.getCodPais(Utils.validate(ip));
//			InformacionPais paisInfoOrigen = paisService.getPaisInfo("ar");
//			InformacionPais paisInfo = paisService.getPaisInfo(cod);
//			String codigo = paisInfo.getCurrencies().get(0).get("code");
//			System.out.println(paisService.getCotizacionDolar(codigo , "USD"));
//			System.out.println(paisInfo.getLanguages().get(0));
//			System.out.println(paisService.getDistancia(paisInfoOrigen, paisInfo));
//					
//			ZoneId zone = ZoneId.of(paisInfo.getTimezones().get(0));
//			ZonedDateTime date = ZonedDateTime.now(zone);
//			System.out.println(date.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
//		    return new ResponseEntity<InformacionPais>(paisInfo, HttpStatus.OK);
//		}
//		response.put("mensaje", "La ip no es valida");
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
}

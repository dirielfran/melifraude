package com.meli.geolocalizacion.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.meli.geolocalizacion.DTO.InformacionPais;
import com.meli.geolocalizacion.exceptions.DolarException;
import com.meli.geolocalizacion.exceptions.IpException;
import com.meli.geolocalizacion.interfaces.IEstadisticasServices;
import com.meli.geolocalizacion.interfaces.IPaisService;
import com.meli.geolocalizacion.utils.Utils;


/**
 * The Class IpFormController.
 */
@Controller
public class IpFormController {
	
	  /** The pais service. */
  	@Autowired
	  private IPaisService paisService;
	  
	  /** The estadisticas service. */
  	@Autowired
	  private IEstadisticasServices estadisticasService;
	
	  /**
  	 * formulario. Metodo que renderiza el formulario de ip 
  	 *
  	 * @return the string
  	 */
  	@RequestMapping("/formIp")
	  public String formulario() {
	    return "formIp";
	  }
	  
	  /**
  	 * geolocalizacion. Metodo  que retorna por medio de objeto Model la informacion
  	 * de los paises de consulta de la i correspondiente
  	 *
  	 * @param ip. Ip de consulta
  	 * @param modelo.  Objeto de tipo model para el retorno de la informacion.
  	 * @return the string
  	 */
  	@GetMapping("/vadaIp")
	  public String geolocalizacion(@RequestParam String ip, Model modelo ){
			try {
				String ipformat = Utils.validate(ip);
				String cod = paisService.getCodPais(ipformat);
				InformacionPais paisInfoOrigen = paisService.getPaisInfo("ar");
				InformacionPais paisInfo = paisService.getPaisInfo(cod);
				String codigo = paisInfo.getCurrencies().get(0).get("code");
				Double distancia = paisService.getDistancia(paisInfoOrigen, paisInfo);
				estadisticasService.saveIps(ipformat, distancia, paisInfoOrigen.getName(), paisInfo.getName());
				modelo.addAttribute("horas", paisService.getHoras(paisInfo));
				modelo.addAttribute("pais",paisInfo);
				modelo.addAttribute("distancia", distancia);
				modelo.addAttribute("cotizacion", paisService.getCotizacionDolar(codigo , "USD"));					
			}catch(IpException ipe) {
				modelo.addAttribute("mensaje", ipe.getMessage());
			}catch (DolarException de) {
				modelo.addAttribute("cotizacionmsg", de.getMessage());
			} catch (Exception e) {
				e.getMessage();
			}			
			return "formIp";
		}
}


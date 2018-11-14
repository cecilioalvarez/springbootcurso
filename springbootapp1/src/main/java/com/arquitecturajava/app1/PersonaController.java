package com.arquitecturajava.app1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	PersonasServices servicio;
	
	@RequestMapping("/listado")
	public String mostrarTodas (Model modelo) {
		
		modelo.addAttribute("listaPersonas",servicio.buscarTodos());
		
		return "personas/listado";
		
		
	}
}

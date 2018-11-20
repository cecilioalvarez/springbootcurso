package com.arquitecturajava.app1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("/formulario")
	public String mostrarFormulario () {
		
	
		
		return "personas/formulario.html";
		
		
	}
	
	@RequestMapping("/insertar")
	public String insertar(Persona persona,Model modelo) {
		
		servicio.insertar(persona);
		modelo.addAttribute("listaPersonas",servicio.buscarTodos());
		
		return "personas/listado";
		
		
		
	}
	
	
	@RequestMapping("/borrar")
	public String borrar(@RequestParam("nombre") String nombre,Model modelo) {
		Persona persona= new Persona(nombre);
		
		servicio.borrar(persona);
		modelo.addAttribute("listaPersonas",servicio.buscarTodos());
		
		return "personas/listado";
		
		
		
	}
}

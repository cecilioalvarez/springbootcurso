package com.arquitecturajava.app1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("webapi")
public class PersonaRESTController {

	
	@Autowired
	private PersonasServices personasService;
	
	@GetMapping("/personas")
	public Iterable<Persona> buscarTodos() {
		
		System.out.println("hola");
		return personasService.buscarTodos();
	}
	
	@GetMapping("/personas/{nombre}")
	public Persona buscarUno(@PathVariable String nombre) {
			
		Optional<Persona> unaPersona= personasService.buscarUno(nombre); 
		
		
		return unaPersona.get();
	}
	
	

	@DeleteMapping("/personas/{nombre}")
	public void borrar(@PathVariable String nombre) {
	
		Optional<Persona> oPersona=personasService.buscarUno(nombre);
		if (oPersona.isPresent()) {
			
			personasService.borrar(oPersona.get()); 
		}

	}
	
	@PostMapping(path="/personas", consumes="application/json")
	public void  insertar(@RequestBody Persona persona) {
		
		
		personasService.insertar(persona);
	}
	
	@PutMapping(path="/personas/{nombre}", consumes="application/json")
	public void  salvar(@RequestBody Persona persona,@PathVariable String nombre ) {
		
		Optional<Persona> oPersona=personasService.buscarUno(nombre);
		Persona unaPersona= oPersona.get();
		
		unaPersona.setApellidos(persona.getApellidos());
		unaPersona.setEdad(persona.getEdad());
		personasService.salvar(persona);
		
	}
	
}

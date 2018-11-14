package com.arquitecturajava.app1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonasServices {
	
	
	@Autowired
	PersonaRepository repositorio;
	
	public List<Persona> buscarTodos() {
		
		return repositorio.buscarTodos();
	}
	

}

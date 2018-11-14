package com.arquitecturajava.app1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PersonasServices {
	
	
	static List<Persona> lista= new ArrayList<Persona>();
	
	static {
		
		Persona p1= new Persona("pepe","perez",20);
		Persona p2= new Persona("ana","sanchez",30);
		lista.add(p1);
		lista.add(p2);
		
		
	}
	
	public List<Persona> buscarTodos() {
		
		return lista;
	}
	

}

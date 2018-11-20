package com.arquitecturajava.app1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepository {

	@Autowired
	private JdbcTemplate plantilla;
	
	
	public List<Persona> buscarTodos() {
		
		
		return plantilla.query("select * from Personas", new PersonaMapper());
	}
	
	
	public void  insertar(Persona persona) {
		
		
		 plantilla.update("insert into Personas values (?,?,?)",persona.getNombre(),persona.getApellidos(),persona.getEdad());
	}
	
	public void borrar(Persona persona) {
		
		plantilla.update("delete from Personas where nombre=?",persona.getNombre());
	}
	
}

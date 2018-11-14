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
	
	
}

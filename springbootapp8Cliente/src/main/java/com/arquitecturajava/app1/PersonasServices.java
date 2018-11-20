package com.arquitecturajava.app1;

import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonasServices {

	RestTemplate plantillaREST;

	public PersonasServices() {
		super();
		this.plantillaREST = new RestTemplate();
	}

	public Iterable<Persona> buscarTodos() {

		ResponseEntity<List<Persona>> respuesta = plantillaREST.exchange("http://localhost:8080/webapi/personas",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Persona>>() {
				});
		List<Persona> personas = respuesta.getBody();
		return personas;
	}

	public void insertar(Persona persona) {

		HttpEntity<Persona> request = new HttpEntity<>(persona);
		plantillaREST.postForObject("http://localhost:8080/webapi/personas", request, Persona.class);
	}

	public void borrar(Persona persona) {

		
		plantillaREST.delete("http://localhost:8080/webapi/personas/"+persona.getNombre());
	}

	public Optional<Persona> buscarUno(String nombre) {

		Persona persona = plantillaREST.getForObject("http://localhost:8080/webapi/personas/" + nombre, Persona.class);
		return Optional.of(persona);
	}

	public void salvar(Persona persona) {

	
		HttpEntity<Persona> peticionPersona = new HttpEntity<>(persona);
		plantillaREST.exchange("http://localhost:8080/webapi/personas/"+persona.getNombre(), HttpMethod.PUT, peticionPersona, Void.class);
		
		
	}
}

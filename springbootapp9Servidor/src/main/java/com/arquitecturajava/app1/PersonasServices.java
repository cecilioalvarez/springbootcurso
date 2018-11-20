package com.arquitecturajava.app1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonasServices {

	@Autowired
	PersonaRepository repositorio;

	public Iterable<Persona> buscarTodos() {

		return repositorio.findAll();
	}

	public void insertar(Persona persona) {

		repositorio.save(persona);
	}

	public void borrar(Persona persona) {

		repositorio.delete(persona);
	}

	public Optional<Persona> buscarUno(String nombre) {

		return repositorio.findById(nombre);
	}

	public void salvar(Persona persona) {

		repositorio.save(persona);
	}

}

package com.arquitecturajava.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
	
		
	AnnotationConfigApplicationContext contexto= new AnnotationConfigApplicationContext();
	contexto.register(SpringConfigurador.class);
	contexto.refresh();
	
	Servicio miservicio= contexto.getBean(Servicio.class);
	System.out.println(miservicio.mensaje());
	miservicio.mensaje();
	contexto.close();

	}

}

package com.arquitecturajava.app1;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PersonaMapper implements RowMapper<Persona> {

	@Override
	public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new Persona(rs.getString("nombre"),rs.getString("apellidos"),rs.getInt("edad"));
	}

}

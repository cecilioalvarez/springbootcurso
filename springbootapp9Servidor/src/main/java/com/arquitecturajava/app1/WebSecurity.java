package com.arquitecturajava.app1;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // desactivamos la cache
    	
        http.headers().cacheControl();
        //eliminamos csrf
        http.csrf().disable() // disable csrf for our requests.
            .authorizeRequests()
            //permitimos la url por defecto
            .antMatchers("/").permitAll()
            .antMatchers(HttpMethod.POST, "/webapi/login").permitAll()
            //protegemos el resto
            .anyRequest().authenticated()
            .and()
             //We filter the api/login requests
            .addFilterBefore(new FiltroLogin("/webapi/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            // And filter other requests to check the presence of JWT in header
            .addFilterBefore(new FiltroJWTAutenticacion(), UsernamePasswordAuthenticationFilter.class);
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       //creamos un autenticantion manager en memoria
    	 PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	   
        auth.inMemoryAuthentication()
            .withUser("cecilio")
            .password(encoder.encode("miclave"))
            .roles("ADMINISTRADOR");
    }

}

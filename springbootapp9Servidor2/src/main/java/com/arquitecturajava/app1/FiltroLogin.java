package com.arquitecturajava.app1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FiltroLogin extends AbstractAuthenticationProcessingFilter{

    private TokenService tokenService;

    public FiltroLogin(String url, AuthenticationManager authenticationManager)
    {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
        tokenService = new TokenService();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException, ServletException {
        
    	 System.out.println("pasa por aqui2");
    	if (CorsUtils.isPreFlightRequest(httpServletRequest)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            System.out.println("pasa por aqui");
            
            
            return null;
        }

    
    	
    	
    	
    	
    	
    	
    	// comprueba que el usuario es valido y existe
    	Usuario usuario = new ObjectMapper().readValue(httpServletRequest.getInputStream(),Usuario.class);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usuario.getNombre(), usuario.getPassword());
       // System.out.println(usuario.getNombre());
        //System.out.println(usuario.getPassword());
        //System.out.println("hola");
       //System.out.println(getAuthenticationManager());
       //System.out.println(getAuthenticationManager().authenticate(token));
       
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws IOException, ServletException{
    	
    	
    	System.out.println("ha ido bien");
        String nombre = authentication.getName();
        tokenService.creaToken(response,nombre);
    }
}


package com.arquitecturajava.app1;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenService {

	
	 private long expiracion = 3600; // 1hora
     private String claveEncriptar = "superClave";
     private String prefijoToken = "Bearer";
     private String cabeceraHttp = "Authorization";
     
     
     public void creaToken(HttpServletResponse response, String usuario) {
         // We generate a token now.
         String JWT = Jwts.builder()
             .setSubject(usuario)
             .setExpiration(new Date(System.currentTimeMillis() + expiracion))
             .signWith(SignatureAlgorithm.HS512, claveEncriptar)
             .compact();
         response.addHeader(cabeceraHttp, prefijoToken + " " + JWT);
     }
     public Authentication leeToken(HttpServletRequest request) {
         String token = request.getHeader(cabeceraHttp);
         if (token != null) {
             // parse the token.
             String usuario = Jwts.parser()
                 .setSigningKey(claveEncriptar)
                 .parseClaimsJws(token)
                 .getBody()
                 .getSubject();
             if (usuario != null) // we managed to retrieve a user
             {
                 return new UsuarioAutenticado(usuario);
             }
         }
         return null;
     }
	
}

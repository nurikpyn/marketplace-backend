package br.com.vfs.marketplacebackend.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider { //TODO para obter os values deve ser um componente ou servico

    @Value("${marketplace.app.jwtSecret}")
    private String jwtSecret;

    @Value("${marketplace.app.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {
        //UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                //.setSubject((userPrincipal.getUsername()))
                .setSubject("teste") //TODO ver esse Userr principal
                .setIssuedAt(new Date()) //TODO trocar para biblioteca mais nova
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        //Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        //return ...;
        return true; //TODO validar isso depois
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}

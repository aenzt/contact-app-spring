package com.aenzt.restfulapi.utils;

import com.aenzt.restfulapi.entity.Extractable;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtils {

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public static final JwtUtils INSTANCE = new JwtUtils();

    private JwtUtils(){}

    public String generateJwtToken(Extractable extractable){
        return Jwts.builder()
                .signWith(key)
                .claim("id", extractable.getId())
                .claim("nama", extractable.getNama())
                .compact();
    }

    public Key getKey() {
        return key;
    }
}

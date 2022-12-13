package com.sphy.hotelmanagementapplication.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/***
 * created by gp
 */
@Service
public class JwtUtil {

    @Value("${secret_key}")
    private String SECRET_KEY;

    /***
     * extract username from a jwt token
     * @param token jwt token
     * @return username
     */
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /***
     * extract expiration date from a jwt token
     * @param token jwt token
     * @return the expiration date
     */
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /***
     * extracts all claims from a jwt token
     * @param token jwt token
     * @return all claims
     */
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /***
     * checks if thw jwt token is expired
     * @param token jwt token
     */
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    /***
     * creates a token
     * @param userDetails the details of the user
     * @return a jwt token
     */
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject){
        return  Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    /***
     * checks if a token is valid
     * @param token jwt token
     * @param userDetails the details of the user
     */
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

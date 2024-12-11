package com.example.Task.Management.System.Security.Filters;

import com.example.Task.Management.System.Security.UserDetails.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.Date;

import static io.jsonwebtoken.Jwts.builder;

@Component
@Slf4j
public class JwtUtils {

    private final Key jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Value("${reports.app.jwtExpirationMs}")
    private long lifetime;

    @Value("${reports.app.jwtCookieName}")
    private String jwtCookie;


    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt((new Date()))
                .setExpiration(new Date((new Date()).getTime() + lifetime))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            log.info("VALID TOKEN");
            return true;
        } catch (Exception ignored) {
            log.error("INVALID TOKEN");
            return false;
        }
    }


    public String getNameFromJwt(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
    
}

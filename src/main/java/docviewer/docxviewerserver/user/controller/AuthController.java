package docviewer.docxviewerserver.user.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import docviewer.docxviewerserver.user.security.AppDaoAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Date;

@RestController
public class AuthController {

    @Autowired
    private AppDaoAuthenticationProvider provider;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestHeader("Authorization") String basicHeader){
        String header=basicHeader.replace("Basic ","");
        String rawHeaderValue = new String(Base64.getDecoder().decode(header));
        String[] credentials = rawHeaderValue.split(":");
        String username = credentials[0];
        String password = credentials[1];

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = provider.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = JWT.create().withIssuer("FolderDocs").withClaim("username", username).withExpiresAt(new Date(Long.MAX_VALUE))
                .sign(Algorithm.HMAC256("DefinietlyNotSecured"));
        return ResponseEntity.ok(jwtToken);
    }
}

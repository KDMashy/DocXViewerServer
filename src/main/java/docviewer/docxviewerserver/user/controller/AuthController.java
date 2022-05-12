package docviewer.docxviewerserver.user.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import docviewer.docxviewerserver.user.entity.UserDto;
import docviewer.docxviewerserver.user.entity.UserEntity;
import docviewer.docxviewerserver.user.repository.RoleRepository;
import docviewer.docxviewerserver.user.repository.UserRepository;
import docviewer.docxviewerserver.user.security.AppDaoAuthenticationProvider;
import docviewer.docxviewerserver.user.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

@RestController
public class AuthController {

    @Autowired
    private AppDaoAuthenticationProvider provider;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(
            /*@RequestHeader("Authorization") String basicHeader*/
    @RequestBody UserDto user){
        //String header=basicHeader.replace("Basic ","");
        //String rawHeaderValue = new String(Base64.getDecoder().decode(header));
        //String[] credentials = rawHeaderValue.split(":");
        //String username = credentials[0];
        //String password = credentials[1];

        String username = user.getUsername();
        String password = user.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = provider.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = JWT.create().withIssuer("FolderDocs").withClaim("username", username).withExpiresAt(new Date(Long.MAX_VALUE))
                .sign(Algorithm.HMAC256("DefinietlyNotSecured"));
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserEntity> save(@RequestBody UserEntity data) {
        data.setAuthorities(new ArrayList<>());
        data.getAuthorities().add(roleRepository.getByAuthorityName("ROLE_USER"));
        data.setPassword(new BCryptPasswordEncoder().encode(data.getPassword()));
        return ResponseEntity.ok(userRepository.save(data));
    }

    @GetMapping(value = "/profile")
    public ResponseEntity getProfile(
            @RequestHeader("Authorization") String jwtHeader
    ){
        String token = jwtHeader.replace("Bearer ", "");
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256("DefinietlyNotSecured")).build().verify(token);
        String userName = jwt.getClaim("username").asString();

        UserDetails details = appUserDetailsService.loadUserByUsername(userName);
        return ResponseEntity.ok(details.getUsername());
    }
}

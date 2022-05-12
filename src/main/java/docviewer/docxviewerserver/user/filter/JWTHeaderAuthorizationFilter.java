package docviewer.docxviewerserver.user.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import docviewer.docxviewerserver.user.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JWTHeaderAuthorizationFilter implements Filter {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String header = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        if (header != null) {
            if (header.startsWith("Bearer")) {
                String token = header.replace("Bearer ", "");
                DecodedJWT jwt = JWT.require(Algorithm.HMAC256("DefinietlyNotSecured")).build().verify(token);
                String userName = jwt.getClaim("username").asString();

                UserDetails details = appUserDetailsService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                        details.getUsername(),
                        details.getPassword(),
                        details.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(loginToken);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

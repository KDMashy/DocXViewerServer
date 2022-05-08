package docviewer.docxviewerserver.user.security;

import docviewer.docxviewerserver.user.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    public AppDaoAuthenticationProvider(AppUserDetailsService userDetailsService) {
        setPasswordEncoder(new BCryptPasswordEncoder());
        setUserDetailsService(userDetailsService);
    }
}

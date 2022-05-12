package docviewer.docxviewerserver.user.service;

import docviewer.docxviewerserver.user.repository.UserRepository;
import docviewer.docxviewerserver.user.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsServiceImpl implements AppUserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.loadUserByUsername(username);
    }
}

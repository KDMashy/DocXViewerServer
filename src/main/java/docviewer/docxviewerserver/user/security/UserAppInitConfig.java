package docviewer.docxviewerserver.user.security;

import docviewer.docxviewerserver.user.entity.RoleEntity;
import docviewer.docxviewerserver.user.entity.UserEntity;
import docviewer.docxviewerserver.user.repository.RoleRepository;
import docviewer.docxviewerserver.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Configuration
public class UserAppInitConfig {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init() {
        if (roleRepository.findAll().isEmpty()){
            RoleEntity user = new RoleEntity();
            user.setAuthority("ROLE_USER");
            roleRepository.save(user);

            RoleEntity admin = new RoleEntity();
            admin.setAuthority("ROLE_ADMIN");
            roleRepository.save(admin);
        }

        if (userRepository.findAll().isEmpty()){
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername("valaki");
            userEntity.setAuthorities(new ArrayList<>());
            userEntity.getAuthorities().add(roleRepository.getByAuthorityName("ROLE_USER"));

            userEntity.setPassword(new BCryptPasswordEncoder().encode("teszt"));
            userRepository.save(userEntity);

            UserEntity adminEntity = new UserEntity();
            adminEntity.setUsername("admin");
            adminEntity.setAuthorities(new ArrayList<>());
            userEntity.getAuthorities().add(roleRepository.getByAuthorityName("ROLE_USER"));
            userEntity.getAuthorities().add(roleRepository.getByAuthorityName("ROLE_ADMIN"));

            adminEntity.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(adminEntity);
        }
    }
}

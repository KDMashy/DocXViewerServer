package docviewer.docxviewerserver.user.repository;

import docviewer.docxviewerserver.core.repository.CoreAbstractRepository;
import docviewer.docxviewerserver.user.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserRepository extends CoreAbstractRepository<UserEntity> {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

package docviewer.docxviewerserver.user.repository;

import docviewer.docxviewerserver.core.repository.CoreAbstractRepositoryImpl;
import docviewer.docxviewerserver.user.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepositoryImpl extends CoreAbstractRepositoryImpl<UserEntity> implements UserRepository {
    @Override
    protected Class<UserEntity> getManagedClass() {
        return UserEntity.class;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TypedQuery<UserEntity> typedQuery = entityManager.createNamedQuery(UserEntity.FIND_USER_BY_USERNAME, UserEntity.class);
        typedQuery.setParameter("username", username);
        List<UserEntity> list = typedQuery.getResultList();
        if (list.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return list.get(0);
    }
}

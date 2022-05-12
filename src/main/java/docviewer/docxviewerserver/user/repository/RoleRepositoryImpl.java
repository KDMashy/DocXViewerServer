package docviewer.docxviewerserver.user.repository;

import docviewer.docxviewerserver.core.repository.CoreAbstractRepositoryImpl;
import docviewer.docxviewerserver.user.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class RoleRepositoryImpl extends CoreAbstractRepositoryImpl<RoleEntity> implements RoleRepository {
    @Override
    protected  Class<RoleEntity> getManagedClass() {
        return RoleEntity.class;
    }

    @Override
    public RoleEntity getByAuthorityName(String name){
        TypedQuery<RoleEntity> typedQuery = entityManager.createNamedQuery(RoleEntity.FIND_BY_AUTHORITY, RoleEntity.class);
        typedQuery.setParameter("authority", name);
        return typedQuery.getSingleResult();
    }
}

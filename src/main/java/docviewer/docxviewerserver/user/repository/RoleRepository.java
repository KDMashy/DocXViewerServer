package docviewer.docxviewerserver.user.repository;

import docviewer.docxviewerserver.core.repository.CoreAbstractRepository;
import docviewer.docxviewerserver.user.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class RoleRepository extends CoreAbstractRepository<RoleEntity> {
    @Override
    protected  Class<RoleEntity> getManagedClass() {
        return RoleEntity.class;
    }

    public RoleEntity getByAuthorityName(String name){
        TypedQuery<RoleEntity> typedQuery = entityManager.createNamedQuery(RoleEntity.FIND_BY_AUTHORITY, RoleEntity.class);
        typedQuery.setParameter("authority", name);
        return typedQuery.getSingleResult();
    }
}

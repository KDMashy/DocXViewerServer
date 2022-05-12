package docviewer.docxviewerserver.user.repository;

import docviewer.docxviewerserver.core.repository.CoreAbstractRepository;
import docviewer.docxviewerserver.user.entity.RoleEntity;

public interface RoleRepository extends CoreAbstractRepository<RoleEntity> {
    RoleEntity getByAuthorityName(String name);
}

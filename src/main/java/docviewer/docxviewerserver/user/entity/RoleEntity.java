package docviewer.docxviewerserver.user.entity;

import docviewer.docxviewerserver.core.entity.CoreEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@NamedQuery(name = RoleEntity.FIND_BY_AUTHORITY,query = "SELECT u FROM RoleEntity u WHERE u.authority=:authority")
@Table(name = "app_role")
@Entity
public class RoleEntity extends CoreEntity implements GrantedAuthority {
    public static final String FIND_BY_AUTHORITY = "RoleEntity.findByAuthority";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}

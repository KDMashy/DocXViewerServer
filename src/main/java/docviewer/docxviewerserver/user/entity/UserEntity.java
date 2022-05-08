package docviewer.docxviewerserver.user.entity;

import docviewer.docxviewerserver.core.entity.CoreEntity;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = UserEntity.FIND_USER_BY_USERNAME,query = "SELECT u FROM UserEntity u WHERE u.username=:username")
@Table(name = "app_user")
@Entity
public class UserEntity extends CoreEntity implements UserDetails {
    public static final String FIND_USER_BY_USERNAME = "findUserByUsername";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> authorities = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public List<RoleEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<RoleEntity> authorized) {
        this.authorities = authorized;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

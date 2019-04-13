package bell.yusipov.fileservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Объект роли пользователя
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    /**
     * Идетнификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Название роли
     */
    @Column(name = "role_name", length = 20, nullable = false)
    private String roleName;

    /**
     * Пользователи с данной ролью
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<User> users;

    public Role() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

package bell.yusipov.fileservice.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Пользователь
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    /**
     * Идентификатор
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
     * Имя
     */
    @Column(name = "user_name", length = 100, nullable = false)
    private String userName;

    /**
     * Электронная почта
     */
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    /**
     * Пароль
     */
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    /**
     * Код активации
     */
    @Column(name = "activ_code", length = 100, nullable = false)
    private String activationCode;

    /**
     * подтвеждение почты
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Роль пользователя
     */
    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

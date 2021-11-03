package project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_user")
@NoArgsConstructor
public class User {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String password;
    private boolean enabled;
    private String role;

    public User(String login, String password, String roles) {
        this.login = login;
        this.password = password;
        this.enabled = true;
        this.role = roles;
    }
}
package app.models;

import app.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
public class User {
    @Id
    @SequenceGenerator(name = "User_Seq", initialValue = 50_000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_Seq")
    @JsonView(Views.Public.class)
    private long id;

    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
    private String email;

    @JsonView(Views.Internal.class)
    private String hashedPassword;

    @JsonView(Views.Public.class)
    private String role;

    protected User() {}

    public User (long id, String name, String email, String hashedPassword, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
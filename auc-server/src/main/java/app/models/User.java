package app.models;

import app.repositories.interfaces.Identifiable;
import app.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "User_find_by_email",
            query = "SELECT u FROM User u WHERE u.email=?1")
})

@Entity
@Table(name = "ACCOUNT")
public class User implements Identifiable {
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonSerialize(using = Views.ShallowSerializer.class)
    private Set<Bid> bids;

    protected User() {}

    public User (String name, String email, String hashedPassword, String role) {
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }

    public static User createSampleUser(String name, String role) {
        String email = String.format("%s@hva.nl", name);
        String hashedPassword = "password123";

        return new User(name.toLowerCase(), email, hashedPassword, role);
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

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public boolean associateBid(Bid bid) {
        if (bid != null && bid.getUser() == null) {
            bid.associateUser(this);
            bids.add(bid);

            return true;
        }

        return false;
    }
}
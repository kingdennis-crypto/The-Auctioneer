package app.rest;

import app.APIConfig;
import app.exceptions.NotAcceptableException;
import app.models.User;
import app.repositories.interfaces.EntityRepository;
import app.utilities.JWToken;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private Environment environment;

    @Autowired
    private EntityRepository<User> userRepo;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody ObjectNode object) {
        String email = object.get("email").textValue();
        String password = object.get("password").textValue();

        String role = "Registered User";
        String[] emailArray = email.split("@");

        if (!emailArray[0].equals(password)) {
            throw new NotAcceptableException("Wrong credentials");
        }

        List<User> users = userRepo.findByQuery("User_find_by_email", email);
        User user;

        if (users.size() == 0) {
            user = new User(emailArray[0], email, password, role);
        } else {
            user = users.get(0);
        }

        JWToken jwToken = new JWToken(user.getName(), user.getId(), user.getRole());

        String issuer = environment.getProperty("jwt.issuer");
        String passphrase = environment.getProperty("jwt.pass-phrase");
        int duration = Integer.parseInt(Objects.requireNonNull(environment.getProperty("jwt.tokenDuration")));

        String tokenString = jwToken.encode(issuer, passphrase, duration);
        System.out.println(tokenString);

        return ResponseEntity.accepted()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString)
                .body(user);
    }
}

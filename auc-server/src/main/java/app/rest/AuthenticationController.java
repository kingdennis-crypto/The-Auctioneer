package app.rest;

import app.APIConfig;
import app.exceptions.NotAcceptableException;
import app.models.User;
import app.utilities.JWToken;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody ObjectNode body) {
        String email = String.valueOf(body.get("email"));
        String password = String.valueOf(body.get("password"));

        if (email.isEmpty() || password.equals("password123")) {
            throw new NotAcceptableException("Wrong login credentials");
        }

        User user = new User(email, password);
        JWToken jwToken = new JWToken(user.getName(), user.getId(), user.getRole());

        String issuer = "HvA";
        String passphrase = "d34b5a43fbdbd1f490304a41067cfe2c790711017c7c524453650498129a1784";
        int tokenDurationOfValidity = 1200;

        String token = jwToken.encode(issuer, passphrase, tokenDurationOfValidity);

        return ResponseEntity.accepted()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(user);
    }
}

package app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Component
public class APIConfig implements WebMvcConfigurer {
    public static final List SECURED_PATHS = new ArrayList<String>(List.of("/authentication", "h2-console", "/favicon.ico"));

    @Value("HvA")
    public String issuer;

    @Value("d34b5a43fbdbd1f490304a41067cfe2c790711017c7c524453650498129a1784")
    private String passphrase;

    @Value("1200")
    public int tokenDurationOfValidity;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:*", "http://*.hva.nl:*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    public String getPassphrase() {
        return passphrase;
    }
}

package app.utilities;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JWToken {
    private static final String JWT_CALLNAME_CLAIM = "sub";
    private static final String JWT_USERID_CLAIM = "id";
    private static final String JWT_ROLE_CLAIM = "role";
    private static final String JWT_IPADDRESS_CLAIM = "ipa";
    public static final String JWT_ATTRIBUTE_NAME = "JWTokenInfo";

    private String callName = null;
    private Long userId = null;
    private String role = null;
    private String ipAddress;

    public JWToken(String callName, long userId, String role) {
        this.callName = callName;
        this.userId = userId;
        this.role = role;
    }

    public String encode(String issuer, String passphrase, int expiration) {
        Key key = getKey(passphrase);

        return Jwts.builder()
                .claim(JWT_CALLNAME_CLAIM, this.callName)
                .claim(JWT_USERID_CLAIM, this.userId)
                .claim(JWT_ROLE_CLAIM, this.role)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration + 1000L))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public static JWToken decode(String token, String passphrase) throws ExpiredJwtException, MalformedJwtException {
        Key key = getKey(passphrase);

        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        Claims claims = jws.getBody();

        // TODO, in the PDF it says something different, but this works for now
        JWToken jwToken = new JWToken(
                JWT_CALLNAME_CLAIM,
                0,
                JWT_ROLE_CLAIM
        );

        jwToken.setIpAddress((String) claims.get(JWT_IPADDRESS_CLAIM));
        System.out.println(jwToken.toString());
        return jwToken;
    }

    private static Key getKey(String passphrase) {
        byte[] hmacKey = passphrase.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(hmacKey, SignatureAlgorithm.HS512.getJcaName());
    }

    public String getCallName() {
        return callName;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "JWToken{" +
                "callName='" + callName + '\'' +
                ", userId=" + userId +
                ", role='" + role + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}

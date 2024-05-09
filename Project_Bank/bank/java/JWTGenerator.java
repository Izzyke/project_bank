package main.bank.java;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTGenerator {

    // Secret key for signing the JWT token
    private static final Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    // Generate JWT token for a given bank account
    public static String generateToken(BankAccount account) {
        return Jwts.builder()
                .setSubject(Long.toString(account.getAccountId())) // You might want to add an accountId to your BankAccount class
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day validity
                .signWith(key)
                .compact();
    }
}

package main.bank.java;

import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@SpringBootApplication
public class BankingAPI {

    public static void main(String[] args) {
        // Run the Spring Boot application
        SpringApplication.run(BankingAPI.class, args);
    }

    @RestController
    @RequestMapping("/banking")
    public static class BankAccountController {

        private static final Logger logger = Logger.getLogger(BankAccountController.class.getName());

        private static final Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

        @GetMapping("/account")
        public String performBankOperations() {
            BankAccount account = new BankAccount(123456789, 1000); // Initial balance of ksh1000

            // Deposit ksh 500
            account.deposit(500);

            // Withdraw ksh 200
            account.withdraw(200);

            // Withdraw ksh 2000 (not enough funds)
            account.withdraw(2000);

            // Generate JWT token for the bank account
            String token = generateToken(account);
            logger.info("JWT Token: " + token);

            return "Bank operations performed successfully. JWT Token generated.";
        }

        // Generate JWT token for a given bank account
        public String generateToken(BankAccount account) {
            return Jwts.builder()
                    .setSubject(Long.toString(account.getAccountId()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day validity
                    .signWith(key)
                    .compact();
        }
    }
}

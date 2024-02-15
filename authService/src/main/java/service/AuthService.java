package service;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private static final AuthService INSTANCE = new AuthService();
    private final Map<String, String> userDatabase = new HashMap<>();
    private final Map<String, String> tokenDatabase = new HashMap<>();

    private AuthService() {
        userDatabase.put("adminUser", "password1");
        userDatabase.put("simpleUser", "password2");
    }

    public static AuthService getINSTANCE() {
        return INSTANCE;
    }

    public String authenticate(String username, String password) {
        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            String token = generateToken(username);
            tokenDatabase.put(token, username);
            return token;
        }
        return null;
    }

    public boolean validateToken(String token) {
        return tokenDatabase.containsKey(token);
    }

    private String generateToken(String username) {
        String token = "a-level-token" + System.currentTimeMillis();
        if (username.equals("adminUser")) {
            token += "admin";
        } else {
            token += "user";
        }
        return token;
    }
}

package com.example.product_api.dto;

public class AuthRequest {
    private String username;
    private String password;

    // getter dan setter
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

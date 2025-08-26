package com.wecp.progressive.entity;

public class Customers {
private int customerId;
private String name;
private String email;
private String username;
private String role;
public Customers(int customerId, String name, String email, String username, String role) {
    this.customerId = customerId;
    this.name = name;
    this.email = email;
    this.username = username;
    this.role = role;
}
public Customers(){}
public int getCustomerId() {
    return customerId;
}
public void setCustomerId(int customerId) {
    this.customerId = customerId;
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
public String getUsername() {
    return username;
}
public void setUsername(String username) {
    this.username = username;
}
public String getRole() {
    return role;
}
public void setRole(String role) {
    this.role = role;
}

}
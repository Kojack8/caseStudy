package com.entitymodels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class AdminEntity {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private @Id @GeneratedValue Long id;

    private String name;

    private @JsonIgnore String password;

    private String role;

    public void setPassword(String password){
        this.password = PASSWORD_ENCODER.encode(password);
    }

    protected AdminEntity() {}

    public AdminEntity(String name, String password, String role) {
        this.name = name;
        this.setPassword(password);
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminEntity that = (AdminEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return role;
    }

    public void setRoles(String roles) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AdminEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roles=" + role +
                '}';
    }
}

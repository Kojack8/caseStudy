package com.entitymodels;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user")
public class UserEntity {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(name = "email", unique = true, nullable = false, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "address1", nullable = false, length = 50)
    private String address1;

    @Column(name = "address2", length = 50)
    private String address2;

    @Column(name = "city", nullable = false, length = 165)
    private String city;

    @Column(name = "state", length = 2)
    private String state = null;

    @Column(name = "country", nullable = false, length = 60)
    private String country = null;

    @Column(name = "zip", nullable = false, length = 15)
    private String zip;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    private boolean enabled;

    private boolean tokenExpired;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<RoleEntity> roles;





    public UserEntity() {
    }

    public UserEntity(String fullName, String email, String password, String address1, String address2,
                      String city, String state, String country, String zip, String phone) {
        this.fullName = fullName;
        this.email = email;
        setPassword(password);
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
        this.phone = phone;
    }

    public UserEntity(String fullName, String email, String password, String address1, String city,
                      String zip, String phone) {
        this.fullName = fullName;
        this.email = email;
        setPassword(password);
        this.address1 = address1;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        if (address2 == "") {
            this.address2 = null;
        } else {
            this.address2 = address2;
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (state == "") {
            this.state = null;
        } else {
            this.state = state;
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id) && fullName.equals(that.fullName) && email.equals(that.email) && password.equals(that.password) && address1.equals(that.address1) && address2.equals(that.address2) && city.equals(that.city) && Objects.equals(state, that.state) && Objects.equals(country, that.country) && zip.equals(that.zip) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email, password, address1, address2, city, state, country, zip, phone);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}




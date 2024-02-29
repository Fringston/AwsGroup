package com.example.CompanyEmployee.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "user_roles_junction",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> authorities;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    public User(Long id, String username, String password, Set<Role> authorities) {  //constructor
        this.userId = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User() {
        super();
        this.authorities = new HashSet<Role>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {return this.authorities;}

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {return this.password;}

    public void setPassword(String password) {this.password = password;}

    @Override
    public String getUsername() {return this.username;}

    public void setUsername(String username) {this.username = username;}

    public Set<Role> getRoles() {return this.authorities;}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}


    /*public Company getCompany() {
        if (companies != null && !companies.isEmpty()) {
            // Assuming a user can be associated with only one company
            return companies.iterator().next();
        } else {
            return null; // Or throw an exception if you expect every user to have a company
        }
    }*/

}

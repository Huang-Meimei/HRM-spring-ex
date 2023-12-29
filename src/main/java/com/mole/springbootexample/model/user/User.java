package com.mole.springbootexample.model.user;

import com.mole.springbootexample.model.user.AppUserRole;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
@AllArgsConstructor
public class User implements UserDetails {
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence"
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    private Long id;

    @Column(
            nullable = false
    )
    private String name;
    private String email;
    private String password;
    private boolean enabled = false;
    private boolean locked = false;

    @Enumerated(EnumType.STRING)
    private AppUserRole role;


    public User(String username, String email, String password, AppUserRole role) {
        this.name = username;
        this.email = email;
        this.password = password;
        this.role = role;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}

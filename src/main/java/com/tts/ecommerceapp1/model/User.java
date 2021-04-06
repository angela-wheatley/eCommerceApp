package com.tts.ecommerceapp1.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    
    @Length(min = 3, message = "Your username must have at least 3 characters")
    @Length(max = 15, message = "Your username cannot have more than 15 characters")
    @Pattern(regexp = "[^\\s]+", message = "Your username cannot contain spaces")
    private String username;
    
    @Length(min = 8, message = "Your password must have at least 8 characters")
    private String password;
    
    @ElementCollection
    private Map<Product, Integer> cart = new HashMap<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        // TODO Auto-generated method stub
        return true;
    }
}

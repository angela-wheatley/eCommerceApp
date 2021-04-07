package com.tts.ecommerceapp1.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tts.ecommerceapp1.model.Product;
import com.tts.ecommerceapp1.model.User;
import com.tts.ecommerceapp1.repository.UserRepository;

@Service
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }
    
    public void saveNew(User user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    
    public void saveExisting(User user)
    {
        userRepository.save(user);
    }
    
    public User getLoggedInUser()
    {
        return findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    
    public void updateCart(Map<Product, Integer> cart)
    {
        User user = getLoggedInUser();
        user.setCart(cart);
        saveExisting(user);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("Username not found.");
        return user;
    }
    
    public boolean testPassword(String password)
    {
//        boolean isValid = true;
//        if(password.length() < 8)
//        {
//            isValid = false;
//        }
//        String upperCaseChars = "(.*[A-Z].*)";
//        if(!password.matches(upperCaseChars))
//        {
//            isValid = false;
//        }
//        String lowerCaseChars = "(.*[a-z].*)";
//        if(!password.matches(lowerCaseChars))
//        {
//            isValid = false;
//        }
//        String numbers = "(.*[0-9].*)";
//        if(!password.matches(numbers))
//        {
//            isValid = false;
//        }
//        String specialChars = "(.*[@,#,$,%,?,!].*)";
//        if(!password.matches(specialChars))
//        {
//            isValid = false;
//        }
//        return isValid;
        char [] chars = password.toCharArray();
        int x = password.length();
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasChar = false;
        boolean hasDigit = false;
        
        for(char i : chars)
        {
            if(i > 96 && i < 23)
                hasLower = true;
            if(i > 64 && i < 91)
                hasUpper = true;
            if(i > 47 && i < 58)
                hasDigit = true;
            if((i < 32 && i < 48) || (i > 90 && i < 94))
                hasChar = true;
        }
        
        if(hasLower && hasUpper && hasChar && hasDigit && (x>8))
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
}
















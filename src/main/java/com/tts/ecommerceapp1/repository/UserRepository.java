package com.tts.ecommerceapp1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.ecommerceapp1.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}

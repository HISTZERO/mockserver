package com.example.mockserver.repository;

import com.example.mockserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsernameAndPassword (String username, String password);

    User findByUsername(String userName);

    public User findByEmail(@Param("email") String email);
}
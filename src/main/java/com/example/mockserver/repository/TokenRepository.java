package com.example.mockserver.repository;

import com.example.mockserver.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;


@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
    void deleteById(Long id);

    @Query(
        value = "SELECT * from token tk where tk.token = :token and tk.available_time >= current_date ",
            nativeQuery = true
    )
    Optional<Token> checkValidToken(@Param("token")  String token);

}
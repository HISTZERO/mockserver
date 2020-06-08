package com.example.mockserver.repository;

import com.example.mockserver.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findById(Long id);
    boolean existsById(Long id);
}

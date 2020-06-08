package com.example.mockserver.repository;

import com.example.mockserver.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value="SELECT * FROM mockserver.project WHERE user_id = ?1 AND delete_flag = ?2 limit ?3 offset ?4", nativeQuery = true)
    List<Project> findByUserIdAndDeleteFlag(Long userId, int deleteFlag, int offset, int limit);

    int countProjectsByUserIdAndAndDeleteFlag(Long userId, int deleteFlag);
    boolean existsByProjectNameAndApiPrefixAndDeleteFlag(String projectName, String apiPrefix, int deleteFlag);
    Project findByProjectNameAndApiPrefixAndDeleteFlag(String projectName, String apiPrefix, int deleteFlag);
    boolean existsByIdAndDeleteFlag(Long id, int delete_flag);
    Optional<Project> findById(Long id);
}

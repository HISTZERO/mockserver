package com.example.mockserver.repository;

import com.example.mockserver.dto.ApiDTO;
import com.example.mockserver.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ApiRepository extends JpaRepository<Api, Long>{

   @Query(
           value = "select * from api a where a.project_id = :projectId and  a.delete_flag = :deleteFlag",
           nativeQuery = true
   )
    List<Api> listApi(@Param("projectId") Long projectId, @Param("deleteFlag") int deleteFlag);

    boolean existsByIdAndDeleteFlag(Long id, int delete_flag);
    boolean existsByNameAndPath(String name, String path);

    Optional<Api> findByNameAndPath(String name, String path);

    Optional<Api> findById(Long id);

    @Query(
            value = "SELECT new com.example.mockserver.dto.ApiDTO(a.id, s.codeValue, s.response, a.path, a.name, a.content, a.deleteFlag) FROM api a  JOIN a.status s WHERE a.projectId = :projectId "

    )
            List<ApiDTO> fetchApiDTO(@Param("projectId") Long projectId);

    @Query(
            value = "SELECT new com.example.mockserver.dto.ApiDTO(a.id, s.codeValue, s.response, a.path, a.name, a.content, a.deleteFlag) FROM api a JOIN a.status s WHERE  a.id = :id"

    )
            List<ApiDTO> getApiDTO(@Param("id") Long id);

}

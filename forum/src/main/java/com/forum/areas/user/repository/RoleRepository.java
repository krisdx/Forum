package com.forum.areas.user.repository;

import com.forum.areas.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "SELECT * from roles AS r" +
            " WHERE r.id = 1", nativeQuery = true)
    Role getUserRole();
}
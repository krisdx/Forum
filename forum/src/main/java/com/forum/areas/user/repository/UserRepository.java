package com.forum.areas.user.repository;

import com.forum.areas.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "SELECT COUNT(c.id) FROM comments AS c" +
            " WHERE c.username = :username",nativeQuery = true)
    int commentsCountByUsername(@Param("username") String username);
}
package com.app.smartcontactmanager.repository;

import com.app.smartcontactmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
//    @Query(value = "select * from users  where email=:email" , nativeQuery = true)
//    User getUserByEmail(@Param("email") String email);

    User findByEmail(String email);
}

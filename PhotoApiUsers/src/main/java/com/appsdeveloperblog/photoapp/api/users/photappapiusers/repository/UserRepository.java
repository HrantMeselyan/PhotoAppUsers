package com.appsdeveloperblog.photoapp.api.users.photappapiusers.repository;

import com.appsdeveloperblog.photoapp.api.users.photappapiusers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String username);
    User findByFirstName(String username);

}

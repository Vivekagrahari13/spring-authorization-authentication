package com.spring.security.authentication.repository;

import com.spring.security.authentication.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

//DAO
@Repository
public interface UserRepository extends JpaRepository< User,Long> {
    Optional<User> findByUsername(String username);
}

package com.bbps.token.rest.repository;

import com.bbps.token.rest.model.TokenUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TokenUser, Long> {
    TokenUser findByUsernameAndPassword(String username, String password);
}

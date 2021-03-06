package com.temzu.monomarket.dao.repositories;

import com.temzu.monomarket.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByLogin(String login);

  boolean existsByLogin(String login);

  boolean existsByEmail(String email);
}

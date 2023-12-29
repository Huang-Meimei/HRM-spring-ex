package com.mole.springbootexample.service.dao.imp;

import com.mole.springbootexample.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository  extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}

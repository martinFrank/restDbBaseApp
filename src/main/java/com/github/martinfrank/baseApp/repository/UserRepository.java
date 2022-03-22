package com.github.martinfrank.baseApp.repository;

import com.github.martinfrank.baseApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


}

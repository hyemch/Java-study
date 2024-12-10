package com.hana4.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hana4.board.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}

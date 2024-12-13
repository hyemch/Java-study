package com.hana4.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hana4.demo.domain.User;

public interface ApiRepository extends JpaRepository<User, Long> {

}

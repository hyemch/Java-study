package com.hana4.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hana4.demo.entity.Code;

public interface CodeRepository extends JpaRepository<Code, Integer> {
	List<Code> findFirstByOrderBy(Pageable pageable);
}

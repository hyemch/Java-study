package com.hana4.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hana4.demo.entity.CodeInfo;

public interface CodeInfoRepository extends JpaRepository<CodeInfo, Integer> {

	List<CodeInfo> findFirstByOrderById(Pageable pageable);
}

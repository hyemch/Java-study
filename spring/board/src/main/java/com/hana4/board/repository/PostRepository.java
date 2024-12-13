package com.hana4.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hana4.board.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByWriterId(String id);
}

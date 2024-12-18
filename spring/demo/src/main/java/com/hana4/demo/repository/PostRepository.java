package com.hana4.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hana4.demo.entity.Post;

public interface PostRepository extends JpaRepository<Post, String> {
	// @Query("select p from Post p where p.writer = :writer and p.createdate <= :dateTime")
	// List<Post> findByAnything(@Param("writer") String writer, @Param("dateTime") LocalDateTime dateTime);

	List<Post> findByCreatedateLessThanEqual(LocalDateTime dateTime);

	long countByCreatedateLessThanEqual(LocalDateTime dateTime);

	List<Post> findByWriter(String writer);

	List<Post> findByWriter(String writer, Sort sort);

	List<Post> findByTitleStartsWith(String title);

	Page<Post> findByTitleLike(String tilte, Pageable pageable);

	long countByTitleLike(String searchStr);

	// @Query("select p from Post p where p.writer = :writer and p.createdate <= :dateTime")
	// List<Post> findByAnything(@Param("writer") String writer, @Param("dateTime") LocalDateTime dateTime);
	// @Query("select m, p from Msg m, Post p where m.post = p.id")
	@Query("""
				select p from Post p
				where p.writer = :writer and p.createdate <= :dateTime
		""")
	List<Post> findByOldWriter(@Param("writer") String writer, @Param("dateTime") LocalDateTime dateTime);

	@Query("select p.title, p.writer, p.createdate from Post p where p.writer = :writer")
	List<Object[]> findBySomeColumns(@Param("writer") String writer);
}

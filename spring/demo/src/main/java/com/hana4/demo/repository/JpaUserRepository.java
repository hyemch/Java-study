package com.hana4.demo.repository;

import java.util.List;
import java.util.Optional;

import com.hana4.demo.domain.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional
public class JpaUserRepository implements UserRepository {
	private final EntityManager em;

	public JpaUserRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<User> findAll() {
		//jpql : entitiy 직접준다.
		return em.createQuery("select u from User u", User.class).getResultList();
	}

	@Override
	public Long addUser(User user) {
		//save와 비슷하다.
		em.persist(user);
		return user.getId();
	}

	@Override
	public User saveUser(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public User deleteUser(Long id) {
		Optional<User> user = this.findById(id); //command alt v 옵셔널 생성
		if (user.isPresent()) {
			em.remove(user);
			return user.get();
		}
		return null;
	}

	@Override
	public Optional<User> findById(Long id) {
		//getSingleResult()는 없을경우 404에러를 띄우무로 꼭 있는 것에서만 사용.
		return em.createQuery("select u from User u where u.id = :id", User.class)
			.setParameter("id", id).getResultList().stream().findAny();
	}

	@Override
	public Optional<User> findByName(String username) {
		List<User> users = em.createQuery("select u from User u where u.name = :name", User.class)
			.setParameter("name", username)
			.getResultList();

		return users.stream().findAny();
	}

	public void initialize() {
		String truncSql = "truncate table DemoUser";
		em.createNativeQuery(truncSql).executeUpdate();
	}
}

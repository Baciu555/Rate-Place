package com.baciu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.baciu.entity.User;

@Transactional
@Repository
public class UserDAO implements IUserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User logIn(String username, String password) {
		String hql = "FROM User as usr WHERE usr.username = :username AND usr.password = :password";
		User user = new User();
		try {
			user = (User) entityManager.createQuery(hql).setParameter("username", username)
					.setParameter("password", password).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
		return user;
	}

	@Override
	public User getById(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}

	@Override
	public void update(User user) {
		User usr = getById(user.getId());
		usr.setUsername(user.getUsername());
		usr.setEmail(user.getEmail());
		entityManager.flush();
	}
	
	@Override
	public void updateAvatar(User user) {
		User usr = getById(user.getId());
		usr.setAvatarPath(user.getAvatarPath());
		entityManager.flush();
	}

	@Override
	public List<User> getAll() {
		String hql = "FROM User as u";
		List<User> users = entityManager.createQuery(hql, User.class).getResultList();
		return users;
	}

	@Override
	public boolean usernameExists(String username) {
		String hql = "FROM User as usr WHERE usr.username = :username";
		try {
			entityManager.createQuery(hql).setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		
		return true;
 	}

	@Override
	public boolean emailExists(String email) {
		String hql = "FROM User as usr WHERE usr.email = :email";
		try {
			entityManager.createQuery(hql).setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		
		return true;
	}

}

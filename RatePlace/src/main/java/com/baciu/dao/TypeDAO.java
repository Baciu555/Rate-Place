package com.baciu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.baciu.entity.Type;

@Transactional
@Repository
public class TypeDAO implements ITypeDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getAll() {
		String hql = "FROM Type as tp ORDER BY tp.id";
		return (List<Type>) entityManager.createQuery(hql).getResultList();
	}

}

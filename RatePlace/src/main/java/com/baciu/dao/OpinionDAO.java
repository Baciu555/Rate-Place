package com.baciu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.baciu.entity.Opinion;


@Transactional
@Repository
public class OpinionDAO implements IOpinionDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Opinion> getByPlaceId(int placeId) {
		String hql = "FROM Opinion as o WHERE o.place.id = :placeId";
		
		return (List<Opinion>) entityManager.createQuery(hql, Opinion.class).setParameter("placeId", placeId).getResultList();
	}
	
	@Override
	public void addOpinion(Opinion opinion) {
		entityManager.persist(opinion);
	}
}

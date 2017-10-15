package com.baciu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.baciu.entity.Opinion;
import com.baciu.entity.Place;
import com.baciu.entity.User;

@Transactional
@Component
public class PlaceDAO implements IPlaceDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Place> getAll() {
		String hql = "FROM Place as plc ORDER BY plc.avgGrade DESC";
		List<Place> places = entityManager.createQuery(hql).getResultList();

		return places;
	}
	
	@Override
	public Place getPlace(int id) {
		return entityManager.find(Place.class, id);
	}
	
	@Override
	public void addPlace(Place place) {
		entityManager.persist(place);
	}
	
	@Override
	public List<Place> getUserPlaces(int userId) {
		User user = entityManager.find(User.class, userId);
		Set<Opinion> opinions = user.getOpinions();
		List<Place> places = new ArrayList<Place>();
		for (Opinion o : opinions)
			places.add(o.getPlace());
		
		return places;
	}
	
	@Override
	public boolean placeExists(String name) {
		String hql = "FROM Place p WHERE p.name = :name";
		try {
			entityManager.createQuery(hql).setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		
		return true;
	}
	
	public Place getByPlaceName(String name) {
		return (Place) entityManager.createQuery("FROM Place p WHERE p.name = ?").setParameter(1, name).getSingleResult();
	}
}

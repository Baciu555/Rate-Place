package com.baciu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.dao.TypeDAO;
import com.baciu.entity.Type;

@Service
public class TypeService implements ITypeService {
	
	@Autowired
	private TypeDAO typeDAO;

	@Override
	public List<Type> getAll() {
		List<Type> types = typeDAO.getAll();
		return types;
	}
	
}

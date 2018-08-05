package com.baciu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.entity.Type;
import com.baciu.repository.TypeRepository;

@Service
public class TypeService implements ITypeService {

	@Autowired
	private TypeRepository typeRepository;
	
	@Override
	public List<Type> getAll() {
		List<Type> types = (List<Type>) typeRepository.findAll();
		return types;
	}
	
}

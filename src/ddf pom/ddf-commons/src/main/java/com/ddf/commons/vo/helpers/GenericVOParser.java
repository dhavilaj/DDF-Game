package com.ddf.commons.vo.helpers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.ddf.commons.vo.interfaces.DDFEntity;
import com.ddf.commons.vo.interfaces.ValueObject;

/**
 * @author David Mantilla
 *
 * @param <T> entity class
 * @param <U> value object class
 * @since 1.7
 */
public class GenericVOParser<T extends DDFEntity, U extends ValueObject> {

	/**
	 * Constructor
	 */
	public GenericVOParser() {

	}

	/**
	 * Converts ValueObject to entity
	 * 
	 * @param vo
	 * @param entityClass
	 * @return the created entity
	 */
	public T toEntity(U vo, Class<T> entityClass) {
		try {
			T entity = entityClass.newInstance();
			copyVOToEntity(vo, entity);
			return entity;
		} catch (IllegalAccessException | InvocationTargetException
			| InstantiationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Copy the value object to the entity
	 * 
	 * @param vo
	 * @param entity
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected void copyVOToEntity(U vo, T entity)
		throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(entity, vo);
	}

	/**
	 * Converts Entity to ValueObject
	 * 
	 * @param entity
	 * @param valueObjectClass
	 * @return the created value object
	 */
	public U toValueObject(T entity, Class<U> valueObjectClass) {
		try {
			U vo = valueObjectClass.newInstance();
			copyEntityToVO(entity, vo);
			return vo;
		} catch (IllegalAccessException | InvocationTargetException
			| InstantiationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Creates a value object list from entity list
	 * 
	 * @param entities entities to convert
	 * @param valueObjectClass value object class
	 * @return the list of the new value objects
	 */
	public List<U> toValueObjectList(List<T> entities, Class<U> valueObjectClass) {
		List<U> list = new ArrayList<>();
		for (T entity : entities) {
			list.add(toValueObject(entity, valueObjectClass));
		}
		return list;
	}

	/**
	 * Creates a entity list from value object list
	 * 
	 * @param vos Value objects to convert
	 * @param entityClass value object class
	 * @return the list of the new entities
	 */
	public List<T> toEntityList(List<U> vos, Class<T> entityClass) {
		List<T> list = new ArrayList<>();
		for (U vo : vos) {
			list.add(toEntity(vo, entityClass));
		}
		return list;
	}

	/**
	 * 
	 * @param entity
	 * @param vo
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected void copyEntityToVO(T entity, U vo)
		throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(vo, entity);
	}
}

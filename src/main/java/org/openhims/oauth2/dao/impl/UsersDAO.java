package org.openhims.oauth2.dao.impl;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.openhims.oauth2.dao.IUsersDAO;
import org.openhims.oauth2.domain.Users;
import org.openhims.oauth2.util.EntityManagerHelper;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for Users
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see org.openhims.oauth2.domain.Users
 * @author MyEclipse Persistence Tools
 */

@Repository
public class UsersDAO implements IUsersDAO {
	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String GIVEN_NAME = "givenName";
	public static final String FAMILY_NAME = "familyName";
	public static final String PHONE = "phone";
	public static final String FAX = "fax";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Users entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UsersDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Users entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Users entity) {
		EntityManagerHelper.log("saving Users instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Users entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UsersDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Users entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Users entity) {
		EntityManagerHelper.log("deleting Users instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Users.class,
					entity.getUsersId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Users entity and return it or a copy of it to
	 * the sender. A copy of the Users entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = UsersDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Users entity to update
	 * @return Users the persisted Users entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Users update(Users entity) {
		EntityManagerHelper.log("updating Users instance", Level.INFO, null);
		try {
			Users result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Users findById(Integer id) {
		EntityManagerHelper.log("finding Users instance with id: " + id,
				Level.INFO, null);
		try {
			Users instance = getEntityManager().find(Users.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Users entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Users property to query
	 * @param value
	 *            the property value to match
	 * @return List<Users> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Users> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Users instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Users model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Users> findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List<Users> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<Users> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<Users> findByGivenName(Object givenName) {
		return findByProperty(GIVEN_NAME, givenName);
	}

	public List<Users> findByFamilyName(Object familyName) {
		return findByProperty(FAMILY_NAME, familyName);
	}

	public List<Users> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List<Users> findByFax(Object fax) {
		return findByProperty(FAX, fax);
	}

	/**
	 * Find all Users entities.
	 * 
	 * @return List<Users> all Users entities
	 */
	@SuppressWarnings("unchecked")
	public List<Users> findAll() {
		EntityManagerHelper
				.log("finding all Users instances", Level.INFO, null);
		try {
			final String queryString = "select model from Users model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}
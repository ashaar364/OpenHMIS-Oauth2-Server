package org.openhims.oauth2.dao;

import java.util.List;

import javax.annotation.Resource;

import org.openhims.oauth2.domain.Users;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Ashaar Riaz
 */

public interface IUsersDAO {
	/**
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUsersDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Users entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Users entity);

	/**
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUsersDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Users entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Users entity);

	/**
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUsersDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Users entity to update
	 * @return Users the persisted Users entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Users update(Users entity);

	public Users findById(Integer id);

	/**
	 * Find all Users entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Users property to query
	 * @param value
	 *            the property value to match
	 * @return List<Users> found by query
	 */
	public List<Users> findByProperty(String propertyName, Object value);

	public List<Users> findByUsername(Object username);

	public List<Users> findByPassword(Object password);

	public List<Users> findByEmail(Object email);

	public List<Users> findByGivenName(Object givenName);

	public List<Users> findByFamilyName(Object familyName);

	public List<Users> findByPhone(Object phone);

	public List<Users> findByFax(Object fax);

	/**
	 * Find all Users entities.
	 * 
	 * @return List<Users> all Users entities
	 */
	public List<Users> findAll();
}
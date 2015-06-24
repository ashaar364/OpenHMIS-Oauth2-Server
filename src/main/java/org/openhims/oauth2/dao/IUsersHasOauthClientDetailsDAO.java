package org.openhims.oauth2.dao;

import java.util.List;

import org.openhims.oauth2.domain.UsersHasOauthClientDetails;
import org.openhims.oauth2.domain.UsersHasOauthClientDetailsId;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Ashaar Riaz
 */
@Repository
public interface IUsersHasOauthClientDetailsDAO {
	/**
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUsersHasOauthClientDetailsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UsersHasOauthClientDetails entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UsersHasOauthClientDetails entity);

	/**
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUsersHasOauthClientDetailsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UsersHasOauthClientDetails entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UsersHasOauthClientDetails entity);

	/**
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUsersHasOauthClientDetailsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UsersHasOauthClientDetails entity to update
	 * @return UsersHasOauthClientDetails the persisted
	 *         UsersHasOauthClientDetails entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UsersHasOauthClientDetails update(UsersHasOauthClientDetails entity);

	public UsersHasOauthClientDetails findById(UsersHasOauthClientDetailsId id);

	/**
	 * Find all UsersHasOauthClientDetails entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the UsersHasOauthClientDetails property to query
	 * @param value
	 *            the property value to match
	 * @return List<UsersHasOauthClientDetails> found by query
	 */
	public List<UsersHasOauthClientDetails> findByProperty(String propertyName,
			Object value);

	public List<UsersHasOauthClientDetails> findByScope(Object scope);

	public List<UsersHasOauthClientDetails> findByStatus(Object status);

	/**
	 * Find all UsersHasOauthClientDetails entities.
	 * 
	 * @return List<UsersHasOauthClientDetails> all UsersHasOauthClientDetails
	 *         entities
	 */
	public List<UsersHasOauthClientDetails> findAll();
}
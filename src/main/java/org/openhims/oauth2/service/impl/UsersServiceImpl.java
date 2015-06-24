package org.openhims.oauth2.service.impl;

import java.util.List;

import org.openhims.oauth2.dao.IUsersDAO;
import org.openhims.oauth2.domain.Users;
import org.openhims.oauth2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
public class UsersServiceImpl implements UsersService, UserDetailsService 
{
	@Autowired
	private IUsersDAO userRepository;
	
	@Override
	public Users getUsersById(Integer userId) throws UsernameNotFoundException 
	{
		//return userRepository.findById(userId);
		return null;
	}

	@Override
	public Users getUserByName(String username) throws UsernameNotFoundException 
	{
		Users foundUsers = new Users();
		List<Users> usersList = userRepository.findByUsername(username);
		if ((usersList != null) && (!usersList.isEmpty()))
		{
			foundUsers =  usersList.get(0);
		}
		return foundUsers;
	}

	public Users getUserByEmail(String email) throws UsernameNotFoundException 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}

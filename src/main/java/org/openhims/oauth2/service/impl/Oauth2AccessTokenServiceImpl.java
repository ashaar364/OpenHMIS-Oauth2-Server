package org.openhims.oauth2.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.openhims.oauth2.dao.Oauth2AccessTokenRepository;
import org.openhims.oauth2.domain.OauthAccessToken;
import org.openhims.oauth2.service.Oauth2AccessTokenService;
import org.openhims.oauth2.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;

@Service
public class Oauth2AccessTokenServiceImpl implements Oauth2AccessTokenService
{
	@Resource
	private Oauth2AccessTokenRepository oauth2AccessTokenRepository;
	
	
	
	@Override
	public OauthAccessToken findByTokenId(String tokenId) 
	{
		return oauth2AccessTokenRepository.findByTokenId(tokenId);
	}

	@Override
	public OauthAccessToken findByRefreshToken(String refreshToken) 
	{
//		return oauth2AccessTokenRepository.findByRefreshToken(refreshToken);
		return null;
	}

	@Override
	public OauthAccessToken findByAuthenticationId(String authenticationId) 
	{
		return oauth2AccessTokenRepository.findByAuthenticationId(authenticationId);
	}

	/*public List<OauthAccessToken> findByClientIdAndUserName(
			String clientId, String userName) {
		return oauth2AccessTokenRepository.findByClientIdAndUserName(clientId, userName);
	}

	public List<OauthAccessToken> findByClientId(String clientId) {
		return oauth2AccessTokenRepository.findByClientId(clientId);
	}

	public List<OauthAccessToken> findByUserName(String userName) {
		return oauth2AccessTokenRepository.findByUserName(userName);
	}
*/

	@Override
	public void delete(String token) 
	{
		oauth2AccessTokenRepository.delete(token);
	}

	@Override
	public OAuth2AccessToken save(OauthAccessToken accessToken) 
	{
		return oauth2AccessTokenRepository.save(accessToken);
	}

	@Override
	public List<OauthAccessToken> findByClientIdAndUserName(String clientId,String userName)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OauthAccessToken> findByClientId(String clientId) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OauthAccessToken> findByUserName(String userName)
	{
		// TODO Auto-generated method stub
		return null;
	}

}

package org.openhims.oauth2.service.impl;

import javax.annotation.Resource;

import org.openhims.oauth2.dao.OAuth2RefreshTokenRepository;
import org.openhims.oauth2.domain.OauthRefreshToken;
import org.openhims.oauth2.service.Oauth2RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.stereotype.Service;

@Service
public class Oauth2RefreshTokenServiceImpl implements Oauth2RefreshTokenService 
{
	@Resource
	private OAuth2RefreshTokenRepository oAuth2RefreshTokenRepository;
	
	public OAuth2RefreshToken findByTokenId(String tokenId) 
	{
		return oAuth2RefreshTokenRepository.findByTokenId(tokenId);
	}


	public void delete(String tokenId) 
	{
		oAuth2RefreshTokenRepository.delete(tokenId);
	}

	public void delete(OAuth2RefreshToken token) {
	//	oAuth2RefreshTokenRepository.delete(token);
		
	}


	public OAuth2RefreshToken save(
			OauthRefreshToken oAuth2AuthenticationRefreshToken) {
		// TODO Auto-generated method stub
		return null;
	}
}

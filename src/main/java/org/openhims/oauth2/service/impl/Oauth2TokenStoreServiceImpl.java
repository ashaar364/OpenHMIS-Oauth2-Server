package org.openhims.oauth2.service.impl;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openhims.oauth2.domain.OauthAccessToken;
import org.openhims.oauth2.domain.OauthClientDetails;
import org.openhims.oauth2.domain.OauthRefreshToken;
import org.openhims.oauth2.domain.Users;
import org.openhims.oauth2.service.Oauth2AccessTokenService;
import org.openhims.oauth2.service.Oauth2RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;


@Service
public class Oauth2TokenStoreServiceImpl implements TokenStore 
{
	private static final Log LOG = LogFactory.getLog(Oauth2TokenStoreServiceImpl.class);
	
	@Autowired
	private Oauth2AccessTokenService oauth2AccessTokenService;
	
	@Autowired
	private Oauth2RefreshTokenService oauth2RefreshTokenService;
	
/*	@Autowired
	private UsersService usersService;*/
	
	private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();
	
	public Oauth2TokenStoreServiceImpl()
	{
		
	}

	public void setAuthenticationKeyGenerator(AuthenticationKeyGenerator authenticationKeyGenerator) 
	{
		this.authenticationKeyGenerator = authenticationKeyGenerator;
	}

	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) 
	{
		return readAuthentication(token.getValue());
	}

	public OAuth2Authentication readAuthentication(String token) 
	{
		OAuth2Authentication authentication = null;
		try {
			OauthAccessToken accessToken = oauth2AccessTokenService.findByTokenId(token);
			if (accessToken != null) {
				authentication = deserializeAuthentication(Base64.decodeBase64(accessToken.getAuthentication().getBytes()));
			} else {
				if (LOG.isInfoEnabled()) {
					LOG.info("Failed to find access token for token " + token);
				}
			}
		} catch (Exception e) {
			LOG.warn("Failed to read authentication for " + token, e);
			removeAccessToken(token);
		}
		return authentication;
	}

	
	

	public void storeAccessToken(OAuth2AccessToken token,OAuth2Authentication authentication)
//	public void storeAccessToken(OAuth2AccessToken token,Authentication authentication)
	{
		/*// Store access token
		OauthAccessToken accessToken = new OauthAccessToken(token);
		accessToken.setAuthentication(Base64.encodeBase64String(serializeAuthentication(authentication)));
		accessToken.setAuthentication(authenticationKeyGenerator.extractKey(authentication));
		accessToken.setUserName(authentication.isClientOnly() ? null : authentication);
		accessToken.setClientId(authentication.getAuthorizationRequest().getClientId());
		oauth2AccessTokenService.save(accessToken);*/
		
		// Attach access token to authentication
		/*CouchbaseOAuth2AuthenticationToAccessToken authAccessToken = new CouchbaseOAuth2AuthenticationToAccessToken();
		authAccessToken.setAuthenticationId(accessToken.getAuthenticationKey());
		authAccessToken.setAccessTokenId(token.getValue());
		oAuth2AuthenticationToAccessTokenService.save(authAccessToken);*/
		
		
		OauthAccessToken accessToken = new OauthAccessToken();
		accessToken.setToken(Base64.encodeBase64String(serializeAccessToken(accessToken)));
		accessToken.setAuthentication(Base64.encodeBase64String(serializeAuthentication(authentication)));
		accessToken.setAuthenticationId(authenticationKeyGenerator.extractKey(authentication));
//		String tokenId = UUID.randomUUID().toString();
		Timestamp now = new Timestamp(new Date().getTime());					// TO DO move this method into utility class
		accessToken.setEntryDate(now);
		accessToken.setLogDate(now);
		String name = authentication==null ? null : authentication.getName();
//		Users entryUsersId = usersService.getUserByName(name);
		Users entryUsersId = new Users();
		entryUsersId.setUsersId(new Integer(1));
		accessToken.setUsersByEntryUsersId(entryUsersId);
		accessToken.setUsersByLogUsersId(entryUsersId);
		accessToken.setUsersByUsersId(entryUsersId);
//		accessToken.setTokenId(tokenId);
		OauthClientDetails oauthClientDetails = new OauthClientDetails();
		oauthClientDetails.setClientId("7b5a38705d7b3562655925406a652e32");
		accessToken.setOauthClientDetails(oauthClientDetails);
		oauth2AccessTokenService.save(accessToken);
		
	}


	public OAuth2AccessToken readAccessToken(String tokenValue) 
	{
		OAuth2AccessToken accessToken = null;
		try {
			accessToken = oauth2AccessTokenService.findByTokenId(tokenValue);
			
			if (accessToken == null) {
				if (LOG.isInfoEnabled()) {
					LOG.info("Failed to find access token for token " + tokenValue);
				}
			}
		} catch (Exception e) {
			LOG.warn("Failed to read access token for " + tokenValue, e);
			removeAccessToken(tokenValue);
		}
		return accessToken;
	}


	public void removeAccessToken(OAuth2AccessToken token) 
	{
		oauth2AccessTokenService.delete(token.getValue());
	}
	public void removeAccessToken(String tokenValue)
	{
		oauth2AccessTokenService.delete(tokenValue);
	}

	public void storeRefreshToken(OAuth2RefreshToken refreshToken,OAuth2Authentication authentication) 
	{
		/*OauthRefreshToken oauth2RefreshToken = new OauthRefreshToken(refreshToken.getValue());
		oauth2RefreshToken.setAuthentication(Base64.encodeBase64String(serializeAuthentication(authentication)));
		
		OAuth2AccessToken accessToken = getAccessToken(authentication);
		if (accessToken != null) {
			oauth2RefreshToken.setTokenId(accessToken.getValue());
		} else {
			if (LOG.isInfoEnabled()) {
				LOG.info("Failed to find access token for authentication while storing refresh token " + authentication);
			}
		}
		oauth2RefreshTokenService.save(oauth2RefreshToken);*/
		
	}


	public OAuth2RefreshToken readRefreshToken(String tokenValue)
	{
		OAuth2RefreshToken refreshToken = null;
		try {
			refreshToken = oauth2RefreshTokenService.findByTokenId(tokenValue);
			if (refreshToken == null) {
				if (LOG.isInfoEnabled()) {
					LOG.info("Failed to find refresh token for token " + tokenValue);
				}
			}
		} catch (Exception e) {
			LOG.warn("Failed to read refresh token for token " + tokenValue, e);
			removeRefreshToken(tokenValue);
		}
		return refreshToken;
	}


	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) 
	{
		return readAuthenticationForRefreshToken(token.getValue());
	}
	
	public OAuth2Authentication readAuthenticationForRefreshToken(String value) 
	{
		OAuth2Authentication authentication = null;
		
		try {
			OauthRefreshToken refreshToken = (OauthRefreshToken) oauth2RefreshTokenService.findByTokenId(value);
			if (refreshToken != null) {
				authentication = deserializeAuthentication(Base64.decodeBase64(refreshToken.getAuthentication()));
			} else {
				if (LOG.isInfoEnabled()) {
					LOG.info("Failed to find refresh token for token " + value);
				}
			}
		} catch (Exception e) {
			LOG.warn("Failed to read authentication for refresh token for " + value, e);
			removeRefreshToken(value);
		}
		return authentication;
	}

	public void removeRefreshToken(OAuth2RefreshToken token) 
	{
		removeRefreshToken(token.getValue());
		
	}
	public void removeRefreshToken(String token) 
	{
		//oauth2RefreshTokenService.delete(token);
	}

	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) 
	{
		removeAccessTokenUsingRefreshToken(refreshToken.getValue());
	}
	public void removeAccessTokenUsingRefreshToken(String refreshTokenValue) 
	{
		/*OauthRefreshToken refreshToken = (OauthRefreshToken) oauth2RefreshTokenService.findByTokenId(refreshTokenValue);
		if (refreshToken != null) {
			oauth2RefreshTokenService.delete(refreshToken.getRefreshToken());
		} else {
			LOG.warn("Failed to read refresh token while removing access token using refresh token " + refreshTokenValue);
			removeRefreshToken(refreshTokenValue);
		}*/
	}

	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) 
	{
		/*OAuth2AccessToken accessToken = null;
		String key = authenticationKeyGenerator.extractKey(authentication);
		CouchbaseOAuth2AuthenticationToAccessToken auth = oAuth2AuthenticationToAccessTokenService.findOne(key);
		
		try {
			if (auth != null) {
				accessToken = oAuth2AccessTokenService.findOne(auth.getAccessTokenId());
			} else {
				if (LOG.isInfoEnabled()) {
					LOG.debug("Failed to find access token for authentication " + authentication);
				}
			}
		} catch (Exception e) {
			LOG.error("Could not get access token for authentication " + authentication, e);
		}
		
		if (accessToken != null
				&& !key.equals(authenticationKeyGenerator.extractKey(readAuthentication(accessToken.getValue())))) {
			removeAccessToken(accessToken.getValue());
			// Keep the store consistent (maybe the same user is represented by this authentication but the details have
			// changed)
			storeAccessToken(accessToken, authentication);
		}
		return accessToken;*/
		return null;
	}


	public Collection<OAuth2AccessToken> findTokensByUserName(String userName) 
	{
		// TODO Auto-generated method stub
		return null;
	}


	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	protected byte[] serializeAccessToken(OAuth2AccessToken token) {
		return SerializationUtils.serialize(token);
	}

	protected byte[] serializeRefreshToken(OAuth2RefreshToken token) {
		return SerializationUtils.serialize(token);
	}

	protected byte[] serializeAuthentication(OAuth2Authentication authentication) {
		return SerializationUtils.serialize(authentication);
	}

	protected OAuth2AccessToken deserializeAccessToken(byte[] token) {
		return SerializationUtils.deserialize(token);
	}

	protected OAuth2RefreshToken deserializeRefreshToken(byte[] token) {
		return SerializationUtils.deserialize(token);
	}

	protected OAuth2Authentication deserializeAuthentication(byte[] authentication) {
		return SerializationUtils.deserialize(authentication);
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(
			String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}

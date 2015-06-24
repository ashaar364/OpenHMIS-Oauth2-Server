package org.openhims.oauth2.service;

import java.util.List;

import org.openhims.oauth2.domain.OauthAccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;


public interface Oauth2AccessTokenService {
	
	public OauthAccessToken findByTokenId(String tokenId);
    public OauthAccessToken findByRefreshToken(String refreshToken);
    public OauthAccessToken findByAuthenticationId(String authenticationId);
    public List<OauthAccessToken> findByClientIdAndUserName(String clientId, String userName);
    public List<OauthAccessToken> findByClientId(String clientId);
    public List<OauthAccessToken> findByUserName(String userName);
    
	public void delete(String token);
	
	public OAuth2AccessToken save(OauthAccessToken accessToken);
	
}

package org.openhims.oauth2.service;

import org.openhims.oauth2.domain.OauthRefreshToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

public interface Oauth2RefreshTokenService 
{
	public OAuth2RefreshToken findByTokenId(String tokenId);
	public OAuth2RefreshToken save(OauthRefreshToken oAuth2AuthenticationRefreshToken);
//	public void delete(String tokenId);
//	public void delete(OAuth2RefreshToken token);
}

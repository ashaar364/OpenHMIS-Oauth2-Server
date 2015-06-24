package org.openhims.oauth2.spring.controller;

import java.util.Collection;

import org.openhims.oauth2.exception.InvalidClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Controller;

@Controller
public class PathwayTokenGranter implements TokenGranter 
{
	@Autowired
	private AuthorizationServerTokenServices tokenService;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Autowired
	private DefaultOAuth2RequestFactory defaultOauth2RequestFactory;

	private String grantType;

	@Override
	public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) 
	{
		if (!this.grantType.equals(grantType)) {
	        return null;
	    }
	    String clientId = tokenRequest.getClientId();
	    ClientDetails client = clientDetailsService.loadClientByClientId(clientId);
	    validateGrantType(grantType, client);
	    return getAccessToken(client, tokenRequest);
	}
	protected OAuth2AccessToken getAccessToken(ClientDetails client, TokenRequest tokenRequest) {
	    return tokenService.createAccessToken(getOAuth2Authentication(client, tokenRequest));
	}

	protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
	    OAuth2Request storedOAuth2Request = defaultOauth2RequestFactory.createOAuth2Request(client, tokenRequest);
	    return new OAuth2Authentication(storedOAuth2Request, null);
	}

	protected void validateGrantType(String grantType, ClientDetails clientDetails) {
	    Collection<String> authorizedGrantTypes = clientDetails.getAuthorizedGrantTypes();
	    if (authorizedGrantTypes != null && !authorizedGrantTypes.isEmpty()
	            && !authorizedGrantTypes.contains(grantType)) {
	        throw new InvalidClientException("Unauthorized grant type: " + grantType);
	    }
	}

	public String getGrantType() {
	    return grantType;
	}

	public void setGrantType(String grantType) {
	    this.grantType = grantType;
	}
}

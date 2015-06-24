package org.openhims.oauth2.spring.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openhims.oauth2.service.Oauth2AccessTokenService;
import org.openhims.oauth2.service.UsersService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.OAuth2RequestValidator;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathwayTokenController implements InitializingBean
{
	protected final Log logger = LogFactory.getLog(getClass());
	
	private WebResponseExceptionTranslator providerExceptionHandler = new DefaultWebResponseExceptionTranslator();
	
//	private TokenGranter tokenGranter;

	private OAuth2RequestFactory oAuth2RequestFactory;

	private OAuth2RequestFactory defaultOAuth2RequestFactory;
	
	private OAuth2RequestValidator oAuth2RequestValidator = new DefaultOAuth2RequestValidator();
	@Autowired
	private Oauth2AccessTokenService oauth2AccessTokenService;
	
	@Autowired
	private DefaultTokenServices tokenServices;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private ClientDetailsService clientDetailsService; 
	
	@Autowired
	private PathwayTokenGranter pathwayTokenGranter;

	private UsersService usersService;
	
	@RequestMapping(value="/oauth/token", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
//	@PermitAll
	public ResponseEntity<OAuth2AccessToken> getAccessToken(Principal principal, @RequestParam
			Map<String, String> parameters) throws HttpRequestMethodNotSupportedException
	{
		return postAccessToken(principal, parameters);
		/*try
		{
			OAuth2Authentication requestingUser = (OAuth2Authentication) securityContext.getAuthentication();
			Object principal = requestingUser.getUserAuthentication().getPrincipal();
			String username = authentication.getPrincipal()
					
					User user = null;
	        if(principal instanceof User) {
	            user = (User)principal;
	        } else {
	            user = userRepository.findById((String)principal);
	        }		
					
			usersService.getUserByName(username);
			OAuth2AccessToken oauth2AccessToken = oauth2AccessTokenService.save(accessToken);
			UsersVO usersVO = new UsersVO();
			TokenResponse tokenResponse = new TokenResponse(usersVO, oauth2AccessToken);
			URI location = uriInfo.getAbsolutePathBuilder().path(tokenResponse.getUsersVO().getUsersId()).build();
			return Response.created(location).entity(tokenResponse).build();
		}
		catch(Exception e)
		{
			
		}*/
	}
	
/*	
	@RequestMapping(value = "/oauth/token", method=RequestMethod.POST)
	public ResponseEntity<OAuth2AccessToken> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException 
	{
		if (!(principal instanceof Authentication)) 
		{
			throw new InsufficientAuthenticationException("There is no client authentication. Try adding an appropriate authentication filter.");
		}
		String clientId = getClientId(principal);
		ClientDetails authenticatedClient = clientDetailsService.loadClientByClientId(clientId);
		
		return null;
	}*/
	
	
	@RequestMapping(value = "/oauth/token", method=RequestMethod.POST)
	public ResponseEntity<OAuth2AccessToken> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException 
	{
		/*if (!(principal instanceof Authentication)) 
		{
			throw new InsufficientAuthenticationException("There is no client authentication. Try adding an appropriate authentication filter.");
		}*/
	//	String clientId = getClientId(principal);
		String clientId = "7b5a38705d7b3562655925406a652e32";
		ClientDetails authenticatedClient = clientDetailsService.loadClientByClientId(clientId);
		
		TokenRequest tokenRequest = getOAuth2RequestFactory().createTokenRequest(parameters, authenticatedClient);
		if (clientId != null && !clientId.equals("")) {
			// Only validate the client details if a client authenticated during this
			// request.
			if (!clientId.equals(tokenRequest.getClientId())) {
				// double check to make sure that the client ID in the token request is the same as that in the
				// authenticated client
				throw new InvalidClientException("Given client ID does not match authenticated client");
			}
		}
		if (authenticatedClient != null) 
		{
			oAuth2RequestValidator.validateScope(tokenRequest, authenticatedClient);
		}
		if (!StringUtils.hasText(tokenRequest.getGrantType())) {
			throw new InvalidRequestException("Missing grant type");
		}
		if (tokenRequest.getGrantType().equals("implicit")) {
			throw new InvalidGrantException("Implicit grant type not supported from token endpoint");
		}

		if (isAuthCodeRequest(parameters)) {
			// The scope was requested or determined during the authorization step
			if (!tokenRequest.getScope().isEmpty()) {
				logger.debug("Clearing scope of incoming token request");
				tokenRequest.setScope(Collections.<String> emptySet());
			}
		}
		if (isRefreshTokenRequest(parameters)) {
			// A refresh token has its own default scopes, so we should ignore any added by the factory here.
			tokenRequest.setScope(OAuth2Utils.parseParameterList(parameters.get(OAuth2Utils.SCOPE)));
		}
	//	OAuth2AccessToken token = this.pathwayTokenGranter.grant(tokenRequest.getGrantType(), tokenRequest);
		OAuth2AccessToken token = pathwayTokenGranter.getAccessToken(authenticatedClient, tokenRequest);
		if (token == null) {
			throw new UnsupportedGrantTypeException("Unsupported grant type: " + tokenRequest.getGrantType());
		}
		return getResponse(token);
	}

	protected String getClientId(Principal principal)
	{
		Authentication client = (Authentication)principal;
		if (!client.isAuthenticated()) 
		{
			throw new InsufficientAuthenticationException("The client is not authenticated.");
		}
		String clientId = client.getName();
		if (client instanceof OAuth2Authentication) 
		{
			clientId = ((OAuth2Authentication) client).getOAuth2Request().getClientId();
		}
		return clientId;
	}
	private boolean isAuthCodeRequest(Map<String, String> parameters) {
		return "authorization_code".equals(parameters.get("grant_type")) && parameters.get("code") != null;
	}
	private boolean isRefreshTokenRequest(Map<String, String> parameters) {
		return "refresh_token".equals(parameters.get("grant_type")) && parameters.get("refresh_token") != null;
	}
	@SuppressWarnings("unused")
	private ResponseEntity<OAuth2AccessToken> getResponse(OAuth2AccessToken accessToken) 
	{
		HttpHeaders headers = new HttpHeaders();
		headers.set("Cache-Control", "no-store");
		headers.set("Pragma", "no-cache");
		return new ResponseEntity<OAuth2AccessToken>(accessToken, headers, HttpStatus.OK);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.state(pathwayTokenGranter != null, "TokenGranter must be provided");
		Assert.state(clientDetailsService != null, "ClientDetailsService must be provided");
		defaultOAuth2RequestFactory = new DefaultOAuth2RequestFactory(getClientDetailsService());
		if (oAuth2RequestFactory == null) {
			oAuth2RequestFactory = defaultOAuth2RequestFactory;
		}
		
	}
	
	public void setProviderExceptionHandler(WebResponseExceptionTranslator providerExceptionHandler) {
		this.providerExceptionHandler = providerExceptionHandler;
	}

	protected WebResponseExceptionTranslator getExceptionTranslator() {
		return providerExceptionHandler;
	}

	protected PathwayTokenGranter getPathwayTokenGranter() {
		return this.pathwayTokenGranter;
	}

	protected void setPathwayTokenGranter(PathwayTokenGranter pathwayTokenGranter) {
		this.pathwayTokenGranter = pathwayTokenGranter;
	}

	protected OAuth2RequestFactory getOAuth2RequestFactory() {
		return oAuth2RequestFactory;
	}

	protected OAuth2RequestFactory getDefaultOAuth2RequestFactory() {
		return defaultOAuth2RequestFactory;
	}

	public void setOAuth2RequestFactory(OAuth2RequestFactory oAuth2RequestFactory) {
		this.oAuth2RequestFactory = oAuth2RequestFactory;
	}

	protected ClientDetailsService getClientDetailsService() {
		return clientDetailsService;
	}

	public void setClientDetailsService(ClientDetailsService clientDetailsService) {
		this.clientDetailsService = clientDetailsService;
	}
}

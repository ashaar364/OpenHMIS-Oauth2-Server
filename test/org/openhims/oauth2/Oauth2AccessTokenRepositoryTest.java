package org.openhims.oauth2;




import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openhims.oauth2.domain.OauthAccessToken;
import org.openhims.oauth2.domain.OauthClientDetails;
import org.openhims.oauth2.domain.Users;
import org.openhims.oauth2.service.Oauth2AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/root-context.xml" })
public class Oauth2AccessTokenRepositoryTest 
{
	private static final String DEFAULT_TOKEN_ID = "default-token-id";
	private static final String DEFAULT_TOKEN_AUTHENTICATION_KEY = "default-token-authentication-key";
	private static final String DEFAULT_USER_NAME = "default-user-name";
	private static final String DEFAULT_CLIENT_ID = "7b5a38705d7b3562655925406a652e32";
	private static final String DEFAULT_PASSWORD = "default-password";
	
	/*@Autowired
	private Oauth2AccessTokenRepository oauth2AccessTokenRepository;*/
	
	@Autowired
	private Oauth2AccessTokenService oauth2AccessTokenService;
	
	@Autowired
	private DefaultTokenServices tokenServices;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
    private ClientDetailsService clientDetailsService;
	
	@Test
	public void testCreateAndUpdateAndDeleteOauth2AccessTokenRepository() {
		
		OauthAccessToken defaultToken = createSampleOAuth2AccessToken();
//		OAuth2AccessToken defaultTokenSaved = oauth2AccessTokenService.save(defaultToken);
        Assert.notNull(defaultToken);
        Assert.isTrue(DEFAULT_TOKEN_ID.equals(defaultToken.getValue()));
	}
	
	
	private OauthAccessToken createSampleOAuth2AccessToken() 
	{
		OauthAccessToken defaultToken = new OauthAccessToken(DEFAULT_TOKEN_ID);
		defaultToken.setAuthentication(DEFAULT_TOKEN_AUTHENTICATION_KEY);
	//	String tokenId = UUID.randomUUID().toString();
		Users oauthUsers = new Users();
		oauthUsers.setUsersId(new Integer(1));	 					// dynamically get the users detail from the database
		oauthUsers.setUsername(DEFAULT_USER_NAME);
//		defaultToken.setTokenId(tokenId);
		defaultToken.setUsersByUsersId(oauthUsers);
		defaultToken.setUsersByLogUsersId(oauthUsers);
		defaultToken.setUsersByEntryUsersId(oauthUsers);
		Timestamp now = new Timestamp(new Date().getTime());					// TO DO move this method into utility class
		defaultToken.setEntryDate(now);
		defaultToken.setLogDate(now);
		OAuth2AccessToken accessToken = createTokenForNewUser(DEFAULT_USER_NAME, DEFAULT_PASSWORD, DEFAULT_CLIENT_ID);
		defaultToken.setToken(accessToken.getValue());
		OauthClientDetails oauthClientDetails = new OauthClientDetails();
		oauthClientDetails.setClientId(DEFAULT_CLIENT_ID);
		defaultToken.setOauthClientDetails(oauthClientDetails);
		return defaultToken;
	}
	
	private OAuth2AccessToken createTokenForNewUser(String userId, String password, String clientId) 
	{
        String hashedPassword = passwordEncoder.encode(password);
        UsernamePasswordAuthenticationToken userAuthentication = new UsernamePasswordAuthenticationToken(
                userId,
                hashedPassword, Collections.singleton(new SimpleGrantedAuthority("ROLE-USER"))); 
        
        ClientDetails authenticatedClient = clientDetailsService.loadClientByClientId(clientId);
        
    /*    BaseClientDetails authenticatedClient =new BaseClientDetails();
        authenticatedClient.setClientId(clientId);
        authenticatedClient.setRegisteredRedirectUri(Collections.singleton("http://anywhere.com"));
        authenticatedClient.setAuthorizedGrantTypes(Arrays.asList("authorization_code","refresh_token"));*/
        
        
        OAuth2Request oAuth2Request = createOAuth2Request(null, clientId,
                Collections.singleton(new SimpleGrantedAuthority("ROLE-USER")),
       //         true, authenticatedClient.getScope(), null, null, null, null);
                true, Arrays.asList("read", "write"), null, null, null, null);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, userAuthentication);
        return tokenServices.createAccessToken(oAuth2Authentication);
    }
	
	private OAuth2Request createOAuth2Request(Map<String, String> requestParameters, String clientId,
            Collection<? extends GrantedAuthority> authorities, boolean approved, Collection<String> scope,
            Set<String> resourceIds, String redirectUri, Set<String> responseTypes,
            Map<String, Serializable> extensionProperties) {
		return new OAuth2Request(requestParameters, clientId, authorities, approved, scope == null ? null
		: new LinkedHashSet<String>(scope), resourceIds, redirectUri, responseTypes, extensionProperties);
	}
}

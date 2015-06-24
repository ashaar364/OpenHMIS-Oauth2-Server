package org.openhims.oauth2;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openhims.oauth2.service.impl.Oauth2TokenStoreServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/root-context.xml" })
public class Oauth2TokenStoreServiceImplTest 
{
	private Oauth2TokenStoreServiceImpl oauth2AccesTokenService;
	private Map<String, String> parameters;
	@Before
	public void prepare() {
		parameters = new HashMap<String, String>();
		parameters.put("client_id", "theClient");
		parameters.put("scope", "read");
	}
/*	@Test
	public void testSaveAndRetrieveToken() throws Exception 
	{
		OAuth2AccessToken accessToken = new DefaultOAuth2AccessToken("FOO");
		Authentication authentication = new UsernamePasswordAuthenticationToken("ashaar", "password");
//		AbstractAuthenticationToken userAuthenticationToken = authUserByToken(accessToken);
	//	AuthorizationRequest authorizationRequest = (AuthorizationRequest) new TestingAuthenticationToken("ashaar", "password");
		
		OAuth2Request authorizationRequest = createFromParameters(parameters);
		
		OAuth2Authentication auth2Authentication = new OAuth2Authentication((AuthorizationRequest)authorizationRequest, authentication);
	//	Authentication authentication = new UsernamePasswordAuthenticationToken("marissa", "koala");
//		AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
//		resource.setClientId("client");
//		resource.setScope(Arrays.asList("foo", "bar"));
	
		oauth2AccesTokenService.storeAccessToken(accessToken,auth2Authentication);
//		OAuth2AccessToken result = oauth2AccesTokenService.getAccessToken(resource, authentication);
//		assertEquals(accessToken, result);
	}
	private OAuth2Request createFromParameters(Map<String, String> parameters) {
		OAuth2Request request = RequestTokenFactory.createOAuth2Request(parameters,
				parameters.get("client_id"), false,
				OAuth2Utils.parseParameterList(parameters.get("scope")));
		return request;
	}*/
}

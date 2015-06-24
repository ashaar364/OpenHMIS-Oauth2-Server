package org.openhims.oauth2.util;

import javax.xml.bind.annotation.XmlRootElement;

import org.openhims.oauth2.model.UsersVO;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@XmlRootElement
public class TokenResponse 
{
	private UsersVO usersVO;
	private OAuth2AccessToken oauth2AccessToken;
	public TokenResponse() {
		super();
	}
	public TokenResponse(UsersVO usersVO, OAuth2AccessToken oauth2AccessToken) {
		super();
		this.usersVO = usersVO;
		this.oauth2AccessToken = oauth2AccessToken;
	}
	public UsersVO getUsersVO() {
		return this.usersVO;
	}
	public void setUsersVO(UsersVO usersVO) {
		this.usersVO = usersVO;
	}
	public OAuth2AccessToken getOauth2AccessToken() {
		return this.oauth2AccessToken;
	}
	public void setOauth2AccessToken(OAuth2AccessToken oauth2AccessToken) {
		this.oauth2AccessToken = oauth2AccessToken;
	}
}

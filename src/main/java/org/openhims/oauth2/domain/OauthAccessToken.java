package org.openhims.oauth2.domain;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * @author Ashaar Riaz
 */
@Entity
@Table(name = "oauth_access_token")

public class OauthAccessToken extends DefaultOAuth2AccessToken {

	
	
	@SuppressWarnings("unused")
	public OauthAccessToken() {
		this((String) null);
	}
	public OauthAccessToken(OAuth2AccessToken accessToken) 
	{
		super(accessToken);
	}

	public OauthAccessToken(String token) 
	{
		super(token);
	}
	// Fields
	private String tokenId;
	private OauthClientDetails oauthClientDetails;
	private Users usersByLogUsersId;
	private Users usersByEntryUsersId;
	private Users usersByUsersId;
	private String token;
	private String authentication;
	private String authenticationId;
	private Timestamp entryDate;
	private Timestamp logDate;


	// Property accessors
	@Id
	@Column(name = "token_id", unique = true, nullable = false)
//	@GeneratedValue(generator = "uuid-string")
//	@GenericGenerator(name = "uuid", strategy = "uuid2")
	public String getTokenId() {
		//return this.tokenId;
		return UUID.randomUUID().toString();
	}
	
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = false)
	public OauthClientDetails getOauthClientDetails() {
		return this.oauthClientDetails;
	}

	public void setOauthClientDetails(OauthClientDetails oauthClientDetails) {
		this.oauthClientDetails = oauthClientDetails;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "log_users_id", nullable = false)
	public Users getUsersByLogUsersId() {
		return this.usersByLogUsersId;
	}

	public void setUsersByLogUsersId(Users usersByLogUsersId) {
		this.usersByLogUsersId = usersByLogUsersId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entry_users_id", nullable = false)
	public Users getUsersByEntryUsersId() {
		return this.usersByEntryUsersId;
	}

	public void setUsersByEntryUsersId(Users usersByEntryUsersId) {
		this.usersByEntryUsersId = usersByEntryUsersId;
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "users_id", nullable = false)
	public Users getUsersByUsersId() {
		return this.usersByUsersId;
	}

	public void setUsersByUsersId(Users usersByUsersId) {
		this.usersByUsersId = usersByUsersId;
	}

	@Column(name = "token")
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "authentication")
	public String getAuthentication() {
		return this.authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	@Column(name = "authenticationId")
	public String getAuthenticationId() {
		return this.authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	@Column(name = "entry_date", length = 19)
	public Timestamp getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
	}

	@Column(name = "log_date", length = 19)
	public Timestamp getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Timestamp logDate) {
		this.logDate = logDate;
	}

}
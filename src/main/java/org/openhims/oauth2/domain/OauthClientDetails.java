package org.openhims.oauth2.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * OauthClientDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "oauth_client_details", catalog = "mydb")
public class OauthClientDetails implements java.io.Serializable {

	// Fields

	private String clientId;
	private Users usersByLogUsersId;
	private Users usersByEntryUsersId;
	private String resourcesId;
	private String clientSecret;
	private String scope;
	private String authorizedGrantsTypes;
	private String webServerRedirectUri;
	private String authorities;
	private Integer accesTokenValidity;
	private Integer refreshTokenValidity;
	private String additionalInformation;
	private Timestamp entryDate;
	private Timestamp logDate;
	private Set<OauthAccessToken> oauthAccessTokens = new HashSet<OauthAccessToken>(
			0);
	private Set<OauthRefreshToken> oauthRefreshTokens = new HashSet<OauthRefreshToken>(
			0);
	private Set<UsersHasOauthClientDetails> usersHasOauthClientDetailses = new HashSet<UsersHasOauthClientDetails>(
			0);

	// Constructors

	/** default constructor */
	public OauthClientDetails() {
	}

	/** minimal constructor */
	public OauthClientDetails(String clientId, Users usersByLogUsersId,
			Users usersByEntryUsersId, Timestamp entryDate, Timestamp logDate) {
		this.clientId = clientId;
		this.usersByLogUsersId = usersByLogUsersId;
		this.usersByEntryUsersId = usersByEntryUsersId;
		this.entryDate = entryDate;
		this.logDate = logDate;
	}

	/** full constructor */
	public OauthClientDetails(String clientId, Users usersByLogUsersId,
			Users usersByEntryUsersId, String resourcesId, String clientSecret,
			String scope, String authorizedGrantsTypes,
			String webServerRedirectUri, String authorities,
			Integer accesTokenValidity, Integer refreshTokenValidity,
			String additionalInformation, Timestamp entryDate,
			Timestamp logDate, Set<OauthAccessToken> oauthAccessTokens,
			Set<OauthRefreshToken> oauthRefreshTokens,
			Set<UsersHasOauthClientDetails> usersHasOauthClientDetailses) {
		this.clientId = clientId;
		this.usersByLogUsersId = usersByLogUsersId;
		this.usersByEntryUsersId = usersByEntryUsersId;
		this.resourcesId = resourcesId;
		this.clientSecret = clientSecret;
		this.scope = scope;
		this.authorizedGrantsTypes = authorizedGrantsTypes;
		this.webServerRedirectUri = webServerRedirectUri;
		this.authorities = authorities;
		this.accesTokenValidity = accesTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		this.additionalInformation = additionalInformation;
		this.entryDate = entryDate;
		this.logDate = logDate;
		this.oauthAccessTokens = oauthAccessTokens;
		this.oauthRefreshTokens = oauthRefreshTokens;
		this.usersHasOauthClientDetailses = usersHasOauthClientDetailses;
	}

	// Property accessors
	@Id
	@Column(name = "client_id", unique = true, nullable = false)
	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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

	@Column(name = "resources_id", length = 1024)
	public String getResourcesId() {
		return this.resourcesId;
	}

	public void setResourcesId(String resourcesId) {
		this.resourcesId = resourcesId;
	}

	@Column(name = "client_secret")
	public String getClientSecret() {
		return this.clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	@Column(name = "scope", length = 1024)
	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Column(name = "authorized_grants_types")
	public String getAuthorizedGrantsTypes() {
		return this.authorizedGrantsTypes;
	}

	public void setAuthorizedGrantsTypes(String authorizedGrantsTypes) {
		this.authorizedGrantsTypes = authorizedGrantsTypes;
	}

	@Column(name = "web_server_redirect_uri", length = 1024)
	public String getWebServerRedirectUri() {
		return this.webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	@Column(name = "authorities", length = 1024)
	public String getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	@Column(name = "acces_token_validity")
	public Integer getAccesTokenValidity() {
		return this.accesTokenValidity;
	}

	public void setAccesTokenValidity(Integer accesTokenValidity) {
		this.accesTokenValidity = accesTokenValidity;
	}

	@Column(name = "refresh_token_validity")
	public Integer getRefreshTokenValidity() {
		return this.refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	@Column(name = "additional_information", length = 4096)
	public String getAdditionalInformation() {
		return this.additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	@Column(name = "entry_date", nullable = false, length = 19)
	public Timestamp getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
	}

	@Column(name = "log_date", nullable = false, length = 19)
	public Timestamp getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Timestamp logDate) {
		this.logDate = logDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "oauthClientDetails")
	public Set<OauthAccessToken> getOauthAccessTokens() {
		return this.oauthAccessTokens;
	}

	public void setOauthAccessTokens(Set<OauthAccessToken> oauthAccessTokens) {
		this.oauthAccessTokens = oauthAccessTokens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "oauthClientDetails")
	public Set<OauthRefreshToken> getOauthRefreshTokens() {
		return this.oauthRefreshTokens;
	}

	public void setOauthRefreshTokens(Set<OauthRefreshToken> oauthRefreshTokens) {
		this.oauthRefreshTokens = oauthRefreshTokens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "oauthClientDetails")
	public Set<UsersHasOauthClientDetails> getUsersHasOauthClientDetailses() {
		return this.usersHasOauthClientDetailses;
	}

	public void setUsersHasOauthClientDetailses(
			Set<UsersHasOauthClientDetails> usersHasOauthClientDetailses) {
		this.usersHasOauthClientDetailses = usersHasOauthClientDetailses;
	}

}
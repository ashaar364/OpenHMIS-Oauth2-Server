package org.openhims.oauth2.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OauthRefreshToken entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "oauth_refresh_token", catalog = "mydb")
public class OauthRefreshToken implements java.io.Serializable {

	// Fields

	private String tokenId;
	private OauthClientDetails oauthClientDetails;
	private Users usersByLogUsersId;
	private Users usersByEntryUsersId;
	private String token;
	private String authentication;
	private Timestamp entryDate;
	private Timestamp logDate;

	// Constructors

	/** default constructor */
	public OauthRefreshToken() {
	}

	/** minimal constructor */
	public OauthRefreshToken(String tokenId,
			OauthClientDetails oauthClientDetails, Users usersByLogUsersId,
			Users usersByEntryUsersId) {
		this.tokenId = tokenId;
		this.oauthClientDetails = oauthClientDetails;
		this.usersByLogUsersId = usersByLogUsersId;
		this.usersByEntryUsersId = usersByEntryUsersId;
	}

	/** full constructor */
	public OauthRefreshToken(String tokenId,
			OauthClientDetails oauthClientDetails, Users usersByLogUsersId,
			Users usersByEntryUsersId, String token, String authentication,
			Timestamp entryDate, Timestamp logDate) {
		this.tokenId = tokenId;
		this.oauthClientDetails = oauthClientDetails;
		this.usersByLogUsersId = usersByLogUsersId;
		this.usersByEntryUsersId = usersByEntryUsersId;
		this.token = token;
		this.authentication = authentication;
		this.entryDate = entryDate;
		this.logDate = logDate;
	}

	// Property accessors
	@Id
	@Column(name = "token_id", unique = true, nullable = false)
	public String getTokenId() {
		return this.tokenId;
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
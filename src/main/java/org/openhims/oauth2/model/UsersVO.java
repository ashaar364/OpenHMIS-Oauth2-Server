package org.openhims.oauth2.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsersVO implements Serializable 
{
	private Integer usersId;
	private String username;
//	@Email  will add them later
//	@NotNull
	private String email;
	private String givenName;
	private String familyName;
	public UsersVO() {
		super();
	}
	
	public UsersVO(Integer usersId, String username, String email,
			String givenName, String familyName) {
		super();
		this.usersId = usersId;
		this.username = username;
		this.email = email;
		this.givenName = givenName;
		this.familyName = familyName;
	}

	public Integer getUsersId() {
		return this.usersId;
	}
	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGivenName() {
		return this.givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return this.familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	
	
}

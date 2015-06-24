package org.openhims.oauth2.dao;

import org.openhims.oauth2.domain.OauthRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.stereotype.Repository;


public interface OAuth2RefreshTokenRepository extends JpaRepository<OauthRefreshToken,String>
{
	public OAuth2RefreshToken findByTokenId(String tokenId);
//	public void delete(OAuth2RefreshToken token);
}

package org.openhims.oauth2.dao;

import org.openhims.oauth2.domain.OauthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface Oauth2AccessTokenRepository extends JpaRepository<OauthAccessToken,String>
{
 	public OauthAccessToken findByTokenId(String tokenId);
//    public OauthAccessToken findByRefreshToken(String refreshToken);
    public OauthAccessToken findByAuthenticationId(String authenticationId);
   /* public List<OauthAccessToken> findByClientIdAndUserName(String clientId, String userName);
    public List<OauthAccessToken> findByClientId(String clientId);
    public List<OauthAccessToken> findByUserName(String userName);*/
} 

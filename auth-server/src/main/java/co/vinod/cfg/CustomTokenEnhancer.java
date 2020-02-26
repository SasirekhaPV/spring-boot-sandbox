package co.vinod.cfg;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import co.vinod.entity.CustomUser;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		CustomUser user = (CustomUser) authentication.getPrincipal();

		Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());

		if (user.getId() != null)
			info.put("id", user.getId());
		if (user.getFirstName() != null)
			info.put("firstName", user.getFirstName());
		if (user.getLastName() != null)
			info.put("lastName", user.getLastName());
		if (user.getCountry() != null)
			info.put("country", user.getCountry());
		if (user.getMobile() != null)
			info.put("mobile", user.getMobile());
		if (user.getUserType() != null)
			info.put("userType", user.getUserType());

		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(info);

		return super.enhance(customAccessToken, authentication);

	}
}

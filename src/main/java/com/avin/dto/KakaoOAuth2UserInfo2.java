package com.avin.dto;

import java.util.Map;
import java.util.Optional;

public class KakaoOAuth2UserInfo2 extends OAuth2UserDTO {

    public KakaoOAuth2UserInfo2(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getName() {
    	Map<String, Object> properties = (Map) attributes.get("properties");
    	return properties == null ? null : properties.get("nickname").toString();
    }

    @Override
    public String getEmail() {
    	Map<String, Object> account = (Map) attributes.get("kakao_account");
    	return account == null ? null : account.get("email").toString();
    }

    @Override
    public String getImageUrl() {
    	String url = Optional.ofNullable((Map) attributes.get("properties"))
    		.map((map) -> {
    			return map.get("profile_image");
    		}).orElse("").toString();
    	return url.isEmpty() ? null : url;
    }
}


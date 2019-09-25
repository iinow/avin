package com.avin.security;

import java.util.Map;

import com.avin.common.util.Constants.PROVIDER;
import com.avin.dto.FacebookOAuth2UserInfo;
import com.avin.dto.GithubOAuth2UserInfo;
import com.avin.dto.GoogleOAuth2UserInfo;
import com.avin.dto.KakaoOAuth2UserInfo2;
import com.avin.dto.OAuth2UserDTO;
import com.avin.exception.OAuth2AuthenticationProcessingException;

public class OAuth2UserInfoFactory {
	public static OAuth2UserDTO getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(PROVIDER.GOOGLE.toString().toLowerCase())) {
            return new GoogleOAuth2UserInfo(attributes);
        }else if (registrationId.equalsIgnoreCase(PROVIDER.FACEBOOK.toString().toLowerCase())) {
            return new FacebookOAuth2UserInfo(attributes);
        }else if (registrationId.equalsIgnoreCase(PROVIDER.GITHUB.toString().toLowerCase())) {
            return new GithubOAuth2UserInfo(attributes);
        }else if(registrationId.equalsIgnoreCase(PROVIDER.KAKAO.toString().toLowerCase())) {
        	return new KakaoOAuth2UserInfo2(attributes);
        }else {
        	throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}

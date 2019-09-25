package com.avin.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
	private final OAuth2 oauth2 = new OAuth2();
	private final Auth auth = new Auth();
	
	@Getter
	@Setter
	public static class Auth {
        private String tokenSecret;
        private long tokenExpirationMsec;
	}
	
	@Getter
	@Setter
	public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();
    }
}

package com.avin.dto;

import java.util.Map;

public class GithubOAuth2UserInfo extends OAuth2UserDTO {

    public GithubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return ((Integer) attributes.get("id")).toString();
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("login") + "@users.noreply.github.com";
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("avatar_url");
    }
}

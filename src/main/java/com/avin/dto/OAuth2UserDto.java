package com.avin.dto;

import java.util.Map;

public abstract class OAuth2UserDto {
	protected Map<String, Object> attributes;

    public OAuth2UserDto(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}

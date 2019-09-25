package com.avin.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.greenrobot.eventbus.EventBus;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BeanFactory {

    public @Bean Cache<String, Object> newCache(){
        return Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build();
    }
    
    @Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}

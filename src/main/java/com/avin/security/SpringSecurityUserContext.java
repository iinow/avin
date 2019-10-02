package com.avin.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityUserContext {

//	@Override
//	public CalendarUser getCurrentUser() {
//		SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        if (authentication == null) {
//            return null;
//        }
//        return (CalendarUser) authentication.getPrincipal();
//	}
//
//	@Override
//	public void setCurrentUser(CalendarUser user) {
//		if (user == null) {
//            throw new IllegalArgumentException("user cannot be null");
//        }
//        Collection<? extends GrantedAuthority> authorities = CalendarUserAuthorityUtils.createAuthorities(user);
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//	}
}

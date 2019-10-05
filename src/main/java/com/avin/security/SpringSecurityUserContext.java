package com.avin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.avin.api.service.UserService;
import com.avin.entity.User;

@Component
public class SpringSecurityUserContext {
	
	@Autowired
	private UserService userService;

	public UserPrincipal getCurrentUserPrincipal() {
		SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (UserPrincipal) authentication.getPrincipal();
	}
	
	public User getCurrentUser() {
		UserPrincipal prin = getCurrentUserPrincipal();
		if(prin == null) {
			return null;
		}
		return userService.getUser(prin.getId()).orElseThrow(() -> new UsernameNotFoundException(String.valueOf(prin.getId())));
	}

	public void setCurrentUser(UserPrincipal user) {
		if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
//        Collection<? extends GrantedAuthority> authorities = CalendarUserAuthorityUtils.createAuthorities(user);
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}

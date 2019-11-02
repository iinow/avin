package com.avin.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.avin.exception.handler.FilterExceptionHandler;
import com.avin.security.LocalUserDetailsService;
import com.avin.security.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BISHOP
 * @since 2019.10.29
 * 
 * doFilterInternal 부분 {https://stackoverflow.com/questions/17715921/exception-handling-for-filter-in-spring}
 * filter chain 전체를 검사한 후 MVC 로 넘어오게 되서 AOP 읽기 힘듦
 * 
 * */
@Slf4j
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private LocalUserDetailsService localUserDetailsService;

    @Autowired
    private FilterExceptionHandler exceptionController;
    
    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    private ObjectMapper mapper = new ObjectMapper();
    
    /**
     * stateless 를 사용할 경우 SecurityContextHolder.getContext().setAuthentication 을 삭제해줘야한다. 
     * */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                Long userId = tokenProvider.getUserIdFromToken(jwt);

                UserDetails userDetails = localUserDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
        	//로그는 StandardWrapperValve 여기서 찍기 때문에 내용을 찍을 필요 없음..
//            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        	response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(mapper.writeValueAsString(exceptionController.handleJwtException(request, ex).getBody()));
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}

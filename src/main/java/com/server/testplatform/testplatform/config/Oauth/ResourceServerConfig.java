package com.server.testplatform.testplatform.config.Oauth;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    // нельзя передавать пустой список
    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/swagger-resources",
           // "/swagger-resources/**",
          //  "/configuration/ui",
          //  "/configuration/security",
         //   "/swagger-ui.html",
         //   "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
          //  "/v3/api-docs/**",
          //  "/swagger-ui/**",
           // "/swagger-ui"
            // other public endpoints of your API may be appended to this array
    };


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
              //  http.cors()
               //         .disable().csrf().disable().
        http.
                anonymous().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());


    }






}

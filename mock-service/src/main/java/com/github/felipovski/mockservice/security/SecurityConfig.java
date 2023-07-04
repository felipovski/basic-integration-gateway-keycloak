package com.github.felipovski.mockservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt-uri}")
    private String jwtUri;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/api").hasRole("tester")
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2Configurer ->
                        oauth2Configurer.jwt(jwtConfigurer ->
                                jwtConfigurer.jwtAuthenticationConverter(jwt -> {
                                            Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
                                            var roles = realmAccess.get("roles");
                                            var grantedAuthorities = roles.stream()
                                                    .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority))
                                                    .toList();
                                            return new JwtAuthenticationToken(jwt, grantedAuthorities);
                                        }
                                )
                        ));
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwtUri).build();
    }

}

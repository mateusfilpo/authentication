package br.com.mateusfilpo.authentication.config;

import br.com.mateusfilpo.authentication.filter.AuthorizationHeaderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.formLogin(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(request -> request.requestMatchers("/foo-bar").permitAll())
                .addFilterBefore(new AuthorizationHeaderFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(request -> request.anyRequest().permitAll());

        return http.build();
    }
}

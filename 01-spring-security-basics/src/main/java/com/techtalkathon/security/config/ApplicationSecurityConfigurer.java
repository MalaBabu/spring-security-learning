package com.techtalkathon.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Configuration
@Slf4j
public class ApplicationSecurityConfigurer {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
				auth -> auth.requestMatchers("/security-basic/welcome").permitAll()
				.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults());
		return http.build();

	}

	/* permits the mentioned endpoint without authontication */
	// OR ==> can be used as  "/**/welcome" OR "/welcome"
	/* ==> Any other requests must be Authenticates */
	// .httpBasic(Customizer.withDefaults()) ==> to enable basic auth / adds BasicAuthenticationFilter
	// .formLogin(Customizer.withDefaults()); ==> to enable form login from brower /login page  (chrome/edge/...etc) add UsernamePasswordAuthenticationFilter

}

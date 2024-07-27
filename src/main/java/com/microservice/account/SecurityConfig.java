package com.microservice.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.microservice.account.service.UserInfoService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserInfoService userInfoService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorize -> authorize
						.antMatchers(HttpMethod.GET, "/api/cap/login").authenticated()
						.antMatchers(HttpMethod.POST, "/api/cap/country/add").authenticated()
						.antMatchers(HttpMethod.POST,"/api/cap/region/add/{countryId}").permitAll()
		            	.antMatchers(HttpMethod.GET,"/api/cap/region/all").permitAll()
						.antMatchers(HttpMethod.GET, "/api/cap/employee/getall").hasAnyAuthority("HR","MANAGER")
						.antMatchers(HttpMethod.POST, "/api/cap/hr/add").permitAll()
						.antMatchers(HttpMethod.POST, "/api/cap/manager/add").hasAuthority("HR")
						.antMatchers(HttpMethod.POST, "/api/cap/employee/add/{managerId}").hasAuthority("HR")
		            	.antMatchers(HttpMethod.GET,"/api/cap/hr/stat").hasAuthority("HR")
		            	.antMatchers(HttpMethod.GET,"/api/cap/hr/manager/employee").hasAuthority("HR")
		            	.antMatchers(HttpMethod.GET,"/api/cap/jobtype").hasAuthority("HR")
		            	.antMatchers(HttpMethod.GET,"/api/cap/manager/all").hasAuthority("HR")
		            	.antMatchers(HttpMethod.GET,"/api/cap/search/employee/manager/{searchStr}").permitAll()
		            	.antMatchers(HttpMethod.GET,"/api/cap/manager/employee").hasAuthority("MANAGER")
		            	.antMatchers(HttpMethod.POST,"/api/cap/task/employee/{eid}").hasAuthority("MANAGER")
						.anyRequest().permitAll())
				.httpBasic(Customizer.withDefaults());
		return http.build(); /* */
	}

	/* AuthenticationManager : in-memory / jdbc */

	@Bean
	public AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getEncoder());
		dao.setUserDetailsService(userInfoService);
		ProviderManager manager = new ProviderManager(dao);
		return manager;
	}

	@Bean
	public PasswordEncoder getEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}

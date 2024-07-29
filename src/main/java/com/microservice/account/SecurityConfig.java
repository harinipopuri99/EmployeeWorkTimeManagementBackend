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
						.antMatchers(HttpMethod.POST, "/api/cap/country/add").permitAll()
						.antMatchers(HttpMethod.GET, "/api/cap/country/all").hasAuthority("MANAGER")
						.antMatchers(HttpMethod.POST,"/api/cap/region/add").permitAll()
		            	.antMatchers(HttpMethod.GET,"/api/cap/region/all").hasAuthority("MANAGER")
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
		            	.antMatchers(HttpMethod.POST,"/api/cap/task/employee/{eid}/{pid}").hasAuthority("MANAGER")
		            	.antMatchers(HttpMethod.GET,"/api/cap/task/{eid}").hasAnyAuthority("MANAGER","EMPLOYEE")
		            	.antMatchers(HttpMethod.GET,"/api/cap/task/archive/{tid}").hasAuthority("MANAGER")
		            	.antMatchers(HttpMethod.POST,"/api/cap/project/add/{regionId}").hasAuthority("MANAGER")
		            	.antMatchers(HttpMethod.POST,"/api/cap/employee/project/assign/{employeeId}/{projectId}").hasAuthority("MANAGER")
		            	.antMatchers(HttpMethod.GET,"/api/cap/employee/projects").hasAnyAuthority("EMPLOYEE", "MANAGER", "HR")
		            	.antMatchers(HttpMethod.GET,"/api/cap/employee").hasAnyAuthority("EMPLOYEE", "MANAGER", "HR")
		            	.antMatchers(HttpMethod.POST,"/api/cap/task/project/{pid}").hasAuthority("MANAGER")
		            	.antMatchers(HttpMethod.GET,"/api/cap/project/all").hasAnyAuthority("EMPLOYEE", "MANAGER")
		            	.antMatchers(HttpMethod.GET,"/api/cap/task/project/{pid}").hasAnyAuthority("EMPLOYEE", "MANAGER")
		            	.antMatchers(HttpMethod.GET,"/api/cap/tasks/all").hasAuthority("MANAGER")
		            	.antMatchers(HttpMethod.GET,"/api/cap/priority/all").hasAuthority("MANAGER")
		            	.antMatchers(HttpMethod.POST,"/api/cap/task/employee/{eid}/{tid}").hasAuthority("MANAGER")
		            	.antMatchers(HttpMethod.POST,"/api/cap/notification/{eid}").hasAuthority("MANAGER")
		            	.antMatchers(HttpMethod.GET,"/api/cap/notification/employee/{eid}").hasAuthority("EMPLOYEE")
		            	.antMatchers(HttpMethod.GET,"/api/cap/project/tasks/all").hasAnyAuthority("EMPLOYEE", "MANAGER")
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

package com.univercenter.servibank.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * Clase que sirve para configuraciones de seguridad - Spring Security.
 *
 * @author David Aldana
 * @author <a href="http://www.univercenter.com">Univercenter</a>
 * 
 * @since 2017.03
 *
 * @see WebSecurityConfigurerAdapter
 * 
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
//
//	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

//		http.requiresChannel().anyRequest().requiresSecure().and().authorizeRequests().antMatchers("/home/**")
//				.authenticated().anyRequest().permitAll().and().formLogin().and().httpBasic();
		
		http.httpBasic().and()
			// .requiresChannel().anyRequest().requiresSecure().and() TODO activar esto en producción para habilitar soporte SSL
		    .formLogin().and()
		    .logout().and()
			.authorizeRequests().antMatchers("/index.html", "/").permitAll()
			.anyRequest().authenticated().and()
			.csrf().disable();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth, final UserDetailsService userDetailsService)
			throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
package com.univercenter.servibank.config;

import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.web.servlet.DispatcherServlet;

import com.univercenter.servibank.util.Global;

/**
 * 
 * Clase que sirve para configuraciones generales de la aplicación.
 *
 * @author David Aldana
 * @author <a href="http://www.univercenter.com">Univercenter</a>
 * 
 * @since 2017.03
 *
 */
@Configuration
@EnableJpaAuditing
@EnableJdbcHttpSession
@PropertySource(value = {Global.APP_EXTERNAL_CONFIG_PROPERTIES})
public class GeneralConfig {

	@Bean
	public DispatcherServlet dispatcherServlet() {
	    return new DispatcherServlet();
	}

	/**
	 * Register dispatcherServlet programmatically
	 *
	 * @return ServletRegistrationBean
	 */
	@Bean
	public ServletRegistrationBean dispatcherServletRegistration() {

	    ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), Global.DEFAULT_API_URL);

	    registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);

	    return registration;
	}
}
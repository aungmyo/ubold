package com.windy.config;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Bean
    public StandardPasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

	/** Public URLs. */
	private static final String[] PUBLIC_URLS = { "/", "/files/**" };

	// tag::dev[]
	@Autowired
	public void configureForDevelopment(AuthenticationManagerBuilder auth, Environment env) throws Exception {
		if (env.acceptsProfiles("!production")) {
			log.info("Setting up memory-based authentication for development.");
			auth.inMemoryAuthentication()
				.withUser("user").password("user").roles("USER").and()
				.withUser("admin").password("admin").roles("USER", "ADMIN");
		}
	}
	// end::dev[]

	// tag::production[]
	@Autowired
	public void configureForProduction(AuthenticationManagerBuilder auth, DataSource dataSource, Environment env)
			throws Exception {
		if (env.acceptsProfiles("production")) {
			log.info("Setting up JDBC-based authentication for production.");
			String query = "select g.id, g.group_name, ga.authority "
					+ "from groups g, group_members gm, group_authorities ga "
					+ "where gm.username = ? and g.id = ga.group_id and g.id = gm.group_id";
			auth.jdbcAuthentication().dataSource(dataSource).groupAuthoritiesByUsername(query).passwordEncoder(passwordEncoder());
		}
	}
	// end::production[]

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(PUBLIC_URLS).permitAll()
				.anyRequest().authenticated()
			.and()
				.requiresChannel()
					.anyRequest().requiresSecure()
			.and()
				.formLogin().loginPage("/login").permitAll()
				.defaultSuccessUrl("/home")
			.and()
				.logout().permitAll();
	}

}
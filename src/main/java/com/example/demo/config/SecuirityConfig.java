package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecuirityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select name, pass, 'true' as enabled from users where name = ?")
				.authoritiesByUsernameQuery("select name, 'USER' from users where name=?");
//		auth.inMemoryAuthentication().withUser("food").password("foo").roles("USER");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and()
				.csrf().disable();
	}
}

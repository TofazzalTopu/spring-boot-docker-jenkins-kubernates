package com.info.demo.config.security;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig 
//extends WebSecurityConfigurerAdapter
{
	/*
	@Autowired
	private Environment env;
	
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
		httpSecurity
				.antMatcher("/apiservice/**")
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
    	String encryptedPassword = "#"+env.getProperty("password")+"#";
        auth.inMemoryAuthentication()
                .withUser(env.getProperty("userId"))
                .password("{noop}"+encryptedPassword)
                .roles("USER");
    }
    */
}
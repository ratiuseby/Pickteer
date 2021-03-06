package com.pickteer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pickteer.controllers.Mappings;
import com.pickteer.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
    private UserService userService;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requiresChannel()
        .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
        .requiresSecure()
        .and()
        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and()
        .authorizeRequests()
        	.antMatchers(
        		Mappings.ROOT,
                "/js/**",
                "/css/**",
                "/img/**",
                "/icons/**",
                "/webjars/**").permitAll()
        	.antMatchers(Mappings.USER_PAGE + "/**").authenticated()
		.and()
        .formLogin()
			.loginPage(Mappings.ROOT)
			.permitAll()
			.defaultSuccessUrl(Mappings.USER_PAGE)
		.and()
        .logout()
	        .invalidateHttpSession(true)
	        .clearAuthentication(true)
	        .logoutRequestMatcher(new AntPathRequestMatcher(Mappings.LOGOUT))
	        .logoutSuccessUrl(Mappings.ROOT + "?logout")
	        .permitAll()
	    .and()
		    .exceptionHandling()
	        .accessDeniedHandler(accessDeniedHandler);
	}

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}

package uo.asw;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

@Override
protected void configure(HttpSecurity http) throws Exception {
    http
    	.csrf().disable()
		.authorizeRequests()
		     .antMatchers("/css/**", "/img/**", "/script/**", "/", "/login/**", "/logout").permitAll()
		     .antMatchers("/incidencias").authenticated()
		     .antMatchers("/incidencias/**").authenticated()
		     .antMatchers("/mapa").authenticated()
		     .antMatchers("/").permitAll()
		        .and()
        .formLogin()
             .loginPage("/login")
             .permitAll()
             .defaultSuccessUrl("/")
             .and()
        .logout()
           .permitAll();
}

}

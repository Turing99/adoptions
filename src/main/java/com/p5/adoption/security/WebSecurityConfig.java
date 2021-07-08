package com.p5.adoption.security;

import com.p5.adoption.security.BCryptPasswordEncoder;
import com.p5.adoption.security.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity //This annotation tells the spring to set certain security filters
//This class performs the security configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private final MyUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DataSource dataSource;

    public WebSecurityConfig(MyUserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder, DataSource dataSource)
    {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
    }

    @Override
    //Configure what type of configuration it uses
    //Obs: Basic Authentication uses the base64 algorithm
//    protected void configure(HttpSecurity http) throws Exception
//    {
//        http.authorizeRequests()
//                .antMatchers("/api/v1/animals/hello").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/shelters/*").hasRole("USER")
  //              .antMatchers(HttpMethod.POST,"/api/v1/shelters/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }


    @Override
//    We tell it through the AuthenticationManagerBuilder class how to do the authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .authenticationProvider(authenticationProvider())
                .jdbcAuthentication()
                .dataSource(dataSource);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    //    After we have implemented UserDertailsService, we don't need this hack anymore.
    //    @Bean
    //    @Override
    //    protected UserDetailsService userDetailsService()
    //    {
    //        UserDetails user = User.withDefaultPasswordEncoder()
    //                               .username("user")
    //                               .password("password")
    //                               .roles("USER")
    //                               .build();
    //        return new InMemoryUserDetailsManager(user);
    //    }
}
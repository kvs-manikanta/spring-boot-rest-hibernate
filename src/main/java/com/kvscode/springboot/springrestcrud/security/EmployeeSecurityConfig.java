package com.kvscode.springboot.springrestcrud.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class EmployeeSecurityConfig {
    /*
     * now spring will not use the application.properties for user/pass 
     * as we have defined here inmemoryuserdetails here
     */
    
    //add support for JDBC and no more hard code like previous 
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource)
    {
        //can call the custom tables from the DB use the defined queried using JDBC 
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(configurer ->
                configurer
                .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT,"/api/employees/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
        );
        
        //use HTTP Basic auth

        http.httpBasic(Customizer.withDefaults());

        //Disable CSRF not required for stateless rest api  that uses for POST<PUT<DELETEand/or PATCH

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    /* 
    @Bean
    public InMemoryUserDetailsManager userDetailsManager()
    {
        UserDetails john=User.builder()
        .username("john")
        .password("{noop}test123")
        .roles("EMPLOYEE")
        .build();

        UserDetails mary=User.builder()
        .username("mary")
        .password("{noop}test123")
        .roles("EMPLOYEE","MANAGER")
        .build();

        UserDetails susan=User.builder()
        .username("susan")
        .password("{noop}test123")
        .roles("Employee","MANAGER","ADMIN")
        .build();

        return new InMemoryUserDetailsManager(john,mary,susan);
    }
    
    */
}

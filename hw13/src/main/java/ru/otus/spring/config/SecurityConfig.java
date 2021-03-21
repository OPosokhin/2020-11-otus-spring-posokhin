package ru.otus.spring.config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/login").anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/book").authenticated()
                .antMatchers("/book/**").hasAnyRole("ADMIN")
                .antMatchers("/bookAdd/**").hasAnyRole("ADMIN")
                .antMatchers("/edit/**").hasAnyRole("ADMIN")
                .antMatchers("/author").authenticated()
                .antMatchers("/author/**").hasAnyRole("ADMIN")
                .antMatchers("/genre").authenticated()
                .antMatchers("/genre/**").hasAnyRole("ADMIN")
                .and()
                .formLogin().defaultSuccessUrl("/book")
                .and()
                .rememberMe().key("olololOtus");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);

    }
}
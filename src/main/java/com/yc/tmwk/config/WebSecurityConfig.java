package com.yc.tmwk.config;

import com.yc.tmwk.util.UserSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService userSercurityService(){
        return new UserSecurityService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSercurityService()).passwordEncoder(new BCryptPasswordEncoder());; //user Details Service验证
        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("155").password(new BCryptPasswordEncoder().encode("123")).roles("1");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin().disable();
        http.authorizeRequests()
                .antMatchers("/welcome/*").permitAll()
                .antMatchers("/admin/*").hasRole("1")
                .antMatchers("/staff/*").hasRole("2")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("login_name")
                .passwordParameter("login_pwd")
                .failureUrl("/login?error")
                .successForwardUrl("/welcome/dispatcher")
                .permitAll()
                .and()
            .logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/lib/**").antMatchers("/temp/**").antMatchers("/mui/**");
    }


}

package system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import system.security.AuthProviderImpl;

@Configuration
@EnableWebSecurity
@ComponentScan("system.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProviderImpl authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/resources/**", "/webjars/**").permitAll().
                antMatchers("/users/new", "/login").anonymous().
                antMatchers("/users", "/chat").authenticated().
                and().csrf().disable().formLogin().
                loginPage("/login").
                loginProcessingUrl("/login/process").usernameParameter("login").defaultSuccessUrl("/chat")
                .failureUrl("/login?error=true").
                and().exceptionHandling().accessDeniedPage("/users").
                and().logout();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/.js" ,"/.css", "/resources/**", "/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

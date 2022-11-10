package pl.sobota.imagesenderspringboot;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sobota.imagesenderspringboot.repository.UserAppRepository;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserAppRepository userAppRepository;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, UserAppRepository userAppRepository) {
        this.userDetailsService = userDetailsService;
        this.userAppRepository = userAppRepository;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser(new User("Janek", passwordEncoder().encode("user"), Collections.singleton(new SimpleGrantedAuthority("user"))));
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/test1").authenticated()
                .and()
                .formLogin().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @EventListener(ApplicationEvent.class)
    public void create(){
        UserApp userApp = new UserApp("Pio", passwordEncoder().encode("Nowak"), "User");
        userAppRepository.save(userApp);
    }
}

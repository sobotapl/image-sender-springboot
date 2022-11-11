package pl.sobota.imagesenderspringboot.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sobota.imagesenderspringboot.model.UserApp;
import pl.sobota.imagesenderspringboot.repository.UserAppRepository;
import pl.sobota.imagesenderspringboot.service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private  UserDetailsServiceImpl userDetailsService;
    private  UserAppRepository userAppRepository;

    @Autowired
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
        http.authorizeRequests()
                .antMatchers("/test1").hasRole("USER")
                .antMatchers("/test2").hasRole("ADMIN")
                .antMatchers("/upload").hasRole("ADMIN")
                .antMatchers("/gallery").hasRole("USER")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @EventListener(ApplicationEvent.class)
    public void create(){
        UserApp userAppAdmin = new UserApp("piotr", "admin", "ROLE_ADMIN");
        UserApp userAppUser = new UserApp("marek", "user", "ROLE_USER");
        userAppRepository.save(userAppAdmin);
        userAppRepository.save(userAppUser);
    }
}

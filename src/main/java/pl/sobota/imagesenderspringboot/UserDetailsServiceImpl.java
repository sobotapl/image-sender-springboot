package pl.sobota.imagesenderspringboot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sobota.imagesenderspringboot.repository.UserAppRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private UserAppRepository userAppRepository;
    private PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserAppRepository userAppRepository, PasswordEncoder passwordEncoder) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAppRepository.findByUsername(username);
    }

    @EventListener(ApplicationEvent.class)
    public void create(){
        UserApp userApp = new UserApp("Pio", passwordEncoder.encode("Nowak"), "User");
        userAppRepository.save(userApp);
    }

}

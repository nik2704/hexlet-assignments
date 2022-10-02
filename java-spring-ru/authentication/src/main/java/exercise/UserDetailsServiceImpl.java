package exercise;

//import exercise.model.User;
import org.springframework.security.core.userdetails.User;
import exercise.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Список полномочий, которые будут предоставлены пользователю после аутентификации
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("user"));

        // BEGIN
        exercise.model.User myUser = repository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User " + username + " not found"));
        UserDetails user = User.withUsername(myUser.getUsername()).password(myUser.getPassword()).authorities("user").build();
        return user;
        // END
    }
}
//
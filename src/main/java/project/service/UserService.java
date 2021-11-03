package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.model.User;
import project.model.UserDTO;
import project.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void addUser(UserDTO userDTO) {
        userRepository.save(new User(userDTO.getLogin(), userDTO.getPassword(), "USER"));
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByLogin(login);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("No user found with login: " + login);
        }

        User user = optionalUser.get();
        System.out.println(user.getRole());

        return
                new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        passwordEncoder.encode(user.getPassword()),
                        Collections.singletonList(
                                new SimpleGrantedAuthority(
                                        user.getRole()
                                )
                        )
                );
    }
}

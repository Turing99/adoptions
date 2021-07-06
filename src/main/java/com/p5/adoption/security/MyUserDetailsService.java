package com.p5.adoption.security;

import com.p5.adoption.model.UserDTO;
import com.p5.adoption.model.adapters.UserAdapter;
import com.p5.adoption.users.User;
import com.p5.adoption.users.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MyUserDetailsService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    // username -> what we send in postman to the user
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User>userOptional = userRepository.findByEmail(username); //userOptional -> user taken from DB

        if(!userOptional.isPresent()) // check if the user is in db
        {
            throw new UsernameNotFoundException(username);
        }

        return new UserPrincipal(userOptional.get()); // UserPrincipal - >current user
    }

    //Initialize DB with a default user
//    @Bean
//    private CommandLineRunner setUpDefaultUser()
//    {
//        return args -> {
//            final String defaultEmail = "animalshelter@pentastagiu.io";
//            final String defaultPassword = "password";
//
//            Optional<User> defaultUser = userRepository.findByEmail(defaultEmail);
//
//            if (!defaultUser.isPresent())
//            {
//                userRepository.save(new User()
//                        .setEmail(defaultEmail)
//                        .setPassword(passwordEncoder.encode(defaultPassword)));
//            }
//        };
//    }

    public UserDTO addUser(UserDTO userDTO)
    {
        if(userDTO.getEmail() == null && userDTO.getPassword() == null)
        {
            throw new RuntimeException("User must have a email and a password!");
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Optional<User> defaultUser = userRepository.findByEmail(userDTO.getEmail());

        if (!defaultUser.isPresent()) {
            return UserAdapter.toDTO(userRepository.save(UserAdapter.fromDto(userDTO)));
        }
        else
            return null;
    }
}

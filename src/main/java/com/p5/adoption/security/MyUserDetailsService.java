package com.p5.adoption.security;

import com.p5.adoption.model.UserDTO;
import com.p5.adoption.model.adapters.UserAdapter;
import com.p5.adoption.model.roles.RolesEnum;
import com.p5.adoption.repository.roles.Role;
import com.p5.adoption.repository.roles.RoleRepository;
import com.p5.adoption.repository.users.User;
import com.p5.adoption.repository.users.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MyUserDetailsService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
    @Bean
    private CommandLineRunner setUpDefaultUser()
    {
        return args -> {
            final String defaultEmail = "animalshelter@pentastagiu.io";
            final String defaultPassword = "password";

            Role moderatorRole =  roleRepository.findByRole(RolesEnum.ROLE_MOD).orElseGet(() -> {
                Role newRole = new Role().setRole(RolesEnum.ROLE_MOD);
                return roleRepository.save(newRole);
            });
            Optional<User> defaultUser = userRepository.findByEmail(defaultEmail);

            if (!defaultUser.isPresent())
            {
                HashSet<Role> roles = new HashSet<>();
                roles.add(moderatorRole);

                userRepository.save(new User()
                        .setEmail(defaultEmail)
                        .setPassword(passwordEncoder.encode(defaultPassword))
                        .setUserRoleSet(Collections.singleton(moderatorRole))
                        .setUserRoleSet(roles));
            }
        };
    }

//    public UserDTO addUser(UserDTO userDTO)
//    {
//        if(userDTO.getEmail() == null && userDTO.getPassword() == null)
//        {
//            throw new RuntimeException("User must have a email and a password!");
//        }
//
//        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//
//        Optional<User> defaultUser = userRepository.findByEmail(userDTO.getEmail());
//
//        if (!defaultUser.isPresent()) {
//            return UserAdapter.toDTO(userRepository.save(UserAdapter.fromDto(userDTO)));
//        }
//        else
//            return null;
//    }

}

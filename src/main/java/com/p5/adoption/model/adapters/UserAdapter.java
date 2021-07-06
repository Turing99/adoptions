package com.p5.adoption.model.adapters;
import com.p5.adoption.model.UserDTO;
import com.p5.adoption.security.BCryptPasswordEncoder;
import com.p5.adoption.users.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter {

private final BCryptPasswordEncoder passwordEncoder;

    public UserAdapter(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static User fromDto(UserDTO userDTO)
    {
        if(userDTO.getEmail().equals("")){
            userDTO.setEmail("generic@email.com");
        }
        return new User().setEmail(userDTO.getEmail()).setPassword((userDTO.getPassword()));
    }

    public static UserDTO toDTO(User user)
    {
        return new UserDTO(user.getId(), user.getPassword(), user.getEmail());
    }

    public static List<UserDTO> toListDTO(List<User> users)
    {
        List<UserDTO> dtoList = new ArrayList<>();
        users.forEach(user -> dtoList.add(toDTO((user))));

        return dtoList;
    }

}

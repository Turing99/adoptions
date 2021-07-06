package com.p5.adoption.api.controllers;

import com.p5.adoption.model.UserDTO;
import com.p5.adoption.repository.UserStore;
import com.p5.adoption.security.MyUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final MyUserDetailsService userDetailsService;


    public UserController(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/Hello")
    public ResponseEntity<String> greetUser(@RequestParam(name = "email", required = false) String username) {
        String name = username == null ? "world" : username;
        return ResponseEntity.ok("Hello " + name + ", from the animal shelter!");
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAvailableUsers() {
        return ResponseEntity.ok(UserStore.available);
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDto) {
        if (userDetailsService.addUser(userDto) == null)
            return ResponseEntity.badRequest().body(userDto);
        else
            return ResponseEntity.ok(userDetailsService.addUser(userDto));
        //  userDetailsService.setUpDefaultUser(userDto);
    }

    @PutMapping("/{email}")
    public void updateUser(@PathVariable(name = "email") String email, @RequestBody UserDTO updateUser) {
        List<UserDTO> available = UserStore.available;
        for (int i = 0; i < available.size(); i++) {
            UserDTO user = available.get(i);
            if (user.getEmail().equals("email")) {
                available.remove(i);
                available.add(updateUser);
                break;
            }
        }
    }

    @DeleteMapping({"/email"})
    public void deleteUser(@PathVariable(name = "email") String email) {

        List<UserDTO> available = UserStore.available;
        for (int i = 0; i < available.size(); i++) {

            UserDTO user = available.get(i);
            if (user.getEmail().equals("email")) {
                available.remove(i);
                break;
            }
        }
    }
}

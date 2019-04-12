package com.teaneck_squad.demo.Controllers;

import com.teaneck_squad.demo.Models.User;
import com.teaneck_squad.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user){
        ModelMap mm = new ModelMap();
        if(userService.createUser(user)) {
            mm.put("message","user successfully created!");
            return new ResponseEntity<>(mm,HttpStatus.OK);
        }
        else{
            mm.put("errorMessage", "unable to add this user");
            return new ResponseEntity<>(mm,HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable(name = "id") long id){
        try {
            User u = userService.getUser(id);
            return new ResponseEntity<>(u, HttpStatus.OK);
        }catch (RuntimeException e) {
            ModelMap mm = new ModelMap();
            mm.put("errorMessage", "user not found");
            return new ResponseEntity<>(mm, HttpStatus.NOT_FOUND);
        }
    }
}

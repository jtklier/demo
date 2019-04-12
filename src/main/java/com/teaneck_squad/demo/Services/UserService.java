package com.teaneck_squad.demo.Services;

import com.teaneck_squad.demo.Models.User;
import com.teaneck_squad.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean createUser(User user){
        final User didSave = userRepository.save(user);
        if(didSave != null){
            return true;
        } else return false;
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

}

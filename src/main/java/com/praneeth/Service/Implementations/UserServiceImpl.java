package com.praneeth.Service.Implementations;

import com.praneeth.DTO.UserDTO;
import com.praneeth.Entity.User;
import com.praneeth.Exceptions.ResourceNotFoundException;
import com.praneeth.Repository.UserRepository;
import com.praneeth.Service.Interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User createUser(UserDTO dto) {
        UserDTO.validate(dto);
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        log.info("Creating user: {}", dto.getEmail());
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }
}

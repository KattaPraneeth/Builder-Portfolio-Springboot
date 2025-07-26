package com.praneeth.Service.Interfaces;

import com.praneeth.DTO.UserDTO;
import com.praneeth.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserDTO dto);
    List<User> getAllUsers();
    User getUserById(Long id);
}

package com.praneeth.ServiceTests;

import com.praneeth.DTO.UserDTO;
import com.praneeth.Entity.User;
import com.praneeth.Enum.UserRole;
import com.praneeth.Repository.UserRepository;
import com.praneeth.Service.Implementations.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_shouldSaveAndReturnUser() {
        UserDTO dto = new UserDTO();
        dto.setName("Bruce Wayne");
        dto.setEmail("bruce@wayne.com");
        dto.setRole(UserRole.CLIENT);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName(dto.getName());
        savedUser.setEmail(dto.getEmail());
        savedUser.setRole(dto.getRole());

        when(userRepo.save(any(User.class))).thenReturn(savedUser);

        User result = userService.createUser(dto);

        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(1L, result.getId());
        verify(userRepo).save(any(User.class));
    }
}

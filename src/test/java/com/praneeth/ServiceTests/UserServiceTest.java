//package com.praneeth.ServiceTests;
//
//import com.praneeth.DTO.UserDTO;
//import com.praneeth.Entity.User;
//import com.praneeth.Enum.UserRole;
//import com.praneeth.Repository.UserRepository;
//import com.praneeth.Service.Implementations.UserServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepo;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @Test
//    void createUser_shouldSaveAndReturnUser() {
//        UserDTO dto = new UserDTO();
//        dto.setName("Bruce Wayne");
//        dto.setEmail("bruce@wayne.com");
//        dto.setRole(UserRole.CLIENT);
//
//        User savedUser = new User();
//        savedUser.setId(1L);
//        savedUser.setName(dto.getName());
//        savedUser.setEmail(dto.getEmail());
//        savedUser.setRole(dto.getRole());
//
//        when(userRepo.save(any(User.class))).thenReturn(savedUser);
//
//        User result = userService.createUser(dto);
//
//        assertEquals(dto.getEmail(), result.getEmail());
//        assertEquals(1L, result.getId());
//        verify(userRepo).save(any(User.class));
//    }
//}

package com.praneeth.ServiceTests;

import com.praneeth.DTO.UserDTO;
import com.praneeth.Entity.User;
import com.praneeth.Enum.UserRole;
import com.praneeth.Exceptions.InvalidUserDataException;
import com.praneeth.Repository.UserRepository;
import com.praneeth.Service.Implementations.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void createUser_shouldThrow_whenUserDTOIsNull() {
        InvalidUserDataException ex = assertThrows(InvalidUserDataException.class, () -> {
            userService.createUser(null);
        });
        assertEquals("User data cannot be null", ex.getMessage());
        verify(userRepo, never()).save(any());
    }

    @Test
    void createUser_shouldThrow_whenNameIsBlank() {
        UserDTO dto = new UserDTO();
        dto.setName("   ");
        dto.setEmail("bruce@wayne.com");
        dto.setRole(UserRole.CLIENT);

        InvalidUserDataException ex = assertThrows(InvalidUserDataException.class, () -> {
            userService.createUser(dto);
        });
        assertEquals("Name is missing or blank", ex.getMessage());
        verify(userRepo, never()).save(any());
    }

    @Test
    void createUser_shouldThrow_whenEmailIsInvalid() {
        UserDTO dto = new UserDTO();
        dto.setName("Bruce Wayne");
        dto.setEmail("invalidEmail");
        dto.setRole(UserRole.BUILDER);

        InvalidUserDataException ex = assertThrows(InvalidUserDataException.class, () -> {
            userService.createUser(dto);
        });
        assertEquals("Email format is invalid", ex.getMessage());
        verify(userRepo, never()).save(any());
    }

    @Test
    void createUser_shouldThrow_whenRoleIsNull() {
        UserDTO dto = new UserDTO();
        dto.setName("Bruce Wayne");
        dto.setEmail("bruce@wayne.com");
        dto.setRole(null);

        InvalidUserDataException ex = assertThrows(InvalidUserDataException.class, () -> {
            userService.createUser(dto);
        });
        assertEquals("User role is missing", ex.getMessage());
        verify(userRepo, never()).save(any());
    }
}

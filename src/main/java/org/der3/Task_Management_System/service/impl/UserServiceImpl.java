package org.der3.Task_Management_System.service.impl;

import org.der3.Task_Management_System.dto.UserDTO;
import org.der3.Task_Management_System.mapper.UserMapper;
import org.der3.Task_Management_System.model.User;
import org.der3.Task_Management_System.repository.UserRepository;
import org.der3.Task_Management_System.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceInterface {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserMapper mapper;

        public UserDTO getAuthenticatedUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userRepo.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.toDTO(user);
    }
}

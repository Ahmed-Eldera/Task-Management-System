package org.der3.Task_Management_System.mapper;

import org.der3.Task_Management_System.dto.UserDTO;
import org.der3.Task_Management_System.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;

    }
        public User toEntity(UserDTO user){
        User entity = new User();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        return entity;

    }
}

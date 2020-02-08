package com.gmail.jahont.pavel.app.service;

import java.util.List;

import com.gmail.jahont.pavel.app.service.model.UserDTO;

public interface UserService {

    List<UserDTO> findAll();

    void addUser(UserDTO userDTO);

    void deleteUserById(Integer userId);

    void updateUser(int id, String address);

}

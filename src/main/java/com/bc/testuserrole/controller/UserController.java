package com.bc.testuserrole.controller;

import com.bc.testuserrole.model.User;
import com.bc.testuserrole.model.UserWithRole;
import com.bc.testuserrole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired private UserService userService;

    //Получать список пользователей из БД (без ролей)
    @GetMapping("/findAllUsers")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    //Получать конкретного пользователя (с его ролями) из БД
    @GetMapping("/findUserWithRoleByLogin")
    public UserWithRole findUserWithRoleByLogin(@RequestParam String login){
        return userService.findUserWithRoleByLogin(login);
    }

    //Удалять пользователя в БД
    @DeleteMapping("/deleteUserByLogin")
    public void deleteUserByLogin(@RequestParam String login){
        userService.deleteUserByLogin(login);
    }

    //Добавлять нового пользователя с ролями в БД.
    @PostMapping("/saveUserWithRoleByLogin")
    public String saveUserWithRoleByLogin(@RequestBody UserWithRole userWithRole){
        return userService.saveUserWithRoleByLogin(userWithRole);
    }

    //Редактировать существующего пользователя в БД.
    @PutMapping ("/editUserWithRole")
    public String editUserWithRole(@RequestBody UserWithRole userWithRole){
        return userService.editUserWithRole(userWithRole);
    }
}

package com.bc.testuserrole.controller;

import com.bc.testuserrole.model.User;
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
    @GetMapping("/findUserByLogin")
    public User findUserByLogin(@RequestParam String login){
        return userService.findUserByLogin(login);
    }

    //Удалять пользователя в БД
    @DeleteMapping("/deleteUserByLogin")
    public String deleteUserByLogin(@RequestParam String login){
        return userService.deleteUserByLogin(login);
    }

    //Добавлять нового пользователя с ролями в БД.
    @PostMapping("/saveUserByLogin")
    public String saveUserByLogin(@RequestBody User user){
        return userService.saveUserByLogin(user);
    }

    //Редактировать существующего пользователя в БД.
    @PutMapping ("/editUser")
    public String editUser(@RequestBody User user){
        return userService.editUser(user);
    }
}

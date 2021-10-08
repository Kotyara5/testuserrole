package com.bc.testuserrole.service;

import com.bc.testuserrole.model.Role;
import com.bc.testuserrole.model.User;
import com.bc.testuserrole.model.UserWithRole;
import com.bc.testuserrole.repository.RoleRepository;
import com.bc.testuserrole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public UserWithRole findUserWithRoleByLogin(String login){
        UserWithRole userWithRole = new UserWithRole();
        userWithRole.setUser(userRepository.findByLogin(login));
        userWithRole.setListRole(roleRepository.getListRoleOfUser(login));
        return userWithRole;
    }

    public void deleteUserByLogin(String login){
        userRepository.deleteById(login);
    }

    public String saveUserWithRoleByLogin(UserWithRole userWithRole) {
        String validate = validateUser(userWithRole.getUser());
        if (validate.equals("success: true")) {
            userRepository.save(userWithRole.getUser());
            String user_login = userWithRole.getUser().getLogin();
            List<Role> listRole = new ArrayList(userWithRole.getListRole());
            if (!listRole.isEmpty())
                for (Role value : listRole)
                    userRepository.addRoleToUser(user_login, value.getId());
        }
        return validate;
    }

    public String editUserWithRole(UserWithRole userWithRole){
        String validate = validateUser(userWithRole.getUser());
        if (validate.equals("success: true")) {
            userRepository.updateUser(userWithRole.getUser().getLogin(), userWithRole.getUser().getName(), userWithRole.getUser().getPassword());
            String user_login = userWithRole.getUser().getLogin();
            List<Role> listRole = new ArrayList(userWithRole.getListRole());
            if (!listRole.isEmpty()) {
                userRepository.deleteAllRoleByUserLogin(user_login);
                for (Role value : listRole)
                    userRepository.addRoleToUser(user_login, value.getId());
            }
        }
        return validate;
    }

    private String validateUser(User user){
        String errors = " ";
        if (user.getLogin().isEmpty())
            errors = errors.concat("login is empty. ");
        if (user.getName().isEmpty())
            errors = errors.concat("name is empty. ");
        if (user.getPassword().isEmpty())
            errors = errors.concat("password is empty. ");
        String numRegex = ".*[0-9].*";
        String alphaRegex = ".*[A-Z].*";
        if (!user.getPassword().matches(numRegex))
            errors = errors.concat("password must contain a number. ");
        if (!user.getPassword().matches(alphaRegex))
            errors = errors.concat("password must contain a capital letter. ");
        if (errors.equals(" "))
            return "success: true";
        else
            return "success: false. errors:" + errors;
    }
}

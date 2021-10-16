package com.bc.testuserrole.service;

import com.bc.testuserrole.model.Role;
import com.bc.testuserrole.model.User;
import com.bc.testuserrole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public List<User> findAllUsers(){
        List<User> allUser = userRepository.findAll();
        for (User value : allUser)
            value.setRoles(null);
        return allUser;
    }

    public User findUserByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public String deleteUserByLogin(String login){
        if (findUserByLogin(login) != null) {
            userRepository.deleteById(login);
            return "success: true";
        }
        return "success: false. errors: user does not exist";
    }

    public String saveUserByLogin(User user) {
        String validate = validateUser(user);
        if (validate.equals("success: true")) {
            userRepository.save(user);
        }
        return validate;
    }

    public String editUser(User user){
        String validate = validateUser(user);
        if (validate.equals("success: true")) {
            userRepository.updateUser(user.getLogin(), user.getName(), user.getPassword());
            String user_login = user.getLogin();
            List<Role> listRole = new ArrayList(user.getRoles());
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

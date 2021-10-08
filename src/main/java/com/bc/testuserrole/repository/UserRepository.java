package com.bc.testuserrole.repository;

import com.bc.testuserrole.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, String> {

    User findByLogin(String login);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO userrole VALUES (?1, ?2)", nativeQuery = true)
    void addRoleToUser(String user_login, int role_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET name = ?2, password = ?3 WHERE login = ?1", nativeQuery = true)
    void updateUser(String login, String name, String password);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM userrole WHERE user_login = ?1", nativeQuery = true)
    void deleteAllRoleByUserLogin(String login);
}

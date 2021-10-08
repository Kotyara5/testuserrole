package com.bc.testuserrole.repository;

import com.bc.testuserrole.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "SELECT * FROM role WHERE id IN " +
            "(SELECT role_id FROM userrole WHERE user_login = ?1)", nativeQuery = true)
    List<Role> getListRoleOfUser(String login);
}

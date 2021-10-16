package com.bc.testuserrole.service;

import com.bc.testuserrole.model.Role;
import com.bc.testuserrole.model.User;
import com.bc.testuserrole.repository.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private UserService userService;

    private static List<Role> m_ListRole;

    @BeforeClass
    public static void setUp(){
        m_ListRole = new ArrayList();
        m_ListRole.add(new Role("USER"));
        m_ListRole.add(new Role("ADMIN"));
        m_ListRole.add(new Role("MODER"));
        m_ListRole.add(new Role("OPER"));
    }

    @Test
    public void saveUserWithRoleByLogin_success() {
        List<Role> listRole = new ArrayList();
        listRole.add(m_ListRole.get(0));
        listRole.add(m_ListRole.get(1));
        User user = new User("kname", "klogin", "qwertyK1", listRole);
        String s = userService.saveUserByLogin(user);
        Assert.assertEquals(s, "success: true");
    }
    @Test
    public void saveUserWithRoleByLogin_errorValidate() {
        List<Role> listRole = new ArrayList();
        listRole.add(m_ListRole.get(0));
        listRole.add(m_ListRole.get(1));
        User user = new User("", "klogin", "qwerty", listRole);
        String s = userService.saveUserByLogin(user);
        Assert.assertEquals(s, "success: false. errors: name is empty. password must contain a number. password must contain a capital letter. ");
    }

    @Test
    public void editUserWithRole_success() {
        List<Role> listRole = new ArrayList();
        listRole.add(m_ListRole.get(0));
        listRole.add(m_ListRole.get(1));
        listRole.add(m_ListRole.get(2));
        User user_edit = new User("kname", "klogin", "K12345", listRole);

        String s = userService.editUser(user_edit);

        when(userRepositoryMock.findByLogin(user_edit.getLogin())).thenReturn(user_edit);

        User findUser = userService.findUserByLogin("klogin");
        Assert.assertEquals(s, "success: true");
        Assert.assertEquals(findUser.getName(), user_edit.getName());
        Assert.assertEquals(findUser.getLogin(), user_edit.getLogin());
        Assert.assertEquals(findUser.getPassword(), user_edit.getPassword());
    }

    @Test
    public void editUserWithRole_errorValidate() {
        List<Role> listRole = new ArrayList();
        listRole.add(m_ListRole.get(0));
        listRole.add(m_ListRole.get(1));
        listRole.add(m_ListRole.get(2));
        User user_edit = new User("kname", "klogin", "qvbm3jgvb", listRole);

        String s = userService.editUser(user_edit);

        when(userRepositoryMock.findByLogin(user_edit.getLogin())).thenReturn(user_edit);

        User findUser = userService.findUserByLogin("klogin");
        Assert.assertEquals(s, "success: false. errors: password must contain a capital letter. ");
        Assert.assertEquals(findUser.getName(), user_edit.getName());
        Assert.assertEquals(findUser.getLogin(), user_edit.getLogin());
        Assert.assertEquals(findUser.getPassword(), user_edit.getPassword());
    }
}
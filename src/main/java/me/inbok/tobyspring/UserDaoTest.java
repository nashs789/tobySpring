package me.inbok.tobyspring;

import me.inbok.tobyspring.common.ConnectionMaker;
import me.inbok.tobyspring.common.DaoFactory;
import me.inbok.tobyspring.common.User;
import me.inbok.tobyspring.common.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("user1004");
        user.setName("김진우");
        user.setPassword("1234");

        dao.add(user);

        System.out.println("=== Successfully add method end ===");

        User user2 = dao.get(user.getId());
        System.out.println("user2.getName() = " + user2.getName());
        System.out.println("user2.getPassword() = " + user2.getPassword());

        System.out.println("=== Successfully get method end ===");
    }
}

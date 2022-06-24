package me.inbok.tobyspring;

import me.inbok.tobyspring.common.CountingConnectionMaker;
import me.inbok.tobyspring.common.CountingDaoFactory;
import me.inbok.tobyspring.common.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoConnectionCountingTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        
        UserDao userDao = context.getBean("userDao", UserDao.class);
        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("ccm.getCount() = " + ccm.getCount());        
        
    }
}

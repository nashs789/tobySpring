package me.inbok.tobyspring.common;

import me.inbok.tobyspring.Dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Driver;

@Configuration
public class DaoFactory {

    @Bean
    public SimpleDriverDataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        Class driverClass = Class.forName("org.mariadb.jdbc.Driver");

        dataSource.setDriverClass(driverClass);
        dataSource.setUrl("jdbc:mariadb://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        return dataSource;
    }

    @Bean
    public UserDao userDao() throws ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(dataSource());
        return userDao;
    }
}

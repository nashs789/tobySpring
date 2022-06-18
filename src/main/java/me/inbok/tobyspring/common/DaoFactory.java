package me.inbok.tobyspring.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public ConnectionMaker getConnectionMaker(){
        return new ConnectionMaker();
    }

    @Bean
    public UserDao userDao(){
        return new UserDao(getConnectionMaker());
    }
}

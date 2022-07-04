package me.inbok.tobyspring;

import me.inbok.tobyspring.Dao.UserDao;
import me.inbok.tobyspring.common.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

class TobySpringApplicationTests {

    @Test
    public void addAndGet() throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        User user = new User();
        user.setId("gyumee");
        user.setName("박성철");
        user.setPassword("1234");

        userDao.add(user);
        assertThat(userDao.getCount()).isEqualTo(1);

        User user2 = userDao.get(user.getId());

        assertThat( user2.getName() ).isEqualTo(user.getName());
        assertThat( user2.getPassword() ).isEqualTo(user.getPassword());
    }

    @Test
    public void count() throws SQLException{
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);

        User user1 = new User("gyumee", "박성철", "springno1");
        User user2 = new User("leegw700", "이길원", "springno2");
        User user3 = new User("bumjin", "박범진", "springno3");

  
    }

}

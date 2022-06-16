package me.inbok.tobyspring.common;

import java.sql.*;

public class UserDao {
    private IConnectionMaker iConnectionMaker;

    public UserDao(){
        this.iConnectionMaker = new SimpleConnectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = iConnectionMaker.makeConnection();

        PreparedStatement ps = connection.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    public User get(String id)throws ClassNotFoundException, SQLException {
        Connection connection = iConnectionMaker.makeConnection();

        PreparedStatement ps = connection.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        connection.close();

        return user;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("user1003");
        user.setName("김선우");
        user.setPassword("1234");

        dao.add(user);

        System.out.println("=== Successfully add method end ===");

        User user2 = dao.get(user.getId());
        System.out.println("user2.getName() = " + user2.getName());
        System.out.println("user2.getPassword() = " + user2.getPassword());

        System.out.println("=== Successfully get method end ===");
    }
}

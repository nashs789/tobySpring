package me.inbok.tobyspring.Dao;

import me.inbok.tobyspring.common.ConnectionMaker;
import me.inbok.tobyspring.common.DataSource;
import me.inbok.tobyspring.common.User;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.*;

public class UserDao {
    private SimpleDriverDataSource dataSource;

    public void setConnectionMaker(SimpleDriverDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement ps = connection.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    public User get(String id)throws SQLException {
        Connection connection = dataSource.getConnection();

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

    public void deleteAll() throws SQLException{
        Connection connection = dataSource.getConnection();

        PreparedStatement ps = connection.prepareStatement(
                "delete from users");

        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    public int getCount() throws SQLException{
        Connection connection = dataSource.getConnection();

        PreparedStatement ps = connection.prepareStatement(
                "select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        connection.close();

        return count;
    }
}

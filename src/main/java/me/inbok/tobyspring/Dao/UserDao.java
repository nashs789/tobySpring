package me.inbok.tobyspring.Dao;

import me.inbok.tobyspring.common.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.*;

public class UserDao {
    private SimpleDriverDataSource dataSource;
    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void setConnectionMaker(SimpleDriverDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(final User user) throws SQLException {
        this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePrepareStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());

                return ps;
            }
        });
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
        this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePrepareStatement(Connection c) throws SQLException {
                return c.prepareStatement("delete from users");
            }
        });
    }

    public int getCount() throws SQLException{
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("select count(*) from users");

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch(SQLException sqle){
            throw sqle;
        } finally {
            if(ps != null){
                try{
                    ps.close();
                } catch(SQLException sqle){

                }
            }
            if(connection != null){
                try{
                    connection.close();
                } catch(SQLException sqle){

                }
            }
            if(rs != null){
                try{
                    rs.close();
                } catch(SQLException sqle){

                }
            }
        }
    }
}

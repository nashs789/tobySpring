package me.inbok.tobyspring.Dao;

import me.inbok.tobyspring.common.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.*;
import java.util.List;

public class UserDao {
    private SimpleDriverDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(SimpleDriverDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    public void setConnectionMaker(SimpleDriverDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(final User user) throws SQLException {
        /*this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());

                return ps;
            }
        });*/
        this.jdbcTemplate.update("insert into users(id, name, password) values(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
    }

    public User get(String id)throws SQLException {
        return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(rs.getString("id"));
                        user.setName(rs.getString("name"));
                        user.setPassword(rs.getString("password"));
                        return user;
                    }
                });
    }

    public List<User> getAll(){
        return this.jdbcTemplate.query("select * from users order by id", new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            });
    }

    public void deleteAll() throws SQLException{
        /*this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection c) throws SQLException {
                return c.prepareStatement("delete from users");
            }
        });*/
        this.jdbcTemplate.update("delete from users");
    }

    public int getCount() throws SQLException{
        // 이거 익명 객체로 만드려면 query 메소드의 파라미터로 2개의 익명 객체가 필요함 = 지옥
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }
}

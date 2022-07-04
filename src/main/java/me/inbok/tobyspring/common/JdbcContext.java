package me.inbok.tobyspring.common;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
    private SimpleDriverDataSource dataSource;

    public void setDataSource(SimpleDriverDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;

        try{
            c = dataSource.getConnection();
            ps = stmt.makePrepareStatement(c);

            ps.executeUpdate();
        } catch(SQLException sqle){
            throw sqle;
        } finally {
            if(ps != null) { try { ps.close(); } catch (SQLException sqle) {} }
            if(c != null) { try { c.close(); } catch (SQLException sqle) {} }
        }
    }
}

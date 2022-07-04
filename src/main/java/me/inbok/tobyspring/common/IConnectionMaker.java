package me.inbok.tobyspring.common;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}



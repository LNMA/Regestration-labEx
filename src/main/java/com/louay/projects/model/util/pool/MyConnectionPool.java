package com.louay.projects.model.util.pool;

import com.louay.projects.model.util.queue.MyList;
import com.louay.projects.model.util.queue.MyQueue;

import java.sql.*;

public class MyConnectionPool {
    private MyList<ConnectionWrapper> connection;
    private String url;
    private String username;
    private String password;


    public MyConnectionPool(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        connection = new MyQueue<>(10);
    }

    public Connection getConnection() throws SQLException {
        if (this.connection.isEmpty()) {
            return new ConnectionWrapper(DriverManager.getConnection(url, username, password)).getConnection();
        } else {
            ConnectionWrapper connectionWrapper = this.connection.dequeue();
            if (connectionWrapper.isAlive()) {
                return connectionWrapper.getConnection();
            } else {
                connectionWrapper.getConnection().close();
                return getConnection();
            }
        }
    }

    public ResultSet selectResult(String query, String key) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setString(1, key);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return resultSet;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}

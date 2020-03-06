package com.louay.projects.registration.model.util.pool;

import com.louay.projects.registration.model.util.queue.MyList;
import com.louay.projects.registration.model.util.queue.MyQueue;

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

    public void updateQuery(String query,Object...objects){
        try{
            PreparedStatement update = this.getConnection().prepareStatement(query);
            for (int i = 0; i < objects.length ; i++) {
                if (objects[i] instanceof String){
                    update.setString((i+1), (String)objects[i]);
                }else{
                    if (objects[i] instanceof Integer){
                        update.setInt((i+1),(Integer)objects[i]);
                    }else {
                        if (objects[i] instanceof java.sql.Date){
                            update.setDate((i+1),(java.sql.Date)objects[i]);
                        }
                    }
                }
            }
            update.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
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

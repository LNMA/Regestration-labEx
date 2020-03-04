package com.louay.projects.model.util;

import java.sql.*;

public class MyConnectionPool{
    private MyQueue<ConnectionWrapper> connection;
    private String driver = "jdbc:mysql";
    private String port = "3306";
    private String host = "localhost";
    private String schema = "regestration";
    private String username = "root";
    private String password = "1729384#General";


    public MyConnectionPool() {
        connection = new MyQueue<>(10);
    }

    public MyConnectionPool(String schema) {
        this.schema = schema;
        connection = new MyQueue<>(10);
    }

    public MyConnectionPool(String username, String password) {
        this.username = username;
        this.password = password;
        connection = new MyQueue<>(10);
    }

    public MyConnectionPool(String driver, String port, String host, String schema, String username, String password) {
        this.driver = driver;
        this.port = port;
        this.host = host;
        this.schema = schema;
        this.username = username;
        this.password = password;
        connection = new MyQueue<>(10);
    }

    public Connection getConnection () throws SQLException {
        if (this.connection.isEmpty()){
            return  new ConnectionWrapper(DriverManager.getConnection(driver+"://"+host+":"+port+"/"+schema , username , password)).getConnection();
        }else{
            ConnectionWrapper connectionWrapper = this.connection.dequeue();
            if (connectionWrapper.isAlive()){
                return connectionWrapper.getConnection();
            }else{
                connectionWrapper.getConnection().close();
                return getConnection();
            }
        }
    }

    public ResultSet prepareStatementQuery(String query,String key){
        ResultSet resultSet = null;
        try{
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(query);
            preparedStatement.setString(1,key);
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }


    public String getDriver() {
        return driver;
    }

    public String getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public String getSchema() {
        return schema;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}

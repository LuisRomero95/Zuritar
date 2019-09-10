
package com.encomienda.servicios;

import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class Conexion {
    
    public DriverManagerDataSource conectar(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/encomienda2");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
}

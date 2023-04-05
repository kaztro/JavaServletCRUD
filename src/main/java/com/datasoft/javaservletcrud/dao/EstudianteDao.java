/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datasoft.javaservletcrud.dao;

import com.datasoft.javaservletcrud.bean.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author germa
 */
public class EstudianteDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/estudiante";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    
    private static final String insertQ = "INSERT INTO ESTUDIANTE (nombre, apellido) VALUES " 
            + "(?, ?);";
    
    private static final String selectAllQ = "SELECT * FROM ESTUDIANTE";

    public EstudianteDao() {
    }
    
    /**
     *
     * @return connection
     */
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("jdbcDriver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }
    
    /**
     *Inserta un estudiante nuevo en la base de datos
     * @param estudiante
     * @throws SQLException
     */
    public void insertarE(Estudiante estudiante) throws SQLException {
        try (Connection connection = getConnection();
                PreparedStatement pStatement = connection.prepareStatement(insertQ)) {
            pStatement.setString(1, estudiante.getNombre());
            pStatement.setString(2, estudiante.getApellido());
        }
    }
    
    /**
     *Retorna una lista de los estudiantes de la base de datos
     * @return estudiantes
     */
    public List<Estudiante> selectAllE() {
        
        List<Estudiante> estudiantes = new ArrayList<>();
        
        try (Connection connection = getConnection();
                PreparedStatement pStatement = connection.prepareStatement(selectAllQ);) {
            ResultSet rs = pStatement.executeQuery();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                estudiantes.add(new Estudiante(id, nombre, apellido));
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        
        return estudiantes;
    }
    
}

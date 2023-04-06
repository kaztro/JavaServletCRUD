/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.datasoft.javaservletcrud.web;

import com.datasoft.javaservletcrud.dao.EstudianteDAO;
import com.datasoft.javaservletcrud.model.Estudiante;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author germa
 */
@WebServlet("/")
public class EstudianteServlet extends HttpServlet {

    private EstudianteDAO estudianteDAO;

    @Override
    public void init() {
        estudianteDAO = new EstudianteDAO();
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String r = request.getServletPath();
        
        try {
            switch(r) {
                case "/new":
                    nuevoEstudiante(request, response);
                    break;
                case "/insert":
                    insertarEstudiante(request, response);
                    break;
                default:
                    listaEstudiantes(request, response);
                    break;
            } 
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listaEstudiantes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Estudiante> estudiantes = estudianteDAO.selectAllE();
        request.setAttribute("estudiantes", estudiantes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista-estudiantes.jsp");
        dispatcher.forward(request, response);
    }

    private void nuevoEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("nuevo-estudiante.jsp");
        dispatcher.forward(request, response);
    }

    private void insertarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        Estudiante nEstudiante = new Estudiante(nombre, apellido);
        estudianteDAO.insertarE(nEstudiante);
        response.sendRedirect("list");
    }
}

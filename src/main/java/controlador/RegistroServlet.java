/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.EscuelaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.Escuela;


/**
 *
 * @author Labing I5
 */
public class RegistroServlet extends HttpServlet {
    private EscuelaDAO dao;

    @Override
    public void init() throws ServletException {
         this.dao = new EscuelaDAO();
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
        try {
            RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
            if(request.getParameter("borrar")!= null){
                try {
                    String id = request.getParameter("borrar");
                    Escuela escuela = this.dao.find(Integer.parseInt(id));
                    this.dao.delete(escuela);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistroServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(request.getParameter("editar")!=null){
                String id = request.getParameter("editar");
                Escuela escuela=null;
                try {
                    escuela = this.dao.find(Integer.parseInt(id));
                } catch (SQLException ex) {
                    Logger.getLogger(RegistroServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("Escuela", escuela);
                
            }
            ArrayList<Escuela> escuelas =(ArrayList) this.dao.findAll();
            request.setAttribute("lista", escuelas);
            rq.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegistroServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
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
        
        if(request.getParameter("Boton1")!=null){
            System.out.println("Presiona el boton 1");
        }else{
          System.out.println("Presiona el boton21");
        }
        
//        String[] vehiculos = request.getParameterValues("vehicle");
//        for (String vehiculo : vehiculos) {
//            System.out.println(vehiculo);
//        }
       if(request.getParameter("editar")!=null){
          //Estoy editando XD
          String codigo = request.getParameter("codigo");
          String nombre = request.getParameter("nombre");
          if(nombre != null && nombre.length()>0){
              try {
                  Escuela escuela = this.dao.find(Integer.parseInt(codigo));
                  escuela.setNombre(nombre);
                  this.dao.update(escuela);
              } catch (SQLException ex) {
                  Logger.getLogger(RegistroServlet.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          
       } else{
          //Estoy registrando uno 
          
          String codigo = request.getParameter("codigo");
          String nombre = request.getParameter("nombre");
          //Validaciones - SQL Inyection - Luego
          if(nombre != null && nombre.length()>0){
              try {
                  Escuela escuela =
                          new Escuela(Integer.parseInt(codigo), nombre);
                  if(!this.dao.insert(escuela)){
                      response.sendRedirect("index.jsp?error=ErrorDatos");
                  }  
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
           
          }
       }
       response.sendRedirect("RegistroServlet");
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

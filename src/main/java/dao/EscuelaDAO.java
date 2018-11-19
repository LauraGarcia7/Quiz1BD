/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vo.Escuela;

/**
 *
 * @author Labing
 */
public class EscuelaDAO implements IBaseDatos<Escuela> {

    public Escuela find(int codigo) throws SQLException {
        Escuela resultado = null;
        String query = "Select * from Escuela Where codigo =" + codigo;
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String nombre = null;
            if (rs.next()) {
                resultado = new Escuela();
                id = rs.getInt("codigo");
                resultado.setCodigo(id);
                nombre = rs.getString("nombre");
                resultado.setNombre(nombre);

            }
            st.close();
        } catch (SQLException e) {
            System.out.println("Problemas al obtener escula");
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public List findAll() throws SQLException {
        List<Escuela> escuelas = null;
        String query = "SELECT * FROM Escuela";
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String nombre = null;
            while (rs.next()) {
                if (escuelas == null) {
                    escuelas = new ArrayList<Escuela>();
                }
                Escuela registro = new Escuela();
                id = rs.getInt("cedula");
                registro.setCodigo(id);
                nombre = rs.getString("nombre");
                registro.setNombre(nombre);
                escuelas.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Departamentos");
            e.printStackTrace();
        }

        return escuelas;
    }

    @Override
    public boolean insert(Escuela escuela) throws SQLException {
        boolean result = false;
        Connection connection = Conexion.getConnection();
        String query = " insert into Escuela" + " values (?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, escuela.getCodigo());
            preparedStmt.setString(2, escuela.getNombre());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Escuela escuela) throws SQLException {
        boolean result = false;
        Connection connection = Conexion.getConnection();
        String query
                = "update Escuela set nombre = ?, where cedula = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, escuela.getNombre());
            preparedStmt.setInt(2, escuela.getCodigo());

            if (preparedStmt.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Escuela escuela) throws SQLException {
        boolean result = false;
        Connection connection = Conexion.getConnection();
        String query = "delete from Escuela where codigo = ?";
        System.out.println(query + " " + escuela.getCodigo());
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, escuela.getCodigo());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
/*
    public Escuela find(int codigo) throws SQLException {
        Escuela resultado = null;
        String query = "Select * from Escuela Where codigo =" + codigo;
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String nombre = null;
            if (rs.next()) {
                resultado = new Escuela();
                id = rs.getInt("codigo");
                resultado.setCodigo(id);
                nombre = rs.getString("nombre");
                resultado.setNombre(nombre);

            }
            st.close();
        } catch (SQLException e) {
            System.out.println("Problemas al obtener Escuela");
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public List findAll() throws SQLException {
        List<Persona> personas = null;
        String query = "SELECT * FROM Persona";
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String nombre = null, apellido = null;
            while (rs.next()) {
                if (personas == null) {
                    personas = new ArrayList<Persona>();
                }

                Persona registro = new Persona();
                id = rs.getInt("cedula");
                registro.setCedula(id);
                nombre = rs.getString("nombre");
                registro.setNombre(nombre);
                apellido = rs.getString("apellido");
                registro.setApellido(apellido);
                personas.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Departamentos");
            e.printStackTrace();
        }

        return personas;
    }

    @Override
    public boolean insert(Persona persona) throws SQLException {
        boolean result = false;
        Connection connection = Conexion.getConnection();
        String query = " insert into Persona" + " values (?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, persona.getCedula());
            preparedStmt.setString(2, persona.getNombre());
            preparedStmt.setString(3, persona.getApellido());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Persona persona) throws SQLException {
        boolean result = false;
        Connection connection = Conexion.getConnection();
        String query
                = "update Persona set nombre = ?, apellido = ? where cedula = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, persona.getNombre());
            preparedStmt.setString(2, persona.getApellido());
            preparedStmt.setInt(3, persona.getCedula());

            if (preparedStmt.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Escuela escuela) throws SQLException {
        boolean result = false;
        Connection connection = Conexion.getConnection();
        String query = "delete from Escuela where codigo = ?";
        System.out.println(query + " " + escuela.getCodigo());
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, escuela.getCodigo());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }*/

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.seguridad;

import Controlador.seguridad.Alumnos; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import Modelo.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author visitante
 */
public class AlumnosDAO {

    private static final String SQL_SELECT = "SELECT CARNET_ALUMNOS, NOMBRE_ALUMNOS, DIRECCION_ALUMNOS, TELEFONO_ALUMNOS, EMAIL_ALUMNOS, ESTATUS_ALUMNOS FROM alumnos";
    private static final String SQL_INSERT = "INSERT INTO alumnos(NOMBRE_ALUMNOS, DIRECCION_ALUMNOS, TELEFONO_ALUMNOS, EMAIL_ALUMNOS, ESTATUS_ALUMNOS) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE alumnos SET NOMBRE_ALUMNOS=?, DIRECCION_ALUMNOS=?, TELEFONO_ALUMNOS=?, EMAIL_ALUMNOS=?, ESTATUS_ALUMNOS=? WHERE CARNET_ALUMNOS = ?";
    private static final String SQL_DELETE = "DELETE FROM alumnos WHERE CARNET_ALUMNOS=?";
    private static final String SQL_QUERY = "SELECT CARNET_ALUMNOS, NOMBRE_ALUMNOS, DIRECCION_ALUMNOS, TELEFONO_ALUMNOS, EMAIL_ALUMNOS, ESTATUS_ALUMNOS FROM alumnos WHERE CARNET_ALUMNOS = ?";

    public List<Alumnos> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Alumnos alumnos = null;
        List<Alumnos> alumnoss = new ArrayList<Alumnos>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int carnetAlumno = rs.getInt("CARNET_ALUMNOS");
                String nombreAlumnos = rs.getString("NOMBRE_ALUMNOS");
                String direccionAlumno = rs.getString("DIRECCION_ALUMNOS");
                String telefonoAlumno = rs.getString("TELEFONO_ALUMNOS");
                String emailAlumno = rs.getString("EMAIL_ALUMNOS");
                String estatusAlumno = rs.getString("ESTATUS_ALUMNOS");
                
                alumnos = new Alumnos();
                alumnos.setCarnetAlumno(carnetAlumno);
                alumnos.setNombreAlumno(nombreAlumnos);
                alumnos.setDireccionAlumno(direccionAlumno);
                alumnos.setTelefonoAlumno(telefonoAlumno);
                alumnos.setEmailAlumno(emailAlumno);
                alumnos.setEstatusAlumno(estatusAlumno);
                
                alumnoss.add(alumnos);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return alumnoss;
    }

    public int insert(Alumnos alumnos) { 
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, alumnos.getNombreAlumno());
            stmt.setString(2, alumnos.getDireccionAlumno());
            stmt.setString(3, alumnos.getTelefonoAlumno());
            stmt.setString(4, alumnos.getEmailAlumno());
            stmt.setString(5, alumnos.getEstatusAlumno());

            System.out.println("ejecutando query: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(Alumnos alumnos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, alumnos.getNombreAlumno());
            stmt.setString(2, alumnos.getDireccionAlumno());
            stmt.setString(3, alumnos.getTelefonoAlumno());
            stmt.setString(4, alumnos.getEmailAlumno());
            stmt.setString(5, alumnos.getEstatusAlumno());
            stmt.setInt(6, alumnos.getCarnetAlumno());

            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado: " + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Alumnos alumnos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, alumnos.getCarnetAlumno());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public Alumnos query(Alumnos alumnos) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Alumnos> alumnoss = new ArrayList<Alumnos>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, alumnos.getCarnetAlumno());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int carnetAlumno = rs.getInt("CARNET_ALUMNOS");
                String nombreAlumno = rs.getString("NOMBRE_ALUMNOS");
                String direccionAlumno = rs.getString("DIRECCION_ALUMNOS");
                String telefonoAlumno = rs.getString("TELEFONO_ALUMNOS");
                String emailAlumno = rs.getString("EMAIL_ALUMNOS");
                String estatusAlumno = rs.getString("ESTATUS_ALUMNOS");
                
                alumnos = new Alumnos();
                alumnos.setCarnetAlumno(carnetAlumno);
                alumnos.setNombreAlumno(nombreAlumno);
                alumnos.setDireccionAlumno(direccionAlumno);
                alumnos.setTelefonoAlumno(telefonoAlumno);
                alumnos.setEmailAlumno(emailAlumno);
                alumnos.setEstatusAlumno(estatusAlumno);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return alumnos; 
    }
        
}

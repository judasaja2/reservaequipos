package udea.drai.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import udea.drai.model.dao.RecursoDAO;
import udea.drai.model.dao.ds.BDConexion;
import udea.drai.model.dao.ds.DataSource;
import udea.drai.model.dto.Recurso;
import udea.drai.model.dto.SoftwareType;

public class RecursoDAOJdbc implements RecursoDAO{

    private BDConexion conexion;
    private Connection con = null;

    public RecursoDAOJdbc() {
        this.conexion = new BDConexion();
//        this.conexion = BDConexion.getInstance(false);
    }

    public void cerrarConexion() {
        this.conexion.closeConnection();
    }

    /**
     * Metodo que retorna un recurso dado su identificador.
     * @param identificador del software
     * @return 
     */
    public Recurso getRecurso(int identificador) {
        Recurso r = null;
        try {
            Connection c = this.conexion.getConnection();

            PreparedStatement ps = c.prepareStatement(BDConexion.getStatement("obtenerRecurso"));

            ps.setInt(1, identificador);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                r = new Recurso();
                r.setIdRecurso(identificador);
                r.setClasificacion(rs.getString("clasificacion"));
                r.setNombre(rs.getString("nombre"));
                r.setIp(rs.getString("ip"));
                r.setMac(rs.getString("mac"));
            }

            ps.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return r;
    }
    
    /**
     * 
     * @param identificador del software
     * @return 
     */
    public Recurso getRecurso2(int identificador){
        Recurso recurso = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DataSource.getInstance().getConnection();
            ps = connection.prepareStatement(BDConexion.getStatement("obtenerRecurso"));
            ps.setInt(1, identificador);
            rs = ps.executeQuery();
            while (rs.next()) {
                recurso = new Recurso();
                recurso.setIdRecurso(identificador);
                recurso.setClasificacion(rs.getString("clasificacion"));
                recurso.setNombre(rs.getString("nombre"));
                recurso.setIp(rs.getString("ip"));
                recurso.setMac(rs.getString("mac"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecursoDAOJdbc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            };
        }        
        return recurso;
    }

    public int obtenerDondeEstaIncluido(String recurso) {
        int incluido = 0;
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("obtenerDondeEstaIncluido"));
            ps.setString(1, recurso);
            ResultSet incluidoEn = ps.executeQuery();
            if (incluidoEn.next()) {
                incluido = incluidoEn.getInt("NMINCLUIDOEN");
                ps.close();
                return incluido;
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return incluido;
    }

    public Vector<Recurso> obtenerRecursosIncluidos(String recurso) {
        Vector incluidos = new Vector(100, 50);
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("obtenerRecursosIncluidos"));
            ps.setString(1, recurso);
            ResultSet recursosIncluidos = ps.executeQuery();
            while (recursosIncluidos.next()) {
                Recurso auxiliar = new Recurso();
                auxiliar.setIdRecurso(recursosIncluidos.getInt("NMIDRECURSO"));
                auxiliar.setNombre(recursosIncluidos.getString("DSNOMBRE"));
                auxiliar.setIncluidoEn(recursosIncluidos.getInt("NMINCLUIDOEN"));
                incluidos.addElement(auxiliar);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return incluidos;
    }

    public Vector<SoftwareType> obtenerTiposDeSoftware() {
        Vector incluidos = new Vector(10, 5);
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("obtenertiposoftware"));
            ResultSet recursosIncluidos = ps.executeQuery();
            while (recursosIncluidos.next()) {
                SoftwareType auxiliar = new SoftwareType();
                auxiliar.setCodeSWT(recursosIncluidos.getInt("nmcodigo_software"));
                auxiliar.setNameSWT(recursosIncluidos.getString("dsnombre_software"));
                incluidos.addElement(auxiliar);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return incluidos;
    }

    public Vector getNuevoSW(String string) {
        Vector lista = new Vector(10, 5);
        System.out.println("inicio");
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("codigoSoftware"));
            ps.setString(1, string);
            ResultSet recursosIncluidos = ps.executeQuery();
            while (recursosIncluidos.next()) {
                SoftwareType auxiliar = new SoftwareType();
                lista.addElement(recursosIncluidos.getString("nmcodigo_pc"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
    public Vector getNuevoSW2(String string) {
        Vector lista = new Vector(10, 5);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DataSource.getInstance().getConnection();
            ps = connection.prepareStatement(BDConexion.getStatement("codigoSoftware"));
            ps.setString(1, string);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.addElement(rs.getString("nmcodigo_pc"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecursoDAOJdbc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            };
        }
        return lista;
    }    

    public void borrarDatrosConCedula(String string) {
        try {
            Connection c = this.conexion.getConnection();

            PreparedStatement ps = c.prepareStatement(BDConexion.getStatement("borrarceduladeagendas"));
            ps.setString(1, string);
            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "error al borrar1");
        }
    }

    public void borrarDatrosConCedula2(String string) {
        try {
            Connection c = this.conexion.getConnection();

            PreparedStatement ps = c.prepareStatement(BDConexion.getStatement("borrarceduladereservas"));
            ps.setString(1, string);
            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "error al borrar2");
        }
    }

    public void IngresarDatosTables() {
        try {
            Connection c = this.conexion.getConnection();
            PreparedStatement ps = c.prepareStatement(BDConexion.getStatement("insertardatosprueba"));
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "error al Insertar  datos1");
        }
    }

    public void IngresarDatosTables2() {
        try {
            Connection c = this.conexion.getConnection();
            PreparedStatement ps = c.prepareStatement(BDConexion.getStatement("insertardatosprueba2"));
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "error al Insertar  datos2");
        }
    }

    public void mostrarDatosTablas() {
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("mostrardatostables"));
            ResultSet incluidoEn = ps.executeQuery();
            while (incluidoEn.next()) {
                System.out.println(incluidoEn.getInt("nmcodigo_software") + "tabla software");
                System.out.println(incluidoEn.getString("dsnombre_software") + "tabla software2");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString() + "Error al mirar tablas1");
        }
    }

    public void mostrarDatosTablas2() {
        try {
            Connection connection = this.conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("mostrardatostables2"));
            ResultSet incluidoEn = ps.executeQuery();
            while (incluidoEn.next()) {
                System.out.println(incluidoEn.getInt("nmcodigo_softwareB") + "tabla pc");
                System.out.println(incluidoEn.getInt("nmcodigo_pc") + "tabla pc2");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString() + "Error al mirar tablas2");
        }
    }

    public void borrarDatosTablapcXsoftware() {
        try {
            Connection c = this.conexion.getConnection();

            PreparedStatement ps = c.prepareStatement(BDConexion.getStatement("borrartablasoftwarexpc"));
            ps.setString(1, "10004");
            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "error al borrar en tabla");
        }
    }
}

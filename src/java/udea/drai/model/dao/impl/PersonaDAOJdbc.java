  package udea.drai.model.dao.impl;
  
  import java.io.PrintStream;
  import java.sql.Connection;
  import java.sql.PreparedStatement;
  import java.sql.ResultSet;
  import java.sql.SQLException;
import udea.drai.model.dao.ds.BDConexion;
  
  public class PersonaDAOJdbc
  {
    private BDConexion conexion;
    private Connection connection;
  
    public PersonaDAOJdbc()
    {
      this.conexion = new BDConexion(false);
    }
  
    public int numerodeRecursos(String identificacion, String fechaActual)
    {
      int contador = 0;
      try
      {
        Connection connection = this.conexion.getConnection();
        PreparedStatement ps = connection.prepareStatement(BDConexion.getStatement("numeroRecursos"));
  
        ps.setString(1, identificacion);
        ps.setString(2, fechaActual);
  
        ResultSet persona = ps.executeQuery();
        while (persona.next()) {
          contador = persona.getInt("recursos");
        }
        ps.close();
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
  
      return contador;
    }
  
    public int numeroHorasRecurso(String identificacion, String fecha, int recurso)
    {
      Connection c = this.conexion.getConnection();
      int horas = 0;
      try
      {
        PreparedStatement ps = c.prepareStatement(BDConexion.getStatement("numeroHorasRecurso"));
  
        ps.setString(1, identificacion);
        ps.setString(2, fecha);
        ps.setInt(3, recurso);
  
        ResultSet rs = ps.executeQuery();
  
        while (rs.next()) {
          horas = rs.getInt("horas");
        }
        ps.close();
      }
      catch (Exception e)
      {
        System.err.println(e.getMessage());
      }
  
      return horas;
    }
  
    public void cerrarConexion()
    {
      this.conexion.closeConnection();
    }
  }

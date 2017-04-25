package ClienteCrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import javax.swing.*;

public class Cliente
{
   private String nome;
   private int RG;
   private String CPF;
   private int telefone;
   private String sexo;
   private String email;
   private int datadnascimento;
   private int numeroderegistro;
   private String estadoemissor;
   private int validadeCNH;
   private int codigoCliente;

   public Cliente()
   {
   }
   public Cliente(int codigoCliente)
   {
      this.codigoCliente = codigoCliente;
   }
   public Cliente(String nome, int RG, String CPF, int telefone, String sexo, String email, int datadnascimento, int numeroderegistro, String estadoemissor, int validadeCNH, int codigoCliente)
   {
      setNome(nome);
      setRG(RG);
      setCPF(CPF);
      setTelefone(telefone);
      setSexo(sexo);
      setEmail(email);
      setDatadNascimento(datadnascimento);
      setNumerodeRegistro(numeroderegistro);
      setEstadoemissor(estadoemissor);
      setValidadeCNH(validadeCNH);
      setCodigoCliente(codigoCliente);
   }
   public String getNome()
   {
      return nome;
   }
   public int getRG()
   {
      return RG;
   }
   public String getCPF()
   {
      return  CPF;
   }
   public int getTelefone()
   {
      return telefone;
   }
   public String getSexo()
   {
      return sexo;
   }
   public String getEmail()
   {
      return email;
   }
   public int getDatadNascimento()
   {
      return datadnascimento;
   }
   public int getNumerodeRegistro()
   {
      return numeroderegistro;
   }
   public String getEstadoemissor()
   {
      return estadoemissor;
   }
   public int getValidadeCNH()
   {
      return validadeCNH;
   }
   public int getCodigoCliente()
   {
      return codigoCliente;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }
   public void setRG(int RG)
   {
      this.RG = RG;
   }
   public void setCPF(String CPF)
   {
      this.CPF = CPF;
   }
   public void setTelefone(int telefone)
   {
      this.telefone = telefone;
   }
   public void setSexo(String sexo)
   {
      this.sexo = sexo;
   }
   public void setEmail(String email)
   {
      this.email = email;
   }
   public void setDatadNascimento(int datadnascimento)
   {
      this.datadnascimento = datadnascimento;
   }
   public void setNumerodeRegistro(int numeroderegistro)
   {
      this.numeroderegistro = numeroderegistro;
   }
   public void setEstadoemissor(String estadoemissor)
   {
      this.estadoemissor = estadoemissor;
   }
   public void setValidadeCNH(int validadeCNH)
   {
      this.validadeCNH = validadeCNH;
   }
   public void setCodigoCliente(int codigoCliente)
   {
      this.codigoCliente = codigoCliente;
   }
   public String toString()
   {
      return "ID Cliente: " + getCodigoCliente() + "\n" + "Nome: " + getNome() + "\n" + "RG: " + getRG()+ "\n" + "CPF: " + getCPF()+ "\n" + "Telefone: " + getTelefone()+ "\n" + "Sexo: " + getSexo()+ "\n" + "E-mail: " + getEmail()+ "\n" + "Data de Nascimento: " + getDatadNascimento()+ "\n" + "Numero do Registro: " + getNumerodeRegistro()+ "\n" + "Estado Emissor: " + getEstadoemissor()+ "\n" + "Validade CNH: " + getValidadeCNH();
   }
   public void cadastrarCliente(Connection conn)
   {
      String sqlInsert = "INSERT INTO Cliente(idCliente,Nome,RG,CPF,Telefone,Sexo,Email,DatadeNascimento,NumerodeRegistro,EstadoEmissor,validadeCNH) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      PreparedStatement stm = null;
      try
      {
      //
      // Inclusao dos dados na tabela CLIENTE
      //
         stm = conn.prepareStatement(sqlInsert);
         stm.setInt(1, getCodigoCliente());
         stm.setString(2, getNome());
         stm.setInt(3, getRG());
         stm.setString(4, getCPF());
         stm.setInt(5, getTelefone());
         stm.setString(6, getSexo());
         stm.setString(7, getEmail());
         stm.setInt(8, getDatadNascimento());
         stm.setInt(9, getNumerodeRegistro());
         stm.setString(10, getEstadoemissor());
         stm.setInt(11, getValidadeCNH());
         
         stm.execute();
      }
      catch (Exception e)
      {
         e.printStackTrace();
         try
         {
            conn.rollback();
         }
         catch (SQLException e1)
         {
            System.out.print(e1.getStackTrace());
            JOptionPane.showMessageDialog(null, "ID: " + getCodigoCliente() + " já está cadastro");
         }
      }
      finally
      {
         if (stm != null)
         {
            try
            {
               stm.close();
            }
            catch (SQLException e1)
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
   }
   public void consultarCliente()
   {
   }
   public boolean carregar(Connection conn)
   {
      String sqlSelect = "SELECT * FROM Cliente WHERE Cliente.idCliente = ?";
      PreparedStatement stm = null;
      boolean x = true;
      ResultSet rs = null;
      try
      {
         stm = conn.prepareStatement(sqlSelect);
         stm.setInt(1, getCodigoCliente());
         rs = stm.executeQuery();
         if (rs.next())
         {
            this.setNome(rs.getString(2));
            this.setRG(rs.getInt(3));
            this.setCPF(rs.getString(4));
            this.setTelefone(rs.getInt(5));
            this.setSexo(rs.getString(6));
            this.setEmail(rs.getString(7));
            this.setDatadNascimento(rs.getInt(8));  
            this.setNumerodeRegistro(rs.getInt(9));
            this.setEstadoemissor(rs.getString(10));
            this.setValidadeCNH(rs.getInt(11));
            
         }
         else
         {
            x = false;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         try
         {
            conn.rollback();
         }
         catch (SQLException e1)
         {
            System.out.print(e1.getStackTrace());
         }
      }
      finally
      {
         if (stm != null)
         {
            try
            {
               stm.close();
            }
            catch (SQLException e1)
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
      return x;
   }
   public ArrayList<Cliente> dados(Connection conn, int idCliente)
   {
      String sqlSelect = "SELECT * FROM Cliente WHERE Cliente.idCliente = ?";
      PreparedStatement stm = null;
      ResultSet rs = null;
      ArrayList<Cliente> c = new ArrayList();
      try
      {
         stm = conn.prepareStatement(sqlSelect);
         stm.setInt(1, getCodigoCliente());
         rs = stm.executeQuery();
         if (rs.next())
         {
            Cliente cl = new Cliente(idCliente);
            cl.setCodigoCliente(rs.getInt(1));
            cl.setNome(rs.getString(2));
            cl.setRG(rs.getInt(3));
            cl.setCPF(rs.getString(4));
            cl.setTelefone(rs.getInt(5));
            cl.setSexo(rs.getString(6));
            cl.setEmail(rs.getString(7));
            cl.setDatadNascimento(rs.getInt(8));  
            cl.setNumerodeRegistro(rs.getInt(9));
            cl.setEstadoemissor(rs.getString(10));
            cl.setValidadeCNH(rs.getInt(11));
            c.add(cl);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         try
         {
            conn.rollback();
         }
         catch (SQLException e1)
         {
            System.out.print(e1.getStackTrace());
         }
      }
      finally
      {
         if (stm != null)
         {
            try
            {
               stm.close();
            }
            catch (SQLException e1)
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
      return (c);
   }

}
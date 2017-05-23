package arqdesis_aula02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//dao
public class ClienteDAO {
	
	//Classe DAO
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	// Obtém conexão com o banco de dados
	public Connection obtemConexao() throws SQLException {
		return DriverManager
				.getConnection("jdbc:mysql://localhost/tutorial?user=alunos&password=alunos");
	}

	public void incluir(int id, String nome, String fone) {
		String sqlInsert = "INSERT INTO cliente(id, nome, fone) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, id);
			stm.setString(2, nome);
			stm.setString(3, fone);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(int id, String nome, String fone) {
		String sqlUpdate = "UPDATE cliente SET nome=?, fone=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, nome);
			stm.setString(2, fone);
			stm.setInt(3, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM cliente WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList carregar(int id) {
		ArrayList retorno = new ArrayList();
		String sqlSelect = "SELECT nome, fone FROM cliente WHERE cliente.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					retorno.add(rs.getString("nome"));
					retorno.add(rs.getString("fone"));
				} else {
					retorno.add(null);
					retorno.add(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return retorno;
	}
}

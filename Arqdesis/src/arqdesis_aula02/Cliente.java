package arqdesis_aula02;

import java.util.ArrayList;

//model
public class Cliente {
	private int id;
	private String nome;
	private String fone;

	public Cliente(int id, String nome, String fone) {
		this.id = id;
		this.nome = nome;
		this.fone = fone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public void criar() {
		ClienteDAO dao = new ClienteDAO();
		dao.incluir(id, nome, fone);
	}

	public void atualizar() {
		ClienteDAO dao = new ClienteDAO();
		dao.atualizar(id, nome, fone);
	}

	public void excluir() {
		ClienteDAO dao = new ClienteDAO();
		dao.excluir(id);
	}

	public void carregar() {
		ClienteDAO dao = new ClienteDAO();
		@SuppressWarnings("rawtypes")
		ArrayList dados = dao.carregar(id);
		nome = (String) dados.get(0);
		fone = (String) dados.get(1);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", fone=" + fone + "]";
	}
}

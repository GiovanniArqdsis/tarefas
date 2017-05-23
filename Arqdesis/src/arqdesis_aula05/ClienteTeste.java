package arqdesis_aula05;

public class ClienteTeste {
	public static void main(String[] args) {
		Cliente cliente = new Cliente(3, "Michael De Santa", null);
		cliente.criar();
		cliente.carregar();
		System.out.println(cliente);
		cliente.setFone("123456789");
		cliente.atualizar();
		cliente.carregar();
		System.out.println(cliente);
		cliente.excluir();
		cliente.carregar();
		System.out.println(cliente);		
	}
}

package rns.ejerciciofinal.entidades;

import rns.ejerciciofinal.TiendaMusica;

public class Vendedor extends Persona {
	private int codigo;

	public Vendedor(int codigo, String nombre, String apellido) {
		super(nombre, apellido);
		this.codigo = codigo;
	}

	/* Creación de vendedor por defecto */
	public static void crearVendedorDefecto() {
		int codigoVendedor = 1;
		String nombre = "Vendedor";
		String apellido = "1";

		Vendedor vendedor = new Vendedor(codigoVendedor, nombre, apellido);

		TiendaMusica.listaVendedores.put(codigoVendedor, vendedor);
		System.out
				.println("# Se ha cargado un vendedor por defecto con codigo = "
						+ vendedor.getCodigo());

	}

	//COMIENZO DEL BLOQUE DE GETTERS Y SETTERS
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	//FIN DEL BLOQUE DE GETTERS Y SETTERS
}

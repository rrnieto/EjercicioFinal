package rns.ejerciciofinal.entidades;

import rns.ejerciciofinal.TiendaMusica;

public class Venta {

	public static void crearVenta() {
		System.out.println("# CREAR VENTA #");

		if (Vendedor.comprobarVendedores()) {
			System.out.println("Existen vendedores");

		} else {
			System.out.println("No existen vendedores");
			System.out
					.println("Se debe crear al menos un vendedor antes de continuar.");
		}
		TiendaMusica.introducirComando();
	}

	public static void eliminarVenta() {
		System.out.println("# ELIMINAR VENTA #");
		TiendaMusica.introducirComando();
	}

	public static void listarVentas() {
		System.out.println("# LISTAR VENTA #");
		TiendaMusica.introducirComando();
	}

}

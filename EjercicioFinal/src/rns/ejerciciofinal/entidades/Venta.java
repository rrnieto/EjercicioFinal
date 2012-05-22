package rns.ejerciciofinal.entidades;

import rns.ejerciciofinal.TiendaMusica;

public class Venta extends Cliente {

	public static void crearVenta() {
		System.out.println("# CREAR VENTA #");
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

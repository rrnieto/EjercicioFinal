package rns.ejerciciofinal.entidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import rns.ejerciciofinal.TiendaMusica;

public class Venta {

	private int codigoVenta;
	private int codigoVendedor;
	private int codigoCliente;
	private int codigoMusica;
	private boolean activo;

	//Constructor de la clase venta
	public Venta(int codigoVenta, int codigoVendedor, int codigoCliente,
			int codigoMusica) {
		this.codigoVenta = codigoVenta;
		this.codigoVendedor = codigoVendedor;
		this.codigoCliente = codigoCliente;
		this.codigoMusica = codigoMusica;

		this.activo = true;
	}

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

	//COMPROBACION DE FICHERO DE VENTAS, si no existe lo crearemos
	public static void cargarVentas() {

		File ficheroVentas = new File("/ventas.txt");
		if (ficheroVentas.exists()) {
			System.out.println("# Fichero de ventas encontrado");
		} else {
			System.out.println("# No existe fichero de ventas");
			try {
				System.out.println("# Creando fichero de ventas");
				FileWriter fichero = new FileWriter("/ventas.txt");
				System.out.println("Fichero ventas.txt creado con éxito");
				fichero.flush();
			} catch (IOException ioe) {
				System.out
						.println("Se ha producido un error al procesar el fichero de ventas");
			}
		}

	}

	// INICIO BLOQUE DE SETTERS Y GETTERS
	public static void eliminarVenta() {
		System.out.println("# ELIMINAR VENTA #");
		TiendaMusica.introducirComando();
	}

	public static void listarVentas() {
		System.out.println("# LISTAR VENTA #");
		TiendaMusica.introducirComando();
	}

	public int getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(int codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public int getCodigoVendedor() {
		return codigoVendedor;
	}

	public void setCodigoVendedor(int codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public int getCodigoMusica() {
		return codigoMusica;
	}

	public void setCodigoMusica(int codigoMusica) {
		this.codigoMusica = codigoMusica;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	// FIN BLOQUE DE SETTERS Y GETTERS
}

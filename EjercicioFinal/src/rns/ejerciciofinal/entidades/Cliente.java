package rns.ejerciciofinal.entidades;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import rns.ejeciciofinal.constantes.Constantes;
import rns.ejerciciofinal.TiendaMusica;
import rns.ejerciciofinal.utilidades.Utilidades;

public class Cliente extends Lista {
	private String nombre = "";
	private String apellido = "";
	private String dni = "";
	private boolean activo = true;

	public Cliente(String nombre, String apellido, String dni, int codigo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.codigo = codigo;
		this.activo = true;
	}

	public static void listarClientes() {
		Cliente cliente = null;

		for (int i = 1; i <= TiendaMusica.listaClientes.size(); i++) {
			cliente = TiendaMusica.listaClientes.get(i);
			if (cliente.isActivo()) {
				System.out.println("Cliente: " + cliente.getCodigo() + "; Nombre: " + cliente.getNombre()
						+ "; Apellido: " + cliente.getApellido() + "; DNI: " + cliente.getDni());
			}
		}
		TiendaMusica.introducirComando();
	}

	public static void eliminarCliente() {
		Scanner entradaPantalla = null;
		int codigoCliente = 0;
		Cliente clienteEliminado = null;
		String eliminar = "";
		boolean salir = false;

		try {
			System.out.print("Introduzca el codigo de cliente que desea eliminar: ");
			entradaPantalla = new Scanner(System.in);
			codigoCliente = entradaPantalla.nextInt();

			if (TiendaMusica.listaClientes.containsKey(codigoCliente)) {
				clienteEliminado = TiendaMusica.listaClientes.get(codigoCliente);
				if (clienteEliminado.activo) {
					do {
						System.out.println("Está seguro de que desea eliminar el siguiente cliente:");
						System.out.println("Codigo cliente: " + clienteEliminado.getCodigo() + "; Nombre: "
								+ clienteEliminado.getNombre());
						entradaPantalla = new Scanner(System.in);
						eliminar = entradaPantalla.nextLine();
						if (Utilidades.compruebaString(eliminar, Constantes.LISTA_SINO)) {
							if (eliminar.equalsIgnoreCase(Constantes.SI)) {
								TiendaMusica.listaClientes.get(codigoCliente).setActivo(false);
								System.out.println("Cliente eliminado");
							} else {
								System.out.println("Cliente NO eliminado");
							}
							salir = true;
						}
					} while (!salir);
				} else {
					System.out.println("Este cliente no existe");
				}
			} else {
				System.out.println("Este cliente no existe");
			}
		} catch (InputMismatchException ime) {
			System.out.println("Dato introducido no válido, debe introducir un código de cliente");
		}
		TiendaMusica.introducirComando();
	}

	public static void eliminarCliente(int codigoCliente) {
		Scanner entradaPantalla = null;
		Cliente clienteEliminado = null;
		String eliminar = "";
		boolean salir = false;

		if (TiendaMusica.listaClientes.containsKey(codigoCliente)) {
			clienteEliminado = TiendaMusica.listaClientes.get(codigoCliente);
			if (clienteEliminado.activo) {
				do {
					System.out.println("Está seguro de que desea eliminar el siguiente cliente:");
					System.out.println("Codigo cliente: " + clienteEliminado.getCodigo() + "; Nombre: "
							+ clienteEliminado.getNombre());
					entradaPantalla = new Scanner(System.in);
					eliminar = entradaPantalla.nextLine();
					if (Utilidades.compruebaString(eliminar, Constantes.LISTA_SINO)) {
						if (eliminar.equalsIgnoreCase(Constantes.SI)) {
							TiendaMusica.listaClientes.get(codigoCliente).setActivo(false);
							System.out.println("Cliente eliminado");
						} else {
							System.out.println("Cliente NO eliminado");
						}
						salir = true;
					}
				} while (!salir);
			} else {
				System.out.println("Este cliente no existe");
			}
		} else {
			System.out.println("Este cliente no existe");
		}
		TiendaMusica.introducirComando();
	}

	public static void crearCliente() {
		Scanner entradaPantalla = null;
		String nombre;
		String apellido;
		String dni;
		int codigoCliente = 0;

		System.out.print("Introduce el nombre del cliente: ");
		entradaPantalla = new Scanner(System.in);
		nombre = entradaPantalla.nextLine();
		System.out.print("Introduce el apellido del cliente: ");
		entradaPantalla = new Scanner(System.in);
		apellido = entradaPantalla.nextLine();
		System.out.print("Introduce el DNI del cliente:");
		entradaPantalla = new Scanner(System.in);
		dni = entradaPantalla.nextLine();

		codigoCliente = Cliente.obtenerCodigoCliente(TiendaMusica.listaClientes);

		Cliente cliente = new Cliente(nombre, apellido, dni, codigoCliente);

		TiendaMusica.listaClientes.put(codigoCliente, cliente);
		System.out.println("Cliente creado con éxito con código: " + codigoCliente);
		System.out.println("Nombre: " + nombre + "; Apellido: " + apellido + "; DNI: " + dni + "; Código cliente: "
				+ codigoCliente);

		TiendaMusica.introducirComando();

	}

	public static void cargarClientes() {
		Cliente cliente = null;
		String nombre;
		String apellido;
		String dni;
		int codigoCliente = 0;

		for (int i = 0; i < 10; i++) {

			nombre = ("Nombre" + (i + 1));
			apellido = ("Apellido" + (i + 1));
			dni = ("A" + (i + 1));
			codigoCliente = obtenerCodigoCliente(TiendaMusica.listaClientes);
			cliente = new Cliente(nombre, apellido, dni, codigoCliente);
			TiendaMusica.listaClientes.put(codigoCliente, cliente);
			System.out.println("Cliente con codigo: " + codigoCliente + " creado.");
		}
	}

	public static int obtenerCodigoCliente(HashMap<Integer, Cliente> listaClientes) {
		int codigoClienteValido = 0;
		int codigoLeido = 0;

		if (listaClientes.size() == 0) {
			codigoClienteValido = 1;
		} else {
			for (int i = 1; i <= listaClientes.size(); i++) {
				codigoLeido = listaClientes.get(i).getCodigo();
				if (codigoLeido >= codigoClienteValido) {
					codigoClienteValido = codigoLeido + 1;
				}

			}
		}

		return codigoClienteValido;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}

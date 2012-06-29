package rns.ejerciciofinal.entidades;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import rns.ejerciciofinal.TiendaMusica;
import rns.ejerciciofinal.constantes.Constantes;
import rns.ejerciciofinal.utilidades.Utilidades;

//La clase Cliente hereda de la calse Persona
public class Cliente extends Persona {

	private String dni = "";
	//La variable boolean activo la utilizaremos como un marcador para saber si el cliente se ha borrado o no
	private boolean activo = true;
	private int codigo;

	public Cliente(String nombre, String apellido, String dni, int codigo) {
		//Llamada al constructor de la clase padre
		super(nombre, apellido);

		this.dni = dni;
		this.codigo = codigo;
		this.activo = true;
	}

	// Listado de clientes
	public static void listarClientes() {
		Cliente cliente = null;
		int clientesActivos = 0;
		Scanner entradaPantalla;
		String eliminar = null;

		//Recorremos la lista de clientes y mostramos sus datos
		for (int i = 1; i <= TiendaMusica.listaClientes.size(); i++) {
			cliente = TiendaMusica.listaClientes.get(i);
			//Solo mostramos los clientes activos (es decir los que no se han borrado)
			if (cliente.isActivo()) {
				System.out.println("Cliente: " + cliente.getCodigo()
						+ "; Nombre: " + cliente.getNombre() + "; Apellido: "
						+ cliente.getApellido() + "; DNI: " + cliente.getDni());
				clientesActivos++;
			}
		}

		//Si la lista está vacia o no hemos encontrado clientes activos mostraremos un mensaje indicndo que no existen clientes
		if ((TiendaMusica.listaClientes.size() == 0) || clientesActivos == 0) {
			System.out.println("No existe ningún cliente");
			System.out.println("¿Quiere crear un cliente?");
			entradaPantalla = new Scanner(System.in);
			eliminar = entradaPantalla.nextLine();
			//Comprobamos que el comando introducido es un SI o un NO
			if (Utilidades.compruebaString(eliminar, Constantes.LISTA_SINO)) {
				if (eliminar.equalsIgnoreCase(Constantes.SI)) {
					//Llamamos al método de creacion de cliente
					crearCliente();
				}
			}
		}

		//Volvemos al menu inicial
		TiendaMusica.introducirComando();
	}

	//Metodo para eliminar un cliente
	public static void eliminarCliente() {
		Scanner entradaPantalla = null;
		int codigoCliente = 0;
		Cliente clienteEliminado = null;
		String eliminar = "";
		boolean salir = false;

		try {
			System.out
					.print("Introduzca el codigo de cliente que desea eliminar: ");
			entradaPantalla = new Scanner(System.in);
			codigoCliente = entradaPantalla.nextInt();

			//Comprobamos que el cliente introducido existe en nuestra lista de clientes
			if (TiendaMusica.listaClientes.containsKey(codigoCliente)) {
				clienteEliminado = TiendaMusica.listaClientes
						.get(codigoCliente);
				//Si el cliente introducido sigue activo continuamos con el borrado
				if (clienteEliminado.activo) {
					//Solicitamos confirmación del cliente que se desea borrar
					do {
						System.out
								.println("Está seguro de que desea eliminar el siguiente cliente:");
						System.out.println("Codigo cliente: "
								+ clienteEliminado.getCodigo() + "; Nombre: "
								+ clienteEliminado.getNombre() + "; Apellido: "
								+ clienteEliminado.getApellido());
						entradaPantalla = new Scanner(System.in);
						eliminar = entradaPantalla.nextLine();
						//Comprobamos que el comando introducido es un SI o un NO
						if (Utilidades.compruebaString(eliminar,
								Constantes.LISTA_SINO)) {
							if (eliminar.equalsIgnoreCase(Constantes.SI)) {
								//Eliminamos el cliente marcando como false su campo "activo"
								TiendaMusica.listaClientes.get(codigoCliente)
										.setActivo(false);
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
		}//Mediante el try catch controlamos que no se introduzcan tipos de datos erróneos (Por ejemplo un String en un int) 
		catch (InputMismatchException ime) {
			System.out
					.println("Dato introducido no válido, debe introducir un código de cliente");
		}

		//Volvemos al menu inicial
		TiendaMusica.introducirComando();
	}

	//Metodo para eliminar un cliente conociendo su código se ha sobrecargado el método eliinarCliente
	public static void eliminarCliente(int codigoCliente) {
		Scanner entradaPantalla = null;
		Cliente clienteEliminado = null;
		String eliminar = "";
		boolean salir = false;

		//Primero comprobamos que el codigo de cliente existe en nuestro listado
		if (TiendaMusica.listaClientes.containsKey(codigoCliente)) {
			//Si el cliente existe lo recuperamos
			clienteEliminado = TiendaMusica.listaClientes.get(codigoCliente);
			//Comprobamos si el cliente recuperado está activo, si es true
			if (clienteEliminado.activo) {
				//Solicitamos confirmación para eliminar el cliente
				do {
					System.out
							.println("Está seguro de que desea eliminar el siguiente cliente:");
					System.out.println("Codigo cliente: "
							+ clienteEliminado.getCodigo() + "; Nombre: "
							+ clienteEliminado.getNombre() + "; Apellido: "
							+ clienteEliminado.getApellido());
					entradaPantalla = new Scanner(System.in);
					eliminar = entradaPantalla.nextLine();
					//Comprobamos que el comando introducido es un SI o un NO
					if (Utilidades.compruebaString(eliminar,
							Constantes.LISTA_SINO)) {
						//Eliminamos el cliente marcando como false su campo "activo"
						if (eliminar.equalsIgnoreCase(Constantes.SI)) {
							TiendaMusica.listaClientes.get(codigoCliente)
									.setActivo(false);
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
		//Volvemos al menu inicial
		TiendaMusica.introducirComando();
	}

	/* Creación de cliente */
	public static void crearCliente() {
		Scanner entradaPantalla = null;
		String nombre;
		String apellido;
		String dni;
		int codigoCliente = 0;
		boolean dniValido = false;

		//Solicitamos el nombre del cliente y nos aseguramos de que se ha introducido
		do {
			System.out.print("Introduce el nombre del cliente: ");
			entradaPantalla = new Scanner(System.in);
			nombre = entradaPantalla.nextLine();
		} while (nombre.equals(""));
		//Solicitamos el apellido del cliente y nos aseguramos de que se ha introducido
		do {
			System.out.print("Introduce el apellido del cliente: ");
			entradaPantalla = new Scanner(System.in);
			apellido = entradaPantalla.nextLine();
		} while (apellido.equals(""));
		//Solicitamos el NIF del cliente y lo validamos
		do {
			System.out.print("Introduce el DNI del cliente:");
			entradaPantalla = new Scanner(System.in);
			dni = entradaPantalla.nextLine().toUpperCase();

			dniValido = Utilidades.validarNif(dni);
		} while (!dniValido);

		//Calculamos el código de cliente que se va a asignar
		codigoCliente = Cliente
				.obtenerCodigoCliente(TiendaMusica.listaClientes);

		//Creamos el cliente con los datos
		Cliente cliente = new Cliente(nombre, apellido, dni, codigoCliente);

		//Metemos el nuevo cliente en la lista de clientes
		TiendaMusica.listaClientes.put(codigoCliente, cliente);
		System.out.println("Cliente creado con éxito con código: "
				+ codigoCliente);
		System.out.println("Nombre: " + nombre + "; Apellido: " + apellido
				+ "; DNI: " + dni + "; Código cliente: " + codigoCliente);

		//Volvemos al menu inicial
		TiendaMusica.introducirComando();

	}

	//Este método solo se ha utilizado durante las pruebas para cargar un listado de clientes
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
			System.out.println("Cliente con codigo: " + codigoCliente
					+ " creado.");
		}
	}

	//Método que obtiene el codigo de cliente que se debe asignar en la creación de un nuevo cliente
	public static int obtenerCodigoCliente(
			HashMap<Integer, Cliente> listaClientes) {
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

	public static boolean comprobarClientes() {
		boolean clientes = false;
		Cliente cliente = null;
		int clientesActivos = 0;

		Iterator<Integer> it = calculaKeys(TiendaMusica.listaClientes);

		//Recorremos la lista de clientes
		while (it.hasNext()) {
			Integer key = it.next();
			cliente = TiendaMusica.listaClientes.get(key);
			//Solo tenemos en cuenta los clientes activos (es decir los que no se han borrado)
			if (cliente.isActivo()) {
				clientesActivos++;
			}
		}

		if (TiendaMusica.listaClientes.size() != 0 && clientesActivos != 0) {
			clientes = true;
		}

		return clientes;
	}

	//Metodo que extrae las keys de un hashmap pasado por parámetro
	//Devuelve un iterator con las keys
	public static Iterator<Integer> calculaKeys(
			HashMap<Integer, Cliente> listaCliente) {
		Iterator<Integer> itKeys = null;
		Set<Integer> keys = listaCliente.keySet();
		itKeys = keys.iterator();
		return itKeys;
	}

	//Inicio de GETTERS y SETTERS
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	//Fin de GETTERS y SETTERS
}

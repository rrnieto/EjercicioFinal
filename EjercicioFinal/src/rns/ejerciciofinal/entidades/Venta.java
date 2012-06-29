package rns.ejerciciofinal.entidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import rns.ejerciciofinal.TiendaMusica;
import rns.ejerciciofinal.constantes.Constantes;
import rns.ejerciciofinal.utilidades.Utilidades;

public class Venta {

	private int codigoVenta;
	private int codigoVendedor;
	private int codigoCliente;
	private int codigoMusica;
	//La variable boolean activo la utilizaremos como un marcador para saber si la venta se ha borrado o no
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

	// Listado de ventas
	public static void listarVentas() {
		System.out.println("# LISTAR VENTA #");
		Venta venta = null;
		int ventasActivos = 0;

		Iterator<Integer> it = calculaKeys(TiendaMusica.listaVentas);

		while (it.hasNext()) {
			Integer key = it.next();
			venta = null;
			if (TiendaMusica.listaVentas.get(key) != null) {
				venta = TiendaMusica.listaVentas.get(key);
				//Solo mostramos los ventas activos (es decir los que no se han borrado)
				if (venta.isActivo()) {
					System.out.println("Venta: " + venta.getCodigoVenta()
							+ "; Cliente: " + venta.getCodigoCliente()
							+ "; Disco: " + venta.getCodigoMusica()
							+ "; Vendedor: " + venta.getCodigoVendedor());
					ventasActivos++;
				}
			}
		}

		//Si la lista está vacia o no hemos encontrado ventas activos mostraremos un mensaje indicndo que no existen ventas
		if ((TiendaMusica.listaVentas.size() == 0) || ventasActivos == 0) {
			System.out.println("No existe ningún venta");
		}

		//Volvemos al menu inicial
		TiendaMusica.introducirComando();
	}

	//Metodo para eliminar una venta
	public static void eliminarVenta() {
		Scanner entradaPantalla = null;
		int codigoVenta = 0;
		Venta ventaEliminada = null;
		String eliminar = "";
		boolean salir = false;

		try {
			System.out
					.print("Introduzca el codigo de la venta que desea eliminar: ");
			entradaPantalla = new Scanner(System.in);
			codigoVenta = entradaPantalla.nextInt();

			//Comprobamos que la venta introducida existe en nuestra lista de ventas
			if (TiendaMusica.listaVentas.containsKey(codigoVenta)) {
				ventaEliminada = TiendaMusica.listaVentas.get(codigoVenta);
				//Si la venta introducida sigue activa continuamos con el borrado
				if (ventaEliminada.activo) {
					//Solicitamos confirmación de la venta que se desea borrar
					do {
						System.out
								.println("Está seguro de que desea eliminar la siguiente venta:");
						System.out.println("Codigo venta: "
								+ ventaEliminada.getCodigoVenta());
						entradaPantalla = new Scanner(System.in);
						eliminar = entradaPantalla.nextLine();
						//Comprobamos que el comando introducido es un SI o un NO
						if (Utilidades.compruebaString(eliminar,
								Constantes.LISTA_SINO)) {
							if (eliminar.equalsIgnoreCase(Constantes.SI)) {
								//Eliminamos la venta marcando como false su campo "activo"
								TiendaMusica.listaVentas.get(codigoVenta)
										.setActivo(false);
								System.out.println("Venta eliminada");
							} else {
								System.out.println("Venta NO eliminada");
							}
							salir = true;
						}
					} while (!salir);
				} else {
					System.out.println("Esta venta no existe");
				}
			} else {
				System.out.println("Esta venta no existe");
			}
		}//Mediante el try catch controlamos que no se introduzcan tipos de datos erróneos (Por ejemplo un String en un int) 
		catch (InputMismatchException ime) {
			System.out
					.println("Dato introducido no válido, debe introducir un código de venta");
		}

		//Volvemos al menu inicial
		TiendaMusica.introducirComando();
	}

	//Metodo para eliminar una venta conociendo su código se ha sobrecargado el método eliminarVenta
	public static void eliminarVenta(int codigoVenta) {
		Scanner entradaPantalla = null;
		Venta ventaEliminado = null;
		String eliminar = "";
		boolean salir = false;

		//Primero comprobamos que el codigo de venta existe en nuestro listado
		if (TiendaMusica.listaVentas.containsKey(codigoVenta)) {
			//Si la venta existe lo recuperamos
			ventaEliminado = TiendaMusica.listaVentas.get(codigoVenta);
			//Comprobamos si la venta recuperada está activo, si es true
			if (ventaEliminado.activo) {
				//Solicitamos confirmación para eliminar la venta
				do {
					System.out
							.println("Está seguro de que desea eliminar la siguiente venta:");
					System.out.println("Codigo venta: "
							+ ventaEliminado.getCodigoVenta());
					entradaPantalla = new Scanner(System.in);
					eliminar = entradaPantalla.nextLine();
					//Comprobamos que el comando introducido es un SI o un NO
					if (Utilidades.compruebaString(eliminar,
							Constantes.LISTA_SINO)) {
						//Eliminamos la venta marcando como false su campo "activo"
						if (eliminar.equalsIgnoreCase(Constantes.SI)) {
							TiendaMusica.listaVentas.get(codigoVenta)
									.setActivo(false);
							System.out.println("Venta eliminada");
						} else {
							System.out.println("Venta NO eliminada");
						}
						salir = true;
					}
				} while (!salir);
			} else {
				System.out.println("Esta venta no existe");
			}
		} else {
			System.out.println("Esta venta no existe");
		}
		//Volvemos al menu inicial
		TiendaMusica.introducirComando();
	}

	/* Creación de venta */
	public static void crearVenta() {

		//Comprobamos que haya clientes, vendedores y discos activos.
		if (Vendedor.comprobarVendedores()) {
			if (Musica.comprobarDiscos()) {
				if (Cliente.comprobarClientes()) {
					System.out.println("Comenzando nueva venta:");
				} else {
					System.out
							.println("No existen clientes activos para efectuar una venta");
					TiendaMusica.introducirComando();
				}
			} else {
				System.out
						.println("No existen discos activos para efectuar una venta");
				TiendaMusica.introducirComando();
			}
		} else {
			System.out
					.println("No existen vendedores activos para efectuar una venta");
			TiendaMusica.introducirComando();
		}

		Scanner entradaPantalla = null;
		int codigoVendedor = 0;
		int codigoMusica = 0;
		int codigoVenta = 0;
		int codigoCliente = 0;
		boolean venta = true;

		//Hay que calcular el código de venta
		//Calculamos el código de venta que se va a asignar
		codigoVenta = obtenerCodigoVenta(TiendaMusica.listaVentas);

		do {
			System.out.print("Introduce el codigo del vendedor: ");
			entradaPantalla = new Scanner(System.in);
			codigoVendedor = Integer.parseInt(entradaPantalla.nextLine());
			//TODO COMPROBAR CODIGO
			if ((TiendaMusica.listaVendedores.containsKey(codigoVendedor))
					&& TiendaMusica.listaVendedores.get(codigoVendedor)
							.isActivo()) {
				venta = true;
			} else {
				venta = false;
				System.out.println("Código de vendedor inexistente o inactivo");

			}
		} while (entradaPantalla == null || !venta);

		do {
			System.out.print("Introduce el codigo del cliente: ");
			entradaPantalla = new Scanner(System.in);
			codigoCliente = Integer.parseInt(entradaPantalla.nextLine());
			//TODO COMPROBAR CODIGO
			if ((TiendaMusica.listaClientes.containsKey(codigoCliente))
					&& TiendaMusica.listaClientes.get(codigoCliente).isActivo()) {
				venta = true;
			} else {
				venta = false;
				System.out.println("Código de cliente inexistente o inactivo");

			}
		} while (entradaPantalla == null || !venta);

		do {
			System.out.print("Introduce el codigo del disco: ");
			entradaPantalla = new Scanner(System.in);
			codigoMusica = Integer.parseInt(entradaPantalla.nextLine());
			//TODO COMPROBAR CODIGO
			if ((TiendaMusica.listaMusica.containsKey(codigoMusica))
					&& TiendaMusica.listaVendedores.get(codigoMusica)
							.isActivo()) {
				venta = true;
			} else {
				venta = false;
				System.out.println("Código de disco inexistente o inactivo");
			}
		} while (entradaPantalla == null || !venta);

		if (venta) {
			//Creamos el venta con los datos
			Venta ventaNueva = new Venta(codigoVenta, codigoVendedor,
					codigoCliente, codigoMusica);

			//Metemos el nuevo venta en la lista de ventas
			TiendaMusica.listaVentas.put(codigoVenta, ventaNueva);
			System.out.println("Venta creado con éxito con código: "
					+ codigoVenta);
			System.out.println("Vendedor: " + codigoVendedor + "; Disco: "
					+ codigoMusica + "; Cliente: " + codigoCliente);
		} else {
			System.out
					.println("No se ha podido guardar la venta porque hay errores en los datos introducidos");
		}

		//Volvemos al menu inicial
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

	//Método que obtiene el codigo de venta que se debe asignar en la creación de una nueva venta
	public static int obtenerCodigoVenta(HashMap<Integer, Venta> listaVentas) {
		int codigoVentaValido = 0;
		int codigoLeido = 0;

		Iterator<Integer> it = calculaKeys(TiendaMusica.listaVentas);

		if (listaVentas.size() == 0) {
			codigoVentaValido = 1;
		} else {
			while (it.hasNext()) {
				Integer key = it.next();
				codigoLeido = listaVentas.get(key).getCodigoVenta();
				if (codigoLeido >= codigoVentaValido) {
					codigoVentaValido = codigoLeido + 1;
				}

			}
		}

		return codigoVentaValido;
	}

	public static boolean comprobarVentas() {
		boolean ventas = false;
		Venta venta = null;
		int ventasActivos = 0;

		Iterator<Integer> it = calculaKeys(TiendaMusica.listaVentas);

		//Recorremos la lista de ventas
		while (it.hasNext()) {
			Integer key = it.next();
			venta = TiendaMusica.listaVentas.get(key);
			//Solo tenemos en cuenta los ventas activos (es decir los que no se han borrado)
			if (venta.isActivo()) {
				ventasActivos++;
			}
		}

		if (TiendaMusica.listaVentas.size() != 0 && ventasActivos != 0) {
			ventas = true;
		}

		return ventas;
	}

	//Este metodo guarda los datos que hay en la memoria sobre los cambios realizados en las ventas y los vuelca a su fichero
	public static void guardarCambios() throws IOException {
		Venta vent = null;
		int codigoVenta;
		int codigoVendedor;
		int codigoCliente;
		int codigoMusica;

		FileWriter ficheroVentas = new FileWriter("/ventas.txt");
		StringBuilder registro = null;

		Iterator<Integer> it = calculaKeys(TiendaMusica.listaVentas);

		//Guardar cambios en los ventas
		if (comprobarVentas()) {
			while (it.hasNext()) {
				Integer key = it.next();

				vent = TiendaMusica.listaVentas.get(key);
				if (vent.isActivo()) {
					codigoVenta = vent.getCodigoVenta();
					codigoVendedor = vent.getCodigoVendedor();
					codigoCliente = vent.getCodigoCliente();
					codigoMusica = vent.getCodigoMusica();
					registro = new StringBuilder("");
					registro.append(codigoVenta);
					registro.append(";");
					registro.append(codigoVendedor);
					registro.append(";");
					registro.append(codigoCliente);
					registro.append(";");
					registro.append(codigoMusica);
					registro.append(System.getProperty("line.separator"));
					ficheroVentas.write(registro.toString());
				}

			}
		}
		ficheroVentas.flush();
		ficheroVentas.close();
	}

	//Metodo que extrae las keys de un hashmap pasado por parámetro
	//Devuelve un iterator con las keys
	public static Iterator<Integer> calculaKeys(
			HashMap<Integer, Venta> listaVentas) {
		Iterator<Integer> itKeys = null;
		Set<Integer> keys = listaVentas.keySet();
		itKeys = keys.iterator();
		return itKeys;
	}

	// INICIO BLOQUE DE SETTERS Y GETTERS

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

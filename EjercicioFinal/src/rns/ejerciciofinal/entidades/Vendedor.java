package rns.ejerciciofinal.entidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import rns.ejerciciofinal.TiendaMusica;
import rns.ejerciciofinal.constantes.Constantes;
import rns.ejerciciofinal.utilidades.Utilidades;

//La clase Vendedor hereda de la clase Persona
public class Vendedor extends Persona {

	//La variable boolean activo la utilizaremos como un marcador para saber si el vendedor se ha borrado o no
	private boolean activo = true;
	private int codigo;

	public Vendedor(int codigo, String nombre, String apellido) {
		//Llamada al constructor de la clase padre
		super(nombre, apellido);

		this.codigo = codigo;
		this.activo = true;
	}

	// Listado de vendedores
	public static void listarVendedores() {
		Vendedor vendedor = null;
		int vendedoresActivos = 0;

		Iterator<Integer> it = calculaKeys(TiendaMusica.listaVendedores);

		while (it.hasNext()) {
			Integer key = it.next();
			vendedor = null;
			if (TiendaMusica.listaVendedores.get(key) != null) {
				vendedor = TiendaMusica.listaVendedores.get(key);
				//Solo mostramos los vendedores activos (es decir los que no se han borrado)
				if (vendedor.isActivo()) {
					System.out.println("Vendedor: " + vendedor.getCodigo()
							+ "; Nombre: " + vendedor.getNombre()
							+ "; Apellido: " + vendedor.getApellido());
					vendedoresActivos++;
				}
			}
		}

		//Recorremos la lista de vendedores y mostramos sus datos		
		//		for (int i = 1; i <= tamLista; i++) {
		//			vendedor = null;
		//			if (TiendaMusica.listaVendedores.get(i) != null) {
		//				vendedor = TiendaMusica.listaVendedores.get(i);
		//
		//				//Solo mostramos los vendedores activos (es decir los que no se han borrado)
		//				if (vendedor.isActivo()) {
		//					System.out.println("Vendedor: " + vendedor.getCodigo()
		//							+ "; Nombre: " + vendedor.getNombre()
		//							+ "; Apellido: " + vendedor.getApellido());
		//					vendedoresActivos++;
		//				}
		//			}
		//		}

		//Si la lista está vacia o no hemos encontrado vendedores activos mostraremos un mensaje indicndo que no existen vendedores
		if ((TiendaMusica.listaVendedores.size() == 0)
				|| vendedoresActivos == 0) {
			System.out.println("No existe ningún vendedor");
		}

		//Volvemos al menu inicial
		TiendaMusica.introducirComando();
	}

	//Metodo para eliminar un vendedor
	public static void eliminarVendedor() {
		Scanner entradaPantalla = null;
		int codigoVendedor = 0;
		Vendedor vendedorEliminado = null;
		String eliminar = "";
		boolean salir = false;

		try {
			System.out
					.print("Introduzca el codigo de vendedor que desea eliminar: ");
			entradaPantalla = new Scanner(System.in);
			codigoVendedor = entradaPantalla.nextInt();

			//Comprobamos que el vendedor introducido existe en nuestra lista de vendedores
			if (TiendaMusica.listaVendedores.containsKey(codigoVendedor)) {
				vendedorEliminado = TiendaMusica.listaVendedores
						.get(codigoVendedor);
				//Si el vendedor introducido sigue activo continuamos con el borrado
				if (vendedorEliminado.activo) {
					//Solicitamos confirmación del vendedor que se desea borrar
					do {
						System.out
								.println("Está seguro de que desea eliminar el siguiente vendedor:");
						System.out.println("Codigo vendedor: "
								+ vendedorEliminado.getCodigo() + "; Nombre: "
								+ vendedorEliminado.getNombre()
								+ "; Apellido: "
								+ vendedorEliminado.getApellido());
						entradaPantalla = new Scanner(System.in);
						eliminar = entradaPantalla.nextLine();
						//Comprobamos que el comando introducido es un SI o un NO
						if (Utilidades.compruebaString(eliminar,
								Constantes.LISTA_SINO)) {
							if (eliminar.equalsIgnoreCase(Constantes.SI)) {
								//Eliminamos el vendedor marcando como false su campo "activo"
								TiendaMusica.listaVendedores
										.get(codigoVendedor).setActivo(false);
								System.out.println("Vendedor eliminado");
							} else {
								System.out.println("Vendedor NO eliminado");
							}
							salir = true;
						}
					} while (!salir);
				} else {
					System.out.println("Este vendedor no existe");
				}
			} else {
				System.out.println("Este vendedor no existe");
			}
		}//Mediante el try catch controlamos que no se introduzcan tipos de datos erróneos (Por ejemplo un String en un int) 
		catch (InputMismatchException ime) {
			System.out
					.println("Dato introducido no válido, debe introducir un código de vendedor");
		}

		//Volvemos al menu inicial
		TiendaMusica.introducirComando();
	}

	//Metodo para eliminar un vendedor conociendo su código se ha sobrecargado el método eliinarVendedor
	public static void eliminarVendedor(int codigoVendedor) {
		Scanner entradaPantalla = null;
		Vendedor vendedorEliminado = null;
		String eliminar = "";
		boolean salir = false;

		//Primero comprobamos que el codigo de vendedor existe en nuestro listado
		if (TiendaMusica.listaVendedores.containsKey(codigoVendedor)) {
			//Si el vendedor existe lo recuperamos
			vendedorEliminado = TiendaMusica.listaVendedores
					.get(codigoVendedor);
			//Comprobamos si el vendedor recuperado está activo, si es true
			if (vendedorEliminado.activo) {
				//Solicitamos confirmación para eliminar el vendedor
				do {
					System.out
							.println("Está seguro de que desea eliminar el siguiente vendedor:");
					System.out.println("Codigo vendedor: "
							+ vendedorEliminado.getCodigo() + "; Nombre: "
							+ vendedorEliminado.getNombre() + "; Apellido: "
							+ vendedorEliminado.getApellido());
					entradaPantalla = new Scanner(System.in);
					eliminar = entradaPantalla.nextLine();
					//Comprobamos que el comando introducido es un SI o un NO
					if (Utilidades.compruebaString(eliminar,
							Constantes.LISTA_SINO)) {
						//Eliminamos el vendedor marcando como false su campo "activo"
						if (eliminar.equalsIgnoreCase(Constantes.SI)) {
							TiendaMusica.listaVendedores.get(codigoVendedor)
									.setActivo(false);
							System.out.println("Vendedor eliminado");
						} else {
							System.out.println("Vendedor NO eliminado");
						}
						salir = true;
					}
				} while (!salir);
			} else {
				System.out.println("Este vendedor no existe");
			}
		} else {
			System.out.println("Este vendedor no existe");
		}
		//Volvemos al menu inicial
		TiendaMusica.introducirComando();
	}

	/* Creación de vendedor */
	public static void crearVendedor() {
		Scanner entradaPantalla = null;
		String nombre;
		String apellido;
		int codigoVendedor = 0;

		//Solicitamos el nombre del vendedor y nos aseguramos de que se ha introducido
		do {
			System.out.print("Introduce el nombre del vendedor: ");
			entradaPantalla = new Scanner(System.in);
			nombre = entradaPantalla.nextLine();
		} while (nombre.equals(""));
		//Solicitamos el apellido del vendedor y nos aseguramos de que se ha introducido
		do {
			System.out.print("Introduce el apellido del vendedor: ");
			entradaPantalla = new Scanner(System.in);
			apellido = entradaPantalla.nextLine();
		} while (apellido.equals(""));

		//Calculamos el código de vendedor que se va a asignar
		codigoVendedor = Vendedor
				.obtenerCodigoVendedor(TiendaMusica.listaVendedores);

		//Creamos el vendedor con los datos
		Vendedor vendedor = new Vendedor(codigoVendedor, nombre, apellido);

		//Metemos el nuevo vendedor en la lista de vendedores
		TiendaMusica.listaVendedores.put(codigoVendedor, vendedor);
		System.out.println("Vendedor creado con éxito con código: "
				+ codigoVendedor);
		System.out.println("Nombre: " + nombre + "; Apellido: " + apellido
				+ "; Código vendedor: " + codigoVendedor);

		//Volvemos al menu inicial
		TiendaMusica.introducirComando();

	}

	//Método para cargar los vendedores. 
	//Se leen de un fichero de texto ubicado en la raiz de la unidad donde se ejecuta el programa.
	public static void cargarVendedores() {
		System.out.println("# Cargando vendedores");
		Vendedor vend = null;
		FileReader leer = null;

		int codigo = 0;
		String nombre = "";
		String apellido = "";
		BufferedReader leerDeFichero = null;
		String cadena = "";
		StringTokenizer stk = null;

		try {
			leer = new FileReader("/vendedores.txt");
			leerDeFichero = new BufferedReader(leer);

			cadena = leerDeFichero.readLine();

			while (cadena != null) {
				//Dividimos la cadena linea leida
				stk = new StringTokenizer(cadena, ";");

				codigo = Integer.parseInt(stk.nextToken());
				nombre = stk.nextToken();
				apellido = stk.nextToken();

				vend = new Vendedor(codigo, nombre, apellido);

				System.out.println("Vendedor " + vend.getCodigo() + " "
						+ vend.getNombre() + " " + vend.getApellido());

				//Añadimos cada vendedor al listado en memoria
				TiendaMusica.listaVendedores.put(vend.getCodigo(), vend);

				cadena = leerDeFichero.readLine();
			}
			//Una vez finalizada la lectura cerramos el fichero
			leer.close();

		} //En caso de que no exista el fichero capturamos la excepción correspondiente, informamos al usuario y llamamos al método que creará el fichero
		catch (FileNotFoundException fnfe) {
			System.out.println("No se ha encontrado el fichero de vendedores.");
			crearFicheroVendedores();
		} catch (IOException ioe) {
			System.out
					.println("Se ha producido un error al procesar el fichero");
		}

	}

	//Este metodo crea el fichero de texto para almacenar los vendedores. Se crea vacío y el usuario deberá crear vendedores antes de poder realizar una venta.
	public static void crearFicheroVendedores() {
		try {
			System.out.println("Creamos el fichero vendedores.txt");
			FileWriter fichero = new FileWriter("/vendedores.txt");
			System.out.println("Fichero vendedores.txt creado con éxito");
			fichero.flush();
		} catch (IOException ioe) {
			System.out
					.println("Se ha producido un error al procesar el fichero de vendedores");
		}
	}

	//Método que obtiene el codigo de vendedor que se debe asignar en la creación de un nuevo vendedor
	public static int obtenerCodigoVendedor(
			HashMap<Integer, Vendedor> listaVendedores) {
		int codigoVendedorValido = 0;
		int codigoLeido = 0;

		Iterator<Integer> it = calculaKeys(TiendaMusica.listaVendedores);

		if (listaVendedores.size() == 0) {
			codigoVendedorValido = 1;
		} else {
			while (it.hasNext()) {
				Integer key = it.next();
				codigoLeido = listaVendedores.get(key).getCodigo();
				if (codigoLeido >= codigoVendedorValido) {
					codigoVendedorValido = codigoLeido + 1;
				}

			}
		}

		return codigoVendedorValido;
	}

	public static boolean comprobarVendedores() {
		boolean vendedores = false;
		Vendedor vendedor = null;
		int vendedoresActivos = 0;

		Iterator<Integer> it = calculaKeys(TiendaMusica.listaVendedores);

		//Recorremos la lista de vendedores
		while (it.hasNext()) {
			Integer key = it.next();
			vendedor = TiendaMusica.listaVendedores.get(key);
			//Solo tenemos en cuenta los vendedores activos (es decir los que no se han borrado)
			if (vendedor.isActivo()) {
				vendedoresActivos++;
			}
		}

		if (TiendaMusica.listaVendedores.size() != 0 && vendedoresActivos != 0) {
			vendedores = true;
		}

		return vendedores;
	}

	//Este metodo guarda los datos que hay en la memoria sobre los cambios realizados en los vendedores y los vuelca a su fichero
	public static void guardarCambios() throws IOException {
		Vendedor vend = null;
		int codigo;
		String nombre;
		String apellido;
		FileWriter ficheroVendedores = new FileWriter("/vendedores.txt");
		StringBuilder registro = null;
		
		Iterator<Integer> it = calculaKeys(TiendaMusica.listaVendedores);
		
		
		//Guardar cambios en los vendedores
		if (comprobarVendedores()) {
			while (it.hasNext()) {
				Integer key = it.next();

				vend = TiendaMusica.listaVendedores.get(key);
				if (vend.isActivo()) {
					codigo = vend.getCodigo();
					nombre = vend.getNombre();
					apellido = vend.getApellido();
					registro = new StringBuilder("");
					registro.append(codigo);
					registro.append(";");
					registro.append(nombre);
					registro.append(";");
					registro.append(apellido);
					registro.append(System.getProperty("line.separator"));
					ficheroVendedores.write(registro.toString());
				}

			}
		}
		ficheroVendedores.flush();
		ficheroVendedores.close();
	}

	//Metodo que extrae las keys de un hashmap pasado por parámetro
	//Devuelve un iterator con las keys
	public static Iterator<Integer> calculaKeys(
			HashMap<Integer, Vendedor> listaVendedores) {
		Iterator<Integer> itKeys = null;
		Set<Integer> keys = listaVendedores.keySet();
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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	//Fin de GETTERS y SETTERS
}

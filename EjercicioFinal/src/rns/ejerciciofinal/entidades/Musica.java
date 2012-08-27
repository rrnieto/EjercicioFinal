//REVISADO
package rns.ejerciciofinal.entidades;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import rns.ejerciciofinal.TiendaMusica;
import rns.ejerciciofinal.constantes.Constantes;
import rns.ejerciciofinal.utilidades.Utilidades;

public class Musica {
	private String titulo;
	private String autor;
	private String formato;
	private int codigo;
	//La variable boolean activo la utilizaremos como un marcador para saber si el disco se ha borrado o no
	private boolean activo = true;

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	//Objeto clase Musica
	public Musica(String titulo, String autor, String formato, int codigo) {
		this.titulo = titulo;
		this.autor = autor;
		this.formato = formato;
		this.codigo = codigo;
	}

	//Obtenemos el codigo de musica que tenemos que utilizar
	public static int obtenerCodigoMusica(HashMap<Integer, Musica> listaMusica) {
		int codigoValido = 0;
		int codigoLeido = 0;

		if (listaMusica.size() == 0) {
			codigoValido = 1;
		} else {
			for (int i = 1; i <= listaMusica.size(); i++) {
				codigoLeido = listaMusica.get(i).getCodigo();
				if (codigoLeido >= codigoValido) {
					codigoValido = codigoLeido + 1;
				}

			}
		}
		return codigoValido;
	}

	//Cargamos Musica de prueba
	public static void cargarMusica() {
		Musica musica = null;
		String titulo;
		String autor;
		String formato;
		int codigo = 0;

		for (int i = 0; i < 3; i++) {

			titulo = ("Titulo" + (i + 1));
			autor = ("Autor" + (i + 1));
			formato = ("DVD");
			codigo = obtenerCodigoMusica(TiendaMusica.listaMusica);
			musica = new Musica(titulo, autor, formato, codigo);
			TiendaMusica.listaMusica.put(codigo, musica);
			System.out.println("Musica con codigo: " + codigo + " creada.");
		}
	}

	//Funcion para la creación de un nuevo disco
	public static void crearMusica() {
		System.out.println("# CREAR MUSICA #");
		String titulo = "";
		String autor = "";
		String formato = "";
		int codigo = 0;

		Scanner leerPantalla = null;

		System.out.print("Introduce el título del disco: ");
		leerPantalla = new Scanner(System.in);
		titulo = leerPantalla.nextLine().toUpperCase();
		System.out.print("Introduce el autor del disco:");
		leerPantalla = new Scanner(System.in);
		autor = leerPantalla.nextLine().toUpperCase();
		do {
			System.out.print("Introduce el formato del disco:");
			leerPantalla = new Scanner(System.in);
			formato = leerPantalla.nextLine().toUpperCase();

		} while (!Utilidades
				.compruebaString(formato, Constantes.LISTA_FORMATOS));

		codigo = obtenerCodigoMusica(TiendaMusica.listaMusica);

		Musica nuevoDisco = new Musica(titulo, autor, formato, codigo);
		TiendaMusica.listaMusica.put(codigo, nuevoDisco);

		System.out.println("Disco creado con éxito con codigo: " + codigo);
		System.out.println("Titulo: " + titulo);
		System.out.println("Autor: " + autor);
		System.out.println("Formato: " + formato);

		TiendaMusica.introducirComando();
	}

	//Metodo para eliminar un disco conociendo su código se ha sobrecargado el método eliinarMusica
	public static void eliminarMusica(int codigoDisco) {
		Scanner entradaPantalla = null;
		Musica discoEliminado = null;
		String eliminar = "";
		boolean salir = false;

		//Primero comprobamos que el codigo de disco existe en nuestro listado
		if (TiendaMusica.listaMusica.containsKey(codigoDisco)) {
			//Si el disco existe lo recuperamos
			discoEliminado = TiendaMusica.listaMusica.get(codigoDisco);
			//Comprobamos si el disco recuperado está activo, si es true
			if (discoEliminado.activo) {
				//Solicitamos confirmación para eliminar el disco
				do {
					System.out
							.println("Está seguro de que desea eliminar el siguiente disco:");
					System.out.println("Codigo disco: "
							+ discoEliminado.getCodigo() + "; Título: "
							+ discoEliminado.getTitulo() + "; Autor: "
							+ discoEliminado.getAutor() + "; Formato: "
							+ discoEliminado.getFormato());
					entradaPantalla = new Scanner(System.in);
					eliminar = entradaPantalla.nextLine();
					//Comprobamos que el comando introducido es un SI o un NO
					if (Utilidades.compruebaString(eliminar,
							Constantes.LISTA_SINO)) {
						//Eliminamos el disco marcando como false su campo "activo"
						if (eliminar.equalsIgnoreCase(Constantes.SI)) {
							TiendaMusica.listaMusica.get(codigoDisco)
									.setActivo(false);
							System.out.println("Disco eliminado");
						} else {
							System.out.println("Disco NO eliminado");
						}
						salir = true;
					}
				} while (!salir);
			} else {
				System.out.println("Este disco no existe");
			}
		} else {
			System.out.println("Este disco no existe");
		}
		//Volvemos al menu inicial
		TiendaMusica.introducirComando();
	}

	//Metodo para eliminar un disco
	public static void eliminarMusica() {
		System.out.println("# ELIMINAR MUSICA #");
		Scanner entradaPantalla = null;
		int codigo = 0;
		Musica discoEliminado = null;
		String eliminar = "";
		boolean salir = false;
		try {

			System.out
					.print("Introduzca el codigo del disco que desea eliminar: ");
			entradaPantalla = new Scanner(System.in);
			codigo = entradaPantalla.nextInt();

			if (TiendaMusica.listaMusica.containsKey(codigo)) {
				discoEliminado = TiendaMusica.listaMusica.get(codigo);
				if (discoEliminado.activo) {
					do {
						System.out
								.println("Está seguro de que desea eliminar el siguiente disco:");
						System.out.println("Codigo: "
								+ discoEliminado.getCodigo() + "; Título: "
								+ discoEliminado.getTitulo());

						entradaPantalla = new Scanner(System.in);
						eliminar = entradaPantalla.nextLine();

						if (Utilidades.compruebaString(eliminar,
								Constantes.LISTA_SINO)) {
							if (eliminar.equalsIgnoreCase(Constantes.SI)) {
								TiendaMusica.listaMusica.get(codigo).setActivo(
										false);
								System.out.println("Disco eliminado");
							} else {
								System.out.println("Disco NO eliminado");
							}
							salir = true;
						}
					} while (!salir);
				} else {
					System.out.println("Este disco no existe");
				}
			} else {
				System.out.println("Este disco no existe");
			}

		} catch (InputMismatchException ime) {
			System.out
					.println("Dato introducido no válido, debe introducir un código de disco");
		}

		//Volvemos al menu inicial
		TiendaMusica.introducirComando();
	}

	// Listado de discos
	public static void listarMusica() {
		Musica disco = null;
		int discosActivos = 0;

		//Recorremos la lista de discos y mostramos sus datos
		for (int i = 1; i <= TiendaMusica.listaMusica.size(); i++) {
			disco = TiendaMusica.listaMusica.get(i);
			//Solo mostramos los discos activos (es decir los que no se han borrado)
			if (disco.isActivo()) {
				System.out
						.println("Codigo de disco: " + disco.getCodigo()
								+ "; Título: " + disco.getTitulo()
								+ "; Autor: " + disco.getAutor()
								+ "; Formato: " + disco.getFormato());
				discosActivos++;
			}
		}

		//Si la lista está vacia o no hemos encontrado discos activos mostraremos un mensaje indicndo que no existen discos
		if ((TiendaMusica.listaMusica.size() == 0) || discosActivos == 0) {
			System.out.println("No existe ningún disco");
		}

		//Volvemos al menu inicial
		TiendaMusica.introducirComando();
	}

	public static boolean comprobarDiscos() {
		boolean discos = false;
		Musica disco = null;
		int discosActivos = 0;

		Iterator<Integer> it = calculaKeys(TiendaMusica.listaMusica);

		//Recorremos la lista de discos
		while (it.hasNext()) {
			Integer key = it.next();
			disco = TiendaMusica.listaMusica.get(key);
			//Solo tenemos en cuenta los discos activos (es decir los que no se han borrado)
			if (disco.isActivo()) {
				discosActivos++;
			}
		}

		if (TiendaMusica.listaMusica.size() != 0 && discosActivos != 0) {
			discos = true;
		}

		return discos;
	}

	//Metodo que extrae las keys de un hashmap pasado por parámetro
	//Devuelve un iterator con las keys
	public static Iterator<Integer> calculaKeys(
			HashMap<Integer, Musica> listaMusica) {
		Iterator<Integer> itKeys = null;
		Set<Integer> keys = listaMusica.keySet();
		itKeys = keys.iterator();
		return itKeys;
	}

	//INICIO BLOQUE GETTERS Y SETTERS

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}

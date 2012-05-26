package rns.ejerciciofinal.entidades;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import rns.ejerciciofinal.TiendaMusica;
import rns.ejerciciofinal.constantes.Constantes;
import rns.ejerciciofinal.utilidades.Utilidades;

public class Musica {
	private String titulo;
	private String autor;
	private String formato;
	private int codigo;
	private boolean activo = true;

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Musica(String titulo, String autor, String formato, int codigo) {
		this.titulo = titulo;
		this.autor = autor;
		this.formato = formato;
		this.codigo = codigo;
	}

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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

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

	/*
	public static void listarMusica() {
		System.out.println("# LISTAR MUSICA #");
		Musica disco = null;

		for (int i = 1; i <= TiendaMusica.listaMusica.size(); i++) {
			disco = TiendaMusica.listaMusica.get(i);
			if (disco.isActivo()) {
				System.out
						.println("Codigo de disco: " + disco.getCodigo()
								+ "; Título: " + disco.getTitulo()
								+ "; Autor: " + disco.getAutor()
								+ "; Formato: " + disco.getFormato());
			}
		}
		TiendaMusica.introducirComando();
	}*/

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

}

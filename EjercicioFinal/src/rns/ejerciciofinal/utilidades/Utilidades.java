package rns.ejerciciofinal.utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rns.ejerciciofinal.TiendaMusica;
import rns.ejerciciofinal.entidades.Vendedor;

public class Utilidades {

	public static boolean compruebaString(String comando, String[] lista) {
		boolean existe = false;

		for (String comandoGuardado : lista) {
			if (comando.equalsIgnoreCase(comandoGuardado)) {
				existe = true;
			}
		}
		if (!existe) {
			System.out.println("ERROR - Comando no reconocido");
			System.out
					.println("El listado de comandos permitidos es el siguiente:"
							+ "\n");
			for (String comandoListado : lista) {
				System.out.println(comandoListado);
			}
		}
		return existe;
	}

	public static StringTokenizer dividirComando(String comando) {
		StringTokenizer st = new StringTokenizer(comando);
		return st;
	}

	public static int obtenerNumeroDeTokens(String comando) {
		int tokens = 0;
		StringTokenizer st = dividirComando(comando);
		tokens = st.countTokens();
		return tokens;
	}

	public static String capturarComando(String comando, int numeroToken) {
		String cadena = "";
		int i = 1;

		StringTokenizer st = dividirComando(comando);

		while (st.hasMoreTokens()) {
			if (i == numeroToken) {
				cadena = st.nextToken();
			} else {
				st.nextToken();
			}
			i++;
		}

		return cadena;
	}

	//INICIO METODOS PARA VALIDAR EL NIF
	//Metodo que valida el patron correcto de un DNI, números y letra separados por un guion
	public static boolean validarNif(String dni) {
		boolean nifValido = false;

		Pattern patron = Pattern.compile("\\d{3,8}\\-[a-zA-Z]{1}");
		Matcher m = patron.matcher(dni);
		if (m.matches()) {
			//La llamada a este metodo valida que la letra sea correcta, lo hemos comentado para facilitar el uso del programa durante las pruebas
			//if (validarLetra(dni))
			nifValido = true;
		}
		if (!nifValido) {
			System.out.println("El formato del DNI indicado no es correcto.");
			System.out.println("Ejemplo de formato correcto: 11222333-A");
		}
		return nifValido;
	}

	//Metodo que valida la letra del DNI indicado, se comentarán sus llamadas para facilitar el uso del programa durante la fase de pruebas
	private static boolean validarLetra(String n) {
		String[] nif = n.split("-");

		int letra = (Integer.valueOf(nif[0]).intValue()) % 23;
		String[] abc = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X",
				"B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E", "T" };

		if (abc[letra].compareToIgnoreCase(nif[1].toUpperCase()) == 0) {
			return true;
		}
		return false;

	}

	//FIN METODOS PARA VALIDAR EL NIF

	//Método para cargar los vendedores. 
	//Se leen de un fichero de texto ubicado en la raiz de la unidad donde se ejecuta el programa.
	public static void cargarVendedores() {
		System.out.println("# Cargando vendedores");
		Vendedor vend = null;
		FileReader leer = null;
		Scanner leerPantalla = null;
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
				stk = new StringTokenizer(cadena,";");
				vend = new Vendedor(Integer.parseInt(stk.nextToken()), stk
						.nextToken(), stk.nextToken());
				System.out.println("Vendedor " + vend.getCodigo() + " "
						+ vend.getNombre() + " " + vend.getApellido());
				TiendaMusica.listaVendedores.put(vend.getCodigo(), vend);
				cadena = leerDeFichero.readLine();
			}

		} catch (FileNotFoundException fnfe) {
			System.out.println("No se ha encontrado el fichero de vendedores.");
			System.out
					.println("¿Desea crear un vendedor por defecto para esta sesión?");
		} catch (IOException ioe) {
			System.out
					.println("Se ha producido un error al procesar el fichero");
		}

	}

}

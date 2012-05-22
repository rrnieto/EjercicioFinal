package rns.ejerciciofinal.utilidades;

import java.util.StringTokenizer;

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
			System.out.println("El listado de comandos permitidos es el siguiente:" + "\n");
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
}

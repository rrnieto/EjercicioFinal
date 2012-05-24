package rns.ejerciciofinal;

import java.util.HashMap;
import java.util.Scanner;

import rns.ejeciciofinal.constantes.Constantes;
import rns.ejerciciofinal.entidades.Cliente;
import rns.ejerciciofinal.entidades.Musica;
import rns.ejerciciofinal.entidades.Venta;
import rns.ejerciciofinal.utilidades.Utilidades;

public class TiendaMusica {

	public static HashMap<Integer, Cliente> listaClientes = new HashMap<Integer, Cliente>();
	public static HashMap<Integer, Musica> listaMusica = new HashMap<Integer, Musica>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO INI comentar esta llamada
		System.out.println("### CARGANDO CLIENTES DE PRUEBA");
		Cliente.cargarClientes();
		System.out.println();
		System.out.println("### CARGANDO MUSICA DE PRUEBA");
		Musica.cargarMusica();
		System.out.println();
		// cargarMusica();
		// TODO FIN comentar esta llamada
		introducirComando();

	}

	public static void introducirComando() {
		Scanner entradaPantalla = null;
		String comando = "";
		boolean comandoValido = false;
		int numeroTokens = 0;

		String accion = "";
		String entidad = "";
		String codigo = "";
		String comandoCapturado = "";
		StringBuffer sb = null;

		do {

			/* Leemos el comando introducido por teclado */
			System.out.println("Introducir comando: ");
			entradaPantalla = new Scanner(System.in);

			// Almacenamos el comando en la variable comando
			comando = entradaPantalla.nextLine();

			// calculamos el numero de tokens en que está dividido el
			// comando introducido, el separador es el espacio
			numeroTokens = Utilidades.obtenerNumeroDeTokens(comando);

			accion = Utilidades.capturarComando(comando, 1);
			entidad = Utilidades.capturarComando(comando, 2);
			codigo = Utilidades.capturarComando(comando, 3);
			System.out.println("Accion: " + accion);
			System.out.println("Entidad: " + entidad);
			System.out.println("Codigo: " + codigo);

			sb = new StringBuffer("");
			sb.append(accion);
			sb.append(" ");
			sb.append(entidad);
			comandoCapturado = sb.toString().trim();

			/*
			  if (Utilidades.compruebaString(accion,
			  Constantes.LISTA_ACCIONES)) { if
			  (Utilidades.compruebaString(entidad, Constantes.LISTA_ENTIDADES))
			  { if (numeroTokens == 2) { comandoValido = true; if
			  (comando.equalsIgnoreCase(Constantes.CREAR_CLIENTE)) {
			  Cliente.crearCliente(); } else if
			  (comando.equalsIgnoreCase(Constantes.ELIMINAR_CLIENTE)) {
			  Cliente.eliminarCliente(); } else if
			  (comando.equalsIgnoreCase(Constantes.LISTAR_CLIENTES)) {
			  Cliente.listarClientes(); } else if
			  (comando.equalsIgnoreCase(Constantes.CREAR_MUSICA)) {
			  Musica.crearMusica(); } else if
			  (comando.equalsIgnoreCase(Constantes.ELIMINAR_MUSICA)) {
			  Musica.eliminarMusica(); } else if
			  (comando.equalsIgnoreCase(Constantes.LISTAR_MUSICA)) {
			  Musica.listarMusica(); } else if
			  (comando.equalsIgnoreCase(Constantes.CREAR_VENTA)) {
			  Venta.crearVenta(); } else if
			  (comando.equalsIgnoreCase(Constantes.ELIMINAR_VENTA)) {
			  Venta.eliminarVenta(); } else if
			  (comando.equalsIgnoreCase(Constantes.LISTAR_VENTAS)) {
			  Venta.listarVentas(); } } }
			  
			  }
			 */
			if (numeroTokens <= 2) {
				if (Utilidades.compruebaString(comandoCapturado,
						Constantes.LISTA_COMANDOS)) {
					comandoValido = true;
					if (comandoCapturado
							.equalsIgnoreCase(Constantes.CREAR_CLIENTE)) {
						Cliente.crearCliente();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.ELIMINAR_CLIENTE)) {
						System.out.println("Cliente.eliminarCliente();");
						// Cliente.eliminarCliente();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.LISTAR_CLIENTES)) {
						Cliente.listarClientes();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.CREAR_MUSICA)) {
						Musica.crearMusica();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.ELIMINAR_MUSICA)) {
						System.out.println("Musica.eliminarMusica();");
						// Musica.eliminarMusica();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.LISTAR_MUSICA)) {
						Musica.listarMusica();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.CREAR_VENTA)) {
						Venta.crearVenta();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.ELIMINAR_VENTA)) {
						System.out.println("Venta.eliminarVenta();");
						// Venta.eliminarVenta();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.LISTAR_VENTAS)) {
						Venta.listarVentas();
					}
				}
				/*
								else {
									System.out.println("ERROR - Comando no reconocido");
									System.out
											.println("El listado de comandos permitidos es el siguiente:"
													+ "\n");
									for (String comandoListado : listaComandos) {
										System.out.println(comandoListado);
									}
								}*/

			}

		} while (!comandoValido);

		if (comando.equalsIgnoreCase(Constantes.CERRAR)) {
			System.out.println("Gracias por su visita");
		}
	}

}

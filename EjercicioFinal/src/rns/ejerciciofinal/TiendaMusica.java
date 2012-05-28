package rns.ejerciciofinal;

import java.util.HashMap;
import java.util.Scanner;

import rns.ejerciciofinal.constantes.Constantes;
import rns.ejerciciofinal.entidades.Cliente;
import rns.ejerciciofinal.entidades.Musica;
import rns.ejerciciofinal.entidades.Vendedor;
import rns.ejerciciofinal.entidades.Venta;
import rns.ejerciciofinal.utilidades.Utilidades;

public class TiendaMusica {

	public static HashMap<Integer, Cliente> listaClientes = new HashMap<Integer, Cliente>();
	public static HashMap<Integer, Musica> listaMusica = new HashMap<Integer, Musica>();
	public static HashMap<Integer, Vendedor> listaVendedores = new HashMap<Integer, Vendedor>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO INI comentar esta llamada
		System.out.println("### CARGANDO CLIENTES DE PRUEBA");
		//Cliente.cargarClientes();
		System.out.println();
		System.out.println("### CARGANDO MUSICA DE PRUEBA");
		Musica.cargarMusica();
		System.out.println();
		// cargarMusica();
		// TODO FIN comentar esta llamada
		// Cargamos los vendedores disponibles, se leen de un fichero de texto ubicado en la raiz de la unidad donde se ejecuta el programa
		Utilidades.cargarVendedores();

		//Llamamos al menú principal
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
			//			System.out.println("Accion: " + accion);
			//			System.out.println("Entidad: " + entidad);
			//			System.out.println("Codigo: " + codigo);

			sb = new StringBuffer("");
			sb.append(accion);
			sb.append(" ");
			sb.append(entidad);
			comandoCapturado = sb.toString().trim();

			if (numeroTokens <= 2) {
				if (Utilidades.compruebaString(comandoCapturado,
						Constantes.LISTA_COMANDOS)) {
					comandoValido = true;
					if (comandoCapturado
							.equalsIgnoreCase(Constantes.CREAR_CLIENTE)) {
						Cliente.crearCliente();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.ELIMINAR_CLIENTE)) {
						Cliente.eliminarCliente();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.LISTAR_CLIENTES)) {
						Cliente.listarClientes();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.CREAR_MUSICA)) {
						Musica.crearMusica();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.ELIMINAR_MUSICA)) {
						Musica.eliminarMusica();
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
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.CREAR_VENDEDOR)) {
						Vendedor.crearVendedor();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.ELIMINAR_VENDEDOR)) {
						Vendedor.eliminarVendedor();
					} else if (comandoCapturado
							.equalsIgnoreCase(Constantes.LISTAR_VENDEDORES)) {
						Vendedor.listarVendedores();
					}
				}
			}

			if (numeroTokens == 3) {
				if (comandoCapturado
						.equalsIgnoreCase(Constantes.ELIMINAR_CLIENTE)) {
					Cliente.eliminarCliente(Integer.parseInt(codigo));
				} else if (comandoCapturado
						.equalsIgnoreCase(Constantes.ELIMINAR_MUSICA)) {
					System.out.println("Musica.eliminarMusica();");
					// Musica.eliminarMusica(codigo);

				} else if (comandoCapturado
						.equalsIgnoreCase(Constantes.ELIMINAR_VENTA)) {
					System.out.println("Venta.eliminarVenta();");
					// Venta.eliminarVenta(codigo);

				}
			}

		} while (!comandoValido);

		if (comando.equalsIgnoreCase(Constantes.CERRAR)) {
			System.out.println("Gracias por su visita");
		}

	}
}

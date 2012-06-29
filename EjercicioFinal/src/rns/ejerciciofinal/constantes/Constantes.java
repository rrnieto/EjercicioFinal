package rns.ejerciciofinal.constantes;

public final class Constantes {

	public final static String CREAR = "Crear";
	public final static String LISTAR = "Listar";
	public final static String ELIMINAR = "Eliminar";

	public final static String MUSICA = "Musica";
	public final static String CLIENTE = "Cliente";
	public final static String VENTA = "Venta";
	public final static String VENDEDOR = "Vendedor";

	public final static String CREAR_MUSICA = "Crear musica";
	public final static String CREAR_CLIENTE = "Crear cliente";
	public final static String CREAR_VENTA = "Crear venta";
	public final static String CREAR_VENDEDOR = "Crear vendedor";

	public final static String LISTAR_MUSICA = "Listar musica";
	public final static String LISTAR_CLIENTES = "Listar clientes";
	public final static String LISTAR_VENTAS = "Listar ventas";
	public final static String LISTAR_VENDEDORES = "Listar vendedores";

	public final static String ELIMINAR_MUSICA = "Eliminar musica";
	public final static String ELIMINAR_CLIENTE = "Eliminar cliente";
	public final static String ELIMINAR_VENTA = "Eliminar venta";
	public final static String ELIMINAR_VENDEDOR = "Eliminar vendedor";

	public final static String CERRAR = "Cerrar";

	public final static String FORMATO_CD = "CD";
	public final static String FORMATO_CASETE = "Casete";
	public final static String FORMATO_VINILO = "Vinilo";
	public final static String FORMATO_DVD = "Dvd";

	public final static String[] LISTA_ACCIONES = { LISTAR, ELIMINAR, CREAR };
	public final static String[] LISTA_ENTIDADES = { MUSICA, CLIENTE, VENTA };

	public final static String SI = "Si";
	public final static String NO = "No";

	public final static String[] LISTA_SINO = { SI, NO };

	public final static String[] LISTA_FORMATOS = { FORMATO_CD, FORMATO_CASETE,
			FORMATO_VINILO, FORMATO_DVD };

	public static String[] getListaFormatos() {
		return LISTA_FORMATOS;
	}

	public final static String[] LISTA_COMANDOS = { CREAR_MUSICA,
			CREAR_CLIENTE, CREAR_VENTA, LISTAR_MUSICA, LISTAR_CLIENTES,
			LISTAR_VENTAS, ELIMINAR_MUSICA, ELIMINAR_CLIENTE, ELIMINAR_VENTA,
			CREAR_VENDEDOR, LISTAR_VENDEDORES, ELIMINAR_VENDEDOR, CERRAR };

}
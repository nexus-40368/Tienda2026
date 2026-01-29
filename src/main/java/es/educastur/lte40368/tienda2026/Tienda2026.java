/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package es.educastur.lte40368.tienda2026;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1dawd18
 */
public class Tienda2026 {

    static Scanner sc = new Scanner(System.in);
    private ArrayList<Pedido> pedidos;
    private HashMap<String, Articulo> articulos;
    private HashMap<String, Cliente> clientes;

    public Tienda2026() {
        pedidos = new ArrayList();
        articulos = new HashMap();
        clientes = new HashMap();
    }

    public static void main(String[] args) {
        Tienda2026 t2026 = new Tienda2026();
        t2026.cargaDatos();

        t2026.menu();
    }

    //<editor-fold defaultstate="collapsed" desc="metodos">
    private void stock(String idArticulo, int unidades) throws StockCero, StockInsuficiente {
        if (articulos.get(idArticulo).getExistencias() == 0) {
            throw new StockCero("0 unidades disponibles"
                    + articulos.get(idArticulo).getDescripcion());
        }
        if (articulos.get(idArticulo).getExistencias() < unidades) {
            throw new StockInsuficiente("Solo hay " + articulos.get(idArticulo).getExistencias()
                    + articulos.get(idArticulo).getDescripcion());
        }

    }

    public String generaIdPedido(String idCliente) {
        int contador = 0;
        String nuevoId; // vble String para ir construyendo un nuevo idPedido
        //Calculamos en la vble contador cuanto pedidos tinen el cliente
        for (Pedido p : pedidos) {
            if (p.getClientePedido().getIdCliente().equalsIgnoreCase(idCliente)) {
                contador++;
            }
        }
        contador++;// sumamos a contador 1 para el nuevo pedido
        nuevoId = idCliente + "-" + String.format("%03d", contador) + "/" + LocalDate.now().getYear();
        return nuevoId;
        //</editor-fold>
    }

    public void cargaDatos() {
        clientes.put("80580845T", new Cliente("80580845T", "ANA ", "658111111", "ana@gmail.com"));
        clientes.put("36347775R", new Cliente("36347775R", "LOLA", "649222222", "lola@gmail.com"));
        clientes.put("63921307Y", new Cliente("63921307Y", "JUAN", "652333333", "juan@gmail.com"));
        clientes.put("02337565Y", new Cliente("02337565Y", "EDU", "634567890", "edu@gmail.com"));

        articulos.put("1-11", new Articulo("1-11", "RATON LOGITECH ST ", 14, 15));
        articulos.put("1-22", new Articulo("1-22", "TECLADO STANDARD  ", 9, 18));
        articulos.put("2-11", new Articulo("2-11", "HDD SEAGATE 1 TB  ", 16, 80));
        articulos.put("2-22", new Articulo("2-22", "SSD KINGSTOM 256GB", 9, 70));
        articulos.put("2-33", new Articulo("2-33", "SSD KINGSTOM 512GB", 0, 200));
        articulos.put("3-22", new Articulo("3-22", "EPSON PRINT XP300 ", 5, 80));
        articulos.put("3-11", new Articulo("3-11", "HP LASER", 5, 80));
        articulos.put("4-11", new Articulo("4-11", "ASUS  MONITOR  22 ", 5, 100));
        articulos.put("4-22", new Articulo("4-22", "HP MONITOR LED 28 ", 5, 180));
        articulos.put("4-33", new Articulo("4-33", "SAMSUNG ODISSEY G5", 12, 580));

        LocalDate hoy = LocalDate.now();
        pedidos.add(new Pedido("80580845T-001/2025", clientes.get("80580845T"), hoy.minusDays(1), new ArrayList<>(List.of(new LineaPedido("1-11", 3), new LineaPedido("4-22", 3)))));
        pedidos.add(new Pedido("80580845T-002/2025", clientes.get("80580845T"), hoy.minusDays(2), new ArrayList<>(List.of(new LineaPedido("4-11", 3), new LineaPedido("4-22", 2), new LineaPedido("4-33", 4)))));
        pedidos.add(new Pedido("36347775R-001/2025", clientes.get("36347775R"), hoy.minusDays(3), new ArrayList<>(List.of(new LineaPedido("4-22", 1), new LineaPedido("2-22", 3)))));
        pedidos.add(new Pedido("36347775R-002/2025", clientes.get("36347775R"), hoy.minusDays(5), new ArrayList<>(List.of(new LineaPedido("4-33", 3), new LineaPedido("2-11", 3)))));
        pedidos.add(new Pedido("63921307Y-001/2025", clientes.get("63921307Y"), hoy.minusDays(4), new ArrayList<>(List.of(new LineaPedido("2-11", 5), new LineaPedido("2-33", 3), new LineaPedido("4-33", 2)))));
    }

    public void menu() {
        int opcion;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU DE OPCIONES");
            System.out.println("\t\t\t\t1 - ARTICULO");
            System.out.println("\t\t\t\t2 - CLIENTE");
            System.out.println("\t\t\t\t3 - PEDIDOS");
            System.out.println("\t\t\t\t4 - LISTAR COLECCIONES");
            System.out.println("\t\t\t\t5 - LISTAR COLECCIONES CON STREAMS");
            System.out.println("\t\t\t\t6 - ORDENAR COLECCIONES CON STREAMS");
            System.out.println("\t\t\t\t9 - SALIR");
            System.out.println("\t\t\t\t¿Qué opción quieres ejecurtar?");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    menuArticulo();
                    break;
                }
                case 2: {
                    menuCliente();
                    break;
                }
                case 3: {
                    menuPedido();
                    break;
                }
                case 4: {
                    listarColecciones();
                    break;
                }
                case 5: {
                    listadosConStreams();
                    break;
                }
                case 6: {
                    ordenarConStream();
                    break;
                }

            }

        } while (opcion != 9);

        /* Estamos creando un menú de opciones, dichas opciones son introducidas por teclado y están contenidas en los diferentes métodos para actualizar la información de la agenda
          - Se debe declarar una variable de tipo int para poder navegar en el menú
          - Se declaran "souts" para hacer visibles las posibles opciones
          - Se pide que introduzcan la opción a ejecutar por teclado
          1- Este método permite crear un nuevo contacto
          2- Permite listar todos los contactos, ya sea después de haber creado un contacto o solo para mostrarla
          3- Permite modificar/actualizar datos de un contacto, es decir, actualizar un atributo
          4- Permite borrar un contacto que ya no necesite
          9- Permite salir del bucle del menú*/
    }

    //<editor-fold defaultstate="collapsed" desc="Menú Artículos">
    public void menuArticulo() {
        int opcion;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU DE ARTICULOS");
            System.out.println("\t\t\t\t1 - ALTA");
            System.out.println("\t\t\t\t2 - BAJA");
            System.out.println("\t\t\t\t3 - REPOSICIÓN");
            System.out.println("\t\t\t\t4 - LISTAR ARTICULOS");
            System.out.println("\t\t\t\t9 - SALIR");
            System.out.println("\t\t\t\t¿Qué opción quieres ejecurtar?");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    altaArticulo();
                    break;
                }
                case 2: {
                    bajaArticulo();
                    break;
                }
                case 3: {
                    reposicionArticulo();
                    break;
                }
                case 4: {
                    listarArticulos();
                    break;
                }

            }

        } while (opcion != 9);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Menú Cliente">
    public void menuCliente() {
        int opcion;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU DE CLIENTES");
            System.out.println("\t\t\t\t1 - ALTA");
            System.out.println("\t\t\t\t2 - BAJA");
            System.out.println("\t\t\t\t3 - MODIFICACION CLIENTE");
            System.out.println("\t\t\t\t4 - LISTAR CLIENTES");
            System.out.println("\t\t\t\t9 - SALIR");
            System.out.println("\t\t\t\t¿Qué opción quieres ejecurtar?");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    altaCliente();
                    break;
                }
                case 2: {
                    bajaCliente();
                    break;
                }
                case 3: {
                    modificarDatos();
                    break;
                }
                case 4: {
                    listarClientes();
                    break;
                }

            }

        } while (opcion != 9);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Menú Pedido">
    public void menuPedido() {
        int opcion;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU DE PEDIDOS");
            System.out.println("\t\t\t\t1 - NUUEVO PEDIDO");
            System.out.println("\t\t\t\t2 - LISTADO DE PEDIDOS");
            System.out.println("\t\t\t\t3 - TOTAL PEDIDO");
            System.out.println("\t\t\t\t9 - SALIR");
            System.out.println("\t\t\t\t¿Qué opción quieres ejecurtar?");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    nuevoPedido();
                    break;
                }
                case 2: {
                    listadoPedido();
                    break;
                }
                case 3: {
                    totalPedido();
                    break;
                }

            }

        } while (opcion != 9);
    }
//</editor-fold>

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Listado tradicional">
    public void listarColecciones() {
        System.out.println("Vamos a mostrar todos los clientes de la tienda: ");
        for (Cliente c : clientes.values()) {
            System.out.println(c);
            //System.out.print("\n" + l.getIsbn() + "/" + l.getTitulo() + "/" + l.getAutor() + "/" + l.getGenero());
        }
        System.out.println("");

        System.out.println("Vamos a mostrar los articulos de la tienda: ");
        for (Articulo a : articulos.values()) {
            System.out.println(a);
            //System.out.print("\n" + a.getidArticulo() + "/" + a.getdescripción() + "/" + a.getexistencias() + "/" + a.getpvp());
        }
        System.out.println("");

        System.out.println("Vamos a mostrar los pedidos de la tienda: ");
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Gestión Artículos">
    private void altaArticulo() {
        //DEBEMOS PEDIR LOS 4 ATRIBUTOS POR TECLADO Y LUEGO AÑADIRLO AL HASHMAP
        Scanner sc = new Scanner(System.in);

        //En el caso del id tiene un formato, el primer nº es la sección (tipo de artículo) y el segundo es el artículo como tal, es necesaria un aexpresión regular para validar el id
        String idArticulo, descripción, existencias, pvp;

        System.out.println("ALTA DE NUEVO ARTICULO");
        //ANTES: idArticulo=sc.next();
        //AHORA DEBEMOS VALIDAR EL idArticulo CON UNA EXPRESÓN REGULAR SENCILLA
        do {
            System.out.println("IdArticulo (IDENTIFICADOR) : ");
            idArticulo = sc.nextLine();
        } while (!idArticulo.matches("[1-5][-][0-9][0-9]") || articulos.containsKey(idArticulo));
        /*EL STRING DEBE COINCIDIR CON ESTE PATRÓN, .matches INICIALIZA LA EXPRESIÓN REGULAR
        -NUESTRA TIENDA TIENE 5 SECCIONES-> [1-5]
        -UN GUIÓN-> [-]
        -UN Nº DEL 0 AL 9->[0-9] [0-9]
        -AÑADIMOS UN O PARA EL CASO DE QUE EL ARTÍCULO YA EXISTE MEDIANTE LA IDENTIFICACIÓN DE LA CLAVE-> ||articulos.containsKey(pvp));
        ESTO HARÁ QUE EL USUARIO ESTÉ EN EL BUCLE ETERNAMENTE HASTA TECLEAR BIEN EL FORMATO QUE HEMOS ESTABLECIDO, ESTAMOS CONTROLANDO EL FORMATP Y SI YA EXISTE*/

        System.out.println("DESCRIPCIÓN");//EN ESTE CASO NO TIENE UN FARMATO DECIDIDO, POR LO TANTO, NO ES NECESARIA UNA VALIDACIÓN
        descripción = sc.nextLine();

        //EXISTENCIAS CON VALIDACIÓN DE TIPO int
        do {
            System.out.println("EXISTENCIAS:");
            existencias = sc.nextLine();
        } while (!MetodosAuxiliares.esInt(existencias));//LLAMAMOS LOS MetodosAuxiliares PARA VALIDAR EL int

        //PVP CON VALIDACIÓN DE TIPO double
        do {
            System.out.println("PVP:");
            pvp = sc.nextLine();
        } while (!MetodosAuxiliares.esDouble(pvp));

        //YA HEMOS VALIDADO LOS ATRIBUTOS, AHORA AÑADIMOS EL NUEVO ARTICULO A LA COLECCIÓN
        Articulo a = new Articulo(idArticulo, descripción,
                Integer.parseInt(existencias), Double.parseDouble(pvp));//DEBEMOS PONER EL Integer.parseInt y el Double para convertir la validación de los Strings al dato que de verdad queremos almacenar
        articulos.put(idArticulo, a);
        System.out.println("SE HA AÑADIDO EL NUEVO ARTICULO CORRECTAMENTE");
    }

    private void bajaArticulo() {
        /*Solo tiene sentido guardar la información de dichos artículos ya sean activos=se venden, inactivos=no se venden, porque nos intereza tener guardados los artículos que he vendio anteriormente
        más que borar esos datos, los desactivo y los almaceno en un histórico para estén accesibles en el caso de ser necesarios, aunque no estén en el catálogo de ventas actual*/
    }

    private void reposicionArticulo() {

    }

    private void listarArticulos() {
        System.out.println("Vamos a mostrar los articulos de la tienda: ");
        for (Articulo a : articulos.values()) {
            System.out.println(a);
            //System.out.print("\n" + a.getidArticulo() + "/" + a.getdescripción() + "/" + a.getexistencias() + "/" + a.getpvp());
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Gestión de Clientes">
    private void altaCliente() {

    }

    private void bajaCliente() {

    }

    private void modificarDatos() {

    }

    private void listarClientes() {
        System.out.println("Vamos a mostrar todos los clientes de la tienda: ");
        for (Cliente c : clientes.values()) {
            System.out.println(c);
            //System.out.print("\n" + l.getIsbn() + "/" + l.getTitulo() + "/" + l.getAutor() + "/" + l.getGenero());
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Gestión Pedidos">
    private void nuevoPedido() {
        sc.nextLine();
        String idCliente;
        do {
            System.out.println("DNI (id) CLIENTE:");
            idCliente = sc.nextLine().toUpperCase();

            if (!clientes.containsKey(idCliente)) {
                System.out.println("El id de clente no es valido. "
                        + "Desea darse de alta como cliente o comprar como invitado");
            }

        } while (!MetodosAuxiliares.validarDni(idCliente));
        ArrayList<LineaPedido> cestaCompra = new ArrayList();
        String idArticulo;
        int unidades = 0;
        System.out.print("\nTeclee el ID del articulo deseado (FIN para terminar la compra)");
        idArticulo = sc.next();

        while (idArticulo.equalsIgnoreCase("FIN"));
        { //Es mejor utilizar un while en lugar de un do while como en el commit anterior, con el do while estamos obligando a la persona a seguir con los atributos aunque no quiera comprar
            System.out.print("\nTeclee las unidades deseadas: ");
            unidades = sc.nextInt();

            try {
                stock(idArticulo, unidades);
                cestaCompra.add(new LineaPedido(idArticulo, unidades));
                
            } catch (StockCero ex) {
                System.out.println(ex.getMessage());
            } catch (StockInsuficiente ex) {
                System.out.println(ex.getMessage());
                
                System.out.println("Quieres las unidades disponibles (SI / NO)");
                String respuesta=sc.next();
                if (respuesta.equalsIgnoreCase("SI")) {
                    cestaCompra.add(new LineaPedido(idArticulo,articulos.get(idArticulo).getExistencias()));// le damos los articulos que hay
                    articulos.get(idArticulo).setExistencias(0);
                }
                System.out.println("\nTeclea el ID del articulo que deseas (FIN para terminar la compra)");
            }
            
            System.out.println("\nTeclee el ID del artículo deseado (FIN para terminar la compra)");
            idArticulo = sc.next();
            /*Pedimos uno a uno los atributos del pedido, son las cosas que necesitamos para realizar un pedido
        -Generamos el id de ese pedido mediante la llamada al método generaIdPedido anterior
        -Lo asociamos a un cliente
        -Obtenemos la fecha del pedido
        -Hace falta rellenar el ArrayList del pedido*/
        }
        if (cestaCompra.size() > 0) {//tambien valdria (!cestaCompra.isEmpty())
            Pedido p = new Pedido(generaIdPedido(idCliente), clientes.get(idCliente), LocalDate.now(), cestaCompra);
            pedidos.add(p);

        }
    }

    /*   do {            
            System.out.println("Teclea el ID del articulo deseado (FIN para terminar la compra) ");
            idArticulo=sc.nextLine();
            System.err.println("\nTeclea las unidades deseadas: ");
            unidades=sc.nextInt();
            cestaCompra.add(new LineaPedido(idArticulo,unidades));
        } while (!idArticulo.equalsIgnoreCase("FIN"));
                
        Pedido p = new Pedido(generaIdPedido(idCliente), clientes.get(idCliente), LocalDate.now(), cestaCompra);
        pedidos.add(p);*/
    private void listadoPedido() {
        System.out.println("Vamos a mostrar los pedidos de la tienda: ");
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
    }

    private void totalPedido() {

    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Listados con STREAM">
    private void listadosConStreams() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Ordenar con STREAMS">
    private void ordenarConStream() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
//</editor-fold>
}

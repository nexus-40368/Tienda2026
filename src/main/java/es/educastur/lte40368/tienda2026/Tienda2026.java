/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package es.educastur.lte40368.tienda2026;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author 1dawd18
 */
public class Tienda2026 implements Serializable {

    static final transient Scanner sc = new Scanner(System.in);
    private ArrayList<Pedido> pedidos;
    private ArrayList<LineaPedido> cestaCompra = new ArrayList();
    private HashMap<String, Articulo> articulos;
    private HashMap<String, Cliente> clientes;

    public Tienda2026() {
        pedidos = new ArrayList();
        articulos = new HashMap();
        clientes = new HashMap();
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public HashMap<String, Articulo> getArticulos() {
        return articulos;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public static void main(String[] args) {
        Tienda2026 t2026 = new Tienda2026();
        t2026.cargaDatos();
        t2026.imporColecciones();
        //t2026.imporSecciones();
        //t2026.pedidoOrden();
        //t2026.pedidoOrden();
        // t2026.guardaClientes();
        //t2026.leeClientes();
        //   t2026.leeArticuloPorSeccion();
        // t2026.guardaArticuloPorseccion();
        // t2026.stream1(); 
        // System.out.println(t2026.uniVendArticulos(t2026.articulos.get("4-33")));
        //System.out.println(t2026.uniVendArticulos2(t2026.articulos.get("4-33")));
        // System.out.println(t2026.uniVendArticulos3(t2026.articulos.get("4-33")));
        //t2026.metodosStream();
        // t2026.menu();
        // t2026.uno1();
        // t2026.dos();
        //  t2026.tres();
        // t2026.cuatro();
        // t2026.cinco();

        //ACHIVOS BINARIOS      
        /* try (ObjectOutputStream oosTienda = new ObjectOutputStream(new FileOutputStream("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\Tienda2026.dat"))) {
            oosTienda.writeObject(t2026);
            System.out.println("TODO OK");
        } catch (IOException e) {
            System.out.println("No se ha podido realizar la copia de seguridad correctamente, "
                    + "revise unidades de almacenamiento e intente de nuevo");
            File f = new File("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\Tienda2026.dat");
            f.delete();
        }*/
        t2026.exporSeccinesBi();
        t2026.exportarColecciones();
    }

    //GUARDAR COLECCIONE DE LA TIENDA
    public void exportarColecciones() {
        try (ObjectOutputStream oosArticulo = new 
        ObjectOutputStream(new FileOutputStream("D:\\Articulo.dat")); 
                ObjectOutputStream oosCliente = new 
        ObjectOutputStream(new FileOutputStream("D:\\Cliente.dat")); 
                ObjectOutputStream oosPedido = new 
        ObjectOutputStream(new FileOutputStream("D:\\Pedido.dat"))) {
            //EL BUCLE RECORRE LA COLECCION UNA A UNA Y LA GUARDA   
            for (Articulo a : articulos.values()) {
                oosArticulo.writeObject(a);
            }
            System.out.println("ARCHIVO GUARDADO CORRECTAMENTE");
            for (Cliente c : clientes.values()) {
                oosCliente.writeObject(c);
            }
            System.out.println("ARCHIVO GUARDADO CORRECTAMENTE");
            for (Pedido p : pedidos) {
                oosPedido.writeObject(p);
            }
            System.out.println("ARCHIVO GUARDADO CORRECTAMENTE");
        } catch (IOException ex) {
            System.out.println("No se ha podido realizar la copia de seguridad correctamente, "
                    + "revise unidades de almacenamiento e intente de nuevo");
            File f = new File("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\Articulo.dat");
            f.delete();
            f = new File("D:\\Cliente.dat");
            f.delete();
            f = new File("D:\\Pedido.dat");
            f.delete();
        }
    }
    //METODOS PARA EXPORTAR ARTICULOS SECCIONES EN BINARIOS 

    public void exporSeccinesBi() {
        System.out.println("\nINTRODUCE UN NUMERO DE SECCION  1 - 2 -  3 - 4");
        try (ObjectOutputStream oosPerifericos = new ObjectOutputStream(new FileOutputStream("D:\\Perifericos.dat")); ObjectOutputStream oosAlmacenamiento = new ObjectOutputStream(new FileOutputStream("D:\\Almacenamiento.dat")); ObjectOutputStream oosImpresoras = new ObjectOutputStream(new FileOutputStream("D:\\Impresoras.dat")); ObjectOutputStream oosMonitores = new ObjectOutputStream(new FileOutputStream("D:\\Monitores.dat"))) {
            for (Articulo a : articulos.values()) {
                switch (a.getIdArticulo().charAt(0)) {
                    case '1':
                        oosPerifericos.writeObject(a);
                        System.out.println("ARCHIVO GUARDADO CORRECTAMENTE");
                        break;
                    case '2':
                        oosAlmacenamiento.writeObject(a);
                        System.out.println("ARCHIVO GUARDADO CORRECTAMENTE");
                        break;
                    case '3':
                        oosImpresoras.writeObject(a);
                        System.out.println("ARCHIVO GUARDADO CORRECTAMENTE");
                        break;
                    case '4':
                        oosMonitores.writeObject(a);
                        System.out.println("ARCHIVO GUARDADO CORRECTAMENTE");
                        break;
                }
            }

        } catch (IOException ex) {
            System.out.println("No se ha podido realizar la copia de seguridad correctamente, "
                    + "revise unidades de almacenamiento e intente de nuevo");
            File f = new File("D:\\Perifericos.dat");
            f.delete();
            f = new File("D:\\Almacenamiento.dat");
            f.delete();
            f = new File("D:\\Impresoras.dat");
            f.delete();
            f = new File("D:\\Monitores.dat");
            f.delete();
        }

        /* PARA COMPROBAR QUE FUNCIONA, VERIFICAMOS QUE SE HAN CREADO LOS 4 ARCHIVOS EN LA CARPETA
        RAÍZ DEL PROYECTO CON LA FECHA Y HORA ACTUAL - 
        ... Y PARA COMPROBAR EL CONTENIDO DE LOS ARCHIVOS LEEREMOS/IMPRIMIREMOS "AL VUELO" SÓLO 1 DE ELLOS
         CUYA SECCION SOLICITAMOS POR TECLADO
         */
        System.out.println("Teclea la Seccion de los articulos CUYO ARCHIVO QUIERES COMPROBAR:");
        char seccion = sc.next().charAt(0);
        String nombreArchivo = null;
        switch (seccion) {
            case '1':
                nombreArchivo = "D:\\Perifericos.dat";
                break;
            case '2':
                nombreArchivo = "D:\\Almacenamiento.dat";
                break;
            case '3':
                nombreArchivo = "D:\\Impresoras.dat";
                break;
            case '4':
                nombreArchivo = "D:\\Monitores.dat";
                break;
        }
        Articulo a;
        try (ObjectInputStream oisArticulos = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            while ((a = (Articulo) oisArticulos.readObject()) != null) {
                System.out.println(a);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (EOFException e) {

        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.toString());
        }

    }
  // EXPORTAR POR FECHA EN BINARIO 
    public void exportarPedidosUltimos7DiasBinario() {

        LocalDate fechaLimite = LocalDate.now().minusDays(7);

        try (ObjectOutputStream oos
                = new ObjectOutputStream(
                        new FileOutputStream("D:\\pedidos_ultimos_7_dias.dat"))) {

            for (Pedido p : pedidos) {

                if (!p.getFechaPedido().isBefore(fechaLimite)) {

                    oos.writeObject(p);
                }
            }

            System.out.println("Archivo binario exportado correctamente");

        } catch (IOException e) {
            System.out.println("Error al exportar pedidos");
        }

    }
    public void leerPedidosUltimos7DiasBinario() {

    ArrayList<Pedido> pedidosAux = new ArrayList<>();

    try (ObjectInputStream ois =
            new ObjectInputStream(
                new FileInputStream("D:\\pedidos_ultimos_7_dias.dat"))) {

        Pedido p;

        while ((p = (Pedido) ois.readObject()) != null) {

            pedidosAux.add(p);
        }

    } catch (EOFException e) {
        System.out.println("Fin del archivo");
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error al leer archivo");
    }

    System.out.println("\nPEDIDOS LEÍDOS:");
    pedidosAux.forEach(System.out::println);
}
 // EXPORTAR POR FECHA
    public void exportarPedidosUltimos7Dias() {

        LocalDate fechaLimite = LocalDate.now().minusDays(7);

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("D:\\pedidos_ultimos_7_dias.csv"))) {

            for (Pedido p : pedidos) {

                if (!p.getFechaPedido().isBefore(fechaLimite)) {

                    double total = 0;

                    for (LineaPedido lp : p.getCestaCompra()) {
                        total += lp.getUnidades() * lp.getArticulo().getPvp();
                    }

                    bw.write(p.getIdPedido() + ","
                            + p.getClientePedido().getIdCliente() + ","
                            + p.getFechaPedido() + ","
                            + total);

                    bw.newLine();
                }
            }

            System.out.println("Archivo exportado correctamente");

        } catch (IOException e) {
            System.out.println("Error al exportar pedidos");
        }

    }
    public void leerPedidosUltimos7DiasCSV() {

    ArrayList<Pedido> pedidosAux = new ArrayList<>();

    try (Scanner sc = new Scanner(
            new File("D:\\pedidos_ultimos_7_dias.csv"))) {

        while (sc.hasNextLine()) {

            String[] datos = sc.nextLine().split(",");

            String id = datos[0];
            String dni = datos[1];
            LocalDate fecha = LocalDate.parse(datos[2]);

            Cliente c = clientes.get(dni);

            Pedido p = new Pedido(id, c, fecha, new ArrayList<>());

            pedidosAux.add(p);
        }

        System.out.println("Pedidos cargados desde CSV");

    } catch (IOException e) {
        System.out.println("Error al leer CSV");
    }

    pedidosAux.forEach(System.out::println);
}
    // con binarios sin pedidos
    public void exportarClientesPorPedidosBinario() {

    try (
        ObjectOutputStream oosConPedidos =
                new ObjectOutputStream(new FileOutputStream("D:\\clientes_con_pedidos.dat"));

        ObjectOutputStream oosSinPedidos =
                new ObjectOutputStream(new FileOutputStream("D:\\clientes_sin_pedidos.dat"))
    ) {

        for (Cliente c : clientes.values()) {

            int contador = 0;

            for (Pedido p : pedidos) {
                if (p.getClientePedido().equals(c)) {
                    contador++;
                    break;
                }
            }

            if (contador > 0) {
                oosConPedidos.writeObject(c);
            } else {
                oosSinPedidos.writeObject(c);
            }
        }

        System.out.println("Archivos binarios creados correctamente");

    } catch (IOException e) {
        System.out.println("Error al exportar clientes en binario");
    }
}
    //sin binarios 
    public void exportarClientesPorPedidos() {

    try (
        BufferedWriter bwConPedidos = new BufferedWriter(new FileWriter("D:\\clientes_con_pedidos.csv"));
        BufferedWriter bwSinPedidos = new BufferedWriter(new FileWriter("D:\\clientes_sin_pedidos.csv"))
    ) {

        for (Cliente c : clientes.values()) {

            int contador = 0;

            for (Pedido p : pedidos) {
                if (p.getClientePedido().equals(c)) {
                    contador++;
                    break; // importante para no seguir recorriendo
                }
            }

            String linea = c.getIdCliente() + "," +
                           c.getNombre() + "," +
                           c.getTelefono() + "," +
                           c.getEmail();

            if (contador > 0) {
                bwConPedidos.write(linea);
                bwConPedidos.newLine();
            } else {
                bwSinPedidos.write(linea);
                bwSinPedidos.newLine();
            }
        }

        System.out.println("Archivos exportados correctamente");

    } catch (IOException e) {
        System.out.println("Error al exportar clientes");
    }
}
    
    private void leerClientesSinPedidosBinario() {

    HashMap<String, Cliente> clientesAux = new HashMap<>();

    try (ObjectInputStream ois =
            new ObjectInputStream(
                new FileInputStream("D:\\clientes_sin_pedidos.dat"))) {

        Cliente c;

        while ((c = (Cliente) ois.readObject()) != null) {

            clientesAux.put(c.getIdCliente(), c);
        }

    } catch (EOFException e) {
        System.out.println("Fin del archivo");
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error al leer binario");
    }

    System.out.println("Clientes cargados en HashMap auxiliar");

    clientesAux.values().forEach(System.out::println);
}
    
    public void leerClientesPorPedidosHashMap() {

    HashMap<String, Cliente> clientesConPedidos = new HashMap<>();
    HashMap<String, Cliente> clientesSinPedidos = new HashMap<>();

    // 🔹 CLIENTES CON PEDIDOS
    try (Scanner sc = new Scanner(
            new File("D:\\clientes_con_pedidos.csv"))) {

        while (sc.hasNextLine()) {

            String[] datos = sc.nextLine().split(",");

            Cliente c = new Cliente(
                    datos[0],
                    datos[1],
                    datos[2],
                    datos[3]
            );

            clientesConPedidos.put(c.getIdCliente(), c);
        }

    } catch (IOException e) {
        System.out.println("Error al leer clientes con pedidos");
    }

    // 🔹 CLIENTES SIN PEDIDOS
    try (Scanner sc = new Scanner(
            new File("D:\\clientes_sin_pedidos.csv"))) {

        while (sc.hasNextLine()) {

            String[] datos = sc.nextLine().split(",");

            Cliente c = new Cliente(
                    datos[0],
                    datos[1],
                    datos[2],
                    datos[3]
            );

            clientesSinPedidos.put(c.getIdCliente(), c);
        }

    } catch (IOException e) {
        System.out.println("Error al leer clientes sin pedidos");
    }

    // 🔥 MOSTRAR RESULTADO
    System.out.println("\nCLIENTES CON PEDIDOS:");
    clientesConPedidos.values().forEach(System.out::println);

    System.out.println("\nCLIENTES SIN PEDIDOS:");
    clientesSinPedidos.values().forEach(System.out::println);
}

    // IMPORTAR COLECIONE CON ESTE METDO YA NO ES NECESARIO USAR EL CARGA DATOS
    public void imporColecciones() {
        try (ObjectInputStream oisArticulo = new ObjectInputStream(new FileInputStream("D:\\Articulo.dat"))) {
            Articulo a;
            while ((a = (Articulo) oisArticulo.readObject()) != null) {
                articulos.put(a.getIdArticulo(), a);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (EOFException e) {
            System.out.println("Finalizada la lectura del archivo Articulo.dat");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.toString());
        }
        try (ObjectInputStream oisCliente = new ObjectInputStream(new FileInputStream("D:\\Cliente.dat"))) {
            Cliente c;
            while ((c = (Cliente) oisCliente.readObject()) != null) {
                clientes.put(c.getIdCliente(), c);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            
        } catch (EOFException e) {
            System.out.println("Finalizada la lectura del archivo Cliente.dat");
            
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.toString());
            
        }
        try (ObjectInputStream oisPedido = new ObjectInputStream(new FileInputStream("D:\\Pedido.dat"))) {
            Pedido p;
            while ((p = (Pedido) oisPedido.readObject()) != null) {
                pedidos.add(p);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            
        } catch (EOFException e) {
            System.out.println("Finalizada la lectura del archivo Pedido.dat");
            
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.toString());
        }
    }

    //GUARDAR ARCHIVO POR SECCION
    private void guardaArticuloPorseccion() {
        System.out.println("\nGUARDAR ARCHIVOS ARTICULOS POR SECCION");
        try (BufferedWriter bwPerifericos = new BufferedWriter(new FileWriter("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\perifericos.csv")); BufferedWriter bwAlmacenamiento = new BufferedWriter(new FileWriter("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\almacenamiento.csv")); BufferedWriter bwImpresoras = new BufferedWriter(new FileWriter("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\impresoras.csv")); BufferedWriter bwMonitores = new BufferedWriter(new FileWriter("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\monitores.csv"))) {
            for (Articulo a : articulos.values()) {
                switch (a.getIdArticulo().charAt(0)) {
                    case '1':
                        bwPerifericos.write(a.getIdArticulo() + "," + a.getDescripcion() + "," + a.getExistencias() + "," + a.getPvp() + "\n");
                        break;
                    case '2':
                        bwAlmacenamiento.write(a.getIdArticulo() + "," + a.getDescripcion() + "," + a.getExistencias() + "," + a.getPvp() + "\n");
                        break;
                    case '3':
                        bwImpresoras.write(a.getIdArticulo() + "," + a.getDescripcion() + "," + a.getExistencias() + "," + a.getPvp() + "\n");
                        break;
                    case '4':
                        bwMonitores.write(a.getIdArticulo() + "," + a.getDescripcion() + "," + a.getExistencias() + "," + a.getPvp() + "\n");
                        break;
                }
            }
            System.out.println("Archivos creados correctamente");
        } catch (IOException e) {
            System.out.println("No se han podido crear los archivos");
            File f = new File("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\perifericos.csv");
            f.delete();
            f = new File("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\almacenamiento.csv");
            f.delete();
            f = new File("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\impresoras.csv");
            f.delete();
            f = new File("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\monitores.csv");
            f.delete();
        }

    }

    //LEE LOS ARTIULOS GUARDADOS POR SECCION
    private void leeArticuloPorSeccion() {
        HashMap<String, Articulo> articuloAux = new HashMap();
        String lineaArchivo;
        String[] atributos;
        System.out.println("\nLEER ARTICULOS POR SECCION");
        try (Scanner scPerifericos = new Scanner(new File("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\perifericos.csv")); Scanner scAlmacenamineto = new Scanner(new File("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\almacenamiento.csv")); Scanner scImpresora = new Scanner(new File("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\impresoras.csv")); Scanner scMonitores = new Scanner(new File("C:\\Users\\1dawd18\\Documents\\Programacion\\Nueva carpeta\\monitores.csv"))) {
            //------------------------------------------------------------------------------------------------------------------------------------------------------------------            
            System.out.println("\nPERIFERICOS");
            while (scPerifericos.hasNextLine()) {
                lineaArchivo = scPerifericos.nextLine();
                System.out.println(lineaArchivo);
                atributos = scPerifericos.nextLine().split("[,]");
                articuloAux.put(atributos[0], new Articulo(atributos[0], atributos[1], Integer.parseInt(atributos[2]), Double.parseDouble(atributos[3])));
                System.out.println(scPerifericos.nextLine());
            }
            System.out.println("ALMACENAMIENTO");
            while (scAlmacenamineto.hasNextLine()) {
                lineaArchivo = scAlmacenamineto.nextLine();
                System.out.println(lineaArchivo);
                atributos = scAlmacenamineto.nextLine().split("[,]");
                articuloAux.put(atributos[0], new Articulo(atributos[0], atributos[1], Integer.parseInt(atributos[2]), Double.parseDouble(atributos[3])));

            }
            //------------------------------------------------------------------------------------------------------------------------------------------------------------------ 
            System.out.println("IMPRESORA");
            while (scImpresora.hasNextLine()) {
                lineaArchivo = scImpresora.nextLine();
                System.out.println(lineaArchivo);
                atributos = scImpresora.nextLine().split("[,]");
                articuloAux.put(atributos[0], new Articulo(atributos[0], atributos[1], Integer.parseInt(atributos[2]), Double.parseDouble(atributos[3])));
            }
            //------------------------------------------------------------------------------------------------------------------------------------------------------------------   
            System.out.println("MONITORES");
            while (scMonitores.hasNextLine()) {
                lineaArchivo = scMonitores.nextLine();
                System.out.println(lineaArchivo);
                atributos = scMonitores.nextLine().split("[,]");
                articuloAux.put(atributos[0], new Articulo(atributos[0], atributos[1], Integer.parseInt(atributos[2]), Double.parseDouble(atributos[3])));
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    // LEEMOS LOS ARCHIVO GUARDADOS 
    private void leeClientes() {
        System.out.println("\nListados de clientes de .txt");
        try (Scanner scClientes = new Scanner(new File("C:\\Users\\1dawd18\\OneDrive - Consejería de Educación\\Programacion\\gruardar archcivo\\clientes.txt"))) {
            while (scClientes.hasNextLine()) {
                System.out.println(scClientes.nextLine());

            }
        } catch (IOException e) {
            System.out.println(e.toString());

        }

        //SE CREA UNA NUEVA COLECCION DE TIPO HASHMAP A PARTITIR DEL ARCHIVO CLIENTES.CSV
        HashMap<String, Cliente> clientesAux = new HashMap();
        try (Scanner scClientes = new Scanner(new File("C:\\Users\\1dawd18\\OneDrive - Consejería de Educación\\Programacion\\gruardar archcivo\\clientes.csv"))) {
            while (scClientes.hasNextLine()) {
                String[] atributos = scClientes.nextLine().split("[,]");
                Cliente c = new Cliente(atributos[0], atributos[1], atributos[2], atributos[3]);
                clientesAux.put(atributos[0], c);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        System.out.println("\nListados de clientes del nuevo Hashmap .csv\n");
        clientesAux.values().forEach(System.out::println);
    }

    private void guardaClientes() {
        System.out.println("\n-----------------------------------------------------------------------");
        //GUARDAMOS LOS CLIENTES LÍNEA A LÍNEA EN UN ARCHIVO .txt ESCRIBIENDO LOS DATOS SEGUN TENGAMOS DISPUESTO EN EL toString()
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\1dawd18\\OneDrive - Consejería de Educación\\Programacion\\gruardar archcivo\\clientes.txt"))) {
            for (Cliente c : clientes.values()) {
                bw.write(c.toString());
                bw.newLine();
            }
            System.out.println("El Archvo se a creado correctamente en: C:\\Users\\1dawd18\\OneDrive - Consejería de Educación\\Programacion\\gruardar archcivo\\clientes.txt");
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }
        //GUARDAMOS LOS CLIENTES LÍNEA A LÍNEA EN UN ARCHIVO .csv CON LOS VALORES DE LOS ATRIBUTOS SEPARADOS POR ,        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\1dawd18\\OneDrive - Consejería de Educación\\Programacion\\gruardar archcivo\\clientes.csv"))) {
            for (Cliente c : clientes.values()) {
                bw.write(c.getIdCliente() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getEmail() + "\n");
            }
            System.out.println("El Archvo se a creado correctamente en: C:\\Users\\1dawd18\\OneDrive - Consejería de Educación\\Programacion\\gruardar archcivo\\clientes.csv ");
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero");
        }
    }

    //METODO SELECIONAR UNA SECCION POR TECLADO 
    private void uno1() {
        String[] seccion = {"", "PERIFERICOS", "ALMACENAMIENTO", "IMPRESORAS", "MONITORES"};
        System.out.println("Selecion a listar");
        String sec = sc.nextLine();
        System.out.println("ARTICULOS DE LA SECCION" + seccion[Integer.parseInt(sec)] + ":");

        // DOS FORMA DE RESOLVER
        // 1 BUCLE FOR-EACH
        for (Articulo a : articulos.values()) {
            if (a.getIdArticulo().startsWith(sec)) {
                System.out.println(a);
            }
        }
        System.out.println("");
        //2 CON STREAMS ES MEJOR  
        articulos.values().stream()
                .filter(a -> a.getIdArticulo().startsWith(sec))
                .forEach(a -> System.out.println(a));
    }

    private void dos1() {
        String[] seccion = {"", "PERIFERICOS", "ALMACENAMIENTO", "IMPRESORAS", "MONITORES"};
    }

    //METODOS PEDIDOS DE UN CLIENTE Y TOTAL GASTADO
    private void tres1() {
        sc.nextLine();
        System.out.println("Introduce un dni");
        String dni;
        do {
            System.out.println("DNI (id) CLIENTE:");
            dni = sc.nextLine().toUpperCase();
        } while (!MetodosAuxiliares.validarDni(dni));
        if (clientes.containsKey(dni)) {
            double total = 0;
            System.out.println("Pedidos Clientes " + clientes.get(dni).getNombre() + ":");
            /*for (Pedido p : pedidos) {
                if (p.getClientePedido().getIdCliente().equals(dni)) {
                    System.out.println(p + "\tTotal: " + totalPedido(p));
                    total += totalPedido(p);
                }
            }*/

            System.out.println("\t total gastado: " + total);
        } else {
            System.out.println("El cliente no exixste:");
        }
    }

    //METODO LISTADO DE TODOS LOS ARTICULOS SEGUN LAS UNIDADES VENDIDAS
    private void cuatro1() {
        System.out.println("LISTADO DE ARTICULOS - UNIDADES VENDIDAS: \n");
        articulos.values().stream()
                .sorted(Comparator.comparing(a -> unidadesVendidaas((Articulo) a)).reversed())
                .forEach(a -> System.out.println("\t" + a.getDescripcion() + "\t VENDIDAS " + unidadesVendidaas(a)));
        // NOTA: PARA QUE ESTE METODO FUNCIONE HAY QUE CREAR UN METODO UNIDADES VENDIAS Y LLAMARLO 
    }

    //METODOS PARA MOSTRAR CLIENTES SIN PEDIDOS 
    private void cinco1() {
        ArrayList<Cliente> clientesSin = new ArrayList();
        for (Cliente c : clientes.values()) {
            int cont = 0;
            for (Pedido p : pedidos) {
                if (p.getClientePedido().equals(c)) {
                    cont++;
                    break;
                }
            }
            if (cont == 0) {
                clientesSin.add(c);
            }
        }
        System.out.println("clientes sin pedidos:\n");
        for (Cliente c : clientesSin) {
            System.out.println(c);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="metodos">
    public void stock(Articulo a, int unidades) throws StockCero, StockInsuficiente {
        if (a.getExistencias() == 0) {
            throw new StockCero("0 unidades disponibles"
                    + a.getDescripcion());
        }
        if (a.getExistencias() < unidades) {
            throw new StockInsuficiente("Solo hay " + a.getExistencias()
                    + a.getDescripcion());
        }
    }

    // METODO UNIDADES VENDIDAS PARA DETERMINAR LOS ARTICULOS VENDIDOS 
    public int unidadesVendidaas(Articulo a) {
        int c = 0;
        for (Pedido p : pedidos) {
            for (LineaPedido l : p.getCestaCompra()) {
                if (l.getArticulo().equals(a)) {
                    c += l.getUnidades();
                }
            }
        }
        return c;
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

    public double totalPedido(Pedido p) { //Cambiamos a public para probarlo en los test de JUNIT 5
        double totalPedido = 0;
        for (LineaPedido l : p.getCestaCompra()) {
            totalPedido += l.getUnidades() * l.getArticulo().getPvp();
            //SE CAMBIA DE l.getIdArticulo a l.getArticulo porque cambiamos la clase de LineaPedido, NOS PERMITE ACCEDER AL ARTICULO DIRECTAMENTE
        }
        return totalPedido;
    }

    /* private double totalPedido(Pedido p) {
        double totalPedido = 0;
        for (LineaPedido l : p.getCestaCompra()) {
            totalPedido += l.getUnidades() * articulos.get(l.getArticulo().getIdArticulo()));
        }
        return totalPedido;
    }*/
    public void cargaDatos() {
        clientes.put("80580845T", new Cliente("80580845T", "ANA", "658111111", "ana@gmail.com"));
        clientes.put("36347775R", new Cliente("36347775R", "LOLA", "649222222", "lola@gmail.com"));
        clientes.put("63921307Y", new Cliente("63921307Y", "JUAN", "652333333", "juan@gmail.com"));
        clientes.put("02337565Y", new Cliente("02337565Y", "EDU", "634567890", "edu@gmail.com"));

        articulos.put("1-11", new Articulo("1-11", "RATON LOGITECH ST", 0, 15));
        articulos.put("1-22", new Articulo("1-22", "TECLADO STANDARD", 5, 18));
        articulos.put("2-11", new Articulo("2-11", "HDD SEAGATE 1 TB ", 0, 80));
        articulos.put("2-22", new Articulo("2-22", "SSD KINGSTOM 256GB", 9, 70));
        articulos.put("2-33", new Articulo("2-33", "SSD KINGSTOM 512GB", 0, 200));
        articulos.put("3-11", new Articulo("3-11", "HP LASERJET HP800", 2, 200));
        articulos.put("3-22", new Articulo("3-22", "EPSON PRINT XP300", 5, 80));
        articulos.put("4-11", new Articulo("4-11", "ASUS  MONITOR  22", 5, 100));
        articulos.put("4-22", new Articulo("4-22", "HP MONITOR LED 28", 5, 180));
        articulos.put("4-33", new Articulo("4-33", "SAMSUNG ODISSEY G5", 12, 580));
        LocalDate hoy = LocalDate.now();
        pedidos.add(new Pedido("80580845T-001/2025", clientes.get("80580845T"), hoy.minusDays(1), new ArrayList<>(List.of(new LineaPedido(articulos.get("1-11"), 3), new LineaPedido(articulos.get("4-22"), 3)))));
        pedidos.add(new Pedido("80580845T-002/2025", clientes.get("80580845T"), hoy.minusDays(2), new ArrayList<>(List.of(new LineaPedido(articulos.get("4-11"), 3), new LineaPedido(articulos.get("4-22"), 2), new LineaPedido(articulos.get("4-33"), 4)))));
        pedidos.add(new Pedido("36347775R-001/2025", clientes.get("36347775R"), hoy.minusDays(3), new ArrayList<>(List.of(new LineaPedido(articulos.get("4-22"), 1), new LineaPedido(articulos.get("2-22"), 3)))));
        pedidos.add(new Pedido("36347775R-002/2025", clientes.get("36347775R"), hoy.minusDays(5), new ArrayList<>(List.of(new LineaPedido(articulos.get("4-33"), 3), new LineaPedido(articulos.get("2-11"), 3)))));
        pedidos.add(new Pedido("63921307Y-001/2025", clientes.get("63921307Y"), hoy.minusDays(4), new ArrayList<>(List.of(new LineaPedido(articulos.get("2-11"), 5), new LineaPedido(articulos.get("2-33"), 3), new LineaPedido(articulos.get("4-33"), 2)))));

    }

    //EJERCIOS EN DE COLECCIONES 
    //ORDENAR UN PEDIDO POR FECHA
    public void pedidoOrden() {

        List<Pedido> pedidosOrdenadosfecha
                = pedidos.stream().sorted(Comparator.comparing(Pedido::getFechaPedido))
                        .collect(Collectors.toList());
        pedidos.stream().forEach(p -> System.out.println(p.getIdPedido() + " - " + p.getFechaPedido()));
        System.out.println("\n");
        pedidosOrdenadosfecha.stream().forEach(p -> System.out.println(p.getIdPedido() + " - " + p.getFechaPedido()));
        System.out.println("\n-----------------------------------------------------------------");

        //coleccion con los pedidos y su importe total como clave pricipal su total y valor el Pedido(coleccion)
        // SI PONEMOS TREEMAP Y NO HASHMAP, ORDENA LA CLAVE DE - A + 
        TreeMap<Double, Pedido> pedidosConTotales = new TreeMap();
        for (Pedido p : pedidos) {
            pedidosConTotales.put(totalPedido(p), p);
        }
        for (Double totPedido : pedidosConTotales.descendingKeySet()) {
            System.out.println(pedidosConTotales.get(totPedido).getIdPedido() + " - " + totPedido);
        }

        //NOTA: descendingKeySet() ORDENA + A - SOLO SE PUEDE USAR EN LOS TREEMAP
        //COLECCION DE CLIENTES CON SU TOTAL DE + A -
        System.out.println("\n-------------------------------------------------------------------------");
        HashMap<Double, Cliente> clienteConTotal = new HashMap();
        for (Cliente c : clientes.values()) {
            if (totalCliente(c) > 0) {
                clienteConTotal.put(totalCliente(c), c);
            }

        }
        for (Double total : clienteConTotal.keySet()) {
            System.out.println(clienteConTotal.get(total).getNombre() + " - " + total);
        }

        System.out.println("\n---------------------------------------------------------------------------");
        //COLECION DE LISTAS DE CADA SECCIONES 
        List<Articulo> periferico = new ArrayList();
        List<Articulo> almacenamiento = new ArrayList();
        List<Articulo> impresora = new ArrayList();
        List<Articulo> monitores = new ArrayList();
        // ESTILOS MODERNOS 
        //SI SE HACE DE UNA FORMA MODERNA SE PUEDE USA CON EJ: (LIST <ARTICULO> PERIFERICOS, ETC, ETC, ETC;)
        periferico = articulos.values().stream()
                .filter(a -> a.getIdArticulo().startsWith("1"))
                .collect(Collectors.toList());
        almacenamiento = articulos.values().stream()
                .filter(a -> a.getIdArticulo().startsWith("1"))
                .collect(Collectors.toList());
        impresora = articulos.values().stream()
                .filter(a -> a.getIdArticulo().startsWith("1"))
                .collect(Collectors.toList());
        monitores = articulos.values().stream()
                .filter(a -> a.getIdArticulo().startsWith("1"))
                .collect(Collectors.toList());

        //ESTILO CLASICO ES MAS EFICIENTE PORQUE SOLO DA UNA PASADA
        for (Articulo a : articulos.values()) {
            switch (a.getIdArticulo().charAt(0)) {
                case '1':
                    periferico.add(a);
                    break;
                case '2':
                    almacenamiento.add(a);
                    break;
                case '3':
                    impresora.add(a);
                    break;
                case '4':
                    monitores.add(a);
                    break;
                default:
            }
        }

        //EJERCICIOS DE BORRADOS EN COLLECIONES   
        // BORRADO LOS ARTICULOS DE LA SECCION
        //1-REMOVEIF SE APLICA UNA CONDICION 
        articulos.values().removeIf(a -> a.getIdArticulo().startsWith("3"));
        //LAS COLECCIONES DE TIPO LIST NO ACEPTAN REMOVEIF
        //-----------------------------------------------------
        // PARA HACER UN BORRADO EN LIST USAMOS OTRA ARTECNATIVA 
        //COLECIONAMOS EN UNA LIST Y BORRAMOS REMOVEALL
        List<Pedido> pedidosAtiguos
                = pedidos.stream().filter(p -> p.getFechaPedido().isBefore(LocalDate.now().minusDays(3)))
                        .collect(Collectors.toList());
        pedidos.removeAll(pedidosAtiguos);
        //REMOVE() TENGO QUE PONER EL OBJETO QUE QUIERO BORRAR
        pedidos.stream().forEach(p -> System.out.println(p));
    }

    public double totalCliente2(Cliente c) {
        double total = 0;
        total = pedidos.stream().filter(p -> p.getClientePedido().equals(c))
                .flatMap(p -> p.getCestaCompra().stream())
                .mapToDouble(l -> l.getUnidades() * l.getArticulo().getPvp()).sum();
        return total;
    }

    private void uno() {

        for (Cliente c : clientes.values()) {
            pedidos.stream().sorted(Comparator.comparing(p -> totalCliente2((Cliente) c)).reversed());
            System.out.println(c + "- Total: " + totalCliente2(c));
        }

    }

    private void dos() {
        System.out.println("INTRODUCE UN NUMERO PARA UNA SECCION"
                + "\n1- PERIFERICOS"
                + "\n2- ALMACENAMIENTO"
                + "\n3- IMPRESORA"
                + "\n4- MONITORES");
        System.out.println("\n");
        String sec = sc.nextLine();
        articulos.values().stream()
                .filter(a -> a.getIdArticulo().startsWith(sec) && a.getExistencias() > 0)
                .sorted(Comparator.comparing(Articulo::getPvp).reversed())
                .forEach(a -> System.out.println(a));

        /*int unidades = pedidos.stream().filter(p -> p.getArtic)
                    .flatMap(p -> p.getCestaCompra().stream()).filter(l -> l.getArticulo().equals(articulos.get("4-22")))
                    .mapToInt(LineaPedido::getUnidades).sum();

            System.out.println(c.getNombre() + ": " + unidades + " de " + articulos.get("4-22").getDescripcion());*/
    }

    private void tres() {
        Set<Articulo> vendidos
                = pedidos.stream()
                        .flatMap(p -> p.getCestaCompra().stream())
                        .map(LineaPedido::getArticulo)
                        .collect(Collectors.toSet());

        List<Articulo> noVendidos
                = articulos.values().stream()
                        .filter(a -> !vendidos.contains(a))
                        .toList();
        System.out.println("\n");
    }

    private void cuatro() {
        LocalDate fecha1 = LocalDate.now().minusDays(5);
        double total5dias = pedidos.stream()
                .filter(p -> p.getFechaPedido().isAfter(fecha1))
                .flatMap(p -> p.getCestaCompra().stream())
                .mapToDouble(lp -> lp.getArticulo().getPvp() * lp.getUnidades()).sum();
        System.out.println("\nTotal facturado los ultimos 5 dias: " + total5dias);
    }

    private void cinco() {
        double total
                = pedidos.stream().flatMap(p -> p.getCestaCompra().stream())
                        .mapToDouble(l -> l.getUnidades() * l.getArticulo().getPvp() / pedidos.size()).sum();

        System.out.println("IMPORTE MEDIO DE LA TIENDA " + total);
    }

    private void metodosStream() {
        //EJEMPLOS SENCILLOS CON filter() - sorted() - forEach()

        // ARTICULOS DE MENOS DE 100€ ORDENADOS POR PRECIO DwE - A +
        articulos.values().stream()
                .filter(a -> a.getPvp() < 100)
                .sorted(Comparator.comparing(Articulo::getPvp))
                .forEach(a -> System.out.println(a));

        //PEDIDOS ORDENADOS POR EL IMPORTE TOTAL DEL PEDIDO DE - A + (usamos el método auxiliar totalPedido()) 
        System.out.println("\n");
        pedidos.stream().sorted(Comparator.comparing(p -> totalPedido(p)))
                .forEach(p -> System.out.println(p + "- Total: " + totalPedido(p)));

        //PEDIDOS ORDENADOS POR EL IMPORTE TOTAL DEL PEDIDO DE + A - (usamos el método auxiliar totalPedido()) 
        System.out.println("\n");
        pedidos.stream().sorted(Comparator.comparing(p -> totalPedido((Pedido) p)).reversed())
                .forEach(p -> System.out.println(p + "- Total: " + totalPedido(p)));

        //PEDIDOS DE MÁS DE 1000€ (filter) ORDENADOS POR LA FECHA DEL PEDIDO DE - A + 
        System.out.println("\n");
        pedidos.stream().filter(p -> totalPedido(p) > 1000)
                .sorted(Comparator.comparing(Pedido::getFechaPedido))
                .forEach(p -> System.out.println(p + "- Total: " + p.getFechaPedido()));

        //EJERCICIOS CON MÉTODOS DEL API PARA REALIZAR CALCULOS count() map() mapToInt() .collect(Collectors.groupingBy) ...
        //CONTABILIZAR LOS PEDIDOS DE UN DETERMINADO CLIENTE - PODRÍA PEDIR NOMBRE O DNI POR TECLADO PERO LO HARÉ PARA UNO CONCRETO
        long numPedidos = pedidos.stream()
                .filter(p -> p.getClientePedido().getIdCliente().equalsIgnoreCase("80580845T"))
                .count();
        System.out.println("\n" + numPedidos + "\n");
        //LAS FUNCIONES TIPO count() counting() almacenan resultados en variables de tipo long 

        //CONTABILIZAR CUANTOS PEDIDOS HAY POR CLIENTE - PARA LAS AGRUPACIONES SON IDEALES LOS MAPAS PORQUE PUEDEN CONTENER 2 DATOS
        Map<Cliente, Long> numPedidosPorCliente
                = pedidos.stream()
                        .collect(Collectors.groupingBy(Pedido::getClientePedido, Collectors.counting()));

        for (Cliente c : numPedidosPorCliente.keySet()) {
            System.out.println(c + " - " + numPedidosPorCliente.get(c));
        }

        // TOTAL DE UNIDADES VENDIDAS DE UN ARTICULO EN TODOS LOS PEDIDOS. PODEMOS APLICARLO AL 
        // MÉTODO UNIDADES VENDIDAS QUE HABÍA QUE HACER EN EL EJERCICIO 4 DE LA ÚLTIMA PRUEBA
        System.out.println("\n");
        for (Articulo a : articulos.values()) {
            int total = 0;
            for (Pedido p : pedidos) {
                total += p.getCestaCompra().stream().filter(l -> l.getArticulo().equals(a))
                        .mapToInt(LineaPedido::getUnidades).sum();
            }
            System.out.println(a + " - " + total);
        }

        /**
         * **************************************************************************************
         * EJERCICIOS CON flatMap() para colecciones anidadas, nuestro caso pues
         * pedidos es un ArrayList de <Pedido> y dentro de cada Pedido hay una
         * cestaCompra, que es un ArrayList de <Lineapedido>
         *
         ****************************************************************************************
         */
        //USUARIOS QUE HAN COMPRADO UN ARTÍCULO DETERMINADO incluyendo CUANTAS UNIDADES HAN COMPRADO 
        //probamos con el artículo articulos.get("4-22") 
        System.out.println("\n");
        for (Cliente c : clientes.values()) {
            int unidades = pedidos.stream().filter(p -> p.getClientePedido().equals(c))
                    .flatMap(p -> p.getCestaCompra().stream()).filter(l -> l.getArticulo().equals(articulos.get("4-22")))
                    .mapToInt(LineaPedido::getUnidades).sum();

            System.out.println(c.getNombre() + ": " + unidades + " de " + articulos.get("4-22").getDescripcion());
        }

        //TODOS LOS ARTICULOS VENDIDOS, LOS ALMACENAMOS EN UN SET PARA EVITAR REPETICIONES
        System.out.println("\n");

        Set<Articulo> articulosVendidos
                = pedidos.stream()
                        .flatMap(p -> p.getCestaCompra().stream())
                        .map(LineaPedido::getArticulo)
                        .collect(Collectors.toSet());

        articulosVendidos.stream().forEach(a -> System.out.println(a));

        //TOTAL DE UNIDADES VENDIDAS DE TODOS LOS ARTÍCULOS usando flatMap()
        System.out.println("\n");
        Map<Articulo, Integer> unidadesPorArticulo
                = pedidos.stream()
                        .flatMap(p -> p.getCestaCompra().stream())
                        .collect(Collectors.groupingBy(LineaPedido::getArticulo, Collectors.summingInt(LineaPedido::getUnidades)
                        ));
        for (Articulo a : unidadesPorArticulo.keySet()) {
            System.out.println(a.getDescripcion() + " - " + unidadesPorArticulo.get(a));

            //
        }
        System.out.println("INTRODUCE UN DNI DE CLIENTE");
        String dni = sc.nextLine();
        double total = pedidos.stream()
                .filter(p -> p.getClientePedido().getIdCliente().equalsIgnoreCase(dni))
                .mapToDouble(p -> totalPedido(p))
                .sum();
        System.out.println(total);
        /*     //ejercicio 1
        pedidos.stream().forEach(p -> System.out.println(p));
        //ejercicio 2 Mostrar todos los pedidos de un cliente cuyo DNI se pide por teclado.
        System.out.println("DNI PARA VER LOS PEDIDOS DE ESE CLIENTE");
        String dni = sc.nextLine();
        pedidos.stream().filter(p -> p.getClientePedido().getIdCliente().equals(dni))
                .forEach(p -> System.out.println(p));
        //ejercio 3 Mostrar el total gastado por un cliente (DNI por teclado).
        double total = pedidos.stream().filter(p -> p.getClientePedido().getIdCliente().equals(dni))
                .mapToDouble(p -> totalPedido(p)).sum();
        System.out.println("\nEL CLIENTE " + clientes.get(dni).getNombre()
                + "TIENE UN TOTAL GASTADO :" + total);
        //ejercico 4 Mostrar el número de pedidos de cada cliente usando groupingBy.
        Map<Cliente, Long> numPedidosPorClientes
                = pedidos.stream()
                        .collect(Collectors.groupingBy(Pedido::getClientePedido, Collectors.counting()));
        Cliente cliente= clientes.get(dni);
        System.out.println(cliente.getNombre() + " - " + numPedidosPorClientes.get(cliente));
        //EJERCIOCIO 6 Mostrar el total de unidades vendidas en la tienda.
            System.out.println("\n");
        Map<Articulo, Integer> unidadesPorArticulo =
        pedidos.stream()
                .flatMap(p -> p.getCestaCompra().stream())
                .collect(Collectors.groupingBy(LineaPedido::getArticulo, Collectors.summingInt(LineaPedido::getUnidades)
                ));
        for (Articulo a:unidadesPorArticulo.keySet()){
            System.out.println(a.getDescripcion() + " - " + unidadesPorArticulo.get(a));
        }                                              */
    }

    /*
    METODOS PARA CALCULAR LAS UNIDADES VENDIDAS DE UN ARTICULO 
    EN TODOS LOS PEDIDOS DE LA TIENDDA
     */
    private int uniVendArticulos(Articulo a) {
        int total = 0;
        for (Pedido p : pedidos) {
            total += p.getCestaCompra().stream()
                    .filter(l -> l.getArticulo().equals(a))
                    .mapToInt(LineaPedido::getUnidades).sum();

        }
        return total;
    }

    /*
    VERSION SEMI-CLASICA
     */
    private int uniVendArticulos2(Articulo a) {
        int c = 0;
        for (Pedido p : pedidos) {
            for (LineaPedido l : p.getCestaCompra()) {
                if (l.getArticulo().equals(a)) {
                    c += l.getUnidades();
                }
            }
        }
        return c;
    }

    /*
   VERSION PROGRAMCION FUNCIONAL  COM FLATMAP
     */
    private int uniVendArticulos3(Articulo a) {
        return pedidos.stream().flatMap(p -> p.getCestaCompra().stream()
                .filter(l -> l.getArticulo().equals(a)))
                .mapToInt(LineaPedido::getUnidades).sum();
    }

    public void stream() {
        System.out.println("Unidades vendidas");
        for (Articulo a : articulos.values()) {
            int total = 0;
            for (Pedido p : pedidos) {
                total += p.getCestaCompra().stream()
                        .filter(l -> l.getArticulo().equals(a))
                        .mapToInt(LineaPedido::getUnidades).sum();
            }
            System.out.println(a + " - " + total);
        }
    }

    public void contarPedidos() {
        long numPedios = pedidos.stream().filter(p -> p.getClientePedido().getIdCliente().equalsIgnoreCase("80580845T"))
                .count();
        Map<Cliente, Long> numPedidosPorcliente
                = pedidos.stream()
                        .collect(Collectors.groupingBy(Pedido::getClientePedido, Collectors.counting()));
        for (Cliente c : numPedidosPorcliente.keySet()) {
            System.out.println(c + " - " + numPedidosPorcliente);
        }
    }

    //muestra los pedidos de una seccion de los cliente que lo han comprado
    public void stream1() {
        for (Cliente c : clientes.values()) {
            int unidades = pedidos.stream().filter(p -> p.getClientePedido().equals(c))
                    .flatMap(l -> l.getCestaCompra().stream())
                    .filter(l -> l.getArticulo().equals(articulos.get("4-22")))
                    .mapToInt(LineaPedido::getUnidades).sum();
            System.out.println(c.getNombre() + " : " + unidades);
        }
    }

    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
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

        /*
        values: Muestra los articulos y keyset muestra la clave pricipal 
         */
        System.out.println("\n");
        articulos.values().stream().forEach(a -> System.out.println(a));
        System.out.println("");
        ArrayList<Articulo> articulosAux = new ArrayList(articulos.values());
        articulosAux.stream()
                //Predicado se usa para un metodo
                .filter(a -> a.getPvp() < 100)
                // se usa para los atributos
                .sorted(Comparator.comparing(Articulo::getPvp))
                //bucle que recorre 
                .forEach(a -> System.out.println(a));
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Gestión de Clientes">

    public double totalCliente(Cliente c) {
        return pedidos.stream().filter(p -> p.getClientePedido().equals(c))
                .mapToDouble(p -> totalPedido(p)).sum();
    }

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
        String idArticulo;
        int unidades = 0;
        System.out.print("\nTeclee el ID del articulo deseado (FIN para terminar la compra)");
        idArticulo = sc.next();
        while (idArticulo.equalsIgnoreCase("FIN")) {
            { //Es mejor utilizar un while en lugar de un do while como en el commit anterior, con el do while estamos obligando a la persona a seguir con los atributos aunque no quiera comprar
                System.out.print("\nTeclee las unidades deseadas: ");
                unidades = sc.nextInt();

                try {
                    stock(articulos.get(idArticulo), unidades);
                    //cestaCompra.add(new LineaPedido(idArticulo, unidades));

                } catch (StockCero ex) {
                    System.out.println(ex.getMessage());
                } catch (StockInsuficiente ex) {
                    System.out.println(ex.getMessage());

                    System.out.println("Quieres las unidades disponibles (SI / NO)");
                    String respuesta = sc.next();
                    if (respuesta.equalsIgnoreCase("SI")) {
                        //   cestaCompra.add(new LineaPedido(idArticulo, articulos.get(idArticulo).getExistencias()));// le damos los articulos que hay
                        articulos.get(idArticulo).setExistencias(0);
                    }
                    System.out.println("\nTeclea el ID del articulo que deseas (FIN para terminar la compra)");
                }

            }
            /*Pedimos uno a uno los atributos del pedido, son las cosas que necesitamos para realizar un pedido
        -Generamos el id de ese pedido mediante la llamada al método generaIdPedido anterior
        -Lo asociamos a un cliente
        -Obtenemos la fecha del pedido
        -Hace falta rellenar el ArrayList del pedido*/
        }
        if (cestaCompra.size() > 0) {//tambien valdria (!cestaCompra.isEmpty())
            System.out.println("Este es tu pedido");
            double totalPedido = 0;
            for (LineaPedido l : cestaCompra) {

                System.out.println("");

                /*  totalLinea = l.getUnidades() * articulos.get(l.getIdArticulo()).getPvp();
                totalPedido += totalLinea;
                System.out.println(l.getIdArticulo() + "-"
                        + articulos.get(l.getIdArticulo()).getDescripcion() + "- " + l.getUnidades()
                        + " - " + articulos.get(l.getIdArticulo()).getPvp());
                System.out.println("El total del precio es " + totalPedido);*/
            }

            System.out.println("Procedemos con la compra (SI/NO) ");
            String respuesta = sc.next();
            if (respuesta.equalsIgnoreCase("SI")) {
                Pedido p = new Pedido(generaIdPedido(idCliente), clientes.get(idCliente), LocalDate.now(), cestaCompra);
                pedidos.add(p);
                for (LineaPedido l : cestaCompra) {
                    // articulos.get(l.getIdArticulo()).setExistencias(articulos.get(l.getIdArticulo()).getExistencias() - l.getUnidades());
                }
            }
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
        System.out.println("");
        for (Pedido p : pedidos) {
            System.out.println(p + "- Total: " + totalPedido(p));
        }

        /* System.out.println("\n");
        pedidos.stream().sorted(Comparator.comparing(p->totalPedido(p)))
                .forEach(p->System.out.println(p + "- Total: " + totalPedido(p)));
    
        System.out.println("\n");
        pedidos.stream().sorted(Comparator.comparing(p->totalPedido((Pedido)p)).reversed())
                .forEach(p->System.out.println(p + "- Total: " + totalPedido(p)));
        
        
        System.out.println("\n");
        pedidos.stream().filter(p->totalPedido(p)>1000)
                .sorted(Comparator.comparing(p->totalPedido((Pedido)p)))
                .forEach(p->System.out.println(p + "- Total: " + totalPedido(p)));*/
    }

    private void totalP() {

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

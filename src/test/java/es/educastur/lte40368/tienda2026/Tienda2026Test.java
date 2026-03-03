/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package es.educastur.lte40368.tienda2026;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 1dawd18
 */
public class Tienda2026Test {

    public Tienda2026Test() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        t.cargaDatos();
    }

    @AfterEach
    public void tearDown() {
    }

    Tienda2026 t = new Tienda2026();

    /**
     * Test of main method, of class Tienda2026.
     */
    @Test
    // TE PARA EL CARGA DATOS 
    public void testCargaDatos() {
        assertAll(
                () -> assertEquals(10, t.getArticulos().size()),
                () -> assertEquals(4, t.getClientes().size()),
                () -> assertEquals(5, t.getPedidos().size())
        );
    }

    @Test
    //TEST PARA GENERA ID PEDIDO
    public void testGeneraIdPedido() {
        assertAll(
                () -> assertEquals("80580845T-003/2026", t.generaIdPedido("80580845T")),
                () -> assertEquals("36347775R-003/2026", t.generaIdPedido("36347775R")),
                () -> assertEquals("63921307Y-002/2026", t.generaIdPedido("63921307Y")),
                () -> assertEquals("02337565Y-001/2026", t.generaIdPedido("02337565Y"))
        );
    }

    @Test
    //TEST PARA PROBAR METODO TOTALPEDIDOS 
    public void testTotalPedido() {
        assertAll(
                () -> assertEquals(585, t.totalPedido(t.getPedidos().get(0))),
                () -> assertEquals(2980, t.totalPedido(t.getPedidos().get(1))),
                () -> assertEquals(390, t.totalPedido(t.getPedidos().get(2))),
                () -> assertEquals(1980, t.totalPedido(t.getPedidos().get(3))),
                () -> assertEquals(2160, t.totalPedido(t.getPedidos().get(4)))
        );
    }
  //TEST PARA PROBAR METODO TOTALCLIENTES
    @Test
    public void testTotalCliente() {
        assertAll(
                () -> assertEquals(3565, t.totalCliente(t.getClientes().get("80580845T"))),
                () -> assertEquals(2370, t.totalCliente(t.getClientes().get("36347775R"))),
                () -> assertEquals(2160, t.totalCliente(t.getClientes().get("63921307Y"))),
                () -> assertEquals(0, t.totalCliente(t.getClientes().get("02337565Y")))
        );
    }

    @Test
    //TEST PARA PROBAR METODO STOCK
    public void testStock() {
        assertAll(
               ()-> assertThrows(StockCero.class, () -> {t.stock(t.getArticulos().get("1-11"), 5);}),
               ()->   assertThrows(StockCero.class, () -> {t.stock(t.getArticulos().get("2-33"), 5);}),
               ()->   assertThrows(StockInsuficiente.class, () -> {t.stock(t.getArticulos().get("3-11"), 5);}),
               ()->   assertThrows(StockInsuficiente.class, () -> {t.stock(t.getArticulos().get("3-22"), 10);})
        );
    }

}

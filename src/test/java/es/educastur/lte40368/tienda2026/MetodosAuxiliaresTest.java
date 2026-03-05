/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package es.educastur.lte40368.tienda2026;

import java.io.Serializable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MetodosAuxiliaresTest implements Serializable{

    public MetodosAuxiliaresTest() {
    }
    @BeforeAll
    public static void setUpClass() {
    }
    @AfterAll
    public static void tearDownClass() {
    }
    @BeforeEach
    public void setUp() {
    }
    @AfterEach
    public void tearDown() {
    }
    /**
     * Test para esInt
     */
    @Test
    public void testEsInt() {
        assertTrue(MetodosAuxiliares.esInt("5"));
        assertFalse(MetodosAuxiliares.esInt("55555555555555555555555555"));
        assertTrue(MetodosAuxiliares.esInt("-5"));
        assertFalse(MetodosAuxiliares.esInt("5.5"));
        assertFalse(MetodosAuxiliares.esInt("sadasda"));
    }
    /**
     * Test para esDouble
     */
    @Test
    public void testEsDouble() {
        assertTrue(MetodosAuxiliares.esDouble("5"));
        assertTrue(MetodosAuxiliares.esDouble("-5"));
        assertTrue(MetodosAuxiliares.esDouble("5.5"));
        assertFalse(MetodosAuxiliares.esDouble("sadasda"));
    }
    /**
     * Test para validarDni
     */
    @Test
    public void testValidarDni() {
        assertTrue(MetodosAuxiliares.validarDni("80580845T"));
        assertTrue(MetodosAuxiliares.validarDni("36347775R"));
        assertFalse(MetodosAuxiliares.validarDni("36347375B"));
        assertFalse(MetodosAuxiliares.validarDni("36349975Y"));
    }
    /**
     * Test of calcularLetraDni method, of class MetodosAuxiliares.
     */
    @Test
    public void testCalcularLetraDni() {
    }
}

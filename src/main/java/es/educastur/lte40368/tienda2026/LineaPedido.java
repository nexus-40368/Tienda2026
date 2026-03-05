/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.educastur.lte40368.tienda2026;

import java.io.Serializable;

/**
 *
 * @author 1dawd18
 */
class LineaPedido implements Serializable {
   private Articulo articulo;
   private int unidades;

    public LineaPedido(Articulo articulo, int unidades) {
        this.articulo = articulo;
        this.unidades = unidades;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    @Override
    public String toString() {
        return articulo.getDescripcion() + " = " + unidades;
    }


   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.educastur.lte40368.tienda2026;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author 1dawd18
 */
public class Pedido {
    private String idPedido;
    private Cliente clientePedido;
    private final LocalDate fachaPedido;
    private ArrayList <LineaPedido> cestaCompra;

    public Pedido(String idPedido, String clientePedido, LocalDate fachaPedido, ArrayList<LineaPedido> cestaCompra) {
        this.idPedido = idPedido;
        this.fachaPedido = fachaPedido;
        this.cestaCompra = cestaCompra;
    }

    public Pedido(String idPedido, Cliente clientePedido, LocalDate fachaPedido, ArrayList<LineaPedido> cestaCompra) {
        this.idPedido = idPedido;
        this.clientePedido = clientePedido;
        this.fachaPedido = fachaPedido;
        this.cestaCompra = cestaCompra;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }


    public ArrayList<LineaPedido> getCestaCompra() {
        return cestaCompra;
    }

    public void setCestaCompra(ArrayList<LineaPedido> cestaCompra) {
        this.cestaCompra = cestaCompra;
    }

    public Cliente getClientePedido() {
        return clientePedido;
    }

    public void setClientePedido(Cliente clientePedido) {
        this.clientePedido = clientePedido;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido{");
        sb.append("idPedido=").append(idPedido);
        sb.append(", clientePedido=").append(clientePedido);
        sb.append(", fachaPedido=").append(fachaPedido);
        sb.append(", cestaCompra=").append(cestaCompra);
        sb.append('}');
        return sb.toString();
    }
    
}

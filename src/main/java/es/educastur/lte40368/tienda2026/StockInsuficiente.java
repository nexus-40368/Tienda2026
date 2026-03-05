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
public class StockInsuficiente extends Exception implements Serializable{
    public  StockInsuficiente (String mensaje){
        super(mensaje);
    }
}

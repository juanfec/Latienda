package com.amovil.latienda;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Juan Rueda on 5/04/2018.
 * juan.felipe.rueda@gmail.com
 */
public class Historico {
    private String fecha;
    private String total;
    private ArrayList<HashMap> productos;

    public Historico() {
    }

    public Historico(String fecha, String total, ArrayList<HashMap> productos) {
        this.fecha = fecha;
        this.total = total;
        this.productos = productos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<HashMap> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<HashMap> productos) {
        this.productos = productos;
    }
}

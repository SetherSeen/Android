package com.example.empleo0001;

public class ItemContenedor {
   private int foto;
    private String puesto;
    private String descripccion;
    private String empresa;
    private String fecha;

    public ItemContenedor(int foto, String puesto, String descripccion, String fecha) {
        this.foto = foto;
        this.puesto = puesto;
        this.descripccion = descripccion;
        this.fecha = fecha;
    }

    public int getFoto() {
        return foto;
    }

    public String getPuesto() {
        return puesto;
    }

    public String getDescripccion() {
        return descripccion;
    }

    public String getFecha() {
        return fecha;
    }
}

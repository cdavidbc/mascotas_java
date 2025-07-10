package model;

public class Mascota {
    private String codigo;
    private String nombre;
    private String tipo;
    private int edad;
    private String documentoDuenio;

    public Mascota(String codigo, String nombre, String tipo, int edad, String documentoDuenio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.edad = edad;
        this.documentoDuenio = documentoDuenio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDocumentoDuenio() {
        return documentoDuenio;
    }

    public void setDocumentoDuenio(String documentoDuenio) {
        this.documentoDuenio = documentoDuenio;
    }
}

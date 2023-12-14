package org.example;

class Participante {
    public   int id;

    public  String dni;
    public   String nombre;
    public  String apellido;

    public  String celular;
    public  String numeroEmergencia;
    public  String grupoSanguineo;
    public   String categoria;
    public   double costoInscripcion;
    public    int edad;

    private static int proximoId = 1;

    public Participante(String dni, String nombre, String apellido, String celular, String numeroEmergencia, String grupoSanguineo, String categoria, double costoInscripcion, int edad) {
        this.id = proximoId++;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.numeroEmergencia = numeroEmergencia;
        this.grupoSanguineo = grupoSanguineo;
        this.categoria = categoria;
        this.costoInscripcion = costoInscripcion;
        this.edad = edad;
    }



    @Override
    public String toString() {
        return "ID: " + id +
                ", DNI: " + dni +
                ", Nombre: " + nombre +
                ", Apellido: " + apellido +
                ", Celular: " + celular +
                ", NumEmergencia: " + numeroEmergencia +
                ", GrupoSanguineo: " + grupoSanguineo +
                ", Edad: " + edad +
                ", Categoría: " + categoria +
                ", Costo de inscripción: " + costoInscripcion;
    }


}
package org.example;

class MontoRecaudado {
    public double monto;

    public MontoRecaudado(double monto) {
        this.monto = monto;
    }

    public double getMonto() {
        return monto;
    }

    public void sumarMonto(double cantidad) {
        monto += cantidad;
    }

    public void restarMonto(double cantidad) {
        monto -= cantidad;
    }
}

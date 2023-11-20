package com.stocksafe.objeto;

public enum Categoria {

    PACOTES(1),
    CPU(2),
    RAM(3),
    TAXA_TRANSFERENCIA(4);

    private final int id;

    Categoria(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

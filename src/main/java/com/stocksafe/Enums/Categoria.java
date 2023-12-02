package com.stocksafe.Enums;

public enum Categoria {

    PACOTES(1, null, null, null, null),
    CPU(2, 30, 80, 20, 90),
    RAM(3, 30, 80, 20, 90),
    TAXA_TRANSFERENCIA(4, 90, null, 80, null);

    private final int ID;
    private final Integer LIMITE_BAIXO;
    private final Integer LIMITE_ALTO;
    private final Integer LIMITE_INFERIOR;
    private final Integer LIMITE_SUPERIOR;

    Categoria(int ID, Integer LIMITE_BAIXO, Integer LIMITE_ALTO, Integer LIMITE_INFERIOR, Integer LIMITE_SUPERIOR) {
        this.ID = ID;
        this.LIMITE_BAIXO = LIMITE_BAIXO;
        this.LIMITE_ALTO = LIMITE_ALTO;
        this.LIMITE_INFERIOR = LIMITE_INFERIOR;
        this.LIMITE_SUPERIOR = LIMITE_SUPERIOR;
    }

    public int getID() {
        return ID;
    }

    public Integer getLIMITE_BAIXO() {
        return LIMITE_BAIXO;
    }

    public Integer getLIMITE_ALTO() {
        return LIMITE_ALTO;
    }

    public Integer getLIMITE_INFERIOR() {
        return LIMITE_INFERIOR;
    }

    public Integer getLIMITE_SUPERIOR() {
        return LIMITE_SUPERIOR;
    }
}

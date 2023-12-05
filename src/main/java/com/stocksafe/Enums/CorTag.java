package com.stocksafe.Enums;

public enum CorTag {
    VERDE("rgb(75, 181, 51)","\uD83D\uDFE2"),
    AMARELO("rgb(255, 225, 31)","\uD83D\uDFE1"),
    VERMELHO("rgb(255, 46, 46)","\uD83D\uDD34"),
    CINZA_CLARO("rgb(196, 196, 196)","⚪\uFE0F"),
    ROXO("rgb(107, 107, 199)","\uD83D\uDFE3"),
    AZUL_PRUSSIA("rgb(7, 77, 85)","\uD83D\uDD35"),
    MARROM("rgb(92, 90, 66)","\uD83D\uDFE4"),
    PRETO("rgb(51, 51, 51);","⚫\uFE0F");

    private final String COR_RGB;
    private final String EMOJI_CORRESPONDENTE;

    CorTag(String COR_RGB, String EMOJI_CORRESPONDENTE){
        this.COR_RGB = COR_RGB;
        this.EMOJI_CORRESPONDENTE = EMOJI_CORRESPONDENTE;
    }

    public String getCOR_RGB() {
        return COR_RGB;
    }

    public String getEMOJI_CORRESPONDENTE() {
        return EMOJI_CORRESPONDENTE;
    }

    @Override
    public String toString() {
        return ("%s [%s]").formatted(
                this.name().replace("_"," ").toLowerCase(),
                this.EMOJI_CORRESPONDENTE
        );
    }
}

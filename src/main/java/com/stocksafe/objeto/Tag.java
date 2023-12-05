package com.stocksafe.objeto;

import com.stocksafe.Enums.CorTag;

public class Tag {
    private Integer idTag;
    private String nomeTag;
    private String corTag;

    public Tag(){}

    public Tag(String nomeTag, String corTag) {
        this.nomeTag = nomeTag;
        this.corTag = corTag;
    }

    public String getEmojiCorrespondente(){
        for (CorTag cor : CorTag.values()){
            if (this.corTag == cor.getCOR_RGB()){
                return cor.getEMOJI_CORRESPONDENTE();
            }
        }
        return null;
    }

    public String getCorRGB(){
        for (CorTag cor : CorTag.values()){
            if (this.corTag == cor.getEMOJI_CORRESPONDENTE()){
                return cor.getCOR_RGB();
            }
        }
        return null;
    }

    public Integer getIdTag() {
        return idTag;
    }

    public String getNomeTag() {
        return nomeTag;
    }

    public String getCorTag() {
        return corTag;
    }

    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
    }

    public void setNomeTag(String nomeTag) {
        this.nomeTag = nomeTag;
    }

    public void setCorTag(String corTag) {
        this.corTag = corTag;
    }
}

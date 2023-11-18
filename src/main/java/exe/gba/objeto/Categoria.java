package exe.gba.objeto;

public class Categoria {

    private Integer idCat;
    private String tipoCat;
    private String unidadeCat;

    public Integer getIdCat() {
        return idCat;
    }

    public void setIdCat(Integer idCat) {
        this.idCat = idCat;
    }

    public String getTipoCat() {
        return tipoCat;
    }

    public void setTipoCat(String tipoCat) {
        this.tipoCat = tipoCat;
    }

    public String getUnidadeCat() {
        return unidadeCat;
    }

    public void setUnidadeCat(String unidadeCat) {
        this.unidadeCat = unidadeCat;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCat=" + idCat +
                ", tipoCat='" + tipoCat + '\'' +
                ", unidadeCat='" + unidadeCat + '\'' +
                '}';
    }
}

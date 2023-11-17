package exe.gba.objeto;

public class Categoria {
    private  int id_cat;
    private String tipo_cat;
    private  String unidade_cat;

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getTipo_cat() {
        return tipo_cat;
    }

    public void setTipo_cat(String tipo_cat) {
        this.tipo_cat = tipo_cat;
    }

    public String getUnidade_cat() {
        return unidade_cat;
    }

    public void setUnidade_cat(String unidade_cat) {
        this.unidade_cat = unidade_cat;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id_cat=" + id_cat +
                ", tipo_cat='" + tipo_cat + '\'' +
                ", unidade_cat='" + unidade_cat + '\'' +
                '}';
    }
}

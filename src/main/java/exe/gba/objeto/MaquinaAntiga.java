package exe.gba.objeto;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.RedeInterface;

import java.util.List;

public class MaquinaAntiga {
    private Processador cpu;
    private Memoria ram;
    private List<RedeInterface> interfaces;

    public MaquinaAntiga() {
        Looca looca = new Looca();

        this.cpu = looca.getProcessador();
        this.ram = looca.getMemoria();
        this.interfaces = new Looca().getRede().getGrupoDeInterfaces().getInterfaces();
    }

    public MaquinaAntiga(Processador cpu, Memoria ram) {
        this.cpu = cpu;
        this.ram = ram;
    }

    public Processador getCpu() {
        return cpu;
    }

    public void setCpu(Processador cpu) {
        this.cpu = cpu;
    }

    public Memoria getRam() {
        return ram;
    }

    public void setRam(Memoria ram) {
        this.ram = ram;
    }

    public Double getMediaBytesEnviados(){
        Double media = 0.0;

        for (RedeInterface rede : interfaces ){
            media += rede.getBytesEnviados();
        }

        return (media / interfaces.size()) / Math.pow(1000, 2);
    }
}

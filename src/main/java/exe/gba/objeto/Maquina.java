package exe.gba.objeto;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.RedeInterface;

import java.util.List;

public class Maquina {

    private Looca looca;
    private Processador cpu;
    private Memoria ram;
    private List<RedeInterface> interfaces;

    public Maquina () {}

    public Maquina(Looca looca) {
        this.looca = looca;
        this.cpu = looca.getProcessador();
        this.ram = looca.getMemoria();
        this.interfaces = looca.getRede().getGrupoDeInterfaces().getInterfaces();
    }

    public Processador getCpu() {
        return cpu;
    }

    public Memoria getRam() {
        return ram;
    }

    public List<RedeInterface> getInterfaces() {
        return interfaces;
    }

    public Double getPorcentagemUsoRam () {
        Double porcentagemDeUso = (ram.getTotal() * (ram.getEmUso() / 100)) / Math.pow(1000, 2);

        return porcentagemDeUso;
    }
}

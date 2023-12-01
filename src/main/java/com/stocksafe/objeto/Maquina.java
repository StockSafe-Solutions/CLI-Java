package com.stocksafe.objeto;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.stocksafe.utils.Conversor;

import java.util.ArrayList;
import java.util.List;

public class Maquina {

    private Looca looca;
    private Processador cpu;
    private Memoria ram;
    private List<Volume> volumes;
    private List<RedeInterface> interfaces;
    private List<Processo> processos;

    public Maquina() {
        this.looca = new Looca();
        this.cpu = looca.getProcessador();
        this.ram = looca.getMemoria();
        this.interfaces = looca.getRede().getGrupoDeInterfaces().getInterfaces();
        this.volumes = looca.getGrupoDeDiscos().getVolumes();
        this.processos = looca.getGrupoDeProcessos().getProcessos();
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

    public Double getPorcentagemUsoCpu () {
        return cpu.getUso();
    }

    public Double getPorcentagemUsoRam () {
        Double ramTotal = Conversor.converteGb(ram.getTotal());
        Double ramEmUso = Conversor.converteGb(ram.getEmUso());

        return (ramEmUso * 100) / ramTotal;
    }

    public Double getTaxaDeTransferencia () {
        double bytesEnviados = 0.0;
        double bytesRecebidos = 0.0;

        for (RedeInterface interfaceAtual : interfaces) {
            bytesEnviados += interfaceAtual.getBytesEnviados();
            bytesRecebidos += interfaceAtual.getBytesRecebidos();
        }

        return Conversor.converteMb((bytesEnviados + bytesRecebidos) / 2);
    }

    public Double getPacotesEnviados () {
        double pacotesEnviados = 0.0;

        for (RedeInterface interfaceAtual :
                interfaces) {
            pacotesEnviados += interfaceAtual.getPacotesEnviados();
        }

        return pacotesEnviados / interfaces.size();
    }

    public Double getArmazenamentoTotal () {
        return Conversor.converteGb(looca.getGrupoDeDiscos().getTamanhoTotal());
    }

    public Double getArmazenamentoUsado () {
        Double armazenamentoUsado = 0.0;

        for (Volume volumeAtual : volumes) {
            armazenamentoUsado += volumeAtual.getTotal() - volumeAtual.getDisponivel();
        }

        return Conversor.converteGb(armazenamentoUsado);
    }

    public List<String> getStringProcessos () {
        List<String> listaProcessos = new ArrayList<>();
        String processoString;

        for (Processo processo: processos) {
            processoString = """
                    PID: %d
                    Nome: %s
                    Uso CPU: %f
                    Uso Memória: %f
                    Bytes utilizados: %d
                    Memória virtual utilizada: %d
                    """
                    .formatted(
                            processo.getPid(),
                            processo.getNome(),
                            processo.getUsoCpu(),
                            processo.getUsoMemoria(),
                            processo.getBytesUtilizados(),
                            processo.getMemoriaVirtualUtilizada());

            listaProcessos.add(processoString);
        }
        return listaProcessos;
    }

    public List<Processo> getProcessos() {
        return processos;
    }
}

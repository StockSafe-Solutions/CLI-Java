package stocksafe.solutions.comBanco;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.servicos.ServicoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;

import java.util.List;

public class Maquina {

    Looca looca = new Looca();

    private Sistema sistema = looca.getSistema();
    private Memoria memoria = looca.getMemoria();
    private Processador processador = looca.getProcessador();
    private Temperatura temperatura = looca.getTemperatura();

    //Criação do gerenciador
    DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
    //Obtendo lista de discos a partir do getter
    List<Disco> discos = grupoDeDiscos.getDiscos();
    private String mensagem;

    public Maquina() {

    }

    public void analiseDoSistema (){



        mensagem = String.format("Sistema:\n%s\nMemoria:\n%s\nProcessador:\nFrequência: %d\nNúmero de CPUs Fisícas: %d\nNúmero de CPUs Lógicas: %d\nEm uso: %.1f\n\nTemperatura:\n%s "
                ,sistema,memoria,processador.getFrequencia(),processador.getNumeroCpusFisicas(), processador.getNumeroCpusLogicas(),processador.getUso() ,temperatura);
         System.out.println(mensagem);


        for (Disco disco : discos) {
            Double discoTrata = disco.getTamanho() *  Math.pow(10,-9);

            System.out.println("\nDisco \nTamanho:%.2fGB\nTranferência: %d".formatted(discoTrata, disco.getTempoDeTransferencia()));
        }


    }

    public void discosGrupo (){
        //Exibindo as informações de cada disco:


    }

}

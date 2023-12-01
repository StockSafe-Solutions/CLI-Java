package com.stocksafe.runnables;

import com.stocksafe.dao.ProcessoDao;
import com.stocksafe.objeto.Maquina;
import com.stocksafe.objeto.Servidor;

public class RegistroDeProcesso implements Runnable{

    private ProcessoDao processoDao;
    private Maquina maquina;
    private Servidor servidor;
    private boolean isInserindo;

    public RegistroDeProcesso(ProcessoDao processoDao, Maquina maquina, Servidor servidor) {
        this.processoDao = processoDao;
        this.maquina = maquina;
        this.servidor = servidor;
        this.isInserindo = true;
    }

    @Override
    public void run() {
        while (isInserindo){
            processoDao.inserirDadosProcessos(servidor, maquina);
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}

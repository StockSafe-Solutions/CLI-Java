package com.stocksafe;

import com.stocksafe.dao.MaquinaDao;
import com.stocksafe.objeto.Maquina;
import com.stocksafe.objeto.Servidor;

public class ColetaDados implements Runnable{

    private final Servidor servidor;
    private final Maquina maquina;
    private final MaquinaDao maquinaDao;
    private Boolean isInserindo;

    public ColetaDados(Servidor servidor, Maquina maquina, MaquinaDao maquinaDao) {
        this.servidor = servidor;
        this.maquina = maquina;
        this.maquinaDao = maquinaDao;
        this.isInserindo = true;
    }

    @Override
    public void run() {
        while (isInserindo) {
            maquinaDao.inserirDadosPacote(servidor, maquina);
            maquinaDao.inserirDadosCpu(servidor, maquina);
            maquinaDao.inserirDadosRam(servidor, maquina);
            maquinaDao.inserirDadosTransferencia(servidor, maquina);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public Boolean getInserindo() {
        return isInserindo;
    }

    public void setInserindo(Boolean inserindo) {
        isInserindo = inserindo;
    }
}

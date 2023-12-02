package com.stocksafe.dao;

import com.stocksafe.Enums.Categoria;
import org.springframework.jdbc.core.JdbcTemplate;

public class AlertaDao {

    public static void inserirAlerta(int idCategoria, Double valor, int idServidor, JdbcTemplate con) {

        switch (idCategoria){
            case 2:
                if (valor <= Categoria.CPU.getLIMITE_INFERIOR() || valor >= Categoria.CPU.getLIMITE_SUPERIOR()){
                    con.update("""
                            INSERT INTO tb_alerta VALUES (
                                null,
                                now(),
                                3,
                                0,
                                'CPU em %d%% do funcionamento normal',
                                %d
                            )
                            """.formatted((int) Math.round(valor), idServidor)
                    );

                } else if (valor <= Categoria.CPU.getLIMITE_BAIXO() || valor >= Categoria.CPU.getLIMITE_ALTO()) {
                    con.update("""
                            INSERT INTO tb_alerta VALUES (
                                null,
                                now(),
                                2,
                                0,
                                'CPU em %d%% do funcionamento normal',
                                %d
                            )
                            """.formatted((int) Math.round(valor), idServidor)
                    );

                }
                break;
            case 3:
                if (valor <= Categoria.RAM.getLIMITE_INFERIOR() || valor >= Categoria.RAM.getLIMITE_SUPERIOR()){
                    con.update("""
                            INSERT INTO tb_alerta VALUES (
                                null,
                                now(),
                                3,
                                0,
                                'RAM em %d%% do funcionamento normal',
                                %d
                            )
                            """.formatted((int) Math.round(valor), idServidor)
                    );

                } else if (valor <= Categoria.RAM.getLIMITE_BAIXO() || valor >= Categoria.RAM.getLIMITE_ALTO()) {
                    con.update("""
                            INSERT INTO tb_alerta VALUES (
                                null,
                                now(),
                                2,
                                0,
                                'RAM em %d%% do funcionamento normal',
                                %d
                            )
                            """.formatted((int) Math.round(valor), idServidor)
                    );

                }
                break;
            case 4:
                if (valor <= Categoria.TAXA_TRANSFERENCIA.getLIMITE_BAIXO()){
                    con.update("""
                            INSERT INTO tb_alerta VALUES (
                                null,
                                now(),
                                3,
                                0,
                                'Taxa de transferência do servidor em %d%%',
                                %d
                            )
                            """.formatted((int) Math.round(valor), idServidor)
                    );

                } else if (valor <= Categoria.CPU.getLIMITE_INFERIOR()) {
                    con.update("""
                            INSERT INTO tb_alerta VALUES (
                                null,
                                now(),
                                2,
                                0,
                                'Taxa de transferência do servidor em %d%%',
                                %d
                            )
                            """.formatted((int) Math.round(valor), idServidor)
                    );

                }
                break;
        }
    }
}

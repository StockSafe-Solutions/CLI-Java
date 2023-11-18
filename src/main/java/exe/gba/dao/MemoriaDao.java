package exe.gba.dao;

import exe.gba.objeto.Maquina;
import exe.gba.objeto.Servidor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MemoriaDao {
    private JdbcTemplate con;
    LocalDateTime   data =  LocalDateTime.now();

    public MemoriaDao(JdbcTemplate con) {
        this.con = con;
    }


    DateTimeFormatter formatador = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );
    String dataHoraFormatada = formatador.format(data);
    public  void  data(){
        System.out.println(dataHoraFormatada);
    }
        public void inserirDadosRamDisponivel(Servidor servidor, Maquina maquina, int cat){
            con.update( """
                INSERT INTO tb_registro (
                    id_registro,
                    fk_servidor,
                    fk_cat,
                    data_hora,
                    valor
                )
                VALUES (
                    null,
                    %d,
                    %d,
                    "%s",
                    %.2f
                );
                """.formatted(
                            servidor.getIdServidor(),
                            cat,
                            dataHoraFormatada,
                            maquina.getPercentagemDisponivelRam()
                    )
            );
            System.out.println("Memória  disponivel \n");
    }

    public void inserirDadosRamTotal(Servidor servidor, Maquina maquina, int cat){
        con.update( """
                INSERT INTO tb_registro (
                    id_registro,
                    fk_servidor,
                    fk_cat,
                    data_hora,
                    valor
                )
                VALUES (
                    null,
                    %d,
                    %d,
                    "%s",
                    %.2f
                );
                """.formatted(
                        servidor.getIdServidor(),
                        cat,
                        dataHoraFormatada,
                        maquina.getTotalRam()
                )
        );
        System.out.println("Memória  total cadastrada\n");
    }

    public void inserirDadosRamEmUso(Servidor servidor, Maquina maquina, int cat){
        con.update( """
                INSERT INTO tb_registro (
                    id_registro,
                    fk_servidor,
                    fk_cat,
                    data_hora,
                    valor
                )
                VALUES (
                    null,
                    %d,
                    %d,
                    "%s",
                    %.2f
                );
                """.formatted(
                    servidor.getIdServidor(),
                    cat,
                    dataHoraFormatada,
                   maquina.getPorcentagemUsoRam()
                )
                );
        System.out.println("Memória  em uso cadastrada\n");
    }

}

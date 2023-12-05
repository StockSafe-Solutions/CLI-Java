package com.stocksafe.dao;

import com.stocksafe.objeto.Servidor;
import com.stocksafe.objeto.Tag;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TagDao {
    private final JdbcTemplate con;
    private final JdbcTemplate conLocal;

    public TagDao(JdbcTemplate con, JdbcTemplate conLocal) {
        this.con = con;
        this.conLocal = conLocal;
    }
    public List<Tag> listarTags(){
        try{
            return con.query("SELECT * FROM tb_tag", new BeanPropertyRowMapper<>(Tag.class));
        } catch (Exception e){
            return conLocal.query("SELECT * FROM tb_tag", new BeanPropertyRowMapper<>(Tag.class));
        }
    }
    public void adicionarTag(Tag tag){
        con.update("""
        INSERT INTO tb_tag
        (nome_tag, cor_tag)
        VALUES ('%s', '%s');   
        """.formatted(tag.getNomeTag(), tag.getCorTag()));

        conLocal.update("""
        INSERT INTO tb_tag
        (nome_tag, cor_tag)
        VALUES (?, ?)        
        """, tag.getNomeTag(), tag.getCorTag());
    }
    public void colocarTagEmServidor(Tag tag, Servidor servidor){
        con.update("""
        INSERT INTO tb_tag_servidor
        VALUES (?, ?)        
        """, servidor.getIdServidor(), tag.getIdTag());

        conLocal.update("""
        INSERT INTO tb_tag_servidor
        VALUES (?, ?)        
        """, servidor.getIdServidor(), tag.getIdTag());
    }
}

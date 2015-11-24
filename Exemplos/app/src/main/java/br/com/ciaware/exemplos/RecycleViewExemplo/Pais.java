package br.com.ciaware.exemplos.RecycleViewExemplo;

/**
 * Created by cw001 on 10/11/15.
 */
public class Pais {

    private String nome;
    private int idBandeira;

    public Pais(String nome, int idBandeira) {
        this.nome = nome;
        this.idBandeira = idBandeira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdBandeira() {
        return idBandeira;
    }

    public void setIdBandeira(int idBandeira) {
        this.idBandeira = idBandeira;
    }
}

package br.com.flaviobarbosa.agendadealunosalura.modelo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by fbarbosa2020 on 30/10/15.
 */
public class Aluno implements Serializable {
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String site;
    private Double nota;
    private byte[] foto;

    public Aluno() {
        id = 0L;
        nome = "";
    }


    @Override
    public String toString() {
        return getNome()+" (" + id.toString() + ")";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void setFoto(Drawable drawable) {

        if (drawable==null) {
            foto = null;
        } else {
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            ByteArrayOutputStream byteImg = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteImg);

            System.out.println("\nTamanho do byteImg: " + byteImg.size());
            System.out.println("\nTamanho do bitmap: " + bitmap.getByteCount());

            foto = byteImg.toByteArray();
        }
    }

    public Bitmap getFotoWithBitmap() {
        Bitmap retorno = null;

        if (this.foto!=null) {
            retorno = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        }

        return retorno;
    }
}

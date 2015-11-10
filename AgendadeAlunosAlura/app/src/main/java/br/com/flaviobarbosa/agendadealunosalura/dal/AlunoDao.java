package br.com.flaviobarbosa.agendadealunosalura.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.flaviobarbosa.agendadealunosalura.modelo.Aluno;

/**
 * Created by fbarbosa2020 on 30/10/15.
 */
public class AlunoDao extends SQLiteOpenHelper {


    public AlunoDao(Context context) {
        super(context, "AgendaAlunosCaelum", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Aluno (id INTEGER NOT NULL PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL, foto BLOB)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //https://thebhwgroup.com/blog/how-android-sqlite-onupgrade
        String sql = "";
        System.out.println("\noldVersion: " + oldVersion);
        System.out.println("\nnewVersion? " + newVersion);

        if (oldVersion<=1) {
            System.out.println("\nAdicionando coluna FOTO");
            sql = "ALTER TABLE Aluno ADD COLUMN foto BLOB";

            System.out.println("\nAtualizando: " + sql);

            db.execSQL(sql);
        }
    }

    public void persist(Aluno aluno) {
        if (aluno!=null) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("nome", aluno.getNome());
            registro.put("endereco", aluno.getEndereco());
            registro.put("site", aluno.getSite());
            registro.put("telefone", aluno.getTelefone());
            registro.put("nota", aluno.getNota());
            registro.put("foto", aluno.getFoto());

            if ((aluno.getId()==null) || (aluno.getId()==0)) {
                db.insert("Aluno", null, registro);
            } else {
                String[] whereArgs = new String[] {aluno.getId().toString()};
                db.update("Aluno", registro, "id=?", whereArgs);
            }

            db.close();

        }
    }

    public List<Aluno> getTodosAlunos() {
        List<Aluno> retorno = new ArrayList<Aluno>();

        String sql = "SELECT * FROM Aluno ORDER BY upper(nome);";
        SQLiteDatabase db = getReadableDatabase();
        Cursor registros = db.rawQuery(sql, null);

        while (registros.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setNome(registros.getString(registros.getColumnIndex("nome")));
            aluno.setEndereco(registros.getString(registros.getColumnIndex("endereco")));
            aluno.setTelefone(registros.getString(registros.getColumnIndex("telefone")));
            aluno.setSite(registros.getString(registros.getColumnIndex("site")));
            aluno.setNota(registros.getDouble(registros.getColumnIndex("nota")));
            aluno.setId(registros.getLong(registros.getColumnIndex("id")));
            if (registros.getColumnIndex("foto")>=0) {
                aluno.setFoto(registros.getBlob(registros.getColumnIndex("foto")));
            }

            retorno.add(aluno);
        }

        registros.close();

        return retorno;
    }

    public void delete(Aluno aluno) {
        if (aluno!=null) {
            SQLiteDatabase db = getWritableDatabase();
            String[] parametros = new String[] {aluno.getId().toString()};
            db.delete("Aluno", "id=?", parametros);
            db.close();

        }
    }
}

package br.com.flaviobarbosa.agendadealunosalura;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.flaviobarbosa.agendadealunosalura.dal.AlunoDao;
import br.com.flaviobarbosa.agendadealunosalura.modelo.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView ltvAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        ltvAlunos = (ListView) findViewById(R.id.ltvAlunos);
        registerForContextMenu(ltvAlunos);

        ltvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View itemClicado, int position, long id) {
                Aluno aluno = (Aluno)lista.getItemAtPosition(position);
                Intent intentFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intentFormulario.putExtra("alunoCarregar", aluno);

                startActivity(intentFormulario);

            }
        });

        Button btnNovoAluno = (Button) findViewById(R.id.btnNovoAluno);

        btnNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentFormulario);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
        AlunoDao alunoDao = new AlunoDao(this);
        List<Aluno> listaAlunos = alunoDao.getTodosAlunos();
        alunoDao.close();



        //String[] alunos = { "Flavio Barbosa", "Michelle Mazer", "Ricardo Mazer Barbosa", "Gabriel Gomes Barbosa", "Leonardo Mazer Bueno da Silva", "André Valença", "Tiago Ferezin", "Rebeka Cunha", "Newton"};
        //ArrayAdapter<String> arrayAdapterAlunos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);

        ArrayAdapter<Aluno> arrayAdapterAlunos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAlunos);



        ltvAlunos.setAdapter(arrayAdapterAlunos);

        arrayAdapterAlunos.notifyDataSetChanged();

        Toast.makeText(this, " Lista carregada!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);

        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

                Aluno aluno = (Aluno)ltvAlunos.getItemAtPosition(info.position);


                AlunoDao dao = new AlunoDao(ListaAlunosActivity.this);

                dao.delete(aluno);
                dao.close();

                carregarLista();
                //Toast.makeText(ListaAlunosActivity.this, "Aluno clicado " + aluno.toString(), Toast.LENGTH_SHORT).show();


                return false;
            }
        });


    }

}

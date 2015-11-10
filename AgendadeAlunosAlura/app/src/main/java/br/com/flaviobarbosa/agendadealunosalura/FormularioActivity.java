package br.com.flaviobarbosa.agendadealunosalura;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.flaviobarbosa.agendadealunosalura.dal.AlunoDao;
import br.com.flaviobarbosa.agendadealunosalura.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private FormularioHelper formularioHelper;
    private ImageView imgFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        formularioHelper = new FormularioHelper(this);

        Intent intent = getIntent();

        if (intent.getSerializableExtra("alunoCarregar")!=null) {
            Aluno aluno = (Aluno)intent.getSerializableExtra("alunoCarregar");
            formularioHelper.setAluno(aluno);
        }


        Button btnFoto = (Button)findViewById(R.id.btnFoto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Apenas uma miniimagem (thumbnails)

                // Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                   // startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

                //}


                dispatchTakePictureIntent();
            }
        });


        imgFoto = (ImageView)findViewById(R.id.imgFoto);

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            System.out.println("\nCriando arquivo\n");
            File photoFile = null;
            try {
                photoFile = createImageFile();
                System.out.println("\nArquivo criado com sucesso!\n");
            } catch (IOException ex) {
                // Error occurred while creating the File
                System.out.println("\n\nErro ao tentar gravar a foto em " + mCurrentPhotoPath);
                System.out.println("\n" );
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                System.out.println("\nRecuperando o extra_output!\n");

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                System.out.println("\nIniciando startActivityForResult\n");
                //startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        System.out.println("\nonActivityResult");
        System.out.println("requestCode: " + requestCode);
        System.out.println("resultCode: " + resultCode);
        System.out.println("REQUEST_TAKE_PHOTO: " + REQUEST_TAKE_PHOTO);
        System.out.println("REQUEST_IMAGE_CAPTURE: " + REQUEST_IMAGE_CAPTURE);
        System.out.println("RESULT_OK: " + RESULT_OK);


//        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data!=null) {
                System.out.println("\nRecuperando data\n");
                Bundle extras = data.getExtras();
                System.out.println("\nMediaStore.EXTRA_OUTPUT\n");
                //Bitmap imageBitmap = (Bitmap) extras.get("data");
                System.out.println(extras.get(MediaStore.EXTRA_OUTPUT).getClass().getName());

                Bitmap imageBitmap = (Bitmap) extras.get(MediaStore.EXTRA_OUTPUT);
                imgFoto.setImageBitmap(imageBitmap);
            } else {
                System.out.println("\ndata is null\n");

            }
        } else {
            System.out.println("\nrequestCode != REQUEST_IMAGE_CAPTURE or resultCode != RESULT_OK\n");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_formulario_confirmar, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuFormularioConfirmar:
                Aluno aluno = formularioHelper.getAluno();

                AlunoDao alunoDao = new AlunoDao(this);

                alunoDao.persist(aluno);
                alunoDao.close();


                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " nota " + aluno.getNota().toString() + " Salvo!", Toast.LENGTH_SHORT).show();

                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);

        System.out.println("\n\nstorageDir: [" + storageDir + "]\n");
        System.out.println("\n\nimageFileName: [" + imageFileName + "]\n");


        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


}


package br.com.flaviobarbosa.agendadealunosalura;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.flaviobarbosa.agendadealunosalura.modelo.Aluno;

/**
 * Created by fbarbosa2020 on 30/10/15.
 */
public class FormularioHelper {

    private final EditText edtNome;
    private final EditText edtEndereco;
    private final EditText edtTelefone;
    private final EditText edtSite;
    private final RatingBar rtbNota;
    private final ImageView imgFoto;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {
        edtNome = (EditText) activity.findViewById(R.id.edtNome);
        edtEndereco = (EditText) activity.findViewById(R.id.edtEndereco);
        edtTelefone = (EditText) activity.findViewById(R.id.edtTelefone);
        edtSite = (EditText) activity.findViewById(R.id.edtSite);
        rtbNota = (RatingBar) activity.findViewById(R.id.rtbNota);
        imgFoto = (ImageView)activity.findViewById(R.id.imgFoto);

        aluno = new Aluno();

        // Recuperando número do telefone
        TelephonyManager tMgr = (TelephonyManager)activity.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

        if (tMgr!=null) {
            System.out.println("\n\ntMgr recuperado!\n");

            String mPhoneNumber = tMgr.getLine1Number();
            if ((mPhoneNumber!=null)&&(!mPhoneNumber.isEmpty())) {
                System.out.println("\n\nNumero do telefone recuperado [" + mPhoneNumber +"]\n");
                edtTelefone.setText(mPhoneNumber);
            }

            System.out.println("\n\nId dispositivo: " + tMgr.getDeviceId());
        }

        // Recuperando um email
        AccountManager am = AccountManager.get(activity);
        Account[] accounts = am.getAccounts();

        String email = "";

        for (Account ac : accounts) {

            System.out.println(ac.type + ": " + ac.name );

            if (ac.name.contains("@")) {
                if (ac.name.contains("@ciaware.com")) {
                    email = ac.name;
                    break;
                } else if ((ac.name.contains("@gmail.com"))) {
                    email = ac.name;
                    break;
                } else if (ac.name.contains("@hotmail.com")) {
                    email = ac.name;
                    break;
                } else if (ac.name.contains("@yahoo.com")) {
                    email = ac.name;
                    break;
                } else if (ac.name.contains("@icloud.com")) {
                    email = ac.name;
                    break;
                } else if (ac.name.contains("@uol.com")) {
                    email = ac.name;
                    break;
                } else if (ac.name.contains("@terra.com")) {
                    email = ac.name;
                    break;
                } else if (ac.name.contains("@ig.com")) {
                    email = ac.name;
                    break;
                } else if (ac.name.contains("@bol.com")) {
                    email = ac.name;
                    break;
                }
            }
        }

        if (!email.isEmpty()) {
            edtSite.setText(email);
        }
    }


    public Aluno getAluno() {

        Aluno retorno = aluno;

        retorno.setNome(edtNome.getText().toString());
        retorno.setEndereco(edtEndereco.getText().toString());
        retorno.setTelefone(edtTelefone.getText().toString());
        retorno.setSite(edtSite.getText().toString());
        retorno.setNota(Double.valueOf(rtbNota.getProgress()));
        retorno.setFoto(imgFoto.getDrawable());

        return retorno;
    }

    public void setAluno(Aluno aluno) {
        edtNome.setText(aluno.getNome());
        edtEndereco.setText(aluno.getEndereco());
        edtTelefone.setText(aluno.getTelefone());
        edtSite.setText(aluno.getSite());
        rtbNota.setProgress(aluno.getNota().intValue());
        imgFoto.setImageBitmap(aluno.getFotoWithBitmap());

        this.aluno = aluno;
    }
}

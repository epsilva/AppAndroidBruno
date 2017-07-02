package br.com.devpi.novocalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import br.com.devpi.novocalendar.entidades.Usuario;
import br.com.devpi.novocalendar.enums.EnumConexoes;
import br.com.devpi.novocalendar.enums.EnumMensagens;
import br.com.devpi.novocalendar.enums.EnumTitulo;
import br.com.devpi.novocalendar.httpclient.HttpCall;
import br.com.devpi.novocalendar.httpclient.HttpRequest;

public class LoginActivity extends Activity {

    private Button botaoLogin;
    private EditText login;
    private EditText senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        botaoLogin = findViewById(R.id.botaoLogin);
        login = findViewById(R.id.login);
        senha = findViewById(R.id.password);

        logar();
    }

    public void logar(){
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpCall httpCall = new HttpCall();
                httpCall.setMethodtype(HttpCall.GET);
                httpCall.setUrl(EnumConexoes.GET_USUARIO_BY_LOGIN.getConexao()+login.getText().toString().replace('.', '+'));
                new HttpRequest(){
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        Gson gson = new Gson();
                        Usuario usuario = gson.fromJson(response, Usuario.class);
                        if(usuario != null && usuario.getSenha().equals(senha.getText().toString())){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                            finish();
                        }else{
                            alertaUsuarioSenhaInvalido();
                        }

                    }
                }.execute(httpCall);
            }
        });
    }

    private void alertaUsuarioSenhaInvalido() {
        AlertDialog alerta;
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle(EnumTitulo.TITULO_AVISO.getTitulo());
        //define a mensagem
        builder.setMessage(EnumMensagens.MENSAGEN_LOGIN_INVALIDO.getMensagem());
        //define um botão como positivo
        builder.setPositiveButton(EnumTitulo.TITULO_OK.getTitulo(), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(LoginActivity.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }
}

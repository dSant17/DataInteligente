package com.example.datainteligente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.versionedparcelable.ParcelField;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private TextView txtUsuario, txtContra;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
    }

    @Override
    public void onStart(){
        super.onStart();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()){
                    logIn();
                } else {
                    Toast.makeText(LoginActivity.this, "Se necesita tener conexión a Internet.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void initComponents() {
        this.txtUsuario = findViewById(R.id.txtUsuario);
        this.txtContra = findViewById(R.id.txtContra);
        this.btnLogin = findViewById(R.id.btnLogin);
    }

    public void logIn(){
        String usuario = txtUsuario.getText().toString();
        String contra = txtContra.getText().toString();
        boolean valido = true;

        if (txtUsuario.getText().toString().equals("")) {
            txtUsuario.setError("Introduce un nombre de usuario encuestador.");
            valido = false;
        }

        if (txtContra.getText().toString().equals("")) {
            txtContra.setError("Introduce una contraseña.");
            valido = false;
        }

        if (valido) {
            phpLogin(usuario, contra);
        } else {
            Toast.makeText(this, "Usuario y/o contraseña incorrectos.", Toast.LENGTH_SHORT).show();
        }
    }

    public void phpLogin(final String user, final String pass) {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Iniciando sesión");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        String url = "http://datainteligente.com.mx/encuestador/app/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Bienvenido "+ user + ".")) {
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("usuario", user);
                    startActivity(i);
                    progressDialog.dismiss();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("user", user);
                param.put("pass", pass);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(LoginActivity.this).addToRequestQueue(request);
    }

    public boolean isNetworkAvailable(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }
}
package com.example.datainteligente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView txtBienvenida;
    private Button btnCerrar;
    private RecyclerView encuestas;
    private RecyclerView.LayoutManager layoutManager;
    public static List<Survey> surveyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    @Override
    public void onStart(){
        super.onStart();
        welcomeMessage();
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                Toast.makeText(MainActivity.this, "Se ha cerrado la sesión.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void initComponents() {
        this.txtBienvenida = findViewById(R.id.txtBienvenida);
        this.btnCerrar = findViewById(R.id.btnLogout);
        this.encuestas = findViewById(R.id.encuestas);
        this.encuestas.setHasFixedSize(true);
        this.encuestas.setLayoutManager(new LinearLayoutManager(this));
        surveyList = new ArrayList<>();
    }

    public void welcomeMessage() {
        Intent i = getIntent();
        String usuario = i.getStringExtra("usuario");

        txtBienvenida.setText(Html.fromHtml("<b>Bienvenido<br>" + usuario + "<b>"));
        loadSurveys(usuario);
    }

    public void loadSurveys(String user) {
        String URL = "http://datainteligente.com.mx/encuestador/app/getMainInfo.php";
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject survey = array.getJSONObject(i);
                        surveyList.add(new Survey(
                                survey.getInt("idEncuesta"),
                                survey.getString("nombre"),
                                survey.getString("descrip"),
                                survey.getString("fdcreacion")
                        ));
                    }
                    SurveyAdapter adapter = new SurveyAdapter(MainActivity.this, surveyList);
                    encuestas.setAdapter(adapter);
                    adapter.setUsuario(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("user", user);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(MainActivity.this).addToRequestQueue(request);
    }
}
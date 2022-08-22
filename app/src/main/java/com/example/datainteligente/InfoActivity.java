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

public class InfoActivity extends AppCompatActivity {
    private TextView txtEncuestador, txtProyecto, txtDescripcion, txtFecha;
    private Button btnRegresar, btnAplicar;
    private RecyclerView preguntas;
    private RecyclerView.LayoutManager layoutManager;
    public static List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initComponents();
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent i = getIntent();
        String usuario = i.getStringExtra("usuario");
        String idEncuesta = i.getStringExtra("idEncuesta");
        String encuesta = i.getStringExtra("encuesta");

        txtEncuestador.setText(Html.fromHtml("<b>Encuestador: </b>" + usuario + "<br>"));
        loadSurveyInfo(usuario, idEncuesta);
        loadQuestions(idEncuesta);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InfoActivity.this, MainActivity.class);
                i.putExtra("usuario", usuario);
                startActivity(i);
                finish();
            }
        });

        btnAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InfoActivity.this, SurveyActivity.class);
                i.putExtra("usuario", usuario);
                i.putExtra("idEncuesta", idEncuesta);
                i.putExtra("encuesta", encuesta);
                startActivity(i);
                finish();
            }
        });
    }

    public void initComponents() {
        this.txtEncuestador = findViewById(R.id.txtEncuestador);
        this.txtProyecto = findViewById(R.id.txtProyecto);
        this.txtDescripcion = findViewById(R.id.txtDescripcion);
        this.txtFecha = findViewById(R.id.txtFecha);
        this.btnRegresar = findViewById(R.id.btnRegresar);
        this.btnAplicar = findViewById(R.id.btnAplicar);
        this.preguntas = findViewById(R.id.lstPreguntas);
        this.preguntas.setHasFixedSize(true);
        this.preguntas.setLayoutManager(new LinearLayoutManager(this));
        questionList = new ArrayList<>();
    }

    public void loadSurveyInfo(String user, String surveyID) {
        String URL = "http://datainteligente.com.mx/encuestador/app/getSurveyInfo.php";
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject question = array.getJSONObject(i);
                        txtProyecto.setText(Html.fromHtml("<b>" + question.getString("nombre") + "</b>"));
                        txtDescripcion.setText(Html.fromHtml(question.getString("descrip")));
                        txtFecha.setText(Html.fromHtml("<b>Fecha de creación: </b>" + question.getString("fdcreacion")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InfoActivity.this, "Ha ocurrido un error.", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("user", user);
                param.put("idEncuesta", surveyID);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(InfoActivity.this).addToRequestQueue(request);
    }

    public void loadQuestions(String idEncuesta) {
        String URL = "http://datainteligente.com.mx/encuestador/app/getSurveyQuestions.php";
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject question = array.getJSONObject(i);
                        questionList.add(new Question(
                                Integer.parseInt(idEncuesta),
                                question.getInt("idPregunta"),
                                question.getInt("tipo"),
                                question.getString("pregunta"),
                                question.getString("opcion1"),
                                question.getString("opcion2"),
                                question.getString("opcion3"),
                                question.getString("opcion4"),
                                question.getString("opcion5"),
                                question.getString("opcion6"),
                                question.getString("opcion7"),
                                question.getString("opcion8"),
                                question.getString("opcion9"),
                                question.getString("opcion10")
                        ));
                    }
                    QuestionAdapter adapter = new QuestionAdapter(InfoActivity.this, questionList);
                    preguntas.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InfoActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("idEncuesta", idEncuesta);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(InfoActivity.this).addToRequestQueue(request);
    }
}
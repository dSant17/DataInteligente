package com.example.datainteligente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
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

public class SurveyActivity extends AppCompatActivity {
    private TextView txtEncuesta, txtNumEncuesta, txtEncuestador;
    private Button btnCancelar, btnEnviar;
    private RecyclerView encuesta;
    private RecyclerView.LayoutManager layoutManager;
    private List<Question> questionList;
    private List<Answers> answersList;

    public void setAnswersList(List<Answers> answersList) {
        this.answersList = answersList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        initComponents();
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent i = getIntent();
        String usuario = i.getStringExtra("usuario");
        String idEncuesta = i.getStringExtra("idEncuesta");
        String nomEncuesta = i.getStringExtra("encuesta");

        txtEncuesta.setText(Html.fromHtml("<b>" + nomEncuesta + "</b>"));
        // txtNumEncuesta.setText("");
        txtEncuestador.setText(Html.fromHtml("<b>Encuestador: </b>" + usuario));
        loadSurvey(idEncuesta);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SurveyActivity.this, MainActivity.class);
                i.putExtra("usuario", usuario);
                startActivity(i);
                finish();
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int j = 0; j < questionList.size(); j++){
                    String numPregunta = String.valueOf(j+1);

                    QuestionAdapter adapter = new QuestionAdapter(SurveyActivity.this, questionList);
                    List<Answers> respuesta = adapter.getAnswersList();
                    String answer = respuesta.get(j).getRespuesta().toString();

                    String URL = "http://datainteligente.com.mx/encuestador/app/saveSurveyAnswers.php";
                    StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("Respuestas enviadas correctamente.")){
                                Toast.makeText(SurveyActivity.this, response, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SurveyActivity.this, MainActivity.class);
                                i.putExtra("usuario", usuario);
                                startActivity(i);
                                finish();
                            } else if (response.equals("Hubo un error de conexión o la respuesta es nula.")) {
                                Toast.makeText(SurveyActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SurveyActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> param = new HashMap<>();
                            param.put("usuario", usuario);
                            param.put("idEncuesta", idEncuesta);
                            param.put("respuesta", answer);
                            param.put("numPregunta", numPregunta);
                            return param;
                        }
                    };
                    request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    MySingleton.getmInstance(SurveyActivity.this).addToRequestQueue(request);
                }
            }
        });
    }

    public void initComponents() {
        this.txtEncuesta = findViewById(R.id.txtEncuesta);
        this.txtNumEncuesta = findViewById(R.id.txtNumEncuesta);
        this.txtEncuestador = findViewById(R.id.txtEncuestador);
        this.btnCancelar = findViewById(R.id.btnCancelar);
        this.btnEnviar = findViewById(R.id.btnSiguiente);
        this.encuesta = findViewById(R.id.lstPreguntas);
        this.encuesta.setHasFixedSize(true);
        this.encuesta.setLayoutManager(new LinearLayoutManager(this));
        questionList = new ArrayList<>();
    }

    public void loadSurvey(String idEncuesta) {
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
                    QuestionAdapter adapter = new QuestionAdapter(SurveyActivity.this, questionList);
                    encuesta.setAdapter(adapter);
                    adapter.setIdEncuesta(idEncuesta);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SurveyActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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
        MySingleton.getmInstance(SurveyActivity.this).addToRequestQueue(request);
    }
}
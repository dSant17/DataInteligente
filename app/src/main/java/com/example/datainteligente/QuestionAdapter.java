package com.example.datainteligente;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    private Context context;
    private List<Question> questionList;
    private List<Answers> answersList = new ArrayList<>();
    private String idEncuesta;
    private String idUsuario;
    private String idNumEncuesta;

    public QuestionAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    public void setIdEncuesta(String idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdNumEncuesta() {
        return idNumEncuesta;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.questions_items, null);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder (QuestionViewHolder holder, int position) {
        String contextName = context.getClass().getSimpleName();
        if (contextName.equals("SurveyActivity")) {
            ifContextIsSurvey(holder, position);
        } else {
            ifContextIsInfo(holder, position);
        };
        String answer = setAnswers(holder, position);
        if (!answer.equals("")){
            answersList.add(new Answers(answer));
            SurveyActivity respuestas = new SurveyActivity();
            respuestas.setAnswersList(answersList);
        }
    }

    @Override
    public int getItemCount(){
        return questionList.size();
    }

    public void ifContextIsInfo(QuestionViewHolder holder, int position) {
        Question preguntas = questionList.get(position);
        holder.txtPregunta.setText(Html.fromHtml("<b>PREGUNTA " + preguntas.getIdPregunta() + "</b><br>" + preguntas.getPregunta()));

        holder.txtAbierta.setVisibility(View.GONE);
        if (preguntas.getTipo() == 1) {
            holder.btnResponder.setVisibility(View.GONE);
            holder.btnReiniciar.setVisibility(View.GONE);

            holder.opc1.setVisibility(View.GONE);
            holder.opc2.setVisibility(View.GONE);
            holder.opc3.setVisibility(View.GONE);
            holder.opc4.setVisibility(View.GONE);
            holder.opc5.setVisibility(View.GONE);
            holder.opc6.setVisibility(View.GONE);
            holder.opc7.setVisibility(View.GONE);
            holder.opc8.setVisibility(View.GONE);
            holder.opc9.setVisibility(View.GONE);
            holder.opc10.setVisibility(View.GONE);

            holder.op1.setVisibility(View.GONE);
            holder.op2.setVisibility(View.GONE);
            holder.op3.setVisibility(View.GONE);
            holder.op4.setVisibility(View.GONE);
            holder.op5.setVisibility(View.GONE);
            holder.op6.setVisibility(View.GONE);
            holder.op7.setVisibility(View.GONE);
            holder.op8.setVisibility(View.GONE);
            holder.op9.setVisibility(View.GONE);
            holder.op10.setVisibility(View.GONE);
        } else if (preguntas.getTipo() == 2) {
            holder.btnResponder.setVisibility(View.GONE);
            holder.btnReiniciar.setVisibility(View.GONE);

            holder.op1.setVisibility(View.GONE);
            holder.op2.setVisibility(View.GONE);
            holder.op3.setVisibility(View.GONE);
            holder.op4.setVisibility(View.GONE);
            holder.op5.setVisibility(View.GONE);
            holder.op6.setVisibility(View.GONE);
            holder.op7.setVisibility(View.GONE);
            holder.op8.setVisibility(View.GONE);
            holder.op9.setVisibility(View.GONE);
            holder.op10.setVisibility(View.GONE);

            holder.opc1.setText(preguntas.getOpc1());
            holder.opc2.setText(preguntas.getOpc2());
            holder.opc3.setText(preguntas.getOpc3());
            holder.opc4.setText(preguntas.getOpc4());
            holder.opc5.setText(preguntas.getOpc5());
            holder.opc6.setText(preguntas.getOpc6());
            holder.opc7.setText(preguntas.getOpc7());
            holder.opc8.setText(preguntas.getOpc8());
            holder.opc9.setText(preguntas.getOpc9());
            holder.opc10.setText(preguntas.getOpc10());

            if (preguntas.getOpc1().equals("")) {
                holder.opc1.setVisibility(View.GONE);
            }
            if (preguntas.getOpc2().equals("")){
                holder.opc2.setVisibility(View.GONE);
            }
            if (preguntas.getOpc3().equals("")){
                holder.opc3.setVisibility(View.GONE);
            }
            if (preguntas.getOpc4().equals("")){
                holder.opc4.setVisibility(View.GONE);
            }
            if (preguntas.getOpc5().equals("")){
                holder.opc5.setVisibility(View.GONE);
            }
            if (preguntas.getOpc6().equals("")){
                holder.opc6.setVisibility(View.GONE);
            }
            if (preguntas.getOpc7().equals("")){
                holder.opc7.setVisibility(View.GONE);
            }
            if (preguntas.getOpc8().equals("")){
                holder.opc8.setVisibility(View.GONE);
            }
            if (preguntas.getOpc9().equals("")){
                holder.opc9.setVisibility(View.GONE);
            }
            if (preguntas.getOpc10().equals("")){
                holder.opc10.setVisibility(View.GONE);
            }
        } else if (preguntas.getTipo() == 3) {
            holder.btnResponder.setVisibility(View.GONE);
            holder.btnReiniciar.setVisibility(View.GONE);

            holder.opc1.setVisibility(View.GONE);
            holder.opc2.setVisibility(View.GONE);
            holder.opc3.setVisibility(View.GONE);
            holder.opc4.setVisibility(View.GONE);
            holder.opc5.setVisibility(View.GONE);
            holder.opc6.setVisibility(View.GONE);
            holder.opc7.setVisibility(View.GONE);
            holder.opc8.setVisibility(View.GONE);
            holder.opc9.setVisibility(View.GONE);
            holder.opc10.setVisibility(View.GONE);

            holder.op1.setText(preguntas.getOpc1());
            holder.op2.setText(preguntas.getOpc2());
            holder.op3.setText(preguntas.getOpc3());
            holder.op4.setText(preguntas.getOpc4());
            holder.op5.setText(preguntas.getOpc5());
            holder.op6.setText(preguntas.getOpc6());
            holder.op7.setText(preguntas.getOpc7());
            holder.op8.setText(preguntas.getOpc8());
            holder.op9.setText(preguntas.getOpc9());
            holder.op10.setText(preguntas.getOpc10());

            if (preguntas.getOpc1().equals("")) {
                holder.op1.setVisibility(View.GONE);
            }
            if (preguntas.getOpc2().equals("")){
                holder.op2.setVisibility(View.GONE);
            }
            if (preguntas.getOpc3().equals("")){
                holder.op3.setVisibility(View.GONE);
            }
            if (preguntas.getOpc4().equals("")){
                holder.op4.setVisibility(View.GONE);
            }
            if (preguntas.getOpc5().equals("")){
                holder.op5.setVisibility(View.GONE);
            }
            if (preguntas.getOpc6().equals("")){
                holder.op6.setVisibility(View.GONE);
            }
            if (preguntas.getOpc7().equals("")){
                holder.op7.setVisibility(View.GONE);
            }
            if (preguntas.getOpc8().equals("")){
                holder.op8.setVisibility(View.GONE);
            }
            if (preguntas.getOpc9().equals("")){
                holder.op9.setVisibility(View.GONE);
            }
            if (preguntas.getOpc10().equals("")){
                holder.op10.setVisibility(View.GONE);
            }
        } else if (preguntas.getTipo() == 4) {
            holder.btnResponder.setVisibility(View.GONE);
            holder.btnReiniciar.setVisibility(View.GONE);

            holder.opc1.setText(preguntas.getOpc1());
            holder.opc2.setText(preguntas.getOpc2());
            holder.opc3.setVisibility(View.GONE);
            holder.opc4.setVisibility(View.GONE);
            holder.opc5.setVisibility(View.GONE);
            holder.opc6.setVisibility(View.GONE);
            holder.opc7.setVisibility(View.GONE);
            holder.opc8.setVisibility(View.GONE);
            holder.opc9.setVisibility(View.GONE);
            holder.opc10.setVisibility(View.GONE);

            holder.op1.setVisibility(View.GONE);
            holder.op2.setVisibility(View.GONE);
            holder.op3.setVisibility(View.GONE);
            holder.op4.setVisibility(View.GONE);
            holder.op5.setVisibility(View.GONE);
            holder.op6.setVisibility(View.GONE);
            holder.op7.setVisibility(View.GONE);
            holder.op8.setVisibility(View.GONE);
            holder.op9.setVisibility(View.GONE);
            holder.op10.setVisibility(View.GONE);
        } else if (preguntas.getTipo() == 5) {
            holder.btnResponder.setVisibility(View.GONE);
            holder.btnReiniciar.setVisibility(View.GONE);

            holder.op1.setVisibility(View.GONE);
            holder.op2.setVisibility(View.GONE);
            holder.op3.setVisibility(View.GONE);
            holder.op4.setVisibility(View.GONE);
            holder.op5.setVisibility(View.GONE);
            holder.op6.setVisibility(View.GONE);
            holder.op7.setVisibility(View.GONE);
            holder.op8.setVisibility(View.GONE);
            holder.op9.setVisibility(View.GONE);
            holder.op10.setVisibility(View.GONE);

            holder.opc1.setText(preguntas.getOpc1());
            holder.opc2.setText(preguntas.getOpc2());
            holder.opc3.setText(preguntas.getOpc3());
            holder.opc4.setText(preguntas.getOpc4());
            holder.opc5.setText(preguntas.getOpc5());
            holder.opc6.setText(preguntas.getOpc6());
            holder.opc7.setText(preguntas.getOpc7());
            holder.opc8.setText(preguntas.getOpc8());
            holder.opc9.setText(preguntas.getOpc9());
            holder.opc10.setText(preguntas.getOpc10());

            if (preguntas.getOpc1().equals("")) {
                holder.opc1.setVisibility(View.GONE);
            }
            if (preguntas.getOpc2().equals("")){
                holder.opc2.setVisibility(View.GONE);
            }
            if (preguntas.getOpc3().equals("")){
                holder.opc3.setVisibility(View.GONE);
            }
            if (preguntas.getOpc4().equals("")){
                holder.opc4.setVisibility(View.GONE);
            }
            if (preguntas.getOpc5().equals("")){
                holder.opc5.setVisibility(View.GONE);
            }
            if (preguntas.getOpc6().equals("")){
                holder.opc6.setVisibility(View.GONE);
            }
            if (preguntas.getOpc7().equals("")){
                holder.opc7.setVisibility(View.GONE);
            }
            if (preguntas.getOpc8().equals("")){
                holder.opc8.setVisibility(View.GONE);
            }
            if (preguntas.getOpc9().equals("")){
                holder.opc9.setVisibility(View.GONE);
            }
            if (preguntas.getOpc10().equals("")){
                holder.opc10.setVisibility(View.GONE);
            }
        }
    }

    public void ifContextIsSurvey(QuestionViewHolder holder, int position) {
        Question preguntas = questionList.get(position);
        holder.txtPregunta.setText(Html.fromHtml("<b>PREGUNTA " + preguntas.getIdPregunta() + "</b><br>" + preguntas.getPregunta()));

        holder.txtAbierta.setVisibility(View.GONE);
        if (preguntas.getTipo() == 1) {
            holder.btnResponder.setVisibility(View.GONE);
            holder.btnReiniciar.setVisibility(View.GONE);

            holder.opc1.setVisibility(View.GONE);
            holder.opc2.setVisibility(View.GONE);
            holder.opc3.setVisibility(View.GONE);
            holder.opc4.setVisibility(View.GONE);
            holder.opc5.setVisibility(View.GONE);
            holder.opc6.setVisibility(View.GONE);
            holder.opc7.setVisibility(View.GONE);
            holder.opc8.setVisibility(View.GONE);
            holder.opc9.setVisibility(View.GONE);
            holder.opc10.setVisibility(View.GONE);

            holder.op1.setVisibility(View.GONE);
            holder.op2.setVisibility(View.GONE);
            holder.op3.setVisibility(View.GONE);
            holder.op4.setVisibility(View.GONE);
            holder.op5.setVisibility(View.GONE);
            holder.op6.setVisibility(View.GONE);
            holder.op7.setVisibility(View.GONE);
            holder.op8.setVisibility(View.GONE);
            holder.op9.setVisibility(View.GONE);
            holder.op10.setVisibility(View.GONE);
            holder.txtAbierta.setVisibility(View.VISIBLE);
        } else if (preguntas.getTipo() == 2) {
            holder.btnResponder.setVisibility(View.GONE);
            holder.btnReiniciar.setVisibility(View.GONE);

            holder.op1.setVisibility(View.GONE);
            holder.op2.setVisibility(View.GONE);
            holder.op3.setVisibility(View.GONE);
            holder.op4.setVisibility(View.GONE);
            holder.op5.setVisibility(View.GONE);
            holder.op6.setVisibility(View.GONE);
            holder.op7.setVisibility(View.GONE);
            holder.op8.setVisibility(View.GONE);
            holder.op9.setVisibility(View.GONE);
            holder.op10.setVisibility(View.GONE);

            holder.opc1.setText(preguntas.getOpc1());
            holder.opc2.setText(preguntas.getOpc2());
            holder.opc3.setText(preguntas.getOpc3());
            holder.opc4.setText(preguntas.getOpc4());
            holder.opc5.setText(preguntas.getOpc5());
            holder.opc6.setText(preguntas.getOpc6());
            holder.opc7.setText(preguntas.getOpc7());
            holder.opc8.setText(preguntas.getOpc8());
            holder.opc9.setText(preguntas.getOpc9());
            holder.opc10.setText(preguntas.getOpc10());

            if (preguntas.getOpc1().equals("")) {
                holder.opc1.setVisibility(View.GONE);
            }
            if (preguntas.getOpc2().equals("")){
                holder.opc2.setVisibility(View.GONE);
            }
            if (preguntas.getOpc3().equals("")){
                holder.opc3.setVisibility(View.GONE);
            }
            if (preguntas.getOpc4().equals("")){
                holder.opc4.setVisibility(View.GONE);
            }
            if (preguntas.getOpc5().equals("")){
                holder.opc5.setVisibility(View.GONE);
            }
            if (preguntas.getOpc6().equals("")){
                holder.opc6.setVisibility(View.GONE);
            }
            if (preguntas.getOpc7().equals("")){
                holder.opc7.setVisibility(View.GONE);
            }
            if (preguntas.getOpc8().equals("")){
                holder.opc8.setVisibility(View.GONE);
            }
            if (preguntas.getOpc9().equals("")){
                holder.opc9.setVisibility(View.GONE);
            }
            if (preguntas.getOpc10().equals("")){
                holder.opc10.setVisibility(View.GONE);
            }
        } else if (preguntas.getTipo() == 3) {
            holder.btnResponder.setVisibility(View.GONE);
            holder.btnReiniciar.setVisibility(View.GONE);

            holder.opc1.setVisibility(View.GONE);
            holder.opc2.setVisibility(View.GONE);
            holder.opc3.setVisibility(View.GONE);
            holder.opc4.setVisibility(View.GONE);
            holder.opc5.setVisibility(View.GONE);
            holder.opc6.setVisibility(View.GONE);
            holder.opc7.setVisibility(View.GONE);
            holder.opc8.setVisibility(View.GONE);
            holder.opc9.setVisibility(View.GONE);
            holder.opc10.setVisibility(View.GONE);

            holder.op1.setText(preguntas.getOpc1());
            holder.op2.setText(preguntas.getOpc2());
            holder.op3.setText(preguntas.getOpc3());
            holder.op4.setText(preguntas.getOpc4());
            holder.op5.setText(preguntas.getOpc5());
            holder.op6.setText(preguntas.getOpc6());
            holder.op7.setText(preguntas.getOpc7());
            holder.op8.setText(preguntas.getOpc8());
            holder.op9.setText(preguntas.getOpc9());
            holder.op10.setText(preguntas.getOpc10());

            if (preguntas.getOpc1().equals("")) {
                holder.op1.setVisibility(View.GONE);
            }
            if (preguntas.getOpc2().equals("")){
                holder.op2.setVisibility(View.GONE);
            }
            if (preguntas.getOpc3().equals("")){
                holder.op3.setVisibility(View.GONE);
            }
            if (preguntas.getOpc4().equals("")){
                holder.op4.setVisibility(View.GONE);
            }
            if (preguntas.getOpc5().equals("")){
                holder.op5.setVisibility(View.GONE);
            }
            if (preguntas.getOpc6().equals("")){
                holder.op6.setVisibility(View.GONE);
            }
            if (preguntas.getOpc7().equals("")){
                holder.op7.setVisibility(View.GONE);
            }
            if (preguntas.getOpc8().equals("")){
                holder.op8.setVisibility(View.GONE);
            }
            if (preguntas.getOpc9().equals("")){
                holder.op9.setVisibility(View.GONE);
            }
            if (preguntas.getOpc10().equals("")){
                holder.op10.setVisibility(View.GONE);
            }
        } else if (preguntas.getTipo() == 4) {
            holder.btnResponder.setVisibility(View.GONE);
            holder.btnReiniciar.setVisibility(View.GONE);

            holder.opc1.setText(preguntas.getOpc1());
            holder.opc2.setText(preguntas.getOpc2());
            holder.opc3.setVisibility(View.GONE);
            holder.opc4.setVisibility(View.GONE);
            holder.opc5.setVisibility(View.GONE);
            holder.opc6.setVisibility(View.GONE);
            holder.opc7.setVisibility(View.GONE);
            holder.opc8.setVisibility(View.GONE);
            holder.opc9.setVisibility(View.GONE);
            holder.opc10.setVisibility(View.GONE);

            holder.op1.setVisibility(View.GONE);
            holder.op2.setVisibility(View.GONE);
            holder.op3.setVisibility(View.GONE);
            holder.op4.setVisibility(View.GONE);
            holder.op5.setVisibility(View.GONE);
            holder.op6.setVisibility(View.GONE);
            holder.op7.setVisibility(View.GONE);
            holder.op8.setVisibility(View.GONE);
            holder.op9.setVisibility(View.GONE);
            holder.op10.setVisibility(View.GONE);
        } else if (preguntas.getTipo() == 5) {
            holder.op1.setVisibility(View.GONE);
            holder.op2.setVisibility(View.GONE);
            holder.op3.setVisibility(View.GONE);
            holder.op4.setVisibility(View.GONE);
            holder.op5.setVisibility(View.GONE);
            holder.op6.setVisibility(View.GONE);
            holder.op7.setVisibility(View.GONE);
            holder.op8.setVisibility(View.GONE);
            holder.op9.setVisibility(View.GONE);
            holder.op10.setVisibility(View.GONE);

            holder.opc1.setText(preguntas.getOpc1());
            holder.opc2.setText(preguntas.getOpc2());
            holder.opc3.setText(preguntas.getOpc3());
            holder.opc4.setText(preguntas.getOpc4());
            holder.opc5.setText(preguntas.getOpc5());
            holder.opc6.setText(preguntas.getOpc6());
            holder.opc7.setText(preguntas.getOpc7());
            holder.opc8.setText(preguntas.getOpc8());
            holder.opc9.setText(preguntas.getOpc9());
            holder.opc10.setText(preguntas.getOpc10());

            if (preguntas.getOpc1().equals("")) {
                holder.opc1.setVisibility(View.GONE);
            }
            if (preguntas.getOpc2().equals("")){
                holder.opc2.setVisibility(View.GONE);
            }
            if (preguntas.getOpc3().equals("")){
                holder.opc3.setVisibility(View.GONE);
            }
            if (preguntas.getOpc4().equals("")){
                holder.opc4.setVisibility(View.GONE);
            }
            if (preguntas.getOpc5().equals("")){
                holder.opc5.setVisibility(View.GONE);
            }
            if (preguntas.getOpc6().equals("")){
                holder.opc6.setVisibility(View.GONE);
            }
            if (preguntas.getOpc7().equals("")){
                holder.opc7.setVisibility(View.GONE);
            }
            if (preguntas.getOpc8().equals("")){
                holder.opc8.setVisibility(View.GONE);
            }
            if (preguntas.getOpc9().equals("")){
                holder.opc9.setVisibility(View.GONE);
            }
            if (preguntas.getOpc10().equals("")){
                holder.opc10.setVisibility(View.GONE);
            }
        }
    }

    public String setAnswers(QuestionViewHolder holder, int position) {
        Question preguntas = questionList.get(position);
        String respuesta = "";
        if (preguntas.getTipo() == 1) {
            respuesta = holder.txtAbierta.getText().toString();
        } else if (preguntas.getTipo() == 2 || preguntas.getTipo() == 4) {
            if (holder.opc1.isChecked()) {
                respuesta = holder.opc1.getText().toString();
            } else if (holder.opc2.isChecked()) {
                respuesta = holder.opc2.getText().toString();
            } else if (holder.opc3.isChecked()) {
                respuesta = holder.opc3.getText().toString();
            } else if (holder.opc4.isChecked()) {
                respuesta = holder.opc4.getText().toString();
            } else if (holder.opc5.isChecked()) {
                respuesta = holder.opc5.getText().toString();
            } else if (holder.opc6.isChecked()) {
                respuesta = holder.opc6.getText().toString();
            } else if (holder.opc7.isChecked()) {
                respuesta = holder.opc7.getText().toString();
            } else if (holder.opc8.isChecked()) {
                respuesta = holder.opc8.getText().toString();
            } else if (holder.opc9.isChecked()) {
                respuesta = holder.opc9.getText().toString();
            } else if (holder.opc10.isChecked()) {
                respuesta = holder.opc10.getText().toString();
            }
        } else if (preguntas.getTipo() == 3) {
            if (holder.op1.isChecked()) {
                respuesta = respuesta + holder.op1.getText().toString() + "<br>";
            } else if (holder.op2.isChecked()) {
                respuesta = respuesta + holder.op2.getText().toString() + "<br>";
            } else if (holder.op3.isChecked()) {
                respuesta = respuesta + holder.op3.getText().toString() + "<br>";
            } else if (holder.op4.isChecked()) {
                respuesta = respuesta + holder.op4.getText().toString() + "<br>";
            } else if (holder.op5.isChecked()) {
                respuesta = respuesta + holder.op5.getText().toString() + "<br>";
            } else if (holder.op6.isChecked()) {
                respuesta = respuesta + holder.op6.getText().toString() + "<br>";
            } else if (holder.op7.isChecked()) {
                respuesta = respuesta + holder.op7.getText().toString() + "<br>";
            } else if (holder.op8.isChecked()) {
                respuesta = respuesta + holder.op8.getText().toString() + "<br>";
            } else if (holder.op9.isChecked()) {
                respuesta = respuesta + holder.op9.getText().toString() + "<br>";
            } else if (holder.op10.isChecked()) {
                respuesta = respuesta + holder.op10.getText().toString() + "<br>";
            }
        } else if (preguntas.getTipo() == 5) {
            holder.btnResponder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveAnswersType5(holder);
                }
            });
            holder.btnReiniciar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resetQuestionType5(holder);
                }
            });
        }
        return respuesta;
    }

    public String saveAnswersType5(QuestionViewHolder holder) {
        String respuesta = "";
        if(holder.opc1.isChecked()){
            holder.opc1.setChecked(false);
            holder.opc1.setEnabled(false);
            respuesta = respuesta + holder.opc1.getText().toString() + "<br>";
        }
        if(holder.opc2.isChecked()){
            holder.opc2.setChecked(false);
            holder.opc2.setEnabled(false);
            respuesta = respuesta + holder.opc2.getText().toString() + "<br>";
        }
        if(holder.opc3.isChecked()){
            holder.opc3.setChecked(false);
            holder.opc3.setEnabled(false);
            respuesta = respuesta + holder.opc3.getText().toString() + "<br>";
        }
        if(holder.opc4.isChecked()){
            holder.opc4.setChecked(false);
            holder.opc4.setEnabled(false);
            respuesta = respuesta + holder.opc4.getText().toString() + "<br>";
        }
        if(holder.opc5.isChecked()){
            holder.opc5.setChecked(false);
            holder.opc5.setEnabled(false);
            respuesta = respuesta + holder.opc5.getText().toString() + "<br>";
        }
        if(holder.opc6.isChecked()){
            holder.opc6.setChecked(false);
            holder.opc6.setEnabled(false);
            respuesta = respuesta + holder.opc6.getText().toString() + "<br>";
        }
        if(holder.opc7.isChecked()){
            holder.opc7.setChecked(false);
            holder.opc7.setEnabled(false);
            respuesta = respuesta + holder.opc7.getText().toString() + "<br>";
        }
        if(holder.opc8.isChecked()){
            holder.opc8.setChecked(false);
            holder.opc8.setEnabled(false);
            respuesta = respuesta + holder.opc8.getText().toString() + "<br>";
        }
        if(holder.opc9.isChecked()){
            holder.opc9.setChecked(false);
            holder.opc9.setEnabled(false);
            respuesta = respuesta + holder.opc9.getText().toString() + "<br>";
        }
        if(holder.opc10.isChecked()){
            holder.opc10.setChecked(false);
            holder.opc10.setEnabled(false);
            respuesta = respuesta + holder.opc10.getText().toString() + "<br>";
        }
        return respuesta;
    }

    public String resetQuestionType5(QuestionViewHolder holder){
        String respuesta = "";
        holder.opc1.setEnabled(true);
        holder.opc2.setEnabled(true);
        holder.opc3.setEnabled(true);
        holder.opc4.setEnabled(true);
        holder.opc5.setEnabled(true);
        holder.opc6.setEnabled(true);
        holder.opc7.setEnabled(true);
        holder.opc8.setEnabled(true);
        holder.opc9.setEnabled(true);
        holder.opc10.setEnabled(true);
        return respuesta;
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView txtPregunta;
        EditText txtAbierta;
        RadioGroup rbgRespuestas;
        RadioButton opc1, opc2, opc3, opc4, opc5, opc6, opc7, opc8, opc9, opc10;
        CheckBox op1, op2, op3, op4, op5, op6, op7, op8, op9, op10;
        Button btnResponder, btnReiniciar;

        public QuestionViewHolder(View itemView) {
            super(itemView);

            txtPregunta = itemView.findViewById(R.id.txtPregunta);
            txtAbierta = itemView.findViewById(R.id.txtAbierta);
            rbgRespuestas = itemView.findViewById(R.id.rbgRespuestas);
            opc1 = itemView.findViewById(R.id.opc1);
            opc2 = itemView.findViewById(R.id.opc2);
            opc3 = itemView.findViewById(R.id.opc3);
            opc4 = itemView.findViewById(R.id.opc4);
            opc5 = itemView.findViewById(R.id.opc5);
            opc6 = itemView.findViewById(R.id.opc6);
            opc7 = itemView.findViewById(R.id.opc7);
            opc8 = itemView.findViewById(R.id.opc8);
            opc9 = itemView.findViewById(R.id.opc9);
            opc10 = itemView.findViewById(R.id.opc10);
            op1 = itemView.findViewById(R.id.op1);
            op2 = itemView.findViewById(R.id.op2);
            op3 = itemView.findViewById(R.id.op3);
            op4 = itemView.findViewById(R.id.op4);
            op5 = itemView.findViewById(R.id.op5);
            op6 = itemView.findViewById(R.id.op6);
            op7 = itemView.findViewById(R.id.op7);
            op8 = itemView.findViewById(R.id.op8);
            op9 = itemView.findViewById(R.id.op9);
            op10 = itemView.findViewById(R.id.op10);
            btnResponder = itemView.findViewById(R.id.btnResponder);
            btnReiniciar = itemView.findViewById(R.id.btnReiniciar);
        }
    }
}

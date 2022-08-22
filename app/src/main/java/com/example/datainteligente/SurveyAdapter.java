package com.example.datainteligente;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.SurveyViewHolder> {
    private Context context;
    private List<Survey> surveyList;
    private String usuario;

    public SurveyAdapter(Context context, List<Survey> surveyList) {
        this.context = context;
        this.surveyList = surveyList;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public SurveyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.survey_items, null);
        return new SurveyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SurveyViewHolder holder, int position){
        Survey encuesta = surveyList.get(position);
        String idEncuesta = String.valueOf(encuesta.getIdEncuesta());
        String fechaCreacion = encuesta.getFechaCreacion();

        holder.txtNombreEncuesta.setText(Html.fromHtml("<b>" + encuesta.getNombre() + "</b>"));
        // holder.txtEncuestaAp.setText();
        holder.txtDescEncuesta.setText(Html.fromHtml(encuesta.getDescripcion()));

        holder.btnRevisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), InfoActivity.class);
                i.putExtra("usuario", usuario);
                i.putExtra("idEncuesta", idEncuesta);
                i.putExtra("encuesta", encuesta.getNombre());
                view.getContext().startActivity(i);
                ((Activity) view.getContext()).finish();
            }
        });

        holder.btnAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SurveyActivity.class);
                i.putExtra("usuario", usuario);
                i.putExtra("idEncuesta", idEncuesta);
                i.putExtra("encuesta", encuesta.getNombre());
                view.getContext().startActivity(i);
                ((Activity) view.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return surveyList.size();
    }

    class SurveyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreEncuesta, txtEncuestaAp, txtDescEncuesta;
        Button btnRevisar, btnAplicar;

        public SurveyViewHolder(View itemView) {
            super(itemView);

            txtNombreEncuesta = itemView.findViewById(R.id.txtNomEncuesta);
            txtEncuestaAp = itemView.findViewById(R.id.txtEncuestasAp);
            txtDescEncuesta = itemView.findViewById(R.id.txtDescEncuesta);
            btnRevisar = itemView.findViewById(R.id.btnRevisar);
            btnAplicar = itemView.findViewById(R.id.btnAplicar);
        }
    }
}

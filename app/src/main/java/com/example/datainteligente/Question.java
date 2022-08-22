package com.example.datainteligente;

import java.util.List;

public class Question {
    private int idEncuesta;
    private int idPregunta;
    private int tipo;
    private String pregunta, opc1, opc2, opc3, opc4, opc5, opc6, opc7, opc8, opc9, opc10;
    // private List<Answers> respuestas;

    public Question() {

    }

    public Question(int idEncuesta, int idPregunta, int tipo, String pregunta, String opc1, String opc2, String opc3, String opc4, String opc5, String opc6, String opc7, String opc8, String opc9, String opc10) {
        this.idEncuesta = idEncuesta;
        this.idPregunta = idPregunta;
        this.tipo = tipo;
        this.pregunta = pregunta;
        this.opc1 = opc1;
        this.opc2 = opc2;
        this.opc3 = opc3;
        this.opc4 = opc4;
        this.opc5 = opc5;
        this.opc6 = opc6;
        this.opc7 = opc7;
        this.opc8 = opc8;
        this.opc9 = opc9;
        this.opc10 = opc10;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpc1() {
        return opc1;
    }

    public void setOpc1(String opc1) {
        this.opc1 = opc1;
    }

    public String getOpc2() {
        return opc2;
    }

    public void setOpc2(String opc2) {
        this.opc2 = opc2;
    }

    public String getOpc3() {
        return opc3;
    }

    public void setOpc3(String opc3) {
        this.opc3 = opc3;
    }

    public String getOpc4() {
        return opc4;
    }

    public void setOpc4(String opc4) {
        this.opc4 = opc4;
    }

    public String getOpc5() {
        return opc5;
    }

    public void setOpc5(String opc5) {
        this.opc5 = opc5;
    }

    public String getOpc6() {
        return opc6;
    }

    public void setOpc6(String opc6) {
        this.opc6 = opc6;
    }

    public String getOpc7() {
        return opc7;
    }

    public void setOpc7(String opc7) {
        this.opc7 = opc7;
    }

    public String getOpc8() {
        return opc8;
    }

    public void setOpc8(String opc8) {
        this.opc8 = opc8;
    }

    public String getOpc9() {
        return opc9;
    }

    public void setOpc9(String opc9) {
        this.opc9 = opc9;
    }

    public String getOpc10() {
        return opc10;
    }

    public void setOpc10(String opc10) {
        this.opc10 = opc10;
    }
}

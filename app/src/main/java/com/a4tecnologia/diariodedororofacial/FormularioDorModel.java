package com.a4tecnologia.diariodedororofacial;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LiliPaulucci on 26/12/16.
 */

public class FormularioDorModel {

    public UUID ID;

    public String PACIENTE_ID;

    public String DATAHORA;

    public String INTENSIDADE;

    public String TIPO;

    //[Display(Name = "Tem percebido Aperto Dentário?")]
    public String APERTO_DENTARIO;

    //[Display(Name = "Sentiu algo antes da dor?")]
    public String SENTIU_ANTES;

    //[Display(Name = "Sentiu algo junto a dor?")]
    public String SENTIU_DURANTE;

    //[Display(Name = "Passando por estresse emocional?")]
    public String ESTRESSE ;

     //[Display(Name = "Qualidade do sono")]
    public String NOITE_SONO;

    //[Display(Name = "Período Menstrual?")]
    public String P_MENSTRUAL;

    //[Display(Name = "Seguindo Orientações?")]
    public String ORIENTACOES;

    //[Display(Name = "Observação")]
    public String INFORMACAO;

    public String LOCALIZACAO_DOR;


    FormularioDorModel(){}

    public void setValue(String nameProp, String value){
        for (Field f : getClass().getDeclaredFields()) {
            if (f.getName().equals(nameProp)) {
                try {
                    f.set(this, value);
                }
                catch (Exception ex){
                    //Crashlytics.logException(ex);
                }
            }
        }
    }

    public String toJson() throws IllegalAccessException {

        JSONObject jsonObject= new JSONObject();

        for (Field f : getClass().getDeclaredFields()) {
            Object value = f.get(this);
            if (value != null) {
                try {
                    jsonObject.put(f.getName(), value.toString());
                } catch (JSONException e) {
                    return "ERRO";
                }
            }
        }

        return jsonObject.toString();
    }

    public String montaFeedBackFormulario() {

        String retorno = "";

        String retornoDor = "";

        if (this.INTENSIDADE != null) {
            switch (this.INTENSIDADE) {
                case "0":
                    retornoDor = "Dor intensidade 0 - Ficamos felizes com níveis baixo de dor !";
                    break;
                case "1":
                    retornoDor = "Dor intensidade 1 - Ficamos felizes com níveis baixo de dor !";
                    break;
                case "2":
                    retornoDor = "Dor intensidade 2 - Seu quadro do Dor se encontra em limites satisfatórios! Continue seguindo as orientações!";
                    break;
                case "3":
                    retornoDor = "Dor intensidade 3 - Estamos com níveis baixos de dor, precisamos da sua colaboração para atingirmos nosso objetivo!";
                    break;
                case "4":
                    retornoDor = "Dor intensidade 4 - Vamos lá! Precisamos da sua colaboração para melhorar os níveis de dor.";
                    break;
                case "5":
                    retornoDor = "Dor intensidade 5 - Tem observado hábito de morder objetos? Podemos ter níveis de dor menor que 5!";
                    break;
                case "6":
                    retornoDor = "Dor intensidade 6 - Que tal observarmos a presença de apartamento dentário para diminuirmos essa dor?";
                    break;
                case "7":
                    retornoDor = "Dor intensidade 7 - Observamos que você está com dor muito acentuada. Siga corretamente as orientações!";
                    break;
                case "8":
                    retornoDor = "Dor intensidade 8 - Verificamos uma dor intensa. Esperamos que se sinta melhor!";
                    break;
                case "9":
                    retornoDor = "Dor intensidade 9 - Dor 9 é incapacitante, precisamos investigar profundamente isso!";
                    break;
                case "10":
                    retornoDor = "Dor intensidade 10 - Dor nessa intensidade normalmente não são característica de Dtm. Converse conosco na próxima consulta.";
                    break;
                default:
                    retornoDor = "";
                    break;
            }
        }

        String retornoEstresse = "";

        if (this.ESTRESSE != null) {
            switch (this.ESTRESSE) {
                case "0":
                    retornoEstresse = "Passando por estresse emocional? Sim - Já fez sua atividade física hoje? Atividade física melhora bastante os níveis de estresses";
                    break;
                case "1":
                    retornoEstresse = "Ficamos felizes que não esteja estressado ! :D ";
                    break;
                default:
                    retornoEstresse = "";
                    break;
            }
        }

        String retornoSono = "";

        if (this.NOITE_SONO != null) {
            switch (this.NOITE_SONO) {
                case "O":
                    retornoSono = "Ótimo sono? :D Bons sonhos !";
                    break;
                case "R":
                    retornoSono = "Um boa noite de sono é fundamente para mantermos um limiar de dor aceitável.";
                    break;
                case "P":
                    retornoSono = "Vamos rever o que podemos fazer pra melhorar esse sono?";
                    break;
            }
        }

        String retornoPeriodoMenstrual = "";

        if (this.P_MENSTRUAL != null) {
            switch (this.P_MENSTRUAL) {
                case "S":
                        retornoPeriodoMenstrual = "Próximo ao período menstrual o limiar de dor costuma diminuir. Lembre-se de nos relatar na próxima consulta.";
                        break;
                    default:
                        retornoPeriodoMenstrual = "";
                        break;
                }
            }

        String retornoOrientacoes = "";

        if (this.ORIENTACOES != null) {
            switch (this.ORIENTACOES) {
                        case "S":
                            retornoOrientacoes = "Parabéns por seguir nossas orientações!\n" +
                                    "Isso aí!!! Seguir corretamente as orientações é um grande passo para o sucesso do tratamento!";
                            break;
                        case "N":
                            retornoOrientacoes = "Precisamos muito da sua colaboração! Vamos lá, siga corretamente as orientações!\n" +
                                    "Sem colaboração não atingiremos nosso objetivo ! Vamos lá!";
                            break;
                        case "V":
                            retornoOrientacoes = "Olá, precisamos que siga as orientações SEMPRE ! ;)";
                            break;
                        default:
                            retornoOrientacoes = "";
                            break;
            }
        }

        if (retornoDor != "")
            retorno += (retornoDor + "\n\n\n\n");

        if (retornoEstresse != "")
            retorno += (retornoEstresse + "\n\n\n\n");

        if (retornoSono != "")
            retorno += (retornoSono + "\n\n\n\n");

        if (retornoPeriodoMenstrual != "")
            retorno += (retornoPeriodoMenstrual + "\n\n\n\n");

        if (retornoOrientacoes != "")
            retorno += (retornoOrientacoes + "\n");


        return retorno;
    }
}

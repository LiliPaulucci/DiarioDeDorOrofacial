package com.a4tecnologia.diariodedororofacial;

/**
 * Created by LiliPaulucci on 20/12/16.
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class WebService {

    //private static String PATH_WSDTM = "http://plataformadtm.azurewebsites.net/Paciente/ValidaCodigoPaciente?codigoPaciente=ABCD";
    private static String PATH_WSDTM = "http://plataformadtm.azurewebsites.net/Paciente/";
    private static String PATH_VALIDA = "ValidaCodigoPaciente";
    private static String PATH_FORM = "InsereFormularioDor";

    public static String dtmWSValidarPaciente(String codigo){
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("codigoPaciente", codigo));

        StringBuilder requestUrl = new StringBuilder(PATH_WSDTM + PATH_VALIDA);
        String querystring = URLEncodedUtils.format(param, "utf-8");
        requestUrl.append("?");
        requestUrl.append(querystring);

        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;

        try {
            HttpGet get = new HttpGet(requestUrl.toString());
            response = httpclient.execute(get);

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");

            return responseString;
        } catch (IOException e) {
            String returnexecao = e.getMessage();
        } catch (Exception e) {
            String returnexecao = e.getMessage();
        }

        return "";
    }

    public static String dtmWSEnviaFormularioDor(String id, FormularioDorModel form) throws IllegalAccessException {
        String jsonForm = "";
        try {
            jsonForm = form.toJson();
        }
        catch (Exception ex){
            return "ERRO";
        }

        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("idPaciente", id));
        param.add(new BasicNameValuePair("jsonFormulario", jsonForm));

        StringBuilder requestUrl = new StringBuilder(PATH_WSDTM + PATH_FORM);
        String querystring = URLEncodedUtils.format(param, "utf-8");
        requestUrl.append("?");
        requestUrl.append(querystring);

        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;

        try {
            HttpGet get = new HttpGet(requestUrl.toString());
            response = httpclient.execute(get);

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");

            return responseString;
        } catch (IOException e) {
            String returnexecao = e.getMessage();
        } catch (Exception e) {
            String returnexecao = e.getMessage();
        }

        return "ERRO";
    }
}

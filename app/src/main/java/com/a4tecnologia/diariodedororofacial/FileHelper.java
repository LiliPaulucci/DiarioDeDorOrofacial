package com.a4tecnologia.diariodedororofacial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by LiliPaulucci on 14/02/17.
 */

public class FileHelper {

    public static String VerificaArquivoInfo(File folder) {

        try{
            //File arquivo = new File(folder, "Info.txt");
            if (existeArquivoInfo(folder)) {
                try{
                    JSONObject obj = retornaInfoPacienteJSON(folder);
                    if (obj != null) {
                        String codigo = obj.optString("codigo");
                        return codigo;
                    }
                }
                catch (Exception ex) { }
            }
        } catch (Exception tex){}
        return null;
    }

    public static JSONObject retornaInfoPacienteJSON(File pasta) {
        try {
            File arquivo = new File(pasta, "Info.txt");
            FileInputStream fin = new FileInputStream(arquivo);
            String informacaoJson = convertStreamToString(fin);
            fin.close();

            if (informacaoJson != null && (informacaoJson != "")) {
                JSONObject objeto = new JSONObject(informacaoJson);
                return objeto;
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static Boolean existeArquivoInfo(File folder) {
        try {
            File arquivo = new File(folder, "Info.txt");
            if (arquivo.exists() && arquivo.isFile()) {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    public static Boolean gravaInformacoes (String codigoPaciente, File folder) {
        Boolean retorno = true;
        File arquivo = new File(folder, "Info.txt");
        JSONObject obj = retornaJSON(codigoPaciente);
        WriteJSONtoFile(obj, arquivo);
        return retorno;
    }

    public static JSONObject retornaJSON (String codigoPaciente){
        JSONObject infoPaciente = new JSONObject();
        try {
            infoPaciente.put("codigo", codigoPaciente);
        } catch (JSONException e) {}
        return infoPaciente;
    }

    public static void WriteJSONtoFile(JSONObject obj, File arquivo) {
        arquivo.setReadable(true, false);
        arquivo.setWritable(true, false);
        try {
            arquivo.createNewFile();
        } catch (IOException e){}

        String json = "";
        try {
            json = obj.toString(4);
        } catch (JSONException e){}

        FileWriter file;
        try {
            file = new FileWriter(arquivo);
            file.write(json);
            file.flush();
            file.close();
        } catch (IOException e) {}
    }
}

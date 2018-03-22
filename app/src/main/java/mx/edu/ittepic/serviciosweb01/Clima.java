package mx.edu.ittepic.serviciosweb01;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Bryan on 20/03/2018.
 */

public class Clima extends AsyncTask <Void, Void, Void> {

    String data ="";
    String sky = "";
    int temp, hum = 0;

    @Override
    protected void onPreExecute() {
        //Toast.makeText(s, "Conectando", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Tepic,mx&APPID=33f7440fc739919ecc7d84930cf49a14");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject JA = new JSONObject(data);
            for(int i = 0 ;i < JA.length(); i++){
                JSONObject JO = (JSONObject) JA.getJSONObject("main");
                JSONArray JS = (JSONArray) JA.getJSONArray("weather");
                JSONObject JAS = (JSONObject) JS.getJSONObject(0);
                //JSONObject JASO = (JSONObject) JAS.getJSONObject("description");
                temp =  (int)JO.getDouble("temp");
                hum = JO.getInt("humidity");
                sky = JAS.getString("description");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }//doInBackground

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.txtMostrar.setText("Temperatura: "+(this.temp-273)+"°C");
        MainActivity.txtHumedad.setText("Humedad: "+this.hum+" %");
        MainActivity.txtSky.setText(sky);
    }//onPostExecute

}

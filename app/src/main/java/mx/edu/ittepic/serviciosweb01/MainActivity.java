package mx.edu.ittepic.serviciosweb01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static TextView txtMostrar, txtHumedad, txtSky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMostrar = findViewById(R.id.txtClima);
        txtHumedad = findViewById(R.id.txtHum);
        txtSky = findViewById(R.id.txtSky);
    }

    public void obtener(View view) {
        new Clima().execute();
    }
}

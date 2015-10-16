package mx.leyenda.leyendabeta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by samo92 on 15/10/2015.
 */
public class LoadLeyenda extends AppCompatActivity{

    private Spinner opGenero;

    final String[] genero =
            new String[]{"comedia","terror","suspenso","amor","accion","urbano"};

    ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadleyenda_view);

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genero);

        opGenero = (Spinner)findViewById(R.id.gen_leyenda);

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        opGenero.setAdapter(adaptador);

        opGenero.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {

                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }

                });

    }


}

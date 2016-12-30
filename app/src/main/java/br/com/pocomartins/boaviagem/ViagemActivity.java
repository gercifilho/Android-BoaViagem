package br.com.pocomartins.boaviagem;



import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;



import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Poço Martins on 2/13/2016.
 */
public class ViagemActivity extends Activity {
    private int ano, mes, dia;
    private Button dataChegada;
    private Button dataSaida;
    private String dataselecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viagem);

        dataChegada = (Button) findViewById(R.id.dataChegada);
        dataSaida = (Button) findViewById(R.id.dataSaida);
    }

    public void selecionarData(View view) {
        showDialog(view.getId());
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar c = Calendar.getInstance();
        ano = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);
        switch (id) {
            case R.id.dataChegada:
                return new DatePickerDialog(this, datachegadaListener, ano, mes, dia);
            case R.id.dataSaida:
                return new DatePickerDialog(this, dataSaidalistener, ano, mes, dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datachegadaListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view,
                              int year, int monthOfYear, int dayOfMonth) {

            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            dataChegada.setText(dia + "/" + (mes + 1) + "/" + ano);
        }
    };
    private DatePickerDialog.OnDateSetListener dataSaidalistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view,
                              int year, int monthOfYear, int dayOfMonth) {

            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            dataSaida.setText(dia + "/" + (mes + 1) + "/" + ano);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.viagem_menu, menu);
        return true;
    };

    @Override
    public boolean onMenuItemSelected(int featureId,
                                      MenuItem item) {
        switch (item.getItemId()) {
            case R.id.novo_gasto:
                startActivity(new Intent(this,
                        GastoActivity.class));
                return true;
            case R.id.remover:
//remover viagem do banco de dados
                return true;
            default:
                return super.onMenuItemSelected(featureId, item);
        }
    }
}




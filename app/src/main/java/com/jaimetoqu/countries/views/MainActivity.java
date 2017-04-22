package com.jaimetoqu.countries.views;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jaimetoqu.countries.R;
import com.jaimetoqu.countries.background.GetCountryCode;
import com.jaimetoqu.countries.models.Wrapper;

public class MainActivity extends AppCompatActivity implements CodeCallback {

    private EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Paises");

        userInput = (EditText) findViewById(R.id.codeEt);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String country = userInput.getText().toString();
                new CodeValidation(MainActivity.this).init(country);

            }
        });
    }

    @Override
    public void code() {
        new GetCountry().execute(userInput.getText().toString());
    }

    @Override
    public void noData() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Faltan datos!").setMessage("Favor ingresar codigo de pais").show();
    }

    private class GetCountry extends GetCountryCode {
        @Override
        protected void onPostExecute(Wrapper wrapper) {
            if (wrapper != null) {
                TextView name = (TextView) findViewById(R.id.nameTv);
                TextView capital = (TextView) findViewById(R.id.capitalTv);
                TextView region = (TextView) findViewById(R.id.regionTv);
                TextView language = (TextView) findViewById(R.id.languageTv);
                name.setText(wrapper.getName());
                capital.setText(wrapper.getCapital());
                region.setText(wrapper.getRegion());
                String langs = "";
                for (String lang : wrapper.getLanguages()){
                    langs += lang + " ";
                    language.setText(lang);
                }
            }
        }
    }
}

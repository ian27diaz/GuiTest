package com.iteso.guitest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    CheckedTextView checkedTextView ;
    EditText editTextName;
    EditText editTextPhone;
    Spinner spinner;
    RadioButton radioButton1;
    RadioButton radioButton2 ;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoTV);
        checkedTextView = (CheckedTextView) findViewById(R.id.cTextView);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        spinner = (Spinner) findViewById(R.id.spinner_escolaridad);
        radioButton1 = (RadioButton) findViewById(R.id.radioB1);
        radioButton2 = (RadioButton) findViewById(R.id.radioB2);
        button2 = (Button) findViewById(R.id.button2);

        String[] books = getResources().getStringArray(R.array.books);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, books);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.showDropDown();
            }
        });

        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedTextView.isChecked())
                    checkedTextView.setChecked(false);
                else
                    checkedTextView.setChecked(true);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMain.this);
                builder.setMessage("¿Desea limpiar el contenido?").setTitle("Seleccione un opción");
                // Add the buttons
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        editTextName.setText("");
                        editTextPhone.setText("");
                        spinner.setSelection(0);
                        radioButton1.setChecked(true);
                        radioButton2.setChecked(false);
                        autoCompleteTextView.setText("");
                        checkedTextView.setChecked(false);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        return;
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    public void clean() {
        editTextName.setText("");
        editTextPhone.setText("");
        spinner.setSelection(0);
        radioButton1.setChecked(true);
        radioButton2.setChecked(false);
        autoCompleteTextView.setText("");
        checkedTextView.setChecked(false);
        return;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_1:
                String gender, bool;
                String[] books = getResources().getStringArray(R.array.books);
                String message = "Nombre: " + editTextName.getText().toString() + "\n" +
                                 "Teléfono: " + editTextPhone.getText().toString() + "\n" +
                                 "Escolaridad: " + spinner.getSelectedItem().toString() + "\n" +
                                 "Género: " + (gender = radioButton1.isChecked() ? radioButton1.getText().toString() :
                                               radioButton2.getText().toString()) + "\n" +
                                 "Practica Deportes: " + (bool = checkedTextView.isChecked() ? "Si" : "No");
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                clean();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

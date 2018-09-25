package com.example.hades44.nav_drawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    EditText correo;
    EditText contraseña;
    Button boton;
    RequestQueue request;
    JsonObjectRequest JsonObjectRequest;
    CheckBox recordar;
    String correos;
    String contraseñas;
    String correo1;
    String contraseña1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo= (EditText) findViewById(R.id.editText);
        contraseña= (EditText) findViewById(R.id.editText2);
        boton= (Button) findViewById(R.id.boton);
        recordar= (CheckBox) findViewById(R.id.checkbox);
        request= Volley.newRequestQueue(this.getBaseContext());

        SharedPreferences pre=getSharedPreferences("usuario", getBaseContext().MODE_PRIVATE);

        correo1 = pre.getString("usuario"," ");
        contraseña1 = pre.getString("contraseña"," ");

        correo.setText(correo1);
        contraseña.setText(contraseña1);

    }

    public void boton(View view) {

        Toast.makeText(this,"boton",Toast.LENGTH_SHORT).show();
        if (recordar.isChecked())
        {
            SharedPreferences pre=getSharedPreferences("usuario", getBaseContext().MODE_PRIVATE);
            SharedPreferences.Editor editor=pre.edit();
            editor.putString("usuario",correo.getText().toString());
            editor.putString("contraseña",contraseña.getText().toString());
            Toast.makeText(this,"logeado",Toast.LENGTH_SHORT).show();
            editor.commit();
        }
        cargarWebService();
    }

    public void cargarWebService()
    {
        String url="http://192.168.1.8:8080/SENA/QUINTO%20TRIMESTRE/ANDROID/ACTIVIDADES%20DE%20EVALUACION/ACTIVIDAD%204/valida.php?cor="+correo.getText().toString()+
                "&con="+contraseña.getText().toString();

        url.replace(" ","%20");

        JsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(JsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this,"Correo o contraseña incorrectas",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this,"exito",Toast.LENGTH_SHORT).show();
        if(response != null)
        {
            Intent inte=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(inte);
        }


    }
}

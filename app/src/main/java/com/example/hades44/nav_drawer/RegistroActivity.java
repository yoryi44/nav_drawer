package com.example.hades44.nav_drawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RegistroActivity extends AppCompatActivity implements Response.ErrorListener,Response.Listener<JSONObject> {

    EditText correo;
    EditText contraseña;
    RequestQueue request;
    JsonObjectRequest JsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        correo= (EditText) findViewById(R.id.editText);
        contraseña= (EditText) findViewById(R.id.editText2);
        request= Volley.newRequestQueue(this.getBaseContext());
    }

    public void cargarWebService()
    {
        String url="http://192.168.1.8:8080/SENA/QUINTO%20TRIMESTRE/ANDROID/ACTIVIDADES%20DE%20EVALUACION/ACTIVIDAD%204/registro.php?cor="+correo.getText().toString()+
                "&con="+contraseña.getText().toString();

        url.replace(" ","%20");

        JsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null, (Response.Listener<JSONObject>) this,this);
        request.add(JsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"errot",Toast.LENGTH_SHORT).show();
        Intent inte=new Intent(RegistroActivity.this,LoginActivity.class);
        startActivity(inte);
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this,"exito",Toast.LENGTH_SHORT).show();
    }

    public void boton(View view) {
        cargarWebService();
    }
}

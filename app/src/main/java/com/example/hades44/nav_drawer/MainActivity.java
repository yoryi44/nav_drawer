package com.example.hades44.nav_drawer;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,InicioFragment.OnFragmentInteractionListener,
        PaquetesFragment.OnFragmentInteractionListener,Paquetes1Fragment.OnFragmentInteractionListener,
        Paquetes3Fragment.OnFragmentInteractionListener,RusiaFragment.OnFragmentInteractionListener,
        Rusia1Fragment.OnFragmentInteractionListener,Rusia2Fragment.OnFragmentInteractionListener,
        ConJaponFragment.OnFragmentInteractionListener,ConRusiaFragment.OnFragmentInteractionListener{

    Button boton;
    Button boton1;
    Button boton2;
    Button boton3;
    Button boton4;
    Button boton6;
    Button boton7;
    String correo1=" ";
    String contraseña1=" ";
    MenuItem rusia;
    NavigationView nav;
    Menu nav_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        boton=(Button) findViewById(R.id.Button);
        boton1=(Button)findViewById(R.id.Button1);
        boton2=(Button)findViewById(R.id.Button2);
        boton3=(Button)findViewById(R.id.Button3);
        boton4=(Button)findViewById(R.id.Button4);
        boton6=(Button)findViewById(R.id.Button6);
        boton7=(Button)findViewById(R.id.Button7);


        nav=(NavigationView)findViewById(R.id.nav_view);
        nav_menu=nav.getMenu();

        boton.setVisibility(View.GONE);
        boton2.setVisibility(View.GONE);
        boton3.setVisibility(View.GONE);
        boton4.setVisibility(View.GONE);



        SharedPreferences pre=getSharedPreferences("usuario", getBaseContext().MODE_PRIVATE);

        correo1 = pre.getString("usuario"," ");
        contraseña1 = pre.getString("contraseña"," ");

        if(correo1==" ")
        {
            boton6.setVisibility(View.VISIBLE);
            boton7.setVisibility(View.VISIBLE);
            boton1.setVisibility(View.GONE);
            nav_menu.findItem(R.id.perfil).setVisible(false);
        }
        else {
            boton6.setVisibility(View.GONE);
            boton7.setVisibility(View.GONE);
            boton1.setVisibility(View.VISIBLE);
            nav_menu.findItem(R.id.perfil).setVisible(true);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        InicioFragment fragment = new InicioFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main,fragment).commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ResourceType")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.support.v4.app.Fragment mifragment=null;
        boolean ban=false;

        if (id == R.id.Inicio) {
            mifragment=new InicioFragment();
            ban=true;
            boton.setVisibility(View.GONE);
            boton4.setVisibility(View.GONE);
            if(correo1==" ")
            {
                boton6.setVisibility(View.VISIBLE);
                boton7.setVisibility(View.VISIBLE);
                boton1.setVisibility(View.GONE);
            }
            else {
                boton6.setVisibility(View.GONE);
                boton7.setVisibility(View.GONE);
                boton1.setVisibility(View.VISIBLE);
            }
        }
        if (id == R.id.perfil) {
            mifragment=new PerfilFragment();
            ban=true;
            boton.setVisibility(View.GONE);
            boton4.setVisibility(View.GONE);
            boton6.setVisibility(View.GONE);
            boton7.setVisibility(View.GONE);
            boton1.setVisibility(View.GONE);

        }else if (id == R.id.Japon) {
            mifragment=new ConJaponFragment();
            ban=true;
            if(correo1==" ")
            {
                boton4.setVisibility(View.VISIBLE);
                boton2.setVisibility(View.GONE);
            }
            else {

                boton4.setVisibility(View.GONE);
                boton2.setVisibility(View.VISIBLE);
            }

            boton1.setVisibility(View.GONE);
            boton3.setVisibility(View.GONE);
            boton.setVisibility(View.GONE);
            boton6.setVisibility(View.GONE);
            boton7.setVisibility(View.GONE);
        } else if (id == R.id.rusia) {
            mifragment=new ConRusiaFragment();
            ban=true;
            if(correo1==" ")
            {
                boton4.setVisibility(View.VISIBLE);
                boton3.setVisibility(View.GONE);
            }
            else {

                boton4.setVisibility(View.GONE);
                boton3.setVisibility(View.VISIBLE);
            }
            boton.setVisibility(View.GONE);
            boton2.setVisibility(View.GONE);
            boton1.setVisibility(View.GONE);
            boton6.setVisibility(View.GONE);
            boton7.setVisibility(View.GONE);

        }else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if(ban==true)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,mifragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void boton(View view) {
        Intent intt = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intt);
    }

    public void boton1(View view) {
        SharedPreferences pre=getSharedPreferences("usuario", getBaseContext().MODE_PRIVATE);
        SharedPreferences.Editor editor=pre.edit();
        editor.putString("usuario",null);
        editor.putString("contraseña",null);
        correo1=" ";
        contraseña1=" ";
        Toast.makeText(this,"Vuelve pronto",Toast.LENGTH_SHORT).show();
        editor.commit();
        boton6.setVisibility(View.VISIBLE);
        boton7.setVisibility(View.VISIBLE);
        boton1.setVisibility(View.GONE);
        nav_menu.findItem(R.id.perfil).setVisible(false);
    }

    public void boton2(View view) {
        Intent intt = new Intent(MainActivity.this, ReservaJapon.class);
        startActivity(intt);
    }

    public void boton3(View view) {
        Intent intt = new Intent(MainActivity.this, ReservaRusia.class);
        startActivity(intt);
    }

    public void boton4(View view) {
        Intent intt = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intt);
    }

    public void boton5(View view) {
        Rusia2Fragment fragment= new Rusia2Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void boton6(View view) {
        Intent intt=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intt);
    }

    public void boton7(View view) {
        Intent intt=new Intent(MainActivity.this,RegistroActivity.class);
        startActivity(intt);
    }
}

package com.example.hades44.nav_drawer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SeccionesAdapter extends FragmentStatePagerAdapter{

    private final List<android.support.v4.app.Fragment> listaFragments=new ArrayList<android.support.v4.app.Fragment>();
    private  final List<String> listaTitulos=new ArrayList<>();

    public SeccionesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return listaTitulos.get(position);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return listaFragments.get(position);
    }

    @Override
    public int getCount() {

        return listaFragments.size();
    }

    public void addFragment(Fragment fragment, String titulo)
    {
        listaFragments.add(fragment);
        listaTitulos.add(titulo);

    }

}

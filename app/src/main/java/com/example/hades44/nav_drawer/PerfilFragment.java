package com.example.hades44.nav_drawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerfilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment implements Response.ErrorListener,Response.Listener<JSONObject> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    String correo;
    String contraseña;
    Button boton;
    ArrayList<Reservas> listaReservas;
    RequestQueue request;
    JsonObjectRequest JsonObjectRequest;
    RecyclerView recycler;

    private InicioFragment.OnFragmentInteractionListener mListener;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_perfil, container, false);

        boton=(Button) vista.findViewById(R.id.recycler);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"boton",Toast.LENGTH_SHORT).show();
                SharedPreferences pre=getActivity().getSharedPreferences("usuario", getContext().MODE_PRIVATE);

                correo = pre.getString("usuario"," ");
                contraseña = pre.getString("contraseña"," ");

                cargarWeb();
            }
        });

        listaReservas=new ArrayList<>();
        recycler=(RecyclerView)vista.findViewById(R.id.recyclerr);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycler.setHasFixedSize(true);
        request= Volley.newRequestQueue(this.getContext());

        return vista;

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InicioFragment.OnFragmentInteractionListener) {
            mListener = (InicioFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void cargarWeb()
    {
        String url="http://192.168.1.8:8080/SENA/QUINTO%20TRIMESTRE/ANDROID/ACTIVIDADES%20DE%20EVALUACION/ACTIVIDAD%204/reservaUsu.php?cor="+correo;

        url.replace(" ","%20");

        JsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null, (Response.Listener<JSONObject>) this,this);
        request.add(JsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No tienes reservas",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Reservas reserva=null;

        try {
            JSONArray json=response.optJSONArray("documento");

            for (int i=0;i<json.length();i++)
            {
                reserva=new Reservas();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                reserva.setDestino(jsonObject.optString("destino"));
                reserva.setInicio(jsonObject.optString("inicio"));
                reserva.setFin(jsonObject.optString("regreso"));
                reserva.setId(jsonObject.optString("id_reserva"));
                Toast.makeText(getContext(),"exito:"+reserva.getId(),Toast.LENGTH_SHORT).show();
                listaReservas.add(reserva);
            }
            AdaptadorReservas res=new AdaptadorReservas(listaReservas);
            recycler.setAdapter(res);
        }

        catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"pm:",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

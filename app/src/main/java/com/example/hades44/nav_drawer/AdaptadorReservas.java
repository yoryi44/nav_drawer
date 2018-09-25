package com.example.hades44.nav_drawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorReservas extends RecyclerView.Adapter<AdaptadorReservas.ViewHolderReservas>{

    ArrayList<Reservas> listareservas;

    public AdaptadorReservas(ArrayList<Reservas> listareservas) {
        this.listareservas = listareservas;
    }

    @Override
    public ViewHolderReservas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.muestra_reservas_usuario,null,false);
        return new ViewHolderReservas(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderReservas holder, int position) {
        holder.dest.setText(listareservas.get(position).getDestino().toString());
        holder.ini.setText(listareservas.get(position).getInicio().toString());
        holder.id.setText(listareservas.get(position).getId().toString());
        holder.fin.setText(listareservas.get(position).getFin().toString());
    }

    @Override
    public int getItemCount() {
        if (listareservas!=null)
        {
            return listareservas.size();
        }
        else
        {
            return 0;
        }
    }

    public class ViewHolderReservas extends RecyclerView.ViewHolder {
        TextView dest,ini,fin,id;

        public ViewHolderReservas(View itemView) {
            super(itemView);
            dest=(TextView) itemView.findViewById(R.id.destino);
            ini=(TextView) itemView.findViewById(R.id.inicio);
            fin=(TextView) itemView.findViewById(R.id.fin);
            id=(TextView)itemView.findViewById(R.id.id);

        }
    }
}

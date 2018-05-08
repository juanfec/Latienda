package com.amovil.latienda.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amovil.latienda.Historico;
import com.amovil.latienda.R;
import com.amovil.latienda.RecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Historicos extends Fragment {

    private RecyclerView recyclerViewHistoria;
    private RecyclerAdapter myAdapter;
    private List<Historico> listaHistoricos = new ArrayList<>();


    public Historicos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_historicos, container, false);

        recyclerViewHistoria = v.findViewById(R.id.my_recycler_view_historia);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(v.getContext());
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewHistoria.setLayoutManager(layoutmanager);

        recyclerViewHistoria.setItemAnimator(new DefaultItemAnimator());//animaciones del recyclerview

        myAdapter = new RecyclerAdapter(v.getContext(),listaHistoricos);//pasamos datos iniciales al recycler para datos posteriores llamar .notifyDataSetChanged()
        recyclerViewHistoria.setAdapter(myAdapter);

        datosIniciales();


        return v;

    }

    public void datosIniciales() {
        ArrayList<HashMap> productos = new ArrayList<>();
        HashMap<String,String> a = new HashMap<>();
        a.put("producto","crispetas");
        a.put("precio","5000");
        a.put("unidades","1");
        HashMap<String,String> b = new HashMap<>();
        b.put("producto","jugo");
        b.put("precio","500000");
        b.put("unidades","2");
        productos.add(b);
        productos.add(a);

        listaHistoricos.add(new Historico("12/04/2018","15000",productos));

        ArrayList<HashMap> productosb = new ArrayList<>();
        HashMap<String,String> c = new HashMap<>();
        c.put("producto","crispetas");
        c.put("precio","5000");
        c.put("unidades","1");
        HashMap<String,String> d = new HashMap<>();
        d.put("producto","jugo");
        d.put("precio","5000");
        d.put("unidades","3");
        productosb.add(d);
        productosb.add(c);

        listaHistoricos.add(new Historico("13/04/2018","20000",productosb));

        myAdapter.notifyDataSetChanged();

    }

}

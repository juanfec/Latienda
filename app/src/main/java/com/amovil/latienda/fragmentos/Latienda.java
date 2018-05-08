package com.amovil.latienda.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.amovil.latienda.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Latienda extends Fragment implements SearchView.OnQueryTextListener {


    private CardView mAseoHogar;

    public Latienda() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_latienda, container, false);

        mAseoHogar = v.findViewById(R.id.cardaseoparaelhogar);




        return v;
    }




    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}

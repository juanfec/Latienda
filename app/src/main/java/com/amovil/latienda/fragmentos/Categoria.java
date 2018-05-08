package com.amovil.latienda.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amovil.latienda.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Categoria extends Fragment {

    private LinearLayout aseoParaElHogar;
    private LinearLayout mostrarAseoParaElHogar;
    private TextView mTituloCategoria;
    private ImageView mFlechaCategoria;

    private String mCategoria;


    public Categoria() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategoria = getArguments().getString("categoria");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_categoria, container, false);

        mFlechaCategoria = v.findViewById(R.id.flechamenucategoria);
        mostrarAseoParaElHogar = v.findViewById(R.id.mostrarsubcategoria);
        aseoParaElHogar = v.findViewById(R.id.menucategoria);
        mTituloCategoria = v.findViewById(R.id.titulocategoria);
        switch (mCategoria){
            case "aseo":
                mTituloCategoria.setText(R.string.aseoparaelhogar);
                break;
            case "lacteos":
                mTituloCategoria.setText(R.string.lacteosyderivados);
                break;
            case "carnes":
                mTituloCategoria.setText(R.string.carnesfrias);
                break;
                default:
                    mTituloCategoria.setText(R.string.aseoparaelhogar);
        }


        //muestra u oculta el submenbu de aseo
        final Animation girar = AnimationUtils.loadAnimation(v.getContext(),R.anim.rotate180);
        final Animation girarback = AnimationUtils.loadAnimation(v.getContext(),R.anim.rotate180back);
        aseoParaElHogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mostrarAseoParaElHogar.getVisibility()==View.VISIBLE){
                    mostrarAseoParaElHogar.setVisibility(View.GONE);
                    mFlechaCategoria.startAnimation(girarback);
                }else{
                    mostrarAseoParaElHogar.setVisibility(View.VISIBLE);
                    mFlechaCategoria.startAnimation(girar);
                }


            }
        });


        return v;
    }

}

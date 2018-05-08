package com.amovil.latienda;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Juan Rueda on 4/04/2018.
 * juan.felipe.rueda@gmail.com
 * esta clase es necesaria para enviar datos al recyclerview
 * el metodo onBindViewHolder carga y actualiza los datos cada vez que sea necesario
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {


    private List<Historico> mListahistoricos;
    private Context mContext;
    private int mExpandedPosition = -1;
    private Animation startRotateAnimation ;
    private Animation startRotateAnimationBack ;


    public RecyclerAdapter(Context context, List<Historico> listahistoricos) {
        this.mContext = context;
        this.mListahistoricos = listahistoricos;
    }

    @Override
    public int getItemCount() {
        return mListahistoricos.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.historiaitem, parent, false);

        return new MyViewHolder(itemView);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        protected LinearLayout vProductos;
        protected TextView vTitulohistoria;
        protected TextView vTotal;
        protected LinearLayout vMuestraProductos;
        protected ImageButton vImagenFlecha;

        public MyViewHolder(View v) {
            super(v);
            vTitulohistoria = v.findViewById(R.id.tituloitemhistoria);
            vTotal = v.findViewById(R.id.totalhistoria);
            vProductos = v.findViewById(R.id.tablahistoria);
            vMuestraProductos = v.findViewById(R.id.mostrarproductos);
            vImagenFlecha = v.findViewById(R.id.flechahistoria);
        }

    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(final MyViewHolder itemsViewHolder, final int i) {
        Historico historico = mListahistoricos.get(i);
        itemsViewHolder.vTitulohistoria.setText(historico.getFecha());
        itemsViewHolder.vTotal.setText(historico.getTotal());
        ArrayList<HashMap> productos = historico.getProductos();
        if (itemsViewHolder.vProductos.getChildCount()>0){//borra los productos para no tener repetidos
            itemsViewHolder.vProductos.removeAllViews();
        }
        for (HashMap producto: productos) {//itera sobre la lista de productos y los guarda en un linearlayout que mas tarde integra en la app
            LinearLayout row = new LinearLayout(mContext);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            TextView nombreProducto = new TextView(mContext);
            nombreProducto.setText(producto.get("producto").toString());
            nombreProducto.setTextColor(Color.GRAY);
            nombreProducto.setMaxLines(1);
            nombreProducto.setLayoutParams(new LinearLayout.LayoutParams(
                            0,
                            LinearLayout.LayoutParams.WRAP_CONTENT,10
                    )
            );
            row.addView(nombreProducto);
            TextView unidadesProducto = new TextView(mContext);
            unidadesProducto.setText(producto.get("unidades").toString());
            unidadesProducto.setGravity(Gravity.RIGHT);
            unidadesProducto.setMaxLines(1);
            unidadesProducto.setTextColor(Color.GRAY);
            unidadesProducto.setLayoutParams(new LinearLayout.LayoutParams(
                            0,
                            LinearLayout.LayoutParams.WRAP_CONTENT,2
                    )
            );
            row.addView(unidadesProducto);
            TextView precioProducto = new TextView(mContext);
            precioProducto.setGravity(Gravity.RIGHT);
            precioProducto.setTextColor(Color.GRAY);
            precioProducto.setMaxLines(1);
            precioProducto.setText(producto.get("precio").toString());

            precioProducto.setLayoutParams(new LinearLayout.LayoutParams(
                            0,
                            LinearLayout.LayoutParams.WRAP_CONTENT,3
                    )
            );
            row.addView(precioProducto);
            itemsViewHolder.vProductos.addView(row);

        }

        /**
         * se encarga de expandir el item
         */
        final boolean isExpanded = i ==mExpandedPosition;
        itemsViewHolder.vMuestraProductos.setVisibility(isExpanded?View.VISIBLE:View.GONE);//muestra o oculta descripcion
        startRotateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.android_rotate_animation);
        startRotateAnimationBack = AnimationUtils.loadAnimation(mContext, R.anim.android_rotate_animation_back);
        itemsViewHolder.vImagenFlecha.startAnimation(isExpanded?startRotateAnimation:startRotateAnimationBack);
        //itemsViewHolder.vImagenFlecha.setRotation(isExpanded?90:0);//rota la flecha
        itemsViewHolder.itemView.setActivated(isExpanded);
        itemsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1:i;
                notifyItemChanged(i);
            }
        });
    }

}
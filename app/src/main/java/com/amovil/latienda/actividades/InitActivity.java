package com.amovil.latienda.actividades;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amovil.latienda.R;
import com.amovil.latienda.fragmentos.Categoria;
import com.amovil.latienda.fragmentos.CerrarSesion;
import com.amovil.latienda.fragmentos.Historicos;
import com.amovil.latienda.fragmentos.Latienda;
import com.amovil.latienda.fragmentos.Perfil;

/**
 * Created by Juan Rueda on 2/04/2018.
 * juan.felipe.rueda@gmail.com
 */

public class InitActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TextView mtitulo;
    private MenuItem carrito;
    private int idItemseleccionado ;
    private RelativeLayout mSearchView;
    private SearchView mSearchViewItem;
    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private boolean viewIsAtHome; // booleano para manejar el onbackpressed solo sale de la aplicacion si la vista esta en el fragment la tienda
    private NavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        //se usa toolbar necesaria para el navigation drawer y se centra el titulo
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mtitulo = findViewById(R.id.titulo);
        mSearchView = findViewById(R.id.search_layout);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setElevation(0);

        //establece el drawer layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //inicia el navigation drawer establece la posicion inicial en la tienda
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.nav_latienda);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, new Latienda()).commit();


        //quita el focus de el searchview para prevenir que abra el teclado al iniciar
        mSearchViewItem = findViewById(R.id.search_view);
        mSearchViewItem.setFocusable(false);
        //mSearchViewItem.clearFocus();


        //cambia el icono de el menu superior derecho
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_menu_pantalla_2);
        toolbar.setOverflowIcon(drawable);

       // SearchView search = (SearchView) item.getActionView();
        //search.setLayoutParams(new ActionBar.LayoutParams(Gravity.RIGHT));


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (!viewIsAtHome) { //if the current view is not the News fragment
            displayView(R.id.nav_latienda); //display the latienda fragment
            mNavigationView.getMenu().getItem(0).setChecked(true);//reestablece la posicion del menu a la del item Latienda
        } else {
            moveTaskToBack(true);  //If view is in News fragment, exit application
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.init, menu);
        carrito =  menu.findItem(R.id.action_settings);
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
            Intent tuspedidos = new Intent(InitActivity.this,TusPedidos.class);
            startActivity(tuspedidos);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        displayView(id); //metodo que se llama para cambiar el fragment
        return true;
    }


    /**
     * metodo usado para cambiar el fragmento de la vista principal, se usa este para que pueda ser llamado incluzo sin dar click
     * en el menu, si no tambien a travez de llamadas internas
     * @param viewId
     */
    public void displayView(int viewId){

        Fragment fragment = null;
        Class fragmentClass;

        switch(viewId) {
            case R.id.nav_latienda:
                fragmentClass = Latienda.class;
                carrito.setVisible(true);//muestra el carrito de compras solo si esta en Latienda
                mSearchView.setVisibility(View.VISIBLE);//muestra la opcion de buscar producto solo si esta en latienda
                mtitulo.setText(R.string.app_name);
                viewIsAtHome = true;
                break;
            case R.id.nav_historicos:
                fragmentClass = Historicos.class;
                carrito.setVisible(false);
                mSearchView.setVisibility(View.GONE);
                mtitulo.setText(R.string.historicos);
                viewIsAtHome = false;
                break;
            case R.id.nav_perfil:
                fragmentClass = Perfil.class;
                carrito.setVisible(false);
                mSearchView.setVisibility(View.GONE);
                mtitulo.setText(R.string.perfil);
                viewIsAtHome = false;
                break;
            case R.id.nav_cerrarsesion:
                fragmentClass = CerrarSesion.class;
                carrito.setVisible(false);
                mSearchView.setVisibility(View.GONE);
                mtitulo.setText(R.string.cerrarsesion);
                viewIsAtHome = false;
                break;
            default:
                fragmentClass = Latienda.class;
                viewIsAtHome = true;
                mtitulo.setText(R.string.app_name);
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();//crea un nuevo fragmento de la clase seleccionada
        } catch (Exception e) {
            e.printStackTrace();
        }


        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (!(idItemseleccionado == viewId)) {//previene lanzar animaciones cuando se quiere entrar al item en el que se esta actualmente
            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);//animaciones de salida y entrada de los fragment
        }

        ft.replace(R.id.frameLayout, fragment, "fragment");
        // Start the animated transition.
        ft.commit();

        idItemseleccionado = viewId; //guarda el item seleccionado del menu para prevenir futuras animaciones de ser necesario


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        }


    /**
     * cambia el fragmento de latienda por la categoria seleccionada(lacteos aseo etc) y envia a travez del bundle la opcion seleccio
     * nada para mostrar menu y productos correspondientes
     * @param v
     */

    public void cambiarfragment(View v){


        Bundle bundle = new Bundle();
        int id = v.getId();
        switch (id){
            case R.id.cardaseoparaelhogar:
                bundle.putString("categoria", "aseo");
            break;
            case R.id.cardlacteosyderivados:
                bundle.putString("categoria", "lacteos");
                break;
            case R.id.cardcarnesfriasyembutidos:
                bundle.putString("categoria", "carnes");
                break;
            default:
                bundle.putString("categoria", "aseo");
        }



        //cambia el fragmento cn sus animaciones correspondientes
        Fragment fragment2 = new Categoria();
        fragment2.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_up_up);
        fragmentTransaction.replace(R.id.frameLayout, fragment2);

        viewIsAtHome=false;
        idItemseleccionado=-1;
        fragmentTransaction.commit();

    }


}

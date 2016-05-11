package com.example.monica.practica4;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarFragment extends Fragment {
    private ListView peluchitosList;
    private String[] tagPeluchitos;
    Cursor c;
    public MostrarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_mostrar, container, false);
        tagPeluchitos = getResources().getStringArray(R.array.Tags);
        peluchitosList = (ListView) view.findViewById(R.id.ListaPeluchitos);

        PeluchitosDatos Peluchitos = new PeluchitosDatos(getActivity().getApplicationContext(), "Peluchitos", null, 1);
        SQLiteDatabase bd = Peluchitos.getWritableDatabase();

        c = bd.rawQuery("select * from Peluchitos",null);
        // Setear una sombra sobre el contenido principal cuando el drawer se despliegue
        // drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        ArrayList<DrawerItem> items = new ArrayList<DrawerItem>();
        items.add(new DrawerItem("id Nombre Cantidad Valor" , R.mipmap.ic_launcher));
        int cnt=0;
        if(c.moveToFirst()==true){
            while(cnt<c.getCount()){
                cnt=cnt+1;
                items.add(new DrawerItem("ID: "+ c.getString(0)+"\nNombre: "+c.getString(1)+"\nCantidad: "+c.getString(2)+"\nValor: $"+c.getString(3) , R.mipmap.ic_launcher));
                c.moveToNext();
            }
        }
        bd.close();

        peluchitosList.setAdapter(new DrawerListAdapter(getActivity().getApplicationContext(), items));
        return view;
    }

}

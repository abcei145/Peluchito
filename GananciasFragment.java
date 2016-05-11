package com.example.monica.practica4;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GananciasFragment extends Fragment {

    ListView peluchitosList;
    Cursor c;
    int Gananciatotal;
    public GananciasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_mostrar, container, false);

        peluchitosList = (ListView) view.findViewById(R.id.ListaPeluchitos);

        PeluchitosDatos Peluchitos = new PeluchitosDatos(getActivity().getApplicationContext(), "Peluchitos", null, 1);
        SQLiteDatabase bd = Peluchitos.getWritableDatabase();

        c = bd.rawQuery("select * from Peluchitos",null);
        // Setear una sombra sobre el contenido principal cuando el drawer se despliegue
        // drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        ArrayList<DrawerItem> items = new ArrayList<DrawerItem>();
        int cnt=0;
        Gananciatotal=0;
        if(c.moveToFirst()==true){
            while(cnt<c.getCount()){
                cnt=cnt+1;

                items.add(new DrawerItem("Peluchito: "+c.getString(1)+"\nGanancia: "+Integer.toString(c.getInt(4))+"\nRestantes: $"+c.getString(2) , R.mipmap.ic_launcher));
                Gananciatotal=Gananciatotal+c.getInt(4);
                c.moveToNext();
            }
        }
        items.add(new DrawerItem("GANANCIA TOTAL:\n $" +Integer.toString(Gananciatotal) , R.mipmap.ic_launcher));
        bd.close();

        peluchitosList.setAdapter(new DrawerListAdapter(getActivity().getApplicationContext(), items));
        return view;
    }

}

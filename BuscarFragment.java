package com.example.monica.practica4;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarFragment extends Fragment {
    EditText Nombre,Cantidad,id,Valor;
    Button eliminar1;
    Cursor c;

    public BuscarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_buscar, container, false);
        Nombre=(EditText) view.findViewById(R.id.NombreBuscar);
        Cantidad=(EditText) view.findViewById(R.id.CantidadBuscar);
        id=(EditText) view.findViewById(R.id.IdentificacionBuscar);
        Valor=(EditText) view.findViewById(R.id.ValorBuscar);
        eliminar1=(Button)view.findViewById(R.id.buscar);
        eliminar1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PeluchitosDatos Peluchitos = new PeluchitosDatos(getActivity().getApplicationContext(), "Peluchitos", null, 1);
                SQLiteDatabase bd = Peluchitos.getWritableDatabase();


                String nombre = Nombre.getText().toString();

                c = bd.rawQuery("select * from Peluchitos where nombre in ('" + nombre+"')",null );
                if(c.moveToFirst()==true){

                    id.setText(c.getString(0));
                    Cantidad.setText(c.getString(2));
                    Valor.setText(c.getString(3));



                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "No existe el Peluchito "+ nombre+":(",
                            Toast.LENGTH_SHORT).show();
                }
                bd.close();


            }
        });
        return view;
    }

}

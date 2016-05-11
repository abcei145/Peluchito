package com.example.monica.practica4;


import android.content.ContentValues;
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
public class ActualizarFragment extends Fragment {
    EditText Nombre, Cantidad;
    Button eliminar1;

    public ActualizarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_actualizar, container, false);
        Nombre = (EditText) view.findViewById(R.id.NombreActualizar);
        Cantidad = (EditText) view.findViewById(R.id.CantidadNueva);
        eliminar1 = (Button) view.findViewById(R.id.modificar);
        eliminar1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PeluchitosDatos Peluchitos = new PeluchitosDatos(getActivity().getApplicationContext(), "Peluchitos", null, 1);
                SQLiteDatabase bd = Peluchitos.getWritableDatabase();

                String peluche = Nombre.getText().toString();
                String cantidad = Cantidad.getText().toString();

                ContentValues registro = new ContentValues();
                registro.put("cantidad", cantidad);

                int cant = bd.update("Peluchitos", registro, "nombre= '" + peluche + "'", null);
                bd.close();
                if (cant == 1)
                    Toast.makeText(getActivity().getApplicationContext(), "se modificaron los datos del peluche " + peluche, Toast.LENGTH_SHORT)
                            .show();
                else
                    Toast.makeText(getActivity().getApplicationContext(), "no existe el Peluchito "+peluche,
                            Toast.LENGTH_SHORT).show();
            }

        });
        return view;
    }
}

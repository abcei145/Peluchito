package com.example.monica.practica4;


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
public class EliminarFragment extends Fragment {

    private EditText Nombre;
    private Cursor c;
    private Button eliminar1;
    public EliminarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_eliminar, container, false);
        eliminar1=(Button)view.findViewById(R.id.eliminar);
        eliminar1.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View v) {
                                             PeluchitosDatos admin = new PeluchitosDatos(getActivity().getApplicationContext(),"Peluchitos", null, 1);
                                             SQLiteDatabase bd = admin.getWritableDatabase();
                                             String peluche = Nombre.getText().toString();
                                             int cant = bd.delete("Peluchitos", "nombre = '" +peluche+"'", null);
                                             bd.close();
                                             Nombre.setText("");

                                             if (cant == 1)
                                                 Toast.makeText(getActivity().getApplicationContext(), "Se borró el peluchito "+ peluche+"  estudiante con dicho documento",Toast.LENGTH_SHORT).show();
                                             else
                                                 Toast.makeText(getActivity().getApplicationContext(), "No existe estudiante con dicho documento",
                                                         Toast.LENGTH_SHORT).show();
                                             // Perform action on click
                                         }
                                     });
            Nombre=(EditText)view.findViewById(R.id.NombreEliminar);
        return view;
    }



}

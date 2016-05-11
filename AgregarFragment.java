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
public class AgregarFragment extends Fragment {

    EditText Nombre,Cantidad,id,Valor;
    Button eliminar1;
    public AgregarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_agregar, container, false);
        Nombre=(EditText) view.findViewById(R.id.NombreAgregar);
        Cantidad=(EditText) view.findViewById(R.id.CantidadAgregar);
        id=(EditText) view.findViewById(R.id.Identificacion);
        Valor=(EditText) view.findViewById(R.id.ValorAgregar);
        eliminar1=(Button)view.findViewById(R.id.agregar);
        eliminar1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PeluchitosDatos estudiante = new PeluchitosDatos(getActivity().getApplicationContext(), "Peluchitos", null, 1);
                SQLiteDatabase bd = estudiante.getWritableDatabase();

                String nombre = Nombre.getText().toString();
                String identificacion = id.getText().toString();
                String cantidad = Cantidad.getText().toString();
                String valor = Valor.getText().toString();

                ContentValues registro = new ContentValues();//Es para guardar los datos ingresados
                registro.put("id", identificacion);//tag debe aparecer igual que en la clase BaseDeDatos
                registro.put("nombre", nombre);
                registro.put("cantidad", cantidad);
                registro.put("valor", valor);
                registro.put("ganancia", 0);

                bd.insert("Peluchitos", null, registro);
                bd.close();// cerrar para que guarde

                Nombre.setText("");
                id.setText("");
                Cantidad.setText("");
                Valor.setText("");
                Toast.makeText(getActivity().getApplicationContext(), "Se guardaron los datos de la persona",
                        Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }


}

package com.example.monica.practica4;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class VentaFragment extends Fragment {

    EditText Nombre,Cantidad,id,Valor,CantidadVenta;

    Button eliminar11;
    Button venta;
    int ganancia;
    Cursor c;
    public VentaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_venta, container, false);

        Nombre=(EditText) view.findViewById(R.id.NombreVenta);
        Cantidad=(EditText) view.findViewById(R.id.CantidadVenta);
        CantidadVenta=(EditText) view.findViewById(R.id.CantidadVentaVenta);
        id=(EditText) view.findViewById(R.id.IdentificacionVenta);
        Valor=(EditText) view.findViewById(R.id.ValorVenta);
        eliminar11=(Button)view.findViewById(R.id.buscarventa);
        venta=(Button)view.findViewById(R.id.Vender);
        eliminar11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PeluchitosDatos Peluchitos = new PeluchitosDatos(getActivity().getApplicationContext(), "Peluchitos", null, 1);
                SQLiteDatabase bd = Peluchitos.getWritableDatabase();


                String nombre = Nombre.getText().toString();

                c = bd.rawQuery("select * from Peluchitos where nombre in ('" + nombre + "')", null);
                if (c.moveToFirst() == true) {

                    id.setText(c.getString(0));
                    Cantidad.setText(c.getString(2));
                    Valor.setText(c.getString(3));
                    venta.setEnabled(true);
                    ganancia=c.getInt(3);
                    CantidadVenta.setEnabled(true);


                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "No existe el Peluchito: " + nombre,
                            Toast.LENGTH_SHORT).show();
                }
                bd.close();

            }
        });
        venta.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                PeluchitosDatos Peluchitos = new PeluchitosDatos(getActivity().getApplicationContext(), "Peluchitos", null, 1);
                SQLiteDatabase bd = Peluchitos.getWritableDatabase();
                String nombre=Nombre.getText().toString();
                String cantidadvendida=CantidadVenta.getText().toString();
                String cantidadactual=Cantidad.getText().toString();


                int cantidadact=Integer.parseInt(cantidadactual);
                int cantidad=Integer.parseInt(cantidadvendida);
                if(cantidad > cantidadact) {
                    Toast.makeText(getActivity().getApplicationContext(), "Lo siento, no hay suficientes peluchitos :(",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    ContentValues registro = new ContentValues();
                    registro.put("cantidad", Float.toString(cantidadact-cantidad));
                    registro.put("ganancia", ((cantidad)*ganancia));
                    if(cantidadact-cantidad<=5) {
                        String titulo = "", contenido = "", ticker = "";
                        titulo = "Quedan pocos Peluchitos";
                        contenido = "Quedan " + cantidad + "\nPeluchitos de" + nombre;
                        ticker = "Faltan Peluches!";

                        NotificationCompat.Builder builder = new NotificationCompat.
                                Builder(getActivity().getApplicationContext());

                        builder.setContentTitle(titulo)
                                .setContentText(contenido)
                                .setTicker(ticker)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentInfo("Dato");
                        Intent noIntent = new Intent(getActivity(), ActualizarFragment.class);
                        //Intent noIntent = new Intent(getActivity().getIntent());
                        PendingIntent contIntent = PendingIntent.getActivity(getActivity(), 0, noIntent, 0);

                        builder.setContentIntent(contIntent);
                        NotificationManager nm = (NotificationManager)
                                getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);

                        nm.notify(1, builder.build());
                    }
                    int cant = bd.update("Peluchitos", registro, "nombre= '" + nombre + "'", null);
                    Toast.makeText(getActivity().getApplicationContext(), "Gracias por su compra, Peluchito: "+nombre+ "\nCantidad: "+cantidadvendida,
                            Toast.LENGTH_SHORT).show();
                }
                bd.close();
                Nombre.setText("");
                Cantidad.setText("");
                id.setText("");
                Valor.setText("");
                venta.setEnabled(true);
                CantidadVenta.setEnabled(true);
            }
        });
        /**/

        return view;
    }


}

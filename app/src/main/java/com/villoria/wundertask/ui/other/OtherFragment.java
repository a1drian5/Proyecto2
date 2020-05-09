package com.villoria.wundertask.ui.other;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.villoria.wundertask.Iomunicacion;
import com.villoria.wundertask.MainActivity2;
import com.villoria.wundertask.R;
import com.villoria.wundertask.db.AppDataBase;
import com.villoria.wundertask.db.Tareas;
import com.villoria.wundertask.ui.home.HomeViewModel;
import com.villoria.wundertask.ui.other.DemoAdapter;

import java.util.ArrayList;
import java.util.List;

public class OtherFragment extends Fragment {

    private OtherViewModel otherViewModel;
    private HomeViewModel homeViewModel;
    public ImageView paisaje,importancia;
    private EditText titulo,desc;
    public int num_aleatorio =0,nivel_importancia,Importancia;
    private RecyclerView rv;
    private List<Tareas> tareas;
    private FloatingActionButton agregar;
    View Vista;
    Activity actividad;
    Iomunicacion comunica;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        num_aleatorio = (int) (Math.random() * 6);
        // nivel_importancia = 2;
        Stetho.initializeWithDefaults(this.getContext());
        final AppDataBase db = AppDataBase.getAppDatabase(this.getContext());

        tareas = new ArrayList<Tareas>();
        tareas = db.tareasDao().getAllTareasNoCompletadasByListID(MainActivity2.ListaEnviada);
        if(MainActivity2.tareasImprimir.size()!=0)
        {
            tareas = MainActivity2.tareasImprimir;
        }

        otherViewModel =
                ViewModelProviders.of(this).get(OtherViewModel.class);
        View root = inflater.inflate(R.layout.other_fragment, container, false);

        agregar = root.findViewById(R.id.fab_agregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actividad = (Activity) getContext();
                comunica = (Iomunicacion) actividad;
                comunica.CambioActivity("AGREGAR");
            }
        });

        rv = root.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(new com.villoria.wundertask.ui.other.DemoAdapter(tareas));
        importancia=(ImageView) root.findViewById(R.id.im_importancia);
        paisaje = root.findViewById(R.id.image_paisaje);

        if(num_aleatorio == 0){
            paisaje.setImageDrawable(getResources().getDrawable(R.drawable.paisaje1,null));
        }
        if(num_aleatorio == 1) {
            paisaje.setImageDrawable(getResources().getDrawable(R.drawable.paisaje2,null));
        }
        if(num_aleatorio == 2) {
            paisaje.setImageDrawable(getResources().getDrawable(R.drawable.paisaje3,null));
        }
        if(num_aleatorio == 3) {
            paisaje.setImageDrawable(getResources().getDrawable(R.drawable.paisaje4,null));
        }
        if(num_aleatorio == 4) {
            paisaje.setImageDrawable(getResources().getDrawable(R.drawable.paisaje5,null));
        }
        if(num_aleatorio == 5) {
            paisaje.setImageDrawable(getResources().getDrawable(R.drawable.paisaje6,null));
        }

        return root;
    }

}

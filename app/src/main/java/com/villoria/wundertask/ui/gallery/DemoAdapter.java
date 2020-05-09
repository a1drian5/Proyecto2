package com.villoria.wundertask.ui.gallery;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.villoria.wundertask.AgregarTareas;
import com.villoria.wundertask.Iomunicacion;
import com.villoria.wundertask.R;
import com.villoria.wundertask.db.Tareas;

import java.util.List;

class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder> {

    public static class DemoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitulo;
        private TextView txtDesc;
        private TextView txtFecha;
        private TextView txtid;
        private ImageView imImportancia;
        public ImageView edit;
        Context Vista;
        Activity actividad;
        Iomunicacion comun;

        public DemoViewHolder(View view) {
            super(view);
            txtTitulo = view.findViewById(R.id.txt_titulo);
            txtDesc = view.findViewById(R.id.txt_desc);
            imImportancia =view.findViewById(R.id.im_importancia);
            txtFecha = view.findViewById(R.id.txt_fecha);
            txtid = view.findViewById(R.id.txt_id);
            edit = view.findViewById(R.id.im_editar);
        }

        public void bind(final Tareas tarea) {
            txtTitulo.setText(tarea.getNombre());
            txtDesc.setText(tarea.getDescripcion());
            txtid.setText(tarea.getId());
            if(tarea.getImportancia() == 0){
                imImportancia.setImageResource((R.drawable.rayo1));
            }
            if(tarea.getImportancia() == 1){
                imImportancia.setImageResource((R.drawable.rayo2));
            }
            if(tarea.getImportancia() == 2){
                imImportancia.setImageResource((R.drawable.rayo3));
            }
            txtFecha.setText(tarea.getDate());
            Vista = this.itemView.getContext();
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actividad = (Activity) Vista;
                    comun = (Iomunicacion) actividad;
                    AgregarTareas.ListaActual = txtid.getText().toString();
                    comun.CambioActivity("Editar");
                }
            });
        }
    }

    private List<Tareas> tareas;

    public DemoAdapter(List<Tareas> tareas) {
        this.tareas = tareas;
    }

    @NonNull
    @Override
    public DemoAdapter.DemoViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cardview, parent, false);
        return new DemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DemoViewHolder holder, int position) {
        holder.bind(tareas.get(position));
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }
}



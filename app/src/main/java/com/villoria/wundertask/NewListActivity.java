package com.villoria.wundertask;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.stetho.Stetho;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.villoria.wundertask.db.AppDataBase;
import com.villoria.wundertask.db.Lists;

class CustomAdapter extends ArrayAdapter{
    int[] spinImages;
    Context mContext;

    public CustomAdapter(@NonNull Context context, int[] images){
        super(context, R.layout.custom_spinner_layout);

        this.spinImages = images;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return spinImages.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.custom_spinner_layout, parent, false);
            mViewHolder.mImage = (ImageView) convertView.findViewById(R.id.icons_iv);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.mImage.setImageResource(spinImages[position]);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private static class ViewHolder {
        ImageView mImage;
    }
}

public class NewListActivity extends AppCompatActivity {

    public int i =0;
    public String id;
    public Lists li;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference dbRef = database.getReference("app").child("Usuarios");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        final AppDataBase db = AppDataBase.getAppDatabase(this);
        setContentView(R.layout.activity_new_list);

        final Spinner iconSpin;
        final Spinner colorSpin;
        final EditText nameET;
        final Switch sharedSw;
        Button create;

        iconSpin = findViewById(R.id.icon_spinner);
        colorSpin = findViewById(R.id.color_spinner);

        final int[] iconsList = {
                R.drawable.ic_menu_all_24dp,
                R.drawable.ic_menu_important_24dp,
                R.drawable.ic_menu_planned_24dp,
                R.drawable.ic_menu_pending_24dp,
                R.drawable.ic_menu_airplane_24dp,
                R.drawable.ic_menu_cake_24dp,
                R.drawable.ic_menu_gym_24dp,
                R.drawable.ic_menu_heart_24dp,
                R.drawable.ic_menu_pets_24dp,
                R.drawable.ic_menu_place_24dp,
                R.drawable.ic_menu_school_24dp,
                R.drawable.ic_menu_store_24dp,
                R.drawable.ic_menu_umbrella_24dp,
                R.drawable.ic_menu_work_24dp
        };
        final int [] colorList = {
                R.color.colorBlack,
                R.color.colorGray,
                R.color.colorSilver,
                R.color.colorWhite,
                R.color.colorRed,
                R.color.colorOrange,
                R.color.colorYellow,
                R.color.colorLime,
                R.color.colorGreen,
                R.color.colorAqua,
                R.color.colorBlue,
                R.color.colorNavy,
                R.color.colorPurple,
                R.color.colorPink
        };


        CustomAdapter iconCA = new CustomAdapter(NewListActivity.this, iconsList);
        CustomAdapter colorCA = new CustomAdapter(NewListActivity.this, colorList);
        iconSpin.setAdapter(iconCA);
        colorSpin.setAdapter(colorCA);

        nameET = findViewById(R.id.list_name_et);
        sharedSw = findViewById(R.id.shared_switch);

        sharedSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedSw.isChecked()== true) {
                    i=1;
                    dbRef = dbRef.child(MainActivity.Correousuario).child("ListasLocales");
                    id = nameET.getText().toString() + "-" + "01" + "-" + MainActivity.Correousuario.charAt(0) + MainActivity.Correousuario.charAt(1) + "-" + "co";
                }
                else {
                    dbRef =  dbRef.child(MainActivity.Correousuario).child("ListasCompartidas");
                    id = nameET.getText().toString() + "-" + "01" + "-" + MainActivity.Correousuario.charAt(0) + MainActivity.Correousuario.charAt(1) + "-" + "ad";

                }
            }
        });

        create = findViewById(R.id.create_list_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedSw.isChecked()== false) {
                    i=0;
                    dbRef = dbRef.child(MainActivity.Correousuario).child("ListasLocales");
                    id = nameET.getText().toString() + "-" + "01" + "-" + MainActivity.Correousuario+ "-" + "nc";
                }
                else {
                    i=1;
                    dbRef =  dbRef.child(MainActivity.Correousuario).child("ListasCompartidas");
                    id = nameET.getText().toString() + "-" + "01" + "-" + MainActivity.Correousuario + "-" + "co";
                }
                li = new Lists(id,nameET.getText().toString(),MainActivity.Correousuario,colorSpin.getSelectedItemPosition()+"",iconSpin.getSelectedItemPosition(),i);
                dbRef.child(id).setValue(li);
                dbRef.child(id).child("ValorIrrelevante").setValue(1);
                dbRef.child(id).child("Tareas").child("ValorIrrelevante").setValue(1);
                db.listsDao().insertList(li);

            }
        });

    }
}

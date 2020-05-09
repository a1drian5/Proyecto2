package com.villoria.wundertask;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.stetho.Stetho;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.villoria.wundertask.db.AppDataBase;
import com.villoria.wundertask.db.Lists;

public class EditListActivity extends AppCompatActivity {

    Spinner iconSpin;
    Spinner colorSpin;
    EditText nameET;
    Button delete;
    Button edit;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference dbRef = database.getReference("app").child("Usuarios");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        Stetho.initializeWithDefaults(this);
        final AppDataBase db = AppDataBase.getAppDatabase(this);

        final Lists li = db.listsDao().getListById(MainActivity2.ListaEnviada);

        iconSpin = findViewById(R.id.icon_edit_spinner);
        colorSpin = findViewById(R.id.color_edit_spinner);

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


        CustomAdapter iconCA = new CustomAdapter(EditListActivity.this, iconsList);
        CustomAdapter colorCA = new CustomAdapter(EditListActivity.this, colorList);
        iconSpin.setAdapter(iconCA);
        colorSpin.setAdapter(colorCA);

        nameET = findViewById(R.id.list_name_edit_et);
        edit = findViewById(R.id.edit_list_button);
        Toast.makeText(EditListActivity.this,MainActivity2.ListaEnviada,Toast.LENGTH_SHORT).show();

        //Esta es para obtener lista a editar
        nameET.setText(li.getListid());
        colorSpin.setSelection(findColorInSpinner(Integer.parseInt(li.getColor())));
        iconSpin.setSelection(findIconInSpinner(li.getIcono()));


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lists newLis = li;
                li.setName(nameET.getText().toString());
                li.setColor(colorList[colorSpin.getSelectedItemPosition()]+"");
                li.setIcono(iconsList[iconSpin.getSelectedItemPosition()]);
                db.listsDao().deleteList(newLis);
                db.listsDao().insertList(li);
            }
        });

        delete = findViewById(R.id.delete_list_button);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.listsDao().deleteList(li);
            }
        });

        //dateText.findViewById(R.id.etDates);


    }

    //Las funciones debajo son para buscar en los spinners
    int findColorInSpinner(int color){
        int pos = 0;
        switch (color){
            case R.color.colorGray:
                pos = 1;
                break;
            case R.color.colorSilver:
                pos = 2;
                break;
            case R.color.colorWhite:
                pos = 3;
                break;
            case R.color.colorRed:
                pos = 4;
                break;
            case R.color.colorOrange:
                pos = 5;
                break;
            case R.color.colorYellow:
                pos = 6;
                break;
            case R.color.colorLime:
                pos = 7;
                break;
            case R.color.colorGreen:
                pos = 8;
                break;
            case R.color.colorAqua:
                pos = 9;
                break;
            case R.color.colorBlue:
                pos = 10;
                break;
            case R.color.colorNavy:
                pos = 11;
                break;
            case R.color.colorPurple:
                pos = 12;
                break;
            case R.color.colorPink:
                pos = 13;
                break;
            default:
                pos = 0;
                break;
        }
        return pos;
    }
    int findIconInSpinner(int icon){
        int pos = 0;
        switch (icon){
            case R.drawable.ic_menu_important_24dp:
                pos = 1;
                break;
            case R.drawable.ic_menu_planned_24dp:
                pos = 2;
                break;
            case R.drawable.ic_menu_pending_24dp:
                pos = 3;
                break;
            case R.drawable.ic_menu_airplane_24dp:
                pos = 4;
                break;
            case R.drawable.ic_menu_cake_24dp:
                pos = 5;
                break;
            case R.drawable.ic_menu_gym_24dp:
                pos = 6;
                break;
            case R.drawable.ic_menu_heart_24dp:
                pos = 7;
                break;
            case R.drawable.ic_menu_pets_24dp:
                pos = 8;
                break;
            case R.drawable.ic_menu_place_24dp:
                pos = 9;
                break;
            case R.drawable.ic_menu_school_24dp:
                pos = 10;
                break;
            case R.drawable.ic_menu_store_24dp:
                pos = 11;
                break;
            case R.drawable.ic_menu_umbrella_24dp:
                pos = 12;
                break;
            case R.drawable.ic_menu_work_24dp:
                pos = 13;
                break;
            default:
                pos = 0;
                break;
        }
        return pos;
    }
}
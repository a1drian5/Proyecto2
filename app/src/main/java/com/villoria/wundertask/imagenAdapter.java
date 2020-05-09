package com.villoria.wundertask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.stetho.Stetho;
import com.villoria.wundertask.db.AppDataBase;
import com.villoria.wundertask.db.User;

import java.util.ArrayList;

public class imagenAdapter extends ArrayAdapter<ImagenItem> {
public imagenAdapter(Context context, ArrayList<ImagenItem> imageList)
{
    super(context,0,imageList);
}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return InitView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return InitView(position,convertView,parent);
    }
    private View InitView(int position, View convertview, ViewGroup parent)
    {
        if (convertview == null)
        {
            convertview = LayoutInflater.from(getContext()).inflate(
                    R.layout.avatar_image, parent, false
            );
        }
        ImageView imAv= convertview.findViewById(R.id.imageAvatar);

        Stetho.initializeWithDefaults(getContext());
        final AppDataBase db = AppDataBase.getAppDatabase(getContext());
        User us = db.userDao().getActiveUser();
        int a = us.getAvatar();
        switch (a)
        {
            case 0:
                a = R.drawable.a1;
                break;
            case 1:
                a = R.drawable.a2;
                break;
            case 2:
                a = R.drawable.a3;
                break;
            case 3:
                a = R.drawable.a4;
                break;
            case 4:
                a = R.drawable.a5;
                break;
            case 5:
                a = R.drawable.a6;
                break;
            case 6:
                a = R.drawable.a7;
                break;
            default:
                a = R.drawable.a8;
                break;
        }
        ImagenItem currentItem = getItem(position);
        if(currentItem != null)
        imAv.setImageResource(a);

        return convertview;

    }
}

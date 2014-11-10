package com.doodlepro.lniu.doodlepro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;

/**
 * Created by lniu on 11/9/14.
 */

public class ColorView extends View {

    public interface OnItemSelectedListener {
        public void onSizeSelected(Bundle bundle);
    }

    OnItemSelectedListener listener;
    Activity host;
    int color;
    String[] size_list;
    // We must provide a constructor that takes a Context and an AttributeSet.
    // This constructor allows the UI to create and edit an instance of your view.
    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        color = Color.TRANSPARENT;
        Drawable background = this.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) background).getColor();
        host = (Activity) this.getContext();
        if (host instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) host;
        } else {
            throw new ClassCastException(host.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSelectSizeDialog();
            }
        });

    }

    public ColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        host = (Activity) this.getContext();
    }

    public void ShowSelectSizeDialog() {
        size_list = host.getResources().getStringArray(R.array.StrokeSizes);
        System.out.println(size_list.length);
        for(String s : size_list) {
            System.out.println(s);
        }
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                host);
        builderSingle.setTitle("Select One Size:");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                host,
                android.R.layout.select_dialog_singlechoice, size_list);
        builderSingle.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        int size = Integer.parseInt(strName);
                        Bundle bundle = new Bundle();
                        bundle.putInt("Color", color);
                        bundle.putInt("Size", size);
                        listener.onSizeSelected(bundle);

                    }
                });
        builderSingle.show();
    }
}
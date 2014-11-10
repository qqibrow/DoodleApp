package com.doodlepro.lniu.doodlepro;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class DoodleActivity extends Activity implements ColorView.OnItemSelectedListener{
    private DoodleView dooleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodlepro);
        dooleView = (DoodleView)this.findViewById(R.id.view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doodlepro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSizeSelected(Bundle bundle) {
        dooleView.ChangePaint(bundle);
    }

    public void onClickErase(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("Color", Color.WHITE);
        bundle.putInt("Size", 20);
        dooleView.ChangePaint(bundle);
    }
}

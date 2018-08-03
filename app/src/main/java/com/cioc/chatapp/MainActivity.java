package com.cioc.chatapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton user = (FloatingActionButton) findViewById(R.id.fab);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName("com.cioc.libreerp", "com.cioc.libreerp.NewChatActivity");
                if (isCallable(MainActivity.this, intent)) {
                    // Attach any extras, start or start with callback
                    intent.putExtra("boolean", false);
                    startActivityForResult(intent,201);
                } else {
                    // Respond to the application or activity not being available
                    Toast.makeText(MainActivity.this, "activity not being available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        FloatingActionButton multiUser = (FloatingActionButton) findViewById(R.id.fab2);
        multiUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName("com.cioc.libreerp", "com.cioc.libreerp.MultiChatActivity");
                if (isCallable(MainActivity.this, intent)) {
                    intent.putExtra("boolean", false);
                    startActivityForResult(intent,202);
                } else {
                    Toast.makeText(MainActivity.this, "activity not being available", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public static boolean isCallable(Activity activity, Intent intent) {
        List<ResolveInfo> list = activity.getPackageManager().queryIntentActivities(intent,PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 201){
            if (resultCode == RESULT_OK) {
                data.getExtras().getString("name");
                Toast.makeText(this, "" + data.getExtras().getString("name"), Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 202){
            if (resultCode == RESULT_OK) {
                ArrayList<HashMap> hashMaps = new ArrayList<HashMap>();
                hashMaps = (ArrayList<HashMap>) getIntent().getSerializableExtra("multi_contacts");
                for (int i=0; i<hashMaps.size(); i++) {
                    HashMap mp = hashMaps.get(i);
                    mp.get("pkUser");
                    Toast.makeText(this, "pk "+mp.get("pkUser"), Toast.LENGTH_SHORT).show();
                }
//                data.getExtras().getInt("with_pk");
//                data.getExtras().getParcelableArrayList("multi_contacts");
                Toast.makeText(this, "multi user "+data.getExtras().getInt("multi_contacts_size"), Toast.LENGTH_SHORT).show();
            }
        }
    }
}

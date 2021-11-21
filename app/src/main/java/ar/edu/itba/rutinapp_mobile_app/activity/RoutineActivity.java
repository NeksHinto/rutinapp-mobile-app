package ar.edu.itba.rutinapp_mobile_app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.fragment.ActiveRoutineFragment;
import ar.edu.itba.rutinapp_mobile_app.fragment.ViewRountineFragment;

public class RoutineActivity extends AppCompatActivity {

    public RoutineActivity() { super(R.layout.routine_activity); }

//    MaterialButton startRoutineBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = getIntent();
//        String routineID = intent.getStringExtra("routineID");
//
//        Bundle bundle = new Bundle();
//        bundle.putInt("routineID", new Integer(routineID));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.routineViewFragment, new ActiveRoutineFragment())
                    .commit();
        }

        setContentView(R.layout.routine_activity);

//        startRoutineBtn = findViewById(R.id.startRountine);
//        startRoutineBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActiveRoutineFragment activeRoutineFragment = new ActiveRoutineFragment();
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.routineViewFragment, activeRoutineFragment);
//                ft.commit();
//            }
//        });
    }

}


package ar.edu.itba.rutinapp_mobile_app.activity;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.edu.itba.rutinapp_mobile_app.api.model.CycleExpandableListAdapter;
import ar.edu.itba.rutinapp_mobile_app.ExpandableListDataPump;
import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.fragment.ActiveRoutineFragment;

public class RoutineActivity extends FragmentActivity {

    MaterialButton startRoutineBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine_activity);

        startRoutineBtn = findViewById(R.id.startRountine);
        startRoutineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActiveRoutineFragment activeRoutineFragment = new ActiveRoutineFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.routineViewFragment, activeRoutineFragment);
                ft.commit();
            }
        });
    }

}


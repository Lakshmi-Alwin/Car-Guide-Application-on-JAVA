package com.example.carguide;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static android.view.View.GONE;

public class VehiclePage extends Fragment {

    private ImageView lockedSelected, unlockedSelected,fuel_level, lockedNotSelected, unlockedNotSelected, startEngineSelected, startEngineNotSelected, tire_lf, tire_rf, tire_lb, tire_rb, outerGuage;
    private LinearLayout vehicle_details;
    private TextView dte, odo, oilLife, pressure_lf, pressure_rf, pressure_lb, pressure_rb, nickName;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_page, container, false);
        lockedSelected = view.findViewById(R.id.locked_selected);
        unlockedSelected = view.findViewById(R.id.unlocked_selected);
        lockedNotSelected = view.findViewById(R.id.locked_not_selected);
        unlockedNotSelected = view.findViewById(R.id.unlocked_not_selected);
        startEngineNotSelected = view.findViewById(R.id.start_engine_not_selected);
        startEngineSelected = view.findViewById(R.id.start_engine_selected);
        fuel_level=view.findViewById(R.id.fuel_level);
        vehicle_details = view.findViewById(R.id.vehicle_odometer_details);
        fuel_level = view.findViewById(R.id.fuel_level);
        outerGuage = view.findViewById(R.id.outer);
        tire_lb = view.findViewById(R.id.lb);
        tire_lf = view.findViewById(R.id.lt);
        tire_rb = view.findViewById(R.id.rb);
        tire_rf = view.findViewById(R.id.rt);
        pressure_lb = view.findViewById(R.id.tyre_lb);
        pressure_lf = view.findViewById(R.id.tyre_lt);
        pressure_rb = view.findViewById(R.id.tyre_rb);
        pressure_rf = view.findViewById(R.id.tyre_rt);
        dte = view.findViewById(R.id.dte);
        odo = view.findViewById(R.id.odo);
        oilLife = view.findViewById(R.id.oil_life);
        nickName = view.findViewById(R.id.nickname);
        vehicle_details.setVisibility(GONE);
        lockedSelected.setVisibility(GONE);
        unlockedSelected.setVisibility(GONE);
        startEngineSelected.setVisibility(GONE);
        String url = "https://car-guide.herokuapp.com/getvehicle?username="+PreferencesManager.getEmail();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            vehicle_details.setVisibility(View.VISIBLE);
            lockedSelected.setVisibility(View.VISIBLE);
            startEngineNotSelected.setVisibility(View.VISIBLE);
            unlockedNotSelected.setVisibility(View.VISIBLE);
            lockedNotSelected.setVisibility(GONE);
            unlockedSelected.setVisibility(GONE);

            JSONObject vehicle;
            try {
                vehicle = response.getJSONObject("vehicle");
                nickName.setText(vehicle.get("nickname").toString());
                oilLife.setText("OIL LIFE\n" + vehicle.getInt("oil_life")+"%");
                if(vehicle.getBoolean("engine_state")){
                    startEngineNotSelected.setVisibility(GONE);
                    startEngineSelected.setVisibility(View.VISIBLE);
                }
                else {
                    startEngineNotSelected.setVisibility(View.VISIBLE);
                    startEngineSelected.setVisibility(GONE);
                }
                if(vehicle.getBoolean("lock_state")){
                    unlockedNotSelected.setVisibility(GONE);
                    unlockedSelected.setVisibility(View.VISIBLE);
                    lockedSelected.setVisibility(GONE);
                    lockedNotSelected.setVisibility(View.VISIBLE);
                }
                else {
                    unlockedNotSelected.setVisibility(View.VISIBLE);
                    unlockedSelected.setVisibility(GONE);
                    lockedNotSelected.setVisibility(GONE);
                    lockedSelected.setVisibility(View.VISIBLE);
                }
                JSONObject tirepressure = vehicle.getJSONObject("tpms");
                pressure_lb.setText(String.valueOf(tirepressure.getInt("lb")));
                pressure_rb.setText(String.valueOf(tirepressure.getInt("rb")));
                pressure_lf.setText(String.valueOf(tirepressure.getInt("lf")));
                pressure_rf.setText(String.valueOf(tirepressure.getInt("rf")));
                if(PreferencesManager.getUnitOfMeasurement().equals("Miles")){
                    int odo_reading = (int) (vehicle.getInt("odometer")/1.6);
                    int dte_reading = (int) (vehicle.getInt("dte")/1.6);
                    odo.setText("ODO\n"+odo_reading+" MILES");
                    dte.setText("DTE\n"+dte_reading+" MILES");
                }
                else {
                    odo.setText("ODO\n"+vehicle.getInt("odometer")+" KM");
                    dte.setText("DTE\n"+vehicle.getInt("dte")+" KM");
                }
                fuel_level.getLayoutParams().height = outerGuage.getHeight()*vehicle.getInt("fuel_level")/100;
                fuel_level.setScaleType(ImageView.ScaleType.FIT_XY);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            vehicle_details.setVisibility(GONE);
            lockedSelected.setVisibility(GONE);
            startEngineNotSelected.setVisibility(GONE);
            unlockedNotSelected.setVisibility(GONE);
            lockedNotSelected.setVisibility(View.VISIBLE);
            startEngineNotSelected.setVisibility(View.VISIBLE);
            unlockedSelected.setVisibility(View.VISIBLE);
        });

        lockedSelected.setOnClickListener(view1 -> {
            lockedSelected.setVisibility(GONE);
            lockedNotSelected.setVisibility(View.VISIBLE);
            unlockedNotSelected.setVisibility(GONE);
            unlockedSelected.setVisibility(View.VISIBLE);
            startEngineNotSelected.setVisibility(GONE);
            startEngineSelected.setVisibility(View.VISIBLE);
        });

        unlockedSelected.setOnClickListener(view1 -> {
            lockedSelected.setVisibility(View.VISIBLE);
            lockedNotSelected.setVisibility(GONE);
            unlockedNotSelected.setVisibility(View.VISIBLE);
            unlockedSelected.setVisibility(GONE);
            startEngineNotSelected.setVisibility(View.VISIBLE);
            startEngineSelected.setVisibility(GONE);
        });

        ApiSingleton.getInstance(view.getContext()).addToRequestQueue(jsonObjectRequest);

        startEngineSelected.setOnClickListener(view2 -> Toast.makeText(view.getContext() , "Engine is starting", Toast.LENGTH_SHORT).show());
        startEngineNotSelected.setOnClickListener(view2 -> Toast.makeText(view.getContext() , "Please Lock the vehicle", Toast.LENGTH_SHORT).show());

        return view;
    }
}

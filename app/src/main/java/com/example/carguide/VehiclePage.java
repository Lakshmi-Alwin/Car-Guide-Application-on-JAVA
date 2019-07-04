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
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.atomic.AtomicBoolean;
import static android.view.View.GONE;

public class VehiclePage extends Fragment {

    private ImageView fuel_level, tire_lf, tire_rf, tire_lb, tire_rb, outerGuage, locked, unlocked, engineState;
    private LinearLayout vehicle_details;
    private TextView dte, odo, oilLife, pressure_lf, pressure_rf, pressure_lb, pressure_rb, nickName;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_page, container, false);
        locked = view.findViewById(R.id.locked);
        unlocked = view.findViewById(R.id.unlocked);
        engineState=view.findViewById(R.id.engine);
        fuel_level=view.findViewById(R.id.fuel_level);
        vehicle_details = view.findViewById(R.id.vehicle_odometer_details);
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
        locked.setEnabled(false);
        unlocked.setEnabled(false);
        engineState.setEnabled(false);
        String username = "?username="+PreferencesManager.getEmail();
        String url = "https://car-guide.herokuapp.com/";
        AtomicBoolean enginestateflag = new AtomicBoolean(false);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url+"getvehicle"+username, null, response -> {
            vehicle_details.setVisibility(View.VISIBLE);
            engineState.setEnabled(true);
            JSONObject vehicle;
            try {
                vehicle = response.getJSONObject("vehicle");
                nickName.setText(vehicle.get("nickname").toString());
                oilLife.setText("OIL LIFE\n" + vehicle.getInt("oil_life")+"%");
                if(vehicle.getBoolean("engine_state")){
                    engineState.setImageResource(R.drawable.stop_engine);
                    enginestateflag.set(false);
                }
                else {
                    engineState.setImageResource(R.drawable.start_engine_selected);
                    enginestateflag.set(true);
                }
                if(vehicle.getBoolean("lock_state")){
                    locked.setImageResource(R.drawable.locked_not_selected);
                    unlocked.setImageResource(R.drawable.unlocked_selected);
                    locked.setEnabled(false);
                    unlocked.setEnabled(true);
                }
                else {
                    locked.setImageResource(R.drawable.lock_selected);
                    unlocked.setImageResource(R.drawable.unlocked_not_selected);
                    locked.setEnabled(true);
                    unlocked.setEnabled(false);
                }
                JSONObject tirepressure = vehicle.getJSONObject("tpms");
                pressure_lb.setText(String.valueOf(tirepressure.getInt("lb")));
                setTireImage(pressure_lb, tire_lb);
                pressure_rb.setText(String.valueOf(tirepressure.getInt("rb")));
                setTireImage(pressure_rb, tire_rb);
                pressure_lf.setText(String.valueOf(tirepressure.getInt("lf")));
                setTireImage(pressure_lf, tire_lf);
                pressure_rf.setText(String.valueOf(tirepressure.getInt("rf")));
                setTireImage(pressure_rf, tire_rf);
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
        });

        locked.setOnClickListener(view1 -> {
            StringRequest request = new StringRequest(Request.Method.PUT, url+"toggledoorlockstate"+username, response -> {
                locked.setImageResource(R.drawable.locked_not_selected);
                unlocked.setImageResource(R.drawable.unlocked_selected);
                locked.setEnabled(false);
                unlocked.setEnabled(true);
            }, error -> Toast.makeText(view.getContext(), "Failed to lock the vehicle",Toast.LENGTH_SHORT).show());
            ApiSingleton.getInstance(view.getContext()).addToRequestQueue(request);
        });

        unlocked.setOnClickListener(view1 -> {
            StringRequest request = new StringRequest(Request.Method.PUT, url+"toggledoorlockstate"+username, response -> {
                locked.setImageResource(R.drawable.lock_selected);
                unlocked.setImageResource(R.drawable.unlocked_not_selected);
                locked.setEnabled(true);
                unlocked.setEnabled(false);
            }, error -> Toast.makeText(view.getContext(), "Failed to unlock the vehicle",Toast.LENGTH_SHORT).show());
            ApiSingleton.getInstance(view.getContext()).addToRequestQueue(request);
        });

        engineState.setOnClickListener(view12 -> {
            StringRequest request = new StringRequest(Request.Method.PUT, url+"toggleenginestate"+username, response -> {
                if(enginestateflag.get()) {
                    Toast.makeText(view12.getContext(), "Engine is starting", Toast.LENGTH_SHORT).show();
                    engineState.postDelayed(() -> engineState.setImageResource(R.drawable.stop_engine), 500);
                    enginestateflag.set(!enginestateflag.get());
                }
                else {
                    Toast.makeText(view12.getContext(), "Engine is stoping", Toast.LENGTH_SHORT).show();
                    engineState.postDelayed(() -> engineState.setImageResource(R.drawable.start_engine_selected), 500);
                    enginestateflag.set(!enginestateflag.get());
                }
            }, error -> {
                if(enginestateflag.get()) {
                    Toast.makeText(view12.getContext(), "Engine is not able to start", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(view12.getContext(), "Engine is not able to stop", Toast.LENGTH_SHORT).show();
                }
            });
            ApiSingleton.getInstance(view12.getContext()).addToRequestQueue(request);
        });


        ApiSingleton.getInstance(view.getContext()).addToRequestQueue(jsonObjectRequest);

        return view;
    }

    private void setTireImage(TextView tirePressure, ImageView tire) {
        int pressure = Integer.parseInt(tirePressure.getText().toString());
        if(pressure<27)
            tire.setImageResource(R.drawable.tyre_red);
        else if(pressure<30)
            tire.setImageResource(R.drawable.tyre_orange);
        else
            tire.setImageResource(R.drawable.tyre_green);
    }


}

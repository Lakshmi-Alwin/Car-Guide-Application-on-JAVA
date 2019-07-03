package com.example.carguide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import org.json.JSONObject;

import java.util.regex.Pattern;

import static android.icu.text.DisplayContext.LENGTH_SHORT;

public class AddVehicle extends AppCompatActivity {

    ImageView vectorLeft, outerRing, midRing, innerRing, successfulVin;
    Button addVehicle;
    EditText vin, nickName;
    TextView result;
    RelativeLayout loading_layLayout;
    private static Pattern ALPHANUMERICUPPERCASE = Pattern.compile("^[A-Z0-9]*$");
    private String url ="http://car-guide.herokuapp.com/addvehicle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        vectorLeft = findViewById(R.id.vector_left);
        addVehicle = findViewById(R.id.addVehicle);
        vin = findViewById(R.id.vin);
        nickName = findViewById(R.id.vehicle_nickname);
        outerRing = findViewById(R.id.outerRing);
        midRing = findViewById(R.id.midRing);
        innerRing = findViewById(R.id.innerRing);
        result = findViewById(R.id.vehicle_result);
        successfulVin = findViewById(R.id.successful_vin);
        loading_layLayout = findViewById(R.id.loading_layout);

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url+"?username="+googleSignInAccount.getEmail()+"&vin="+vin.getText().toString()+"&nickname="+nickName.getText().toString(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                          Toast.makeText(AddVehicle.this,"Vehicle added succesfully",Toast.LENGTH_SHORT).show();
                        //textView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

// Access the RequestQueue through your singleton class.
      ApiSingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);



        successfulVin.setVisibility(View.GONE);

        Animation outerAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        outerAnimation.setDuration(2000);
        outerAnimation.setRepeatCount(-1);
        outerAnimation.setInterpolator(v -> v);
        outerRing.setAnimation(outerAnimation);

        Animation middleAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        middleAnimation.setDuration(2000);
        middleAnimation.setRepeatCount(-1);
        middleAnimation.setInterpolator(v -> v);
        midRing.setAnimation(middleAnimation);

        Animation innerAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        innerAnimation.setDuration(2000);
        innerAnimation.setRepeatCount(-1);
        innerAnimation.setInterpolator(v -> v);
        innerRing.setAnimation(innerAnimation);

        loading_layLayout.setVisibility(View.GONE);

        vectorLeft.setOnClickListener(v -> onBackPressed());
        addVehicle.setOnClickListener(v -> {
            final String editTextVin = vin.getText().toString();
            if (editTextVin.length() != 17) {
                vin.setError("VIN should have 17 characters");
                return;
            }
            if (!isAlphaNumericUpperCase(editTextVin)) {
                vin.setError("VIN should only contain uppercase letters and numbers");
                return;
            }
            vectorLeft.setVisibility(View.GONE);
            vin.setVisibility(View.GONE);
            nickName.setVisibility(View.GONE);
            addVehicle.setVisibility(View.GONE);

            PreferencesManager.saveVIN(vin.getText().toString());
            PreferencesManager.saveVehicleNickName(nickName.getText().toString());

                loading_layLayout.setVisibility(View.VISIBLE);
                loading_layLayout.postDelayed(() -> loading_layLayout.setVisibility(View.GONE),3000);

                successfulVin.postDelayed(() -> successfulVin.setVisibility(View.VISIBLE),3000);

                new Handler().postDelayed(() -> {
                    PreferencesManager.saveVIN(editTextVin);
                    Intent intent = new Intent(AddVehicle.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }, 6000);
                });
    }

    private boolean isAlphaNumericUpperCase(String s) {
        return ALPHANUMERICUPPERCASE.matcher(s).find();
    }
}

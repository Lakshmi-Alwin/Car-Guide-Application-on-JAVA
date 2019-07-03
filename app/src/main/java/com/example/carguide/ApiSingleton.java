package com.example.carguide;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class ApiSingleton {
    private static ApiSingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private ApiSingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    static synchronized ApiSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new ApiSingleton(context);
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
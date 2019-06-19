package com.example.carguide;

import androidx.fragment.app.FragmentActivity;

public interface SupportContract {
    interface View{
    }

    interface Presenter{
        void onClickSupport(FragmentActivity a);
    }
}

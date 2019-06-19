package com.example.carguide;

import androidx.fragment.app.FragmentActivity;

public interface SupportContract {
    public interface View{
    }

    public interface Presenter{
        public void onClickSupport(FragmentActivity a);
    }
}

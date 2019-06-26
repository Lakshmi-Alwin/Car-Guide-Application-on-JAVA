package com.example.carguide;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;




public class mainActFragment extends Fragment {

    public mainActFragment(int imageText, String titleText, String extraText) {
        super();
        this.imageText = imageText;
        this.titleText = titleText;
        this.extraText = extraText;
    }

    private int imageText;
    private String titleText;
    private String extraText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_main_act_fragment, container, false);
        TextView mainText = rootView.findViewById(R.id.main_text);
        mainText.setText(titleText);
        TextView subText = rootView.findViewById(R.id.sub_text);
        subText.setText(extraText);
        AppCompatImageView sliderImage = rootView.findViewById(R.id.slider_image);
        sliderImage.setImageResource(imageText);



        return rootView;
    }

    public int getImageID() { return imageText; }
    public String getTitleText() { return titleText; }
    public String getExtraText() { return extraText; }


}

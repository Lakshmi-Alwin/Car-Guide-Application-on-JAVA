package com.example.carguide;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MainActivityFragmentsTest {
    private mainActFragment currentFragment;

    private int imageID;
    private String titleText;
    private String extraText;

    @Before
    public void prepareFragment() {
        imageID = 0;
        titleText = "Title Text";
        extraText = "Sub Text";
        currentFragment = new mainActFragment(imageID, titleText, extraText);
    }

    @Test
    public void checkFragmentImageData() {
        Assert.assertEquals(currentFragment.getImageID(), imageID);
    }

    @Test
    public void checkFragmentTitleTextData() {
        Assert.assertEquals(currentFragment.getTitleText(), titleText);
    }

    @Test
    public void checkFragmentExtraTextData() {
        Assert.assertEquals(currentFragment.getExtraText(), extraText);
    }



}

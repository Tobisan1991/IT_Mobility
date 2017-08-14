package com.group6.TakeOff;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * Created by STzavelas on 13.08.17.
 */

public class BottomNavigationViewHelper {

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
        bottomNavigationViewEx.setIconsMarginTop(30);
    }
}

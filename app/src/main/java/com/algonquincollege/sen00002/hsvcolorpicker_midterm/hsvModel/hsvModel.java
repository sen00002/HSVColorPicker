package com.algonquincollege.sen00002.hsvcolorpicker_midterm.hsvModel;

import java.util.Observable;

/**
 * Created by rajatsen sen00002
 */

public class hsvModel extends Observable {


    // value set
    public final int maximumHUE = 359;
    public final int minimumHUE = 0;
    public final int maximumSATURATION = 100;
    public final int minimumSATURATION = 0;
    public final int maximumVALUE = 100;
    public final int minimumVALUE = 0;
    // properties
    public int hue;
    public int saturation;
    public int value;

    public hsvModel() {
        setHue(maximumHUE);
        setSaturation(maximumSATURATION);
        setValue(maximumVALUE);
    }

    public hsvModel( int hue, int saturation, int value ) {
        super();
        setHSV(hue,saturation,value);
    }

    public void setHSV( int hue, int saturation, int value ) {
        setHue(hue);
        setSaturation(saturation);
        setValue(value);
    }

    private void updateObservers(){
        this.setChanged();
        this.notifyObservers();
    }

    // setter methods
    public void setHue(int hue) {
        if (hue <= this.maximumHUE && hue >= this.minimumHUE) {
            this.hue = hue;
        }
        this.updateObservers();
    }

    public void setSaturation(int saturation) {
        if (saturation <= this.maximumSATURATION && saturation >= this.minimumSATURATION) {
            this.saturation = saturation;
        }
        this.updateObservers();
    }

    public void setValue(int value) {
        if (value <= this.maximumVALUE && value >= this.minimumVALUE) {
            this.value = value;
        }
        this.updateObservers();
    }

    // getter methods
    public int getHue() {
        return this.hue;
    }

    public int getSaturation() {
        return this.saturation;
    }

    public int getValue() {
        return this.value;
    }



}
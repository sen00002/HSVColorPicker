package com.algonquincollege.sen00002.hsvcolorpicker_midterm;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.algonquincollege.sen00002.hsvcolorpicker_midterm.hsvModel.hsvModel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by rajatsen sen00002
 */

public class MainActivity extends Activity implements Observer, SeekBar.OnSeekBarChangeListener {

    public aboutDialogBox aboutDialog;
    public hsvModel hsvModel;

    // colorswatch
    public TextView TextViewColorSwatch;
    //seekbar
    public SeekBar SeekbarHue;
    public SeekBar SeekbarSaturation;
    public SeekBar SeekbarValue;
    //textView
    public TextView TextViewHue;
    public TextView TextViewSaturation;
    public TextView TextViewValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ini about dialog
        aboutDialog = new aboutDialogBox();
        // ini elements
        TextViewColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        //model
        hsvModel = new hsvModel();
        hsvModel.addObserver(this);
        //Hue
        SeekbarHue = (SeekBar) findViewById(R.id.HueS);
        TextViewHue = (TextView) findViewById(R.id.hue);
        //Saturation
        SeekbarSaturation = (SeekBar) findViewById(R.id.SaturationS);
        TextViewSaturation = (TextView) findViewById(R.id.saturation);
        //Value
        SeekbarValue = (SeekBar) findViewById(R.id.ValueS);
        TextViewValue = (TextView) findViewById(R.id.value);


        //seekbar on change Event
        SeekbarHue.setOnSeekBarChangeListener(this);
        SeekbarSaturation.setOnSeekBarChangeListener(this);
        SeekbarValue.setOnSeekBarChangeListener(this);

        //ColorSwatch long press click event
        TextViewColorSwatch.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                Toast.makeText( getApplicationContext(), getHSVMessage(), Toast.LENGTH_SHORT ).show();
                return true;
            }
        });

        //update view - mvc stuff
        this.updateView();

    }

    // menu About Author
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaboutme, menu);
        return true;
    }

    // About popup
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.aboutMe){
            aboutDialog.show( getFragmentManager(), "About author" );
            return true;
        }

        return false;
    }

    // Observer stuff
    @Override
    public void update(Observable observable, Object o) {
        this.updateView();
    }


    // hsv code
    private String getHSVMessage(){
        return getResources().getString(
                R.string.hsv_values,
                hsvModel.getHue(),
                hsvModel.getSaturation(),
                hsvModel.getValue());
    }


    private void updateColorSwatch() {
        float[] hsvColor = {hsvModel.getHue(), hsvModel.getSaturation()/100.f, hsvModel.getValue()/100.f};
        TextViewColorSwatch.setBackgroundColor(Color.HSVToColor(hsvColor));
    }

    private void updateHueSeekbar(){
        SeekbarHue.setProgress( hsvModel.getHue() );
    }
    private void updateSaturationSeekbar(){
        SeekbarSaturation.setProgress( hsvModel.getSaturation() );
    }
    private void updateValueSeekbar(){
        SeekbarValue.setProgress( hsvModel.getValue() );
    }

    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSeekbar();
        this.updateSaturationSeekbar();
        this.updateValueSeekbar();
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        if ( !b ) {
            return;
        }
        switch ( seekBar.getId() ) {
            case R.id.HueS:
                hsvModel.setHue(SeekbarHue.getProgress());
                TextViewHue.setText(getResources().getString(R.string.hue_progress, i).toUpperCase());
                break;

            case R.id.SaturationS:
                hsvModel.setSaturation(SeekbarSaturation.getProgress());
                TextViewSaturation.setText(getResources().getString(R.string.saturation_progress, i).toUpperCase());
                break;

            case R.id.ValueS:
                hsvModel.setValue(SeekbarValue.getProgress());
                TextViewValue.setText(getResources().getString(R.string.value_progress, i).toUpperCase());
                break;
        }
    }

    // not essential
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        switch (seekBar.getId()) {
            case R.id.HueS:
                TextViewHue.setText(getResources().getString(R.string.hue));
                break;
            case R.id.SaturationS:
                TextViewSaturation.setText(getResources().getString(R.string.saturation));
                break;
            case R.id.ValueS:
                TextViewValue.setText(getResources().getString(R.string.value));
                break;
        }
    }


    // color buttons action
    public void onColorButtonClick(View view) {
        switch (view.getId()){
            case R.id.blackButton:
                hsvModel.setHSV(0, 0, 0);
                break;
            case R.id.redButton:
                hsvModel.setHSV(0, 100, 359);
                break;
            case R.id.limeButton:
                hsvModel.setHSV(120, 76, 80 );
                break;
            case R.id.blueButton:
                hsvModel.setHSV(240, 100, 100);
                break;
            case R.id.yellowButton:
                hsvModel.setHSV(60, 100, 100);
                break;
            case R.id.cyanButton:
                hsvModel.setHSV(180, 100, 100);
                break;
            case R.id.magentaButton:
                hsvModel.setHSV(300, 100, 100);
                break;
            case R.id.silverButton:
                hsvModel.setHSV(0, 0, 75);
                break;
            case R.id.grayButton:
                hsvModel.setHSV(0, 0, 50 );
                break;
            case R.id.maroonButton:
                hsvModel.setHSV(0, 100, 50);
                break;
            case R.id.oliveButton:
                hsvModel.setHSV(60, 100, 50);
                break;
            case R.id.greenButton:
                hsvModel.setHSV(120, 100, 50);
                break;
            case R.id.purpleButton:
                hsvModel.setHSV(300, 100, 50);
                break;
            case R.id.tealButton:
                hsvModel.setHSV(180, 100, 50);
                break;
            case  R.id.navyButton:
                hsvModel.setHSV(240, 100, 50);
                break;
        }

        Toast.makeText( getApplicationContext(), getHSVMessage(), Toast.LENGTH_SHORT ).show();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        int[] hsvColor = { hsvModel.getHue(), hsvModel.getSaturation(), hsvModel.getValue() };
        savedInstanceState.putIntArray("HSVColoerPicker", hsvColor);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        if (savedInstanceState != null && savedInstanceState.containsKey("HSVColoerPicker")){
            int[] hsvColoerPickers = savedInstanceState.getIntArray("HSVColoerPicker");
            if (hsvColoerPickers != null && hsvColoerPickers.length == 3) {
                hsvModel.setHSV(hsvColoerPickers[0],hsvColoerPickers[1],hsvColoerPickers[2]);
            }
        }
    }
}

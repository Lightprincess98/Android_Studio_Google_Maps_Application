package com.example.opscsem2task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

import java.util.Arrays;
import java.util.Map;

public class SettingsVariableStorage {
    private String Name;
    private String email;
    private boolean imperial;
    private boolean metric;
    private boolean artistic;
    private boolean natural;
    private boolean historal;


    public SettingsVariableStorage(){

    }

    public SettingsVariableStorage(String Name){
        this.Name = Name;
    }

    public String getName(){
        return Name;
    }

    public String getEmail() { return email; }

    public boolean isImperial() {
        return imperial;
    }

    public boolean isMetric() {
        return metric;
    }

    public boolean isArtistic() { return artistic; }

    public boolean isNatural() { return natural; }

    public boolean isHistoral() { return historal; }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public void setMetric(boolean value) {this.metric = value; }

    public void setImperial(boolean value) {this.imperial = value; }

    public void setArtistic(boolean value) {this.artistic = value; }

    public void setHistoral(boolean value) {this.historal = value; }

    public void setNatural(boolean value) {this.natural = value; }

    public Map<String,Object> CreateMap(SettingsVariableStorage settings) {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String,Object> map = oMapper.convertValue(settings, Map.class);
        return map;
    }
}

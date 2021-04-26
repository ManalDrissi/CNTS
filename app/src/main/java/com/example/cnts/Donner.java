package com.example.cnts;

import java.util.Date;

public class Donner {
    public String Name;
    public String PhoneNumber;
    public String BloodType;
    public Float Weight;
    public Float Height;
    public String BirthDate;
    public String CreationDate;
    public Integer Donated;

    public Donner (String Name, String BloodType, String CreationDate, Integer Donated) {
        this.Name = Name;
        this.BloodType = BloodType;
        this.CreationDate = CreationDate;
        this.Donated = Donated;
    }
}

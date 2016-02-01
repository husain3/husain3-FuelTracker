package com.example.arshadhusain.fueltracker;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by arshadhusain on 16-01-29.
 */
public class FuelLog {

    private String date;
    private String station;
    private String odometer;
    private String fuelGrade;
    private String fuelAmount;
    private String fuelUnitCost;
    private String fuelCost;

    public FuelLog(String date, String station, String odometer, String fuelGrade, String fuelAmount, String fuelUnitCost) {
        this.date = date;
        this.station = station;
        this.odometer = odometer;
        this.fuelGrade = fuelGrade;
        this.fuelAmount = fuelAmount;
        this.fuelUnitCost = fuelUnitCost;
        //this.fuelCost = fuelCost;
        this.fuelCost = fuelCostCalculation(fuelUnitCost, fuelAmount);
    }

    @Override
    public String toString() {
        //return date.toString() + " | " + message;
        return "Date: " + date + "\nStation: " + station + "\nOdometer: " + odometer + "\nFuel Grade: " + fuelGrade + "\nFuel Amount: " + fuelAmount + "\nFuel Unit Cost: " + fuelUnitCost + "\nFuel Cost: " + fuelCost;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setOdometer(String odometer) {
        boolean isDouble = isDouble(odometer);

        if(isDouble == false)
        {
            this.odometer = "false";
        } else {
            this.odometer = odometer;
        }
    }

    public void setFuelGrade(String fuelGrade) {
        this.fuelGrade = fuelGrade;
    }

    public void setFuelAmount(String fuelAmount) {
        boolean isDouble = isDouble(fuelAmount);



        if(isDouble == false)
        {
            this.fuelAmount = "false";
        } else {
            this.fuelAmount = fuelAmount;
            this.fuelCost = fuelCostCalculation(this.fuelUnitCost, this.fuelAmount);

        }
    }

    public void setFuelUnitCost(String fuelUnitCost) {
        boolean isDouble = isDouble(fuelUnitCost);

        if(isDouble == false)
        {
            this.fuelUnitCost = "false";
        } else {
            this.fuelUnitCost = fuelUnitCost;
            this.fuelCost = fuelCostCalculation(this.fuelUnitCost, this.fuelAmount);
        }

    }

    public void setFuelCost(String fuelCost) {
        boolean isInteger = isInteger(odometer);

        if(isInteger == false)
        {
            this.fuelCost = "false";
        } else {
            this.fuelCost = fuelCost;
        }
    }

    public String getDate() {
        return this.date;
    }

    public String getStation() {
        return this.station;
    }

    public String getOdometer() {
        return this.odometer;
    }

    public String getFuelGrade()
    {
        return this.fuelGrade;
    }

    public String getFuelAmount()
    {
        return this.fuelAmount;
    }

    public String getFuelUnitCost()
    {
        return this.fuelUnitCost;
    }
    public String getFuelCost()
    {
        return this.fuelCost;
    }

    public static boolean isInteger(String s) {
        try
        {
            Integer.parseInt(s);
            // s is a valid integer
            return true;
        }
        catch (NumberFormatException ex)
        {
            return false;
        }
    }

    public String fuelCostCalculation(String fuelUnitCost, String fuelAmount) {
        double intUnitCost = Double.parseDouble(fuelUnitCost);
        double intFuelAmount = Double.parseDouble(fuelAmount);

        round(intUnitCost, 1);
        round(intFuelAmount, 3);

        double intFuelCost = intUnitCost * intFuelAmount;
        intFuelCost = intFuelCost/100;

        intFuelCost = round(intFuelCost, 2);

        String fuelCost = String.valueOf(intFuelCost);

        return fuelCost;
    }


    public static double round(double value, int places) { //Taken from: http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public boolean isDouble(String str) { //Taken from :http://stackoverflow.com/questions/3133770/how-to-find-out-if-the-value-contained-in-a-string-is-double-or-not
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

package com.guillaume2127.travelmilescalculator;

import android.util.Log;

import java.util.Objects;

/*
 * Created by guillaume2127
 */
public class TravelCalculator {
    private String cityOne;
    private String cityTwo;
    private String ticketDiscount;
    //Setters
    public void setCityOne(String cityONE){
        this.cityOne = cityONE;
        Log.d("City One: ",cityOne);
    }
    public void setCityTwo(String cityTWO){
        this.cityTwo = cityTWO;
        Log.d("City Two: ",cityTwo);
    }
    public void setTicketDiscount(String ticketdiscount){
        this.ticketDiscount = ticketdiscount;
        Log.d("Discount: ", ticketDiscount);
    }
    //Getters
    public int getDistance(){
        //Initiating the travel distance variable
        int travelDistance;
        //Regina > Edmonton and Edmonton > Regina
        if(Objects.equals(cityOne, "Regina") && Objects.equals(cityTwo, "Edmonton")
                || Objects.equals(cityOne, "Edmonton") && Objects.equals(cityTwo, "Regina"))
        {travelDistance = 691;}
        //Regina > Vancouver and Vancouver > Regina
        else if(Objects.equals(cityOne, "Regina") && Objects.equals(cityTwo, "Vancouver")
                || Objects.equals(cityOne, "Vancouver") && Objects.equals(cityTwo, "Regina"))
        {travelDistance = 1335;}
        //Edmonton > Vancouver and Vancouver to Edmonton
        else if(Objects.equals(cityOne, "Edmonton") && Objects.equals(cityTwo, "Vancouver")
                || Objects.equals(cityOne, "Vancouver") && Objects.equals(cityTwo, "Edmonton"))
        {travelDistance = 809;}
        else{travelDistance = 0;}
        Log.d("Travel Distance: ",String.valueOf(travelDistance));
        //Returning the travel distance
        return travelDistance;
    }
    public Double getTicketPrice(){
        //Initiating the ticket price variable
        Double ticketPrice;
        //Regina > Edmonton and Edmonton > Regina
        if(Objects.equals(cityOne, "Regina") && Objects.equals(cityTwo, "Edmonton")
                || Objects.equals(cityOne, "Edmonton") && Objects.equals(cityTwo, "Regina"))
        {ticketPrice = 175.00;}
        //Regina > Vancouver and Vancouver > Regina
        else if(Objects.equals(cityOne, "Regina") && Objects.equals(cityTwo, "Vancouver")
                || Objects.equals(cityOne, "Vancouver") && Objects.equals(cityTwo, "Regina"))
        {ticketPrice = 245.00;}
        //Edmonton > Vancouver and Vancouver to Edmonton
        else if(Objects.equals(cityOne, "Edmonton") && Objects.equals(cityTwo, "Vancouver")
                || Objects.equals(cityOne, "Vancouver") && Objects.equals(cityTwo, "Edmonton"))
        {ticketPrice = 195.00;}
        else{ticketPrice = 0.00;}
        Log.d("Ticket Price: ",String.valueOf(ticketPrice));
        //Returning the ticket price
        if(Objects.equals(ticketDiscount, "savings")) {
            Double valueOfDiscount = ticketPrice/10;
            return ticketPrice - valueOfDiscount;
        }
        else{
            return ticketPrice;
        }
    }
    public int getBonusMiles(){
        //Initiating the ticket price variable as a double to prevent dividing errors
        double bonusMiles;
        //Regina > Edmonton and Edmonton > Regina
        if(Objects.equals(cityOne, "Regina") && Objects.equals(cityTwo, "Edmonton")
                || Objects.equals(cityOne, "Edmonton") && Objects.equals(cityTwo, "Regina"))
        {bonusMiles = 691/10;}
        //Regina > Vancouver and Vancouver > Regina
        else if(Objects.equals(cityOne, "Regina") && Objects.equals(cityTwo, "Vancouver")
                || Objects.equals(cityOne, "Vancouver") && Objects.equals(cityTwo, "Regina"))
        {bonusMiles = 1335/18;}
        //Edmonton > Vancouver and Vancouver to Edmonton
        else if(Objects.equals(cityOne, "Edmonton") && Objects.equals(cityTwo, "Vancouver")
                || Objects.equals(cityOne, "Vancouver") && Objects.equals(cityTwo, "Edmonton"))
        {bonusMiles = 809/12;}
        else{bonusMiles = 0;}
        //Converting the double to an int
        int bonusMilesInt = (int)bonusMiles;
        Log.d("Bonus Miles: ",String.valueOf(bonusMilesInt));
        //Returning the bonus miles
        return bonusMilesInt;
    }
}

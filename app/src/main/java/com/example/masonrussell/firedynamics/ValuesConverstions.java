package com.example.masonrussell.firedynamics;

/**
 * Created by masonrussell on 2/15/18.
 */

public class ValuesConverstions {

    public static double toMeters(double measurement, String currentUnits)
    {
        double meters =0;
        switch (currentUnits)
        {
            case "ft":
                meters = measurement * 0.3048780488;
                break;
            case "in":
                meters = measurement * 0.02540650407;
                break;
            case "mm":
                meters = measurement * 0.001;
                break;
            case "cm":
                meters = measurement * 0.01;
                break;
            case "m":
                meters = measurement;
        }
        return meters;
    }

    public static double thermalConductivity(String material)
    {
        double thermalValue=0;
        switch (material)
        {
            case "Aerated Concrete":
                thermalValue=0.00026;
                break;
            case "Alumina Silicate Block":
                thermalValue=0.00014;
                break;
            case "Aluminum (pure)":
                thermalValue=0.206;
                break;
            case "Brick":
                thermalValue=0.0008;
                break;
            case "Brick/Concrete Block":
                thermalValue=0.00073;
                break;
            case "Calcium Silicate Board":
                thermalValue=0.00013;
                break;
            case "Chipboard":
                thermalValue=0.00015;
                break;
            case "Concrete":
                thermalValue=0.0016;
                break;
            case "Expended Polystyrene":
                thermalValue=0.000034;
                break;
            case "Fiber Insulation Board":
                thermalValue=0.00053;
                break;
            case "Glass Fiber Insulation":
                thermalValue=0.000037;
                break;
            case "Glass Plate":
                thermalValue=0.00076;
                break;
            case "Gypsum Board":
                thermalValue=0.00017;
                break;
            case "Plasterboard":
                thermalValue=0.00016;
                break;
            case "Plywood":
                thermalValue=0.00012;
                break;
            case "Steel (0.5% Carbon)":
                thermalValue=0.054;
                break;
        }
        return thermalValue;
    }
}

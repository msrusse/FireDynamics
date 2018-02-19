package com.example.masonrussell.firedynamics;

/**
 * Created by masonrussell on 2/15/18.
 */

class ValuesConverstions {

    static double toMeters(double measurement, String currentUnits)
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

    static double toCentimeters(double measurement, String currentUnits)
    {
        double centimeters=0;
        switch (currentUnits)
        {
            case "ft":
                centimeters = (measurement * 0.3048780488) / 0.01;
                break;
            case "in":
                centimeters = (measurement * 0.02540650407) / 0.01;
                break;
            case "mm":
                centimeters = measurement * 0.1;
                break;
            case "cm":
                centimeters = measurement;
                break;
            case "m":
                centimeters = measurement / 0.01;
        }
        return centimeters;
    }

    static double toMillimeters(double measurement, String currentUnits)
    {
        double millimeters=0;
        switch (currentUnits)
        {
            case "ft":
                millimeters = (measurement * 0.3048780488) / 0.001;
                break;
            case "in":
                millimeters = (measurement * 0.02540650407) / 0.001;
                break;
            case "mm":
                millimeters = measurement;
                break;
            case "cm":
                millimeters = measurement / 0.1;
                break;
            case "m":
                millimeters = measurement / 0.001;
        }
        return millimeters;
    }

    static double toFeet(double measuerement, String currentUnits)
    {
        double feet=0;
        switch (currentUnits)
        {
            case "ft":
                feet = measuerement;
                break;
            case "in":
                feet = measuerement / 12;
                break;
            case "mm":
                feet = measuerement / (0.3048780488*0.001);
                break;
            case "cm":
                feet = measuerement / (0.3048780488*0.01);
                break;
            case "m":
                feet = measuerement / (0.3048780488);
        }
        return feet;
    }

    static double toInches(double measurement, String currentUnits)
    {
        double inches=0;
        switch (currentUnits)
        {
            case "ft":
                inches = measurement * 12;
                break;
            case "in":
                inches = measurement;
                break;
            case "mm":
                inches = (measurement / (0.3048780488*0.001)) * 12;
                break;
            case "cm":
                inches = (measurement / (0.3048780488*0.001)) * 12;
                break;
            case "m":
                inches = (measurement / (0.3048780488)) * 12;
        }
        return inches;
    }

    static double toSquareMeters(double measurement, String currentUnits)
    {
        double meters =0;
        switch (currentUnits)
        {
            case "ft^2":
                meters = measurement * 0.3048780488;
                break;
            case "in^2":
                meters = measurement * 0.02540650407;
                break;
            case "mm^2":
                meters = measurement * 0.001;
                break;
            case "cm^2":
                meters = measurement * 0.01;
                break;
            case "m^2":
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

    public static double FuelHeatCombustion(String fuel)
    {
        double heatCombustion=0;
        switch(fuel)
        {
            case "Cellulose":
                heatCombustion=16.1;
                break;
            case "Gasoline":
                heatCombustion=43.7;
                break;
            case "Heptane":
                heatCombustion=44.6;
                break;
            case "Methanol":
                heatCombustion=19.8;
                break;
            case "PMMA":
                heatCombustion=24.9;
                break;
            case "Polythylene":
                heatCombustion=43.3;
                break;
            case "Polypropylene":
                heatCombustion=43.3;
                break;
            case "Polystyrene":
                heatCombustion=39.8;
                break;
            case "PVC":
                heatCombustion=16.4;
                break;
            case "Wood":
                heatCombustion=14;
                break;
        }
        return heatCombustion;
    }

    public static double FuelBurningFlux(String fuel)
    {
        double burningFlux=0;
        switch(fuel)
        {
            case "Cellulose":
                burningFlux=14;
                break;
            case "Gasoline":
                burningFlux=55;
                break;
            case "Heptane":
                burningFlux=70;
                break;
            case "Methanol":
                burningFlux=22;
                break;
            case "PMMA":
                burningFlux=28;
                break;
            case "Polythylene":
                burningFlux=26;
                break;
            case "Polypropylene":
                burningFlux=24;
                break;
            case "Polystyrene":
                burningFlux=38;
                break;
            case "PVC":
                burningFlux=16;
                break;
            case "Wood":
                burningFlux=11;
                break;
        }
        return burningFlux;
    }

    static double EnergyDensitytoBtu(double energy, String newUnits)
    {
        double newEnergy = 0;
        switch (newUnits)
        {
            case "kW/m^2":
                newEnergy = energy * 0.088055092;
                break;
            case "Btu/sec/ft^2":
                newEnergy = energy;
                break;
        }
        return newEnergy;
    }

    static double EnergyDensitytokW(double energy, String newUnits)
    {
        double newEnergy = 0;
        switch (newUnits)
        {
            case "kW/m^2":
                newEnergy = energy;
                break;
            case "Btu/sec/ft^2":
                newEnergy = energy / 0.088055092;
                break;
        }
        return newEnergy;
    }
}

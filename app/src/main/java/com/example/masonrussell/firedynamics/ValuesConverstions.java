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
                meters = measurement / 3.28;
                break;
            case "in":
                meters = measurement / 12 / 3.28;
                break;
            case "mm":
                meters = measurement / 1000;
                break;
            case "cm":
                meters = measurement / 100;
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

    static double toFeet(double measurement, String currentUnits)
    {
        double feet=0;
        switch (currentUnits)
        {
            case "ft":
                feet = measurement;
                break;
            case "in":
                feet = measurement / 12;
                break;
            case "mm":
                feet = measurement / (0.003048780488);
                break;
            case "cm":
                feet = measurement / (0.03048780488);
                break;
            case "m":
                feet = measurement / (0.3048780488);
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

    static double thermalConductivity(String material)
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

    static double FuelHeatCombustion(String fuel)
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

    static double FuelBurningFlux(String fuel)
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

    static double PressureToMbar(double pressure, String currentUnits)
    {
        double mbarPressure = 0;
        switch (currentUnits)
        {
            case "in H2O":
                mbarPressure = pressure * 2.490889083;
                break;
            case "kPa":
                mbarPressure = pressure * 10;
                break;
            case "mbar":
                mbarPressure = pressure;
                break;
            case "psi":
                mbarPressure = pressure * 68.9475728;
                break;
        }
        return mbarPressure;
    }

    static double FlowtoMetersCubedPerHour(double flow, String currentUnits)
    {
        double metersCubed = 0;
        switch (currentUnits)
        {
            case "cfm":
                metersCubed = flow / 0.588125867;
                break;
            case "ft^3/sec":
                metersCubed = flow / 0.009802098;
                break;
            case "m^3/hr":
                metersCubed = flow;
                break;
            case "m^3/sec":
                metersCubed = flow * 3600;
                break;
        }
        return  metersCubed;
    }

    static double Flowtocfm(double flow, String currentUnits)
    {
        double cfm = 0;
        switch (currentUnits)
        {
            case "cfm":
                cfm = flow;
                break;
            case "ft^3/sec":
                cfm = flow / 0.009802098 * 0.588125867;
                break;
            case "m^3/hr":
                cfm = flow * 0.588125867;
                break;
            case "m^3/sec":
                cfm = flow * 3600 * 0.588125867;
                break;
        }
        return  cfm;
    }

    static double FlowtoFeetCubedPerSec(double flow, String currentUnits)
    {
        double ftcubed = 0;
        switch (currentUnits)
        {
            case "cfm":
                ftcubed = flow / 0.588125867 * 0.009802098;
                break;
            case "ft^3/sec":
                ftcubed = flow;
                break;
            case "m^3/hr":
                ftcubed = flow * 0.009802098;
                break;
            case "m^3/sec":
                ftcubed = flow * 3600 * 0.009802098;
                break;
        }
        return  ftcubed;
    }

    static double FlowtoMetersCubedPerSec(double flow, String currentUnits) {
        double mcubed = 0;
        switch (currentUnits)
        {
            case "cfm":
                mcubed = flow / 0.588125867 * 3600;
                break;
            case "ft^3/sec":
                mcubed = flow / 0.009802098 * 3600;
                break;
            case "m^3/hr":
                mcubed = flow / 3600;
                break;
            case "m^3/sec":
                mcubed = flow;
                break;
        }
        return mcubed;
    }

    static double ConductionThermalConductivity(String material)
    {
        double thermalConduct = 0;
        switch (material)
        {
            case "Air":
                thermalConduct = 0.026;
                break;
            case "Asbestos":
                thermalConduct = 0.15;
                break;
            case "Brick":
                thermalConduct = 0.69;
                break;
            case "Concrete (high)":
                thermalConduct = 1.4;
                break;
            case "Concrete (low)":
                thermalConduct = 0.8;
                break;
            case "Copper":
                thermalConduct = 387;
                break;
            case "Fiber Insulating Board":
                thermalConduct = 0.041;
                break;
            case "Glass (plate)":
                thermalConduct = 0.76;
                break;
            case "Gypsum Plaster":
                thermalConduct = 0.48;
                break;
            case "Oak":
                thermalConduct = 0.17;
                break;
            case "PMMA":
                thermalConduct = 0.19;
                break;
            case "Polyurethane Foam":
                thermalConduct = 0.034;
                break;
            case "Steel (mild)":
                thermalConduct = 45.8;
                break;
            case "Yellow Pine":
                thermalConduct = 0.14;
                break;
        }
        return thermalConduct;
    }

    static double ConductionSpecificHeat(String material)
    {
        double specificHeat = 0;
        switch (material)
        {
            case "Air":
                specificHeat = 1.04;
                break;
            case "Asbestos":
                specificHeat = 1.05;
                break;
            case "Brick":
                specificHeat = 0.84;
                break;
            case "Concrete (high)":
                specificHeat = 0.88;
                break;
            case "Concrete (low)":
                specificHeat = 0.88;
                break;
            case "Copper":
                specificHeat = 0.38;
                break;
            case "Fiber Insulating Board":
                specificHeat = 2.09;
                break;
            case "Glass (plate)":
                specificHeat = 0.84;
                break;
            case "Gypsum Plaster":
                specificHeat = 0.84;
                break;
            case "Oak":
                specificHeat = 2.38;
                break;
            case "PMMA":
                specificHeat = 1.42;
                break;
            case "Polyurethane Foam":
                specificHeat = 1.4;
                break;
            case "Steel (mild)":
                specificHeat = 0.46;
                break;
            case "Yellow Pine":
                specificHeat = 2.85;
                break;
        }
        return specificHeat;
    }

    static double ConductionDensity(String material)
    {
        double density = 0;
        switch (material)
        {
            case "Air":
                density = 1.1;
                break;
            case "Asbestos":
                density = 577;
                break;
            case "Brick":
                density = 1600;
                break;
            case "Concrete (high)":
                density = 2300;
                break;
            case "Concrete (low)":
                density = 1900;
                break;
            case "Copper":
                density = 8940;
                break;
            case "Fiber Insulating Board":
                density = 229;
                break;
            case "Glass (plate)":
                density = 2700;
                break;
            case "Gypsum Plaster":
                density = 1440;
                break;
            case "Oak":
                density = 800;
                break;
            case "PMMA":
                density = 1190;
                break;
            case "Polyurethane Foam":
                density = 20;
                break;
            case "Steel (mild)":
                density = 7850;
                break;
            case "Yellow Pine":
                density = 640;
                break;
        }
        return density;
    }

    static double ConductionThermalInertia(String material)
    {
        double thermalInertia = 0;
        switch (material)
        {
            case "Air":
                thermalInertia = 0.00003;
                break;
            case "Asbestos":
                thermalInertia = 0.091;
                break;
            case "Brick":
                thermalInertia = 0.93;
                break;
            case "Concrete (high)":
                thermalInertia = 2;
                break;
            case "Concrete (low)":
                thermalInertia = 2;
                break;
            case "Copper":
                thermalInertia = 1300;
                break;
            case "Fiber Insulating Board":
                thermalInertia = 0.02;
                break;
            case "Glass (plate)":
                thermalInertia = 1.7;
                break;
            case "Gypsum Plaster":
                thermalInertia = 0.58;
                break;
            case "Oak":
                thermalInertia = 0.32;
                break;
            case "PMMA":
                thermalInertia = 0.32;
                break;
            case "Polyurethane Foam":
                thermalInertia = 0.00095;
                break;
            case "Steel (mild)":
                thermalInertia = 160;
                break;
            case "Yellow Pine":
                thermalInertia = 0.25;
                break;
        }
        return thermalInertia;
    }

    static double toDegreesCentigrade(double currentTemp, String currentUnits)
    {
        double centigrade = currentTemp;
        switch (currentUnits)
        {
            case "C":
                centigrade = currentTemp;
                break;
            case "F":
                centigrade = (currentTemp-32) * (5/9);
                break;
            case "K":
                centigrade = currentTemp - 273.15;
                break;
            case "R":
                centigrade = (currentTemp - 459.67 - 32) * (5/9);
                break;
        }
        return centigrade;
    }

    static double timeToHours(double currentTime, String currentUnits)
    {
        double timeInHours = currentTime;
        switch (currentUnits)
        {
            case "sec":
                timeInHours = currentTime / 3600;
                break;
            case "min":
                timeInHours = currentTime / 60;
                break;
            case "hour":
                timeInHours = currentTime;
                break;
        }
        return timeInHours;
    }

    static double timeToMinutes(double currentTime, String currentUnits)
    {
        double timeInMinutes = currentTime;
        switch (currentUnits)
        {
            case "sec":
                timeInMinutes = currentTime / 60;
                break;
            case "min":
                timeInMinutes = currentTime;
                break;
            case "hour":
                timeInMinutes = currentTime * 60;
                break;
        }
        return  timeInMinutes;
    }

    static double timeToSeconds(double currentTime, String currentUnits)
    {
        double timeInSeconds = currentTime;
        switch (currentUnits)
        {
            case "sec":
                timeInSeconds = currentTime;
                break;
            case "min":
                timeInSeconds = currentTime * 60;
                break;
            case "hour":
                timeInSeconds = currentTime * 3600;
                break;
        }
        return timeInSeconds;
    }

    static double heatFluxToBtuPerSecondPerSquaredFeet(double currentHeatFlux, String currentUnits)
    {
        double btuHeatFlux = currentHeatFlux;
        switch (currentUnits)
        {
            case "Btu/sec/ft^2":
                btuHeatFlux = currentHeatFlux;
                break;
            case "kW/m^2":
                btuHeatFlux = currentHeatFlux / 11.3565267;
                break;
        }
        return btuHeatFlux;
    }

    static double heatFluxToKillowattPerSquaredMeters(double currentHeatFlux, String currentUnits)
    {
        double kwHeatFlux = currentHeatFlux;
        switch (currentUnits)
        {
            case "Btu/sec/ft^2":
                kwHeatFlux = currentHeatFlux * 11.3565267;
                break;
            case "kW/m^2":
                kwHeatFlux = currentHeatFlux;
                break;
        }
        return kwHeatFlux;
    }
}

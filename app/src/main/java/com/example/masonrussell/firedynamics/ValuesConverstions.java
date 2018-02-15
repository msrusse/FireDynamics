package com.example.masonrussell.firedynamics;

/**
 * Created by masonrussell on 2/15/18.
 */

public class ValuesConverstions {

    public double toMeters(int measurement, String currentUnits)
    {
        switch (currentUnits)
        {
            case "ft":
                return measurement * 0.3048780488;
        }
    }
}

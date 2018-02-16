package com.example.masonrussell.firedynamics;

/**
 * Created by masonrussell on 2/15/18.
 */

class Calculations
{
    static double Calculatehk(double thermalConductivity, double intLiningThickness)
    {
        return thermalConductivity/intLiningThickness;
    }

    static double CalculateAv(double ventWidth, double ventHeight)
    {
        return ventHeight*ventWidth;
    }

    static double CalculateAT(double compWidth, double compLength, double compHeight, double ventWidth, double ventHeight)
    {
        return ((2*(compWidth*compLength) + 2*(compHeight*compWidth) + 2*(compHeight*compLength)) - CalculateAv(ventWidth, ventHeight));
    }

    //This method calculates the Value of Q according to the Method of McCaffrey, Quintiera, and Harkleroad in kW
    static double CalculateMccaffreyQ(double hk, double at, double av, double ventHeight)
    {
        return (610 * Math.sqrt(hk*at*av*Math.sqrt(ventHeight)));
    }
    //This method calculates the value of Q according to the method of Babrauskas
    static double CalculateBarbraukas(double av, double ventHeight)
    {
        return (750 * av * Math.sqrt(ventHeight));
    }
    //This method calculates the value of Q according to the method of Thomas
    static double CalculateThomas(double av, double at, double ventHeight)
    {
        return (7.8 * at + 378 * av * Math.sqrt(ventHeight));
    }
    //This method converts the Q calculation from kW to Btu/sec
    static double CalculateBtuPerSec(double kw)
    {
        return (kw * 1.055055852);
    }
}

package com.example.masonrussell.firedynamics;

/**
 * Created by masonrussell on 2/15/18.
 */

public class Calculations
{
    public static double Calculatehk(double thermalConductivity, double intLiningThickness)
    {
        return thermalConductivity/intLiningThickness;
    }

    public static double CalculateAv(double ventWidth, double ventHeight)
    {
        return ventHeight*ventWidth;
    }

    public static double CalculateAT(double compWidth, double compLength, double compHeight, double ventWidth, double ventHeight)
    {
        return ((2*(compWidth*compLength) + 2*(compHeight*compWidth) + 2*(compHeight*compLength)) - CalculateAv(ventWidth, ventHeight));
    }

    //This method calculates the Value of Q according to the Method of McCaffrey, Quintiera, and Harkleroad in kW
    public static double CalculateMccaffreyQ(double hk, double at, double av, double ventHeight)
    {
        return (610 * Math.sqrt(hk*at*av*Math.sqrt(ventHeight)));
    }
}

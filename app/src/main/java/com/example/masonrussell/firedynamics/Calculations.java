package com.example.masonrussell.firedynamics;

/**
 * Created by masonrussell on 2/15/18.
 */

class Calculations
{
    static double Calculatehk(double thermalConductivity, double intLiningThickness) { return thermalConductivity/intLiningThickness; }

    static double CalculateAv(double ventWidth, double ventHeight)
    {
        return ventHeight*ventWidth;
    }

    static double CalculateAT(double compWidth, double compLength, double compHeight, double ventWidth, double ventHeight) { return ((2*(compWidth*compLength) + 2*(compHeight*compWidth) + 2*(compHeight*compLength)) - CalculateAv(ventWidth, ventHeight)); }

    static double CalculateTGasLayerAT(double compWidth, double compLength, double compHeight, double ao) { return ((2*(compWidth*compLength) + 2*(compHeight*compWidth) + 2*(compHeight*compLength)) - ao); }

    //This method calculates the Value of Q according to the Method of McCaffrey, Quintiera, and Harkleroad in kW
    static double CalculateMccaffreyQ(double hk, double at, double av, double ventHeight) { return (610 * Math.sqrt(hk*at*av*Math.sqrt(ventHeight))); }

    //This method calculates the value of Q according to the method of Babrauskas
    static double CalculateBarbraukas(double av, double ventHeight) { return (750 * av * Math.sqrt(ventHeight)); }

    //This method calculates the value of Q according to the method of Thomas
    static double CalculateThomas(double av, double at, double ventHeight) { return (7.8 * at + 378 * av * Math.sqrt(ventHeight)); }

    //This method converts the Q calculation from kW to Btu/sec
    static double CalculateBtuPerSec(double kw)
    {
        return (kw * 1.055055852);
    }

    static double CalculateArea(double radius)
    {
        return (Math.PI * (Math.pow(radius,2)));
    }

    static double CalculateHRRQ(double mbf, double ehc, double area)
    {
        return (mbf*ehc*area);
    }

    static double CalculateDiameterFromArea(double diameter) { return (2 * Math.sqrt(diameter/Math.PI)); }

    static double CalculateDiameterFromLengthWidth(double length, double width) { return Math.sqrt(4*((length) * width) / Math.PI); }

    static double CalculateFlameHeight(double q, double diameter) { return (0.23 * Math.pow(q,0.4)-1.02*diameter); }

    static double CalculateHeatFluxtoTarget(double l, double d) { return (15.4 * Math.pow((l/d),-1.59)); }

    static double CalculateOpenPipeQ(double dP, double d, double l, double sg) { return (0.00403*Math.pow(dP*(Math.pow(d,4.8)/(Math.pow(sg,0.8)*l)),0.555)); }

    static double CalculateConductiveHeatFlux(double length, double thermalConductivity, double hotSideTemp, double coldSideTemp) { return (thermalConductivity * (hotSideTemp-coldSideTemp)/length); }

    static double CalculateConductiveThermalPenTime(double length, double thermalDiffucivity) { return (Math.pow(length,2)/(16*thermalDiffucivity)); }

    static double CalculateThermallyThinTimeToIgnition(double density, double specificHeat, double thickness, double ignitionTemp, double ambientTemp, double heatFlux) { return ((density*specificHeat*thickness*(ignitionTemp-ambientTemp))/heatFlux); }

    static double CalculateThermallyThickTimeToIgnition(double c, double density, double specificHeat, double thermalConductivity, double ignitionTemp, double ambientTemp, double heatFlux) { return (c*thermalConductivity*density*specificHeat*(Math.pow((ignitionTemp-ambientTemp)/heatFlux,2))); }

    static double CalculateThermallyThickTimeToIgnitionWithMaterialSelected(double thermalinertia, double ignitionTemp, double criticalIgnitionFlux, double c, double ambientTemp, double heatFluxExposure)
    {
        if (heatFluxExposure > criticalIgnitionFlux)
        {
            return (c*thermalinertia*Math.pow((ignitionTemp-ambientTemp)/heatFluxExposure,2));
        }
        else
        {
            return 0;
        }
    }

    static double CalculateTempOfUpperGasLayerAccordingtoMQH(double q, double ambientTemp, double hk, double ao, double at, double ho)
    {
        double power = (1.0/3.0);
        double firstCalculation = Math.pow(q,2)/(ao*Math.sqrt(ho)*hk*at);
        return (6.85*Math.pow(firstCalculation,power)+ambientTemp);
    }
}

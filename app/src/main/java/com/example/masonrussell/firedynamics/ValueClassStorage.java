package com.example.masonrussell.firedynamics;

import java.util.HashMap;
import java.util.Map;

public class ValueClassStorage {

    public class QFlashover {
        double compartmentWidth, compartmentLength, compartmentHeight, ventWidth, ventHeight, interiorLiningThickness;
        String interiorLiningMaterial;

        public QFlashover(double compartmentWidth, double compartmentLength, double compartmentHeight, double ventWidth, double ventHeight, double interiorLiningThickness, String interiorLiningMaterial) {
            this.compartmentWidth = compartmentWidth;
            this.compartmentLength = compartmentLength;
            this.compartmentHeight = compartmentHeight;
            this.ventWidth = ventWidth;
            this.ventHeight = ventHeight;
            this.interiorLiningThickness = interiorLiningThickness;
            this.interiorLiningMaterial = interiorLiningMaterial;
        }

        public void setValues(double compartmentWidth, double compartmentLength, double compartmentHeight, double ventWidth, double ventHeight, double interiorLiningThickness, String interiorLiningMaterial) {
            this.compartmentWidth = compartmentWidth;
            this.compartmentLength = compartmentLength;
            this.compartmentHeight = compartmentHeight;
            this.ventWidth = ventWidth;
            this.ventHeight = ventHeight;
            this.interiorLiningThickness = interiorLiningThickness;
            this.interiorLiningMaterial = interiorLiningMaterial;
        }

        public Map<String, Object> getValues()
        {
            return new HashMap<String, Object>(){{
                put(CommonlyUsedVariables.compartmentWidth, compartmentWidth);
                put(CommonlyUsedVariables.compartmentHeight, compartmentHeight);
                put(CommonlyUsedVariables.interiorLiningThickness, interiorLiningThickness);
                put(CommonlyUsedVariables.compartmentLength, compartmentLength);
                put(CommonlyUsedVariables.ventHeight, ventHeight);
                put(CommonlyUsedVariables.ventWidth, ventWidth);
            }};
        }

    }
    public class T2Fires
    {
        double t1Doub, peakHrrDoub, timeIntervalDoub;

        public T2Fires(double t1Doub, double peakHrrDoub, double timeIntervalDoub)
        {
            this.t1Doub = t1Doub;
            this.peakHrrDoub = peakHrrDoub;
            this.timeIntervalDoub = timeIntervalDoub;
        }

        public void setT2FireValues(double t1Doub, double peakHrrDoub, double timeIntervalDoub)
        {
            this.t1Doub = t1Doub;
            this.peakHrrDoub = peakHrrDoub;
            this.timeIntervalDoub = timeIntervalDoub;
        }

        public Map<String, Double> getValues()
        {
            return new HashMap<String, Double>(){{
                put("t1Double", t1Doub);
                put("peakHrrDouble", peakHrrDoub);
                put("timeIntervalDouble", timeIntervalDoub);
            }};
        }
    }
}

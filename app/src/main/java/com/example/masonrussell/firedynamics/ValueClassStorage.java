package com.example.masonrussell.firedynamics;

import java.util.HashMap;
import java.util.Map;

public class ValueClassStorage {

    public static T2Fires t2Fires;
    public static QFlashover qFlashover;
    public static Conduction conduction;
    public static FlameHeight flameHeight;
    public static RadiationPoolFire radiationPoolFire;
    public static SolidIgnition solidIgnition;
    public static OpenPipe openPipe;
    public static GasConcentration gasConcentration;
    public static GasAmount gasAmount;
    public static HRR hrr;
    public static OxygenLevels oxygenLevels;
    public static SelfHeating selfHeating;
    public static TGasLayer tGasLayer;

    public class QFlashover {
        double compartmentWidth, compartmentLength, compartmentHeight, ventWidth, ventHeight, interiorLiningThickness, mqh, babrauskas, thomas;
        String interiorLiningMaterial;

        public QFlashover(double compartmentWidth, double compartmentLength, double compartmentHeight, double ventWidth, double ventHeight, double interiorLiningThickness, String interiorLiningMaterial, double mqh, double babrauskas, double thomas) {
            this.compartmentWidth = compartmentWidth;
            this.compartmentLength = compartmentLength;
            this.compartmentHeight = compartmentHeight;
            this.ventWidth = ventWidth;
            this.ventHeight = ventHeight;
            this.interiorLiningThickness = interiorLiningThickness;
            this.interiorLiningMaterial = interiorLiningMaterial;
            this.mqh = mqh;
            this.babrauskas = babrauskas;
            this.thomas = thomas;
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
                put("mqh", mqh);
                put("babrauskas", babrauskas);
                put("thomas", thomas);
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

        public Map<String, Double> getValues()
        {
            return new HashMap<String, Double>(){{
                put("t1Double", t1Doub);
                put("peakHrrDouble", peakHrrDoub);
                put("timeIntervalDouble", timeIntervalDoub);
            }};
        }
    }

    public class Conduction
    {
        double lengthDoub, hotSideDoub, coldSideDoub, heatFluxDoub, thermalPenetrationTimeDoub;
        String materialSelected;

        public Conduction(double lengthDoub, double hotSideDoub, double coldSideDoub, double heatFluxDoub, double thermalPenetrationTimeDoub, String materialSelected)
        {
            this.lengthDoub = lengthDoub;
            this.hotSideDoub = hotSideDoub;
            this.coldSideDoub = coldSideDoub;
            this.heatFluxDoub = heatFluxDoub;
            this.thermalPenetrationTimeDoub = thermalPenetrationTimeDoub;
            this.materialSelected = materialSelected;
        }

        public Map<String, Object> getValues()
        {
            return new HashMap<String, Object>(){{
                put("lengthDoub", lengthDoub);
                put("hotSideDoub", hotSideDoub);
                put("coldSideDoub", coldSideDoub);
                put("heatFluxDoub", heatFluxDoub);
                put("thermalPenetrationTimeDoub", thermalPenetrationTimeDoub);
                put("selectedMaterial", materialSelected);
            }};
        }
    }

    public class FlameHeight
    {
        double qHeatReleaseDoub, diameterDoub;

        public FlameHeight(double qHeatReleaseDoub, double diameterDoub)
        {
            this.qHeatReleaseDoub = qHeatReleaseDoub;
            this.diameterDoub = diameterDoub;
        }

        public Map<String, Double> getValues()
        {
            return new HashMap<String, Double>(){{
              put("qHeatRelease", qHeatReleaseDoub);
              put("diameter", diameterDoub);
            }};
        }
    }

    public class RadiationPoolFire
    {
        double targetDistanceDoub, diameterDoub;

        public RadiationPoolFire(double targetDistanceDoub, double diameterDoub)
        {
            this.targetDistanceDoub = targetDistanceDoub;
            this.diameterDoub = diameterDoub;
        }

        public Map<String, Object> getValues()
        {
            return new HashMap<String, Object>(){{
                put("targetDistance", targetDistanceDoub);
                put("diameter", diameterDoub);
            }};
        }
    }

    public class SolidIgnition
    {
        String ignitionType, material;
        double densityDoub, specificHeatDoub, thicknessDoub, ignitionTempDoub, ambientTempDoub, heatFluxExposureDoub, cDoub, thermalConductivityDoub;

        public SolidIgnition(String ignitionType, double densityDoub, double specificHeatDoub, double thicknessDoub, double ignitionTempDoub, double ambientTempDoub, double heatFluxExposureDoub, double cDoub, String material, double thermalConductivityDoub)
        {
            this.ignitionType = ignitionType;
            this.densityDoub = densityDoub;
            this.specificHeatDoub = specificHeatDoub;
            this.thicknessDoub = thicknessDoub;
            this.ignitionTempDoub = ignitionTempDoub;
            this.ambientTempDoub = ambientTempDoub;
            this.heatFluxExposureDoub = heatFluxExposureDoub;
            this.cDoub = cDoub;
            this.material = material;
            this.thermalConductivityDoub = thermalConductivityDoub;
        }

        public Map<String, Object> getValues()
        {
            return new HashMap<String, Object>(){{
                put("ignitionType", ignitionType);
                put("density", densityDoub);
                put("specificHeat", specificHeatDoub);
                put("thickness", thicknessDoub);
                put("ignitionTemp", ignitionTempDoub);
                put("ambientTemp", ambientTempDoub);
                put("heatFluxExposure", heatFluxExposureDoub);
                put("cDoub", cDoub);
                put("material", material);
                put("thermalConductivity", thermalConductivityDoub);
            }};
        }
    }

    public class OpenPipe
    {
        double pressureDropDoub, pipeDiameterDoub, lengthDoub, specificGravityDoub, qDoub;

        public OpenPipe(double pressureDropDoub, double pipeDiameterDoub, double lengthDoub, double specificGravityDoub, double qDoub)
        {
            this.pressureDropDoub = pressureDropDoub;
            this.pipeDiameterDoub = pipeDiameterDoub;
            this.lengthDoub = lengthDoub;
            this.specificGravityDoub = specificGravityDoub;
            this.qDoub = qDoub;
        }

        public Map<String, Double> getValues()
        {
            return new HashMap<String, Double>(){{
                put("pressureDrop", pressureDropDoub);
                put("pipeDiameter", pipeDiameterDoub);
                put("length", lengthDoub);
                put("specificGravity", specificGravityDoub);
                put("q", qDoub);
            }};
        }
    }

    public class GasConcentration
    {
        double airChangesDoub, leakageRateDoub, gasFilledAreaVolumeDoub, timestepDoub;

        public GasConcentration(double airChangesDoub, double leakageRateDoub, double gasFilledAreaVolumeDoub, double timestepDoub)
        {
            this.airChangesDoub = airChangesDoub;
            this.leakageRateDoub = leakageRateDoub;
            this.gasFilledAreaVolumeDoub = gasFilledAreaVolumeDoub;
            this.timestepDoub = timestepDoub;
        }

        public Map<String, Double> getValues()
        {
            return new HashMap<String, Double>(){{
                put("airChanges", airChangesDoub);
                put("leakageRate", leakageRateDoub);
                put("gasFilledAreaVolume", gasFilledAreaVolumeDoub);
                put("timestep", timestepDoub);
            }};
        }
    }

    public class GasAmount
    {
        String material;
        double areaDoub, heightDoub, lelDoub, stoichiometricDoub, uelDoub, vaporDensityDoub, liquidDensityDoub;

        public GasAmount(String material, double areaDoub, double heightDoub, double lelDoub, double stoichiometricDoub, double uelDoub, double vaporDensityDoub, double liquidDensityDoub)
        {
            this.material = material;
            this.areaDoub = areaDoub;
            this.heightDoub = heightDoub;
            this.lelDoub = lelDoub;
            this.stoichiometricDoub = stoichiometricDoub;
            this.uelDoub = uelDoub;
            this.vaporDensityDoub = vaporDensityDoub;
            this.liquidDensityDoub = liquidDensityDoub;
        }

        public Map<String, Object> getValues()
        {
            return new HashMap<String, Object>(){{
                put("material", material);
                put("area", areaDoub);
                put("height", heightDoub);
                put("lel", lelDoub);
                put("stoichiometric", stoichiometricDoub);
                put("uel", uelDoub);
                put("vaporDensity", vaporDensityDoub);
                put("liquidDensity", liquidDensityDoub);
            }};
        }
    }

    public class OxygenLevels
    {
        double roomWidthDoub, roomLengthDoub, roomHeightDoub, initialOxygenPercentDoub, qDoub, timestepDoub;
        String fireType, time, graphType;

        public OxygenLevels(double roomWidthDoub, double roomLengthDoub, double roomHeightDoub, double initialOxygenPercentDoub, double qDoub, double timestepDoub, String fireType, String time, String graphType)
        {
            this.roomWidthDoub = roomWidthDoub;
            this.roomLengthDoub = roomLengthDoub;
            this.roomHeightDoub = roomHeightDoub;
            this.initialOxygenPercentDoub = initialOxygenPercentDoub;
            this.qDoub = qDoub;
            this.timestepDoub = timestepDoub;
            this.fireType = fireType;
            this.time = time;
            this.graphType = graphType;
        }

        public Map<String, Object> getValues()
        {
            return new HashMap<String, Object>(){{
                put("width", roomWidthDoub);
                put("length", roomLengthDoub);
                put("height", roomHeightDoub);
                put("initialOxygenPercent", initialOxygenPercentDoub);
                put("q", qDoub);
                put("timestep", timestepDoub);
                put("fireType", fireType);
                put("time", time);
                put("graphType", graphType);
            }};
        }
    }

    public class SelfHeating
    {
        double pileVolumeDoub, pileHeightDoub, temperatureDoub, mDoub, pDoub;
        String material;

        public SelfHeating(double pileVolumeDoub, double pileHeightDoub, String material, double temperatureDoub, double mDoub, double pDoub)
        {
            this.pileVolumeDoub = pileVolumeDoub;
            this.pileHeightDoub = pileHeightDoub;
            this.material = material;
            this.temperatureDoub = temperatureDoub;
            this.mDoub = mDoub;
            this.pDoub = pDoub;
        }

        public Map<String, Object> getValues()
        {
            return new HashMap<String, Object>(){{
                put("pileVolume", pileVolumeDoub);
                put("pileHeight", pileHeightDoub);
                put("material", material);
                put("temperature", temperatureDoub);
                put("p", pDoub);
                put("m", mDoub);
            }};
        }
    }

    public class TGasLayer
    {
        double compLengthDoub, compWdithDoub, compHeightDoub, ventWidthDoub, ventHeightDoub, intLiningThicknessDoub, qDoub, ambientTempDoub;
        String material;

        public TGasLayer(double compLengthDoub, double compWdithDoub, double compHeightDoub, double ventWidthDoub, double ventHeightDoub, double intLiningThicknessDoub, double qDoub, double ambientTempDoub, String material)
        {
            this.compLengthDoub = compLengthDoub;
            this.compWdithDoub = compWdithDoub;
            this.compHeightDoub = compHeightDoub;
            this.ventWidthDoub = ventWidthDoub;
            this.ventHeightDoub = ventHeightDoub;
            this.intLiningThicknessDoub = intLiningThicknessDoub;
            this.qDoub = qDoub;
            this.ambientTempDoub = ambientTempDoub;
            this.material = material;
        }

        public Map<String, Object> getValues()
        {
            return new HashMap<String, Object>(){{
                put("compLength", compLengthDoub);
                put("compWidth", compWdithDoub);
                put("compHeight", compHeightDoub);
                put("ventWidth", ventWidthDoub);
                put("ventHeight", ventHeightDoub);
                put("intLiningThickness", intLiningThicknessDoub);
                put("q", qDoub);
                put("ambientTemp", ambientTempDoub);
                put("material", material);
            }};
        }
    }

    public class HRR
    {
        double areaDoub, radiusDoub;
        String material;

        public HRR(double areaDoub, double radiusDoub, String material)
        {
            this.areaDoub = areaDoub;
            this.radiusDoub = radiusDoub;
            this.material = material;
        }

        public Map<String, Object> getValues()
        {
            return new HashMap<String, Object>(){{
                put("area", areaDoub);
                put("radius", radiusDoub);
                put("material", material);
            }};
        }
    }
}

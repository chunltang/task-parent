package day200411;

import java.time.LocalDateTime;

/**
 * 实体类：水文信息类
 * */
public class HydrologicalInfo {

    /**
     * 测量时间
     * */
    private LocalDateTime measureDateTime;

    /**
     * 目标水位
     * */
    private double objectWaterLevel;

    /**
     * 实际水位
     * */
    private double actualWaterLevel;

    /**
     * 闸门开度
     * */
    private double objectGateOpening;

    /**
     * 闸门实际开度
     * */
    private double actualGateOpening;

    /**
     * 流速
     * */
    private double waterFlow;

    public HydrologicalInfo() {
    }

    public HydrologicalInfo(LocalDateTime measureDateTime,
                            double objectWaterLevel,
                            double actualWaterLevel,
                            double objectGateOpening,
                            double actualGateOpening,
                            double waterFlow) {
        this.measureDateTime = measureDateTime;
        this.objectWaterLevel = objectWaterLevel;
        this.actualWaterLevel = actualWaterLevel;
        this.objectGateOpening = objectGateOpening;
        this.actualGateOpening = actualGateOpening;
        this.waterFlow = waterFlow;
    }

    public LocalDateTime getMeasureDateTime() {
        return measureDateTime;
    }

    public void setMeasureDateTime(LocalDateTime measureDateTime) {
        this.measureDateTime = measureDateTime;
    }

    public double getObjectWaterLevel() {
        return objectWaterLevel;
    }

    public void setObjectWaterLevel(double objectWaterLevel) {
        this.objectWaterLevel = objectWaterLevel;
    }

    public double getActualWaterLevel() {
        return actualWaterLevel;
    }

    public void setActualWaterLevel(double actualWaterLevel) {
        this.actualWaterLevel = actualWaterLevel;
    }

    public double getObjectGateOpening() {
        return objectGateOpening;
    }

    public void setObjectGateOpening(double objectGateOpening) {
        this.objectGateOpening = objectGateOpening;
    }

    public double getActualGateOpening() {
        return actualGateOpening;
    }

    public void setActualGateOpening(double actualGateOpening) {
        this.actualGateOpening = actualGateOpening;
    }

    public double getWaterFlow() {
        return waterFlow;
    }

    public void setWaterFlow(double waterFlow) {
        this.waterFlow = waterFlow;
    }
}

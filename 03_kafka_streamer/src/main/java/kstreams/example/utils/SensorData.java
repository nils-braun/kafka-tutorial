package kstreams.example.utils;

public class SensorData {
    private double sensorData1;
    private double sensorData2;

    public SensorData() { }

    public SensorData(double sensorData1, double sensorData2) {
        this.sensorData1 = sensorData1;
        this.sensorData2 = sensorData2;
    }


    public double getSensorData1() {
        return sensorData1;
    }

    public double getSensorData2() {
        return sensorData2;
    }
}

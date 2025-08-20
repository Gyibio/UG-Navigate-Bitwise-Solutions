package model;

public class Edge {
    private int fromId;
    private int toId;
    private double distance;
    private double baseTime;
    private double trafficFactor;

    public Edge(int fromId, int toId, double distance, double baseTime, double trafficFactor) {
        this.fromId = fromId;
        this.toId = toId;
        this.distance = distance;
        this.baseTime = baseTime;
        this.trafficFactor = trafficFactor;
    }
    public int getFromId() {
        return fromId;
    }
    public int getToId() {
        return toId;
    }
    public double getDistance() {
        return distance;
    }
    public double getBaseTime() {
        return baseTime;
    }
    public double getTrafficFactor() {
        return trafficFactor;
    }
    
    public void setFromId(int fromId) {
        this.fromId = fromId;
    }
    public void setToId(int toId) {
        this.toId = toId;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public void setBaseTime(double baseTime) {
        this.baseTime = baseTime;
    }
    public void setTrafficFactor(double trafficFactor) {
        this.trafficFactor = trafficFactor;
    }
    @Override
    public String toString() {
        return "Edge{" +
                "fromId=" + fromId +
                ", toId=" + toId +
                ", distance=" + distance +
                ", baseTime=" + baseTime +
                ", trafficFactor=" + trafficFactor +
                '}';
    }
    public double getEffectiveTime(int hour) {
        double timeFactor = trafficFactor;
        // Simulate higher traffic during 7-9am and 4-6pm
        if ((hour >= 7 && hour <= 9) || (hour >= 16 && hour <= 18)) {
            timeFactor *= 1.5;
        }
        return baseTime * timeFactor;
    }


}

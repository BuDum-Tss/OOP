package ru.nsu.fit.apotapova.json;


public class CourierData extends EmployeeData {

  private int trunkCapacity;
  private long speed;

  public long getSpeed() {
    return speed;
  }

  public int getTrunkCapacity() {
    return trunkCapacity;
  }
}

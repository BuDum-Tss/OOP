package ru.nsu.fit.apotapova.json;

import java.util.HashMap;

/**
 * Class containing HashMaps with employees data for JSON.
 */
public class EmployeesDataBase {

  private HashMap<Integer, BakerData> bakerHashMap;
  private HashMap<Integer, CourierData> courierHashMap;

  public HashMap<Integer, BakerData> getBakerHashMap() {
    return bakerHashMap;
  }

  public HashMap<Integer, CourierData> getCourierHashMap() {
    return courierHashMap;
  }
}

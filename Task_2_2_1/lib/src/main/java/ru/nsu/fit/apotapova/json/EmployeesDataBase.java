package ru.nsu.fit.apotapova.json;

import java.util.HashMap;
import java.util.List;

public class EmployeesDataBase {

  private HashMap<Integer, BakerData> bakerHashMap;
  private HashMap<Integer, CourierData> courierHashMap;


  public HashMap<Integer, ? extends EmployeeData> getHashMap(
      Class<? extends EmployeeData> employeeType) {
    if (employeeType == BakerData.class) {
      return bakerHashMap;
    } else if (employeeType == CourierData.class) {
      return courierHashMap;
    }
    return null;
  }

  public List<Class<? extends EmployeeData>> getOrderExecutorsDataClasses() {
    return List.of(BakerData.class, CourierData.class);
  }
}

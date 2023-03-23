package ru.nsu.fit.apotapova.json;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * JSON reader reads JSON and returns EmployeeDataBase.
 */
public class JSONReader {

  private static final String FILE_PATH =
      "lib/src/main/resources/ru/nsu/fit/apotapova/employees.txt";

  public EmployeesDataBase read(File file) {
    return parse(file);
  }

  public EmployeesDataBase read() {
    return parse(new File(FILE_PATH));
  }

  private EmployeesDataBase parse(File file) {
    EmployeesDataBase dataBase;
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      dataBase = new Gson().fromJson(reader, EmployeesDataBase.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return dataBase;
  }
}

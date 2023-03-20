package ru.nsu.fit.apotapova.json;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {

  private static final String FILE_PATH = "lib/src/main/resources/ru/nsu/fit/apotapova/employees.txt";
  private final File file;
  private BufferedReader reader;

  public JSONReader() {
    file = new File(FILE_PATH);
    open();
  }

  public JSONReader(File file) {
    this.file = file;
    open();
  }

  public void open() {
    try {
      if (!file.exists()) {
        //file.createNewFile();
        throw new RuntimeException();
      }
      reader = new BufferedReader(new FileReader(file));
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public EmployeesDataBase read() {
    EmployeesDataBase dataBase = new Gson().fromJson(reader, EmployeesDataBase.class);
    if (dataBase == null) {
      throw new IllegalArgumentException("File not found");
    }
    return dataBase;
  }

  public void close() {
    try {
      reader.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}

package ru.nsu.fit.apotapova;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.openjdk.jmh.annotations.AuxCounters;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 0)
@Measurement(iterations = 1, batchSize = 1)
@AuxCounters
public class NotPrimeBenchmark {
  static List<Integer> list = loadData();
  private static List<Integer> loadData(){
    List<Integer> list = new ArrayList<>();
    try {
      File file = new File("src/jmh/resources/ru/nsu/fit/apotapova/benchmark_data.txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line = reader.readLine();
      while (line != null){
        list.add(Integer.parseInt(line));
        line = reader.readLine();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return list;
  }
  @Benchmark
  public void notPrimeFinder(Blackhole blackhole) throws ExecutionException, InterruptedException {
    NotPrimeFinder npf = new NotPrimeFinder();
    blackhole.consume(npf.hasNotPrime(list));
  }
  @Benchmark
  public void notPrimeStreamFinder(Blackhole blackhole)
      throws ExecutionException, InterruptedException {
    NotPrimeFinder npsf = new NotPrimeStreamFinder();
    blackhole.consume(npsf.hasNotPrime(list));
  }
  @Benchmark
  public void notPrimeStandardThreadPoolFinder4Threads(Blackhole blackhole)
      throws ExecutionException, InterruptedException {
    NotPrimeFinder npstpf = new NotPrimeStandardThreadPoolFinder(4);
    blackhole.consume(npstpf.hasNotPrime(list));
  }
  @Benchmark
  public void notPrimeStandardThreadPoolFinder8Threads(Blackhole blackhole)
      throws ExecutionException, InterruptedException {
    NotPrimeFinder npstpf = new NotPrimeStandardThreadPoolFinder(8);
    blackhole.consume(npstpf.hasNotPrime(list));
  }
  @Benchmark
  public void notPrimeStandardThreadPoolFinder16Threads(Blackhole blackhole)
      throws ExecutionException, InterruptedException {
    NotPrimeFinder npstpf = new NotPrimeStandardThreadPoolFinder(16);
    blackhole.consume(npstpf.hasNotPrime(list));
  }
  @Benchmark
  public void notPrimeMyThreadPoolFinder4Threads(Blackhole blackhole)
      throws ExecutionException, InterruptedException {
    NotPrimeMyThreadPoolFinder npmtpf = new NotPrimeMyThreadPoolFinder(4, 10);
    blackhole.consume(npmtpf.hasNotPrime(list));
  }
  @Benchmark
  public void notPrimeMyThreadPoolFinder8Threads(Blackhole blackhole)
      throws ExecutionException, InterruptedException {
    NotPrimeMyThreadPoolFinder npmtpf = new NotPrimeMyThreadPoolFinder(8, 10);
    blackhole.consume(npmtpf.hasNotPrime(list));
  }
  @Benchmark
  public void notPrimeMyThreadPoolFinder16Threads(Blackhole blackhole)
      throws ExecutionException, InterruptedException {
    NotPrimeMyThreadPoolFinder npmtpf = new NotPrimeMyThreadPoolFinder(16, 10);
    blackhole.consume(npmtpf.hasNotPrime(list));
  }
}
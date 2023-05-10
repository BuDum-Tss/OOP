package ru.nsu.fit.apotapova.snake;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.util.Pair;

public class Utils {
  public static RandomSystem randomSystem=new RandomSystem();
  public static <T> List<T> getNeighbors(Point2D point, List<List<T>> map){
    List<T> list = getNeighborsWithPosition(point, map).stream().map(Pair::getValue).toList();
    return list;
  }

  public static <T> List<Pair<Point2D, T>> getNeighborsWithPosition(Point2D point, List<List<T>> map) {
    List<Pair<Point2D,T>> list = new ArrayList<>();
    int x = (int) point.getX();
    int y = (int) point.getY();
    int length = map.size();
    if (length==0) throw new  IllegalArgumentException("Map size must be greater than 0");
    int width = map.get(0).size();
    if (x<0 || x>length) throw new  IllegalArgumentException("X must be in bounds of map length");
    if (y<0 || y>width) throw new  IllegalArgumentException("Y must be in bounds of map width");
    if (x>0 && map.get(x - 1) != null) {
      list.add(new Pair<>(new Point2D(x-1,y),map.get(x - 1).get(y)));
    } else {
      list.add(new Pair<>(new Point2D(x-1,y),null));
    }
    if (y>0 && map.get(x) != null) {
      list.add(new Pair<>(new Point2D(x,y-1),map.get(x).get(y-1)));
    } else {
      list.add(new Pair<>(new Point2D(x,y-1),null));
    }
    if (x+1<length && map.get(x + 1) != null) {
      list.add(new Pair<>(new Point2D(x+1,y),map.get(x + 1).get(y)));
    } else {
      list.add(new Pair<>(new Point2D(x+1,y),null));
    }
    if (y+1<width && map.get(x) != null) {
      list.add(new Pair<>(new Point2D(x,y+1),map.get(x).get(y+1)));
    } else {
      list.add(new Pair<>(new Point2D(x,y+1),null));
    }
    return list;
  }
}

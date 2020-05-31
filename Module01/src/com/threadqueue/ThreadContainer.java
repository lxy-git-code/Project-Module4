package com.threadqueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXingyu
 * @date 2020-04-04 4:04 下午
 */
public class ThreadContainer {
    int maxLoad=10;
  public  static List<ReadData> threadList= new ArrayList<ReadData>();
  public static boolean checkIsFull()
  {
      if(threadList.size()<10)
          return false;
      else
          return false;
  }
}

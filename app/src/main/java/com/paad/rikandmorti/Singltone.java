package com.paad.rikandmorti;

import com.paad.rikandmorti.API.Result;

import java.util.ArrayList;
import java.util.List;

 class Singltone {
     List<Result> characterList;
     private static  Singltone instance = new Singltone();
     private Singltone(){
          characterList = new ArrayList<>();
     }

     public static  Singltone getInstance(){
         return instance;
     }

}

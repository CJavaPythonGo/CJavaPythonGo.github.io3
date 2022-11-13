package com.lovo.sgproj.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class LovoConvertion {

    public static boolean stringToGender(String genderStr){
        return genderStr.equals("男") ? true : false;
    }

    public static String genderToString(boolean gender){
        return gender ? "男" : "女";
    }

    public static boolean stringToRoomGender(String genderStr){
        return genderStr.equals("男生寝室") ? true : false;
    }

    public static String roomGenderToString(boolean gender){
        return gender ? "男生寝室" : "女生寝室";
    }

    public static String roomStatusToString(boolean status){
        return status ? "正常" : "设施损坏";
    }
    public static String roomRecordToString(boolean status){
        return status ? "已解决" : "未解决";
    }

    public static int stringToRoomPayWay(String roomPayWayStr){
        return switch(roomPayWayStr){
            case "月付" -> 0;
            case "季付" -> 1;
            case "半年付" -> 2;
            case "年付" -> 3;
            default -> -1;
        };
    }

    public static String roomPayWayToString(int roomPayWay){
        return switch(roomPayWay){
           case 0 -> "月付";
           case 1 -> "季付";
           case 2 -> "半年付";
           case 3 -> "年付";
           default -> "";
        };
    }

    public static LocalDate dateToLoaclDate(Date someDay){
        return someDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    //房间查询三个转化

    public static int stringToRoomStatusWhenQuery(String statusStr){
        return switch (statusStr){
            case "不限"->-1;
            case "正常"->1;
            case "损坏"->0;
            default -> -1;
        };
    }
    public static int stringToRoomGenderWhenQuery(String genderStr){
        return switch (genderStr){
            case "不限"->-1;
            case "男生寝室"->1;
            case "女生寝室"->0;
            default -> -1;

        };
    }
    public static boolean stringToRoomCanIn(String numStr){
        return switch (numStr){
            case "不限"->true;
            case "可住房间"->false;
            default -> true;
        };
    }

}

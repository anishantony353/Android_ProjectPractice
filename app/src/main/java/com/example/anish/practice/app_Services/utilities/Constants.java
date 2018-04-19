package com.example.anish.practice.app_Services.utilities;

/**
 * Created by Anish on 07-02-2018.
 */

public class Constants {

    public interface ACTION {
        public static String MAIN_ACTION = "com.anish.foregroundservice.action.main";
        public static String PREV_ACTION = "com.anish.foregroundservice.action.prev";
        public static String PLAY_ACTION = "com.anish.foregroundservice.action.play";
        public static String NEXT_ACTION = "com.anish.foregroundservice.action.next";
        public static String STARTFOREGROUND_ACTION = "com.anish.foregroundservice.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.anish.foregroundservice.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}

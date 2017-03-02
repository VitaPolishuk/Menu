package com.menuAndersen.model;

import com.menuAndersen.service.MyDateService;
import com.menuAndersen.service.TimeBlockedService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.TimerTask;

/**
 * Created by wital on 01.03.2017.
 */
public class MyTimer extends TimerTask {
    private TimeBlockedService timeBlockedService;
    private MyDateService myDateService;

    public MyTimer(TimeBlockedService timeBlockedService, MyDateService myDateService) {
        this.timeBlockedService = timeBlockedService;
        this.myDateService = myDateService;
    }
    @Override
    public void run() {
        checkTime();
    }

    public void checkTime() {
        java.util.Date newDate = new java.util.Date();
        String time = timeBlockedService.getTime(Long.valueOf(1)).getCurrentTime();
        Date curDate = new Date(newDate.getTime());
        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);
           if (newDate.getHours() >= hour && newDate.getMinutes() >= minute) {
            myDateService.setStatusDate(curDate);

        }

    }


}

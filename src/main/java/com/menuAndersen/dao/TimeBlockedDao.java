package com.menuAndersen.dao;

import com.menuAndersen.model.TimeBlocked;

/**
 * Created by wital on 01.03.2017.
 */
public interface TimeBlockedDao {

    public void addTime(String currentTime,String globalTime);

    public void editTime(TimeBlocked timeBlocked);

    public void removeTime(Long id);

    public TimeBlocked getTime(Long id);

    public void cheangeCurrentTime(String currentTime);

    public void cheangeGlobalTime(String globalTime);
}

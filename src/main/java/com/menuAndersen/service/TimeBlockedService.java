package com.menuAndersen.service;

import com.menuAndersen.model.TimeBlocked;
import org.springframework.stereotype.Component;

/**
 * Created by wital on 01.03.2017.
 */
@Component
public interface TimeBlockedService {

    public void addTime(String currentTime,String globalTime);

    public void editTime(TimeBlocked timeBlocked);

    public void removeTime(Long id);

    public TimeBlocked getTime(Long id);

    public void cheangeCurrentTime(String currentTime);

    public void cheangeGlobalTime(String globalTime);
}

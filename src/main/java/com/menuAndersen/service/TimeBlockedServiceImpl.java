package com.menuAndersen.service;

import com.menuAndersen.dao.PasswordDao;
import com.menuAndersen.dao.TimeBlockedDao;
import com.menuAndersen.model.TimeBlocked;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wital on 01.03.2017.
 */
@Service("timeblockedService")
public class TimeBlockedServiceImpl implements TimeBlockedService {
    private TimeBlockedDao timeBlockedDao;

    public TimeBlockedServiceImpl(TimeBlockedDao timeBlockedDao) {
        this.timeBlockedDao = timeBlockedDao;
    }

    @Override
    @Transactional
    public void addTime(String currentTime, String globalTime) {
        this.timeBlockedDao.addTime(currentTime, globalTime);
    }

    @Override
    @Transactional
    public void editTime(TimeBlocked timeBlocked) {
        this.timeBlockedDao.editTime(timeBlocked);
    }

    @Override
    @Transactional
    public void removeTime(Long id) {
        this.timeBlockedDao.removeTime(id);
    }

    @Override
    @Transactional
    public TimeBlocked getTime(Long id) {
        return this.timeBlockedDao.getTime(id);
    }

    @Override
    @Transactional
    public void cheangeCurrentTime(String currentTime) {
        this.timeBlockedDao.cheangeCurrentTime(currentTime);
    }

    @Override
    @Transactional
    public void cheangeGlobalTime(String globalTime) {
        this.timeBlockedDao.cheangeGlobalTime(globalTime);
    }
}

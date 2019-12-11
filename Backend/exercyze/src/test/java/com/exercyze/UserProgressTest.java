package com.exercyze;

import com.exercyze.api.UserProgressController;
import com.exercyze.dao.UserProgressDao;
import com.exercyze.model.UserProgress;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserProgressTest {

    @InjectMocks
    UserProgressController userProgressController;

    @Mock
    UserProgressDao repo;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUserProgressEntryTest(){
        ArrayList<UserProgress> userProgressEntries = new ArrayList<UserProgress>();

        UserProgress userProgress1 = new UserProgress(5, 500, new Date(12-11-2001));
        UserProgress userProgress2 = new UserProgress(5, 300, new Date(12-13-2019));
        UserProgress userProgress3 = new UserProgress(7, 150, new Date(12-13-2021));

        userProgressEntries.add(userProgress1);
        userProgressEntries.add(userProgress2);
        userProgressEntries.add(userProgress3);

        when(repo.findAll()).thenReturn(userProgressEntries);

        List<UserProgress> userProgressTestList = userProgressController.getAllUserProgressEntry();

        assertEquals(3, userProgressTestList.size());
    }

    @Test
    public void getAllUserProgressEntryByIdTest(){
        ArrayList<UserProgress> userProgressEntries = new ArrayList<UserProgress>();

        UserProgress userProgress1 = new UserProgress(5, 500, new Date(12-11-2001));
        UserProgress userProgress2 = new UserProgress(5, 300, new Date(12-13-2019));
        UserProgress userProgress3 = new UserProgress(5, 150, new Date(12-13-2021));

        userProgressEntries.add(userProgress1);
        userProgressEntries.add(userProgress2);
        userProgressEntries.add(userProgress3);

        when(repo.findAllByUserId(5)).thenReturn(userProgressEntries);

        List<UserProgress> userProgressTestList = userProgressController.getAllUserProgressEntryById(5);

        assertEquals(3, userProgressTestList.size());
    }
}

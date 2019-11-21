package com.exercyze;

import com.exercyze.api.UserController;
import com.exercyze.api.WorkoutController;
import com.exercyze.dao.UserDao;
import com.exercyze.dao.WorkoutDao;
import com.exercyze.model.User;
import com.exercyze.model.Workout;
import org.hibernate.jdbc.Work;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class WorkoutTest {
    @InjectMocks
    WorkoutController workoutController;

    @Mock
    WorkoutDao repo;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllByWorkoutRoutineIdTest(){
       List<Workout> list = new ArrayList<Workout>();
       Workout workout1 = new Workout(5,
               "Bench Press",
               5,
               10,
               "Chest",
               135,
               5);
       list.add(workout1);

        when(repo.findAllByWorkoutRoutineId(5)).thenReturn(list);

        List<Workout> toCheck = workoutController.getWorkoutsByRoutineId(5);

        assertEquals(1, list.size());


    }
}

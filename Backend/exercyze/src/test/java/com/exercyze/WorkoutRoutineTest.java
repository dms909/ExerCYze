package com.exercyze;

import com.exercyze.api.WorkoutRoutineController;
import com.exercyze.dao.WorkoutRoutineDao;
import com.exercyze.model.WorkoutRoutine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

public class WorkoutRoutineTest {

    @InjectMocks
    WorkoutRoutineController workoutRoutineController;

    @Mock
    WorkoutRoutineDao repo;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByIdTest(){
        when(repo.findWorkoutRoutineById(1)).thenReturn(new WorkoutRoutine(
                1,
                "testName",
                "testCreator"
        ));

        WorkoutRoutine wr = workoutRoutineController.getWorkoutRoutineById(1);

        assertEquals("testName", wr.getWorkoutRoutineName());
        assertEquals("testCreator", wr.getWorkoutRoutineCreator());
    }
}

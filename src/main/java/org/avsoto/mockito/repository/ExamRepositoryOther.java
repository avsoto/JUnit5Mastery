package org.avsoto.mockito.repository;

import org.avsoto.mockito.model.Exam;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExamRepositoryOther implements ExamRepository{
    @Override
    public List<Exam> findAll() {
        try{
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package org.avsoto.mockito.service.imp;

import org.avsoto.mockito.model.Exam;

import java.util.Arrays;
import java.util.List;

public class Data {
    public final static List<Exam> EXAMS = Arrays.asList(new Exam(5L, "Maths"), new Exam(6L, "Geography"),
            new Exam(7L, "History"));

    public final static List<String> QUESTIONS = Arrays.asList("Arithmetic", "Integral", "Geometry");
}

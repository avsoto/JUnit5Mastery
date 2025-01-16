package org.avsoto.mockito.service;

import org.avsoto.mockito.model.Exam;

import java.util.Optional;

public interface ExamService {

    Optional<Exam> findExamenByName(String name);

    Exam findExamByNameAndQuestions(String name);

}

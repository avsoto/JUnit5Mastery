package org.avsoto.mockito.service.imp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.avsoto.mockito.model.Exam;
import org.avsoto.mockito.repository.ExamRepository;
import org.avsoto.mockito.repository.ExamRepositoryOther;
import org.avsoto.mockito.repository.QuestionRepository;
import org.avsoto.mockito.service.ExamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExamServiceImplTest {

    ExamRepository repository;
    ExamService service;
    QuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
        repository = mock(ExamRepository.class);
        questionRepository = mock(QuestionRepository.class);
        service = new ExamServiceImpl(repository, questionRepository);
    }

    @Test
    void findExamByName(){

        when(repository.findAll()).thenReturn(Data.EXAMS);

       Optional<Exam> exam = service.findExamenByName("Maths");

        assertTrue(exam.isPresent());
        assertEquals(5, exam.orElseThrow().getId());
        assertEquals("Maths", exam.get().getName());
    }

    @Test
    void findExamByNameOrEmptyList(){
        List<Exam> data = Collections.emptyList();

        when(repository.findAll()).thenReturn(data);

        Optional<Exam> exam = service.findExamenByName("Maths");

        assertFalse(exam.isPresent());
    }

    @Test
    void testQuestionsExam(){
        when(repository.findAll()).thenReturn(Data.EXAMS);
        when(questionRepository.findQuestionsByExamId(5L)).thenReturn(Data.QUESTIONS);

        Exam examn = service.findExamByNameAndQuestions("Maths");

        assertEquals(3, examn.getQuestions().size());
        assertTrue(examn.getQuestions().contains("Arithmetic"));
    }


}
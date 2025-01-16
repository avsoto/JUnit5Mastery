package org.avsoto.mockito.service.imp;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.avsoto.mockito.model.Exam;
import org.avsoto.mockito.repository.ExamRepository;
import org.avsoto.mockito.repository.QuestionRepository;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {

    @Mock
    ExamRepository repository;

    @Mock
    QuestionRepository questionRepository;

    @InjectMocks
    ExamServiceImpl service;

    @BeforeEach
    void setUp() {

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

        Exam exam = service.findExamByNameAndQuestions("Maths");

        assertEquals(3, exam.getQuestions().size());
        assertTrue(exam.getQuestions().contains("Arithmetic"));
    }

    @Test
    void testQuestionsExamVerify(){
        when(repository.findAll()).thenReturn(Data.EXAMS);
        when(questionRepository.findQuestionsByExamId(5L)).thenReturn(Data.QUESTIONS);

        Exam exam = service.findExamByNameAndQuestions("Maths");

        assertEquals(3, exam.getQuestions().size());
        assertTrue(exam.getQuestions().contains("Arithmetic"));

        verify(repository).findAll();
        verify(questionRepository).findQuestionsByExamId(5L);
    }

    @Test
    void testNoExitsExamVerify(){
        when(repository.findAll()).thenReturn(Collections.emptyList());
        when(questionRepository.findQuestionsByExamId(anyLong())).thenReturn(Data.QUESTIONS);

        Exam exam = service.findExamByNameAndQuestions("Maths");

        assertNull(exam);

        verify(repository).findAll();
        verify(questionRepository).findQuestionsByExamId(5L);
    }


}
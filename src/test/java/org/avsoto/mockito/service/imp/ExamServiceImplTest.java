package org.avsoto.mockito.service.imp;

import java.util.Arrays;
import java.util.List;

import org.avsoto.mockito.model.Exam;
import org.avsoto.mockito.repository.ExamRepository;
import org.avsoto.mockito.service.ExamService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExamServiceImplTest {

    @Test
    void findExamByName(){
        ExamRepository repository = mock(ExamRepository.class);
        ExamService service = new ExamServiceImpl(repository);
        List<Exam> data = Arrays.asList(new Exam(5L, "Maths"), new Exam(6L, "Geography"),
                new Exam(7L, "History"));

        when(repository.findAll()).thenReturn(data);

        Exam exam = service.findExamenByName("Maths");

        assertNotNull(exam);
        assertEquals(5, exam.getId());
        assertEquals("Maths", exam.getName());

    }
}
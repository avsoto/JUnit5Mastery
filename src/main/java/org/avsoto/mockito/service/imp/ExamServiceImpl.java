package org.avsoto.mockito.service.imp;

import lombok.AllArgsConstructor;
import org.avsoto.mockito.model.Exam;
import org.avsoto.mockito.repository.ExamRepository;
import org.avsoto.mockito.service.ExamService;

import java.util.Optional;

@AllArgsConstructor
public class ExamServiceImpl implements ExamService {

    private ExamRepository examRepository;

    @Override
    public Exam findExamenByName(String name) {
        Optional<Exam> examOptional = examRepository.findAll()
                .stream().filter(e -> e.getName().contains(name))
                .findFirst();

        Exam exam = null;

        if(examOptional.isPresent()){
            exam = examOptional.orElseThrow();
        }

        return exam;
    }
}

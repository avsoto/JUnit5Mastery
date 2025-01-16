package org.avsoto.mockito.service.imp;

import lombok.AllArgsConstructor;
import org.avsoto.mockito.model.Exam;
import org.avsoto.mockito.repository.ExamRepository;
import org.avsoto.mockito.repository.ExamRepositoryOther;
import org.avsoto.mockito.repository.QuestionRepository;
import org.avsoto.mockito.service.ExamService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ExamServiceImpl implements ExamService {

    private ExamRepository examRepository;
    private QuestionRepository questionRepository;

    @Override
    public Optional<Exam> findExamenByName(String name) {
        return examRepository.findAll()
                .stream()
                .filter(e -> e.getName().contains(name))
                .findFirst();
    }

    @Override
    public Exam findExamByNameAndQuestions(String name) {
        Optional<Exam> examOptional = findExamenByName(name);
        Exam exam = null;

        if(examOptional.isPresent()){
            exam = examOptional.orElseThrow();
            List<String> questions = questionRepository.findQuestionsByExamId(exam.getId());

            exam.setQuestions(questions);
        }

        return exam;
    }
}

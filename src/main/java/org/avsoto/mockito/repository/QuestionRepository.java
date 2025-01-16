package org.avsoto.mockito.repository;

import org.avsoto.mockito.model.Exam;

import java.util.List;

public interface QuestionRepository {
    List<String> findQuestionsByExamId(long id);

}

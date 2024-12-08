package org.avsoto.mockito.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    private Long id;
    private String name;
    private List<String> questions;

    public Exam(Long id, String name){
        this.id = id;
        this.name = name;
        this.questions = new ArrayList<>();
    }
}

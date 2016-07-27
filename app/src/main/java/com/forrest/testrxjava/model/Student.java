package com.forrest.testrxjava.model;

import java.util.List;

/**
 * Created by forrest on 16/7/18.
 */
public class Student {
    private String name;

    private List<Lesson> lessonList;

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package com.example.bryanty.materialdesignproject.object;

/**
 * Created by BRYANTY on 14/05/2015.
 */
public class Subject {
    private String subjectID;
    private String subjectTitle;

    public Subject() {
    }

    public Subject(String subjectID, String subjectTitle) {
        this.subjectID = subjectID;
        this.subjectTitle = subjectTitle;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectID='" + subjectID + '\'' +
                ", subjectTitle='" + subjectTitle + '\'' +
                '}';
    }


}

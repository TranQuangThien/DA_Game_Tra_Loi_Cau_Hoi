package com.example.tranquangthien;

public class Question {

    private String question;
    private int level;
    private String caseA, caseB, caseC, caseD;
    int trueCase;

    public Question(String question, int level, String caseA, String caseB, String caseC, String caseD, int trueCase) {
        this.question = question;
        this.level = level;
        this.caseA = caseA;
        this.caseB = caseB;
        this.caseC = caseC;
        this.caseD = caseD;
        this.trueCase = trueCase;
    }

    public String getQuestion() {
        return question;
    }

    public int getLevel() {
        return level;
    }

    public String getCaseA() {
        return caseA;
    }

    public String getCaseB() {
        return caseB;
    }

    public String getCaseC() {
        return caseC;
    }

    public int getTrueCase() {
        return trueCase;
    }

    public String getCaseD() {
        return caseD;
    }
}

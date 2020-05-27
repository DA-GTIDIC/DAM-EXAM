package cat.udl.tidic.amd.dam_tips.models;

import java.util.ArrayList;

public class Question {
    private int id;
    private String question;
    private String category;
    private ArrayList<Answer> answers;

    public Question(int id, String question, String category, ArrayList<Answer> answers) {
        this.id = id;
        this.question = question;
        this.category = category;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }



}



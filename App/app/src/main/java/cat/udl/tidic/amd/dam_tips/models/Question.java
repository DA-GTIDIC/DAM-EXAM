package cat.udl.tidic.amd.dam_tips.models;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("question")
    private String question;

    public Question(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

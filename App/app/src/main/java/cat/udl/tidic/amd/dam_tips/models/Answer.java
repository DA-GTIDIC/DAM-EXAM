package cat.udl.tidic.amd.dam_tips.models;

import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("answer")
    private String answer;

    public Answer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

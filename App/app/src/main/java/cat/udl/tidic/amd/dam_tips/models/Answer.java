package cat.udl.tidic.amd.dam_tips.models;

import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("answer")
    String answer;

    @SerializedName("is_correct")
    boolean is_correct;

    public Answer(){

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isIs_correct() {
        return is_correct;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }
}

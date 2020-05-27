package cat.udl.tidic.amd.dam_tips.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Answer
{
    @SerializedName("id")
    private int id;
    @SerializedName("answer")
    private String answerBody;
    @SerializedName("is_correct")
    private  Boolean isCorrect;



    public Answer(int id, String answerBody, Boolean isCorrect)
    {
        setId(id);
        setAnswor_body(answerBody);
        setCorrect(isCorrect);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return this.isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }


    public void setAnswor_body(String answor_body) {
        this.answerBody = answor_body;
    }


    public String getAnswerBody()
    {
        return answerBody;
    }
}

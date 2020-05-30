package cat.udl.tidic.amd.dam_tips.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Respuesta {
    @SerializedName("id")
    private int id;
    @SerializedName("answer")
    private String answer;
    @SerializedName("is_correct")
    private Boolean is_correct;
    public Respuesta(String answer, Boolean is_correct){
        this.is_correct = is_correct;
        this.answer = answer;
    }
    public Respuesta(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(Boolean is_correct) {
        this.is_correct = is_correct;
    }
}


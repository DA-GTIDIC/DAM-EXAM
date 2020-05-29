package cat.udl.tidic.amd.dam_tips.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {

    @SerializedName("question")
    String question;

    @SerializedName("category")
    CategoryEnum category;

    @SerializedName("answers")
    List<Answer> answers;

    public Question(){

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString(){
        return question + " answers " + answers.toString();
    }
}

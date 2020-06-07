package cat.udl.tidic.amd.dam_tips.models;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("id")
    public String id;

    @SerializedName("question")
    private String question;

    @SerializedName("category")
    private String category;


    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getQuestion(){
        return question;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public Question(String question, String category){
        this.question = question;
        this.category = category;
    }

}

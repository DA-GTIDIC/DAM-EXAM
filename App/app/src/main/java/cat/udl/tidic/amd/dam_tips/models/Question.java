package cat.udl.tidic.amd.dam_tips.models;
import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("id")
    private int id;

    @SerializedName("question")
    private String question;

    @SerializedName("category")
    private String category;


    public Question(int id, String question, String category){

        setId(id);
        setQuestion(question);
        setCategory(category);
    }

    @Override
    public String toString(){
        return ("question / " + "id:" + getId() + "\n" + "question:" + getQuestion() + "\n" + "category:" + getCategory());
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public String getQuestion(){
        return question;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return category;
    }


}

package cat.udl.tidic.amd.dam_tips.models;
import com.google.gson.annotations.SerializedName;
public class Question {

    @SerializedName("question")
    private String question;
    @SerializedName("category")
    private String category;

    public Question() {
    }

    public Question(String question, String category) {
        this.question = question;
        this.category = category;
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
}

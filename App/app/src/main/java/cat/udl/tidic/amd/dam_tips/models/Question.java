package cat.udl.tidic.amd.dam_tips.models;
import com.google.gson.annotations.SerializedName;

public class Question {
    @SerializedName("question")
    private String question;

    @SerializedName("category")
    private String category;

    @SerializedName("id")
    private String id;


    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Question(String question, String category) {
        this.category = category;
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {

        if (o.equals(this)) {
            return true;
        }

        if (!(o instanceof Question)) {
            return false;
        }

        Question equ = (Question) o;
        return this.id == equ.getId()
                && this.question.equals(equ.getQuestion());

    }
}










/*
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


*/
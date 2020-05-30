package cat.udl.tidic.amd.dam_tips.models;
import com.google.gson.annotations.SerializedName;

public class Pregunta {
    @SerializedName("question")
    private String question;

    @SerializedName("category")
    private String category;

    @SerializedName("id")
    private String id;




    public String getQuestion() {
        return question;
    }

    public String getCategory() {
        return category;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Pregunta(String question, String category, String id) {
        this.category = category;
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Pregunta)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Pregunta e = (Pregunta) o;

        // Compare the data members and return accordingly
        return this.id == e.getId()
                && this.question.equals(e.getQuestion());

    }
}
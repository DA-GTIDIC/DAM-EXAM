package cat.udl.tidic.amd.dam_tips.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Pregunta {

    @SerializedName("id")
    private int id;
    @SerializedName("question")
    private String question;
    @SerializedName("category")
    private Categories category;
    @SerializedName("answers")
    private List<Respuesta> respuestas;
    @SerializedName("owner_id")
    private Integer owner_id;
    public Pregunta(String question, Categories category, List<Respuesta> respuestas, Integer owner_id){
        this.category = category;
        this.question = question;
        this.respuestas = respuestas;
        this.owner_id = owner_id;
    }
    public Pregunta(){

    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
    @Override
    public String toString(){
        return "" + this.category.name;
    }
}

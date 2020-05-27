package cat.udl.tidic.amd.dam_tips.models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question
{

    @SerializedName("id")
    private int id;
    @SerializedName("question")
    private String question;
    @SerializedName("category")
    private String cat;
    @SerializedName("answers")
    private List<Answer> answer;



    public Question(int id, String question, String cat, List<Answer> answer)
    {
        setId(id);
        setQuestion(question);
        setCat(cat);
        setAnswer(answer);
    }

    @Override
    public String toString()
    {
        return  ("QUESTION --> "+ "ID :" + getId()+ "\n" +
                "QUESTION :" + getQuestion()+ "\n" +
                "CATEGORIA :" + getCat()+ "\n" +
                "ANSWER :" +  getAllAnswers());
    }

    public int getId() {
        return id;
    }


    String getAllAnswers()
    {
        StringBuilder conc = new StringBuilder();
        conc.append("\n");
        for (Answer answ: getAnswer())
        {
            conc.append(answ.getAnswerBody() + "\n");
        }

        return conc.toString();
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

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public List<Answer> getAnswer()
    {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }


}

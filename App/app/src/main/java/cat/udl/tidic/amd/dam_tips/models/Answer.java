package cat.udl.tidic.amd.dam_tips.models;

public class Answer{
    private int id;
    private String answer;
    private boolean is_correct;

    public Answer(int id, String answer, boolean is_correct) {
        this.id = id;
        this.answer = answer;
        this.is_correct = is_correct;
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

    public boolean isIs_correct() {
        return is_correct;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }
}
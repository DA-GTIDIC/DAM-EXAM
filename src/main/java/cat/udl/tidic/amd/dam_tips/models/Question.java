package cat.udl.tidic.amd.dam_tips.models;


import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Question {
    /* "id": self.id,
            "question": self.question,
            "category": self.category.value,*/
    @SerializedName("id")
    private String id;
    @SerializedName("question")
    private String question;
    @SerializedName("category")
    private Category category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;
        return this.id == question1.getId() &&
                this.question.equals(question1.getQuestion()) &&
                this.category == question1.getCategory();
    }
}

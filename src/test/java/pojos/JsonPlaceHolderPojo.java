package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true)//

public class JsonPlaceHolderPojo {
    //Create private fields
    private Integer userId;
    private String title;
    private Boolean completed;
    // Create and parametrized constructors
    // Create a Default Constractor


    public JsonPlaceHolderPojo() {
    }
    // Create parametrized Costractor


    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }
    // Create Getters and Setters-We need that because the class is private and we can not
    // get the fields out of the clas.We can do that with Getters and Setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    // Create toString() method-Used to Read the data

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
 /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed":
     */


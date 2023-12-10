package facebook.src;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class post {
    int post_id;
    boolean is_public;

    int author_id;
    LocalDate Date;
    String content;

    //public ArrayList<Integer> tagged_users_ids;

    post(int post_id,LocalDate Date,boolean is_public,String content, int author_id){
        this.post_id=post_id;
        this.Date=Date;
        this.is_public=is_public;
        this.author_id=author_id;
        this.content=content;
    }
    public Label createFixedPost() {
        // Outer label representing the post
        Label postLabel = new Label();
        postLabel.setMinSize(600, 400);
        postLabel.setMaxSize(600, 400); // Optional, depending on your requirements
        postLabel.setStyle("-fx-border-color: black; -fx-padding: 5px;");

        // Inner label representing the post content
        Label contentLabel = new Label(this.content);
        contentLabel.setStyle("-fx-text-fill: black;"); // Adjust text color as needed

        // Add the content label to the post label
        postLabel.setGraphic(contentLabel);

        return postLabel;
    }



}

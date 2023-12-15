package facebook.src;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.LocalDate;

public class WritePost {


    @FXML
    private TextArea post_content;

    @FXML
    private Label confirmationlabel;

    @FXML
    private CheckBox privacy_chkbx;
    @FXML
    public void add_new_post(MouseEvent mouseEvent) {
        boolean ispublic =true;
        String postcontent;
        if (post_content.getText().trim().isEmpty()) {
            confirmationlabel.setTextFill(Color.RED);
            confirmationlabel.setText("Error: Please write something in the post.");
        } else {
            confirmationlabel.setTextFill(Color.BLUE);
            confirmationlabel.setText("post created successfully!");
            postcontent=post_content.getText();
            // Example: Print the content to the console
            // System.out.println("Post Content: " + post_content.getText());
            if(privacy_chkbx.isSelected()){
                //System.out.println("Post is private");
                ispublic=false;
            }else{
                // System.out.println("Post is public");
            }
            LocalDate currentDate = LocalDate.now();

            // Create a formatter for the "yy/MM/dd" format


            // Format the current date using the formatter

            Post new_post = new Post(DATA.Posts.size()+1,currentDate,ispublic,postcontent,1 );
            DATA.Posts.add(new_post);
            int x= DATA.Posts.size()-1;
            Post p= DATA.Posts.get(x);
            System.out.println(p.post_id+" "+ p.content+ " "+p.is_public+"  "+p.Date);
        }
    }

    public void new_post_Back_btn(MouseEvent mouseEvent) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(mouseEvent,"feed.fxml");
    }
}
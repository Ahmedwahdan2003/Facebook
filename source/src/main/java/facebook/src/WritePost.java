package facebook.src;

import javafx.event.ActionEvent;
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

            post new_post = new post(DATA.posts.size()+1,currentDate,ispublic,postcontent,4 );
            DATA.posts.add(new_post);
            int x= DATA.posts.size()-1;
            post p= DATA.posts.get(x);
            System.out.println(p.post_id+" "+ p.content+ " "+p.is_public+"  "+p.Date);
        }
    }
    @FXML private void new_post_Back_btn(ActionEvent actionEvent) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(actionEvent, "feed.fxml");
    }
}

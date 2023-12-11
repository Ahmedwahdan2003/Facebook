package facebook.src;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
    private Scene scene;
    private Parent root;
    public void new_post_Back_btn(MouseEvent mouseEvent) throws IOException {

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("feed.fxml")));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

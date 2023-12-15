package facebook.src;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class WritePost {

    private final ArrayList<Integer> tags = new ArrayList<>();
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

            Post new_post = new Post(DATA.Posts.size() + 1, currentDate, ispublic, postcontent, DATA.currentUser.id, tags);
            DATA.Posts.add(new_post);

            Post p = DATA.Posts.getLast();
            System.out.println(p.post_id+" "+ p.content+ " "+p.is_public+"  "+p.Date);
            System.out.println("Tags:");
            for(Integer id : tags){
                System.out.print(" ".concat(DATA.users.get(id - 1).name));
            }
            System.out.println();
        }
    }

    public void new_post_Back_btn(MouseEvent mouseEvent) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(mouseEvent,"feed.fxml");
    }
    @FXML private void tag_btn() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Tag_User.fxml"));
        Parent root = loader.load();
        Tag_User tagUser = loader.getController();
        tagUser.tags = tags;

        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setTitle("Tag Users");
        window.setScene(scene);
        window.show();
    }
}

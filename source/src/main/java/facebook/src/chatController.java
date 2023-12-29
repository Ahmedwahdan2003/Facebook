package facebook.src;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class chatController {
    @FXML
    private Label se_time;
    @FXML
    private ImageView ChatIcon2;
    @FXML
    private ImageView ChatIcon;
    @FXML
    private ImageView recipient_image_l;
    @FXML
    private Label recipient_name_l;
    @FXML
    private VBox recipients_vbox;
    @FXML
    private HBox recipient_hbox;
    @FXML
    private ImageView GroupIcon2;
    @FXML
    private ImageView GroupIcon;
    @FXML
    private ImageView SettingIcon2;
    @FXML
    private ImageView SettingIcon;
    @FXML
    private ImageView UserPhoto;
    @FXML
    private StackPane stackPane;
    @FXML
    private ImageView recipient_image;
    @FXML
    private ImageView recipient_image2;
    @FXML
    private ImageView client_image;
    @FXML
    private Label recipient_label;
    @FXML
    private TextField SearchTextField;
    @FXML
    private TextField MessageTextField;
    @FXML
    private Label se_chat_lb;
    @FXML
    private ClientApp client;
    @FXML
    private HBox se_hbox;
    @FXML
    private HBox re_hbox;
    @FXML
    private VBox mainVBox;
    @FXML
    private ImageView se_chat;
    private ScrollPane scrollPane;

    // Map to store copies of se_hbox
    private Map<String, List<HBox>> userMessagesMap = new HashMap<>();

    /**
     * Initializes the chat controller.
     * - Sets up UI elements.
     * - Loads user data.
     * - Sets event listeners.
     */
    @FXML
    public void initialize() {
        

        GroupIcon.setVisible(true);
        GroupIcon2.setVisible(false);
        SettingIcon2.setVisible(false);
        SettingIcon.setVisible(true);
        se_hbox.setVisible(false);
        re_hbox.setVisible(false);
        scrollPane = new ScrollPane(mainVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.vvalueProperty().bind(mainVBox.heightProperty());
        try {
            loadUserData("C:\\Users\\ahmed\\Desktop\\facebook_source\\source\\Text Files\\sameh.txt",
                    "C:\\Users\\ahmed\\Desktop\\facebook_source\\source\\Text Files\\samehimage.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        recipient_image2.setImage(recipient_image.getImage());
        client_image.setImage(UserPhoto.getImage());
        HBox.setMargin(se_chat_lb, new Insets(0, 0, 0, 10));

        Circle clip = new Circle(30, 30, 30);
        UserPhoto.setClip(clip);
        if (MessageTextField != null) {
            MessageTextField.setOnAction(event -> {
                String message = MessageTextField.getText();
                if (message != null && !message.isEmpty()) {
                    LocalTime currentTime = LocalTime.now();
                    String time = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                    String recipientName = recipient_label.getText();
                    client.sendMessage(recipientName, message, time);
                    Platform.runLater(() -> {
                        se_chat_lb.setText(message);
                        se_time.setText(time);
                        duplicateHBox();
                    });
                    MessageTextField.clear();
                }
            });
        }

        recipient_hbox.setOnMouseClicked(event -> {
            recipient_label.setText(recipient_name_l.getText());
            recipient_image.setImage(recipient_image_l.getImage());
            recipient_image2.setImage(recipient_image.getImage());
            client_image.setImage(UserPhoto.getImage());
            String username = recipient_label.getText();
            List<HBox> userMessages = userMessagesMap.get(username);
            mainVBox.getChildren().clear();
            if (userMessages != null) {
                mainVBox.getChildren().addAll(userMessages);
            }
        });

        SearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.trim().toLowerCase();
            for (Node node : recipients_vbox.getChildren()) {
                if (node instanceof HBox) {
                    HBox hbox = (HBox) node;
                    Label nameLabel = (Label) ((VBox) hbox.getChildren().get(1)).getChildren().get(0);
                    boolean isVisible = searchText.isEmpty() || nameLabel.getText().toLowerCase().contains(searchText);
                    hbox.setVisible(isVisible);
                }
            }

            MessageTextField.setOnAction(event -> {
                String message = MessageTextField.getText();
                if (!message.isEmpty()) {
                    String recipientName = recipient_label.getText();
                    String time = "03:30";
                    client.sendMessage(recipientName, message, time);
                    Platform.runLater(() -> {
                        se_chat_lb.setText(MessageTextField.getText());
                        se_time.setText("02:30");
                    });

                    MessageTextField.clear();
                }
            });
        });
    }





    /**
     * Loads user data from files.
     *
     * @param namesFilePath   The path to the file containing user names.
     * @param imagesFilePath  The path to the file containing user images.
     * @throws IOException If an I/O error occurs.
     */
    public void loadUserData(String namesFilePath, String imagesFilePath) throws IOException {
        List<String> namesData = Files.readAllLines(Paths.get(namesFilePath));
        List<String> imagesData = Files.readAllLines(Paths.get(imagesFilePath));



        Map<String, String> names = new HashMap<>();
        Map<String, String> images = new HashMap<>();

        for (String line : namesData) {
            String[] parts = line.split(",");
            if (parts.length >= 2) {
                String id = parts[0].trim();
                String name = parts[1].trim();
                names.put(id, name);
            }
        }

        for (String line : imagesData) {
            String[] parts = line.split(",");
            if (parts.length >= 2) {
                String id = parts[0].trim();
                String imagePath = parts[1].trim();
                images.put(id, imagePath);
            }
        }

        if (!names.isEmpty() && !images.isEmpty()) {
            String firstId = names.keySet().iterator().next();
            if (images.containsKey(firstId)) {
                String name = names.remove(firstId);
                String imagePath = images.remove(firstId);

                recipient_hbox.getChildren().clear();
                ImageView imageView = new ImageView(new Image(imagePath));
                imageView.setFitHeight(52.0);
                imageView.setFitWidth(50.0);
                Circle clip = new Circle(26, 26, 26);
                imageView.setClip(clip);
                recipient_hbox.getChildren().add(imageView);

                VBox vbox = new VBox();
                vbox.setPrefHeight(50.0);
                vbox.setPrefWidth(145.0);
                vbox.setPadding(new Insets(0, 0, 0, 10));

                Label nameLabel = new Label(name);
                nameLabel.setPrefHeight(29.0);
                nameLabel.setPrefWidth(172.0);
                nameLabel.setTextFill(Color.WHITE);
                nameLabel.setFont(new Font("Ebrima Bold", 18));
                vbox.getChildren().add(nameLabel);

                Label messageLabel = new Label("message content");
                messageLabel.setPrefHeight(29.0);
                messageLabel.setPrefWidth(172.0);
                messageLabel.setTextFill(Color.web("#90558e"));
                messageLabel.setFont(new Font("Ebrima", 14));
                vbox.getChildren().add(messageLabel);

                recipient_hbox.getChildren().add(vbox);
                HBox.setHgrow(vbox, Priority.ALWAYS);

                Label timeLabel = new Label("03:30");
                timeLabel.setPrefHeight(28.0);
                timeLabel.setPrefWidth(44.0);
                timeLabel.setTextAlignment(TextAlignment.CENTER);
                timeLabel.setTextFill(Color.WHITE);
                recipient_hbox.getChildren().add(timeLabel);

                recipient_hbox.setOnMouseClicked(event -> {
                    recipient_label.setText(nameLabel.getText());
                    recipient_image.setImage(imageView.getImage());
                    String username = recipient_label.getText();
                    List<HBox> userMessages = userMessagesMap.get(username);
                    mainVBox.getChildren().clear();
                    if (userMessages != null) {
                        mainVBox.getChildren().addAll(userMessages);
                    }
                });
            }
        }

        for (String id : names.keySet()) {
            String name = names.get(id);
            String imagePath = images.getOrDefault(id, "file:/C:/Users/ahmed/Desktop/facebook_source/source/src/main/resources/images/profile.png");
            HBox hbox = createRecipientHBox(name, imagePath, "message content", "03:30");
            recipients_vbox.getChildren().add(hbox);
        }
    }

    private HBox createRecipientHBox(String name, String imagePath, String message, String time) {
        HBox hbox = new HBox();
        hbox.setPrefHeight(43.0);
        hbox.setPrefWidth(254.0);
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setCursor(Cursor.HAND);

        ImageView imageView = new ImageView(new Image("file:" + imagePath.replace("\\", "/")));
        imageView.setFitHeight(52.0);
        imageView.setFitWidth(50.0);
        Circle clip = new Circle(26, 26, 26);
        imageView.setClip(clip);
        hbox.getChildren().add(imageView);

        VBox vbox = new VBox();
        vbox.setPrefHeight(50.0);
        vbox.setPrefWidth(145.0);
        vbox.setPadding(new Insets(0, 0, 0, 10));

        Label nameLabel = new Label(name);
        nameLabel.setPrefHeight(29.0);
        nameLabel.setPrefWidth(172.0);
        nameLabel.setTextFill(Color.WHITE);
        nameLabel.setFont(new Font("Ebrima Bold", 18));
        vbox.getChildren().add(nameLabel);

        Label messageLabel = new Label(message);
        messageLabel.setPrefHeight(29.0);
        messageLabel.setPrefWidth(172.0);
        messageLabel.setTextFill(Color.web("#90558e"));
        messageLabel.setFont(new Font("Ebrima", 14));
        vbox.getChildren().add(messageLabel);

        hbox.getChildren().add(vbox);
        HBox.setHgrow(vbox, Priority.ALWAYS);

        Label timeLabel = new Label(time);
        timeLabel.setPrefHeight(28.0);
        timeLabel.setPrefWidth(44.0);
        timeLabel.setTextAlignment(TextAlignment.CENTER);
        timeLabel.setTextFill(Color.WHITE);
        hbox.getChildren().add(timeLabel);

        hbox.setOnMouseClicked(event -> {
            recipient_label.setText(nameLabel.getText());
            recipient_image.setImage(imageView.getImage());
            recipient_image2.setImage(recipient_image.getImage());
            client_image.setImage(UserPhoto.getImage());

            // Search for copies in the map and display them
            String username = recipient_label.getText();
            List<HBox> userMessages = userMessagesMap.get(username);
            mainVBox.getChildren().clear();
            if (userMessages != null) {
                mainVBox.getChildren().addAll(userMessages);
            }
        });

        return hbox;
    }

    public void setClient(ClientApp client) {
        this.client = client;
        this.client.setUsername(DATA.currentUser.name);
    }

    @FXML
    public void UserPhotoClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            UserPhoto.setImage(image);

            UserPhoto.setPreserveRatio(true);
            UserPhoto.setSmooth(true);
        }
    }

    @FXML
    public void ChatIconClicked() {
        ChatIcon.setVisible(true);
        ChatIcon2.setVisible(false);

        GroupIcon2.setVisible(false);
        GroupIcon.setVisible(true);

        SettingIcon2.setVisible(false);
        SettingIcon.setVisible(true);
    }

    @FXML
    public void GroupIconClicked() {
        ChatIcon.setVisible(false);
        ChatIcon2.setVisible(true);

        GroupIcon2.setVisible(true);
        GroupIcon.setVisible(false);

        SettingIcon2.setVisible(false);
        SettingIcon.setVisible(true);
    }

    @FXML
    public void SettingIconClicked() {
        ChatIcon.setVisible(false);
        ChatIcon2.setVisible(true);

        GroupIcon2.setVisible(false);
        GroupIcon.setVisible(true);

        SettingIcon2.setVisible(true);
        SettingIcon.setVisible(false);
    }


    @FXML
    public void sendMessage() {
        if (MessageTextField != null) {
            String message = MessageTextField.getText();
            if (!message.isEmpty()) {
                String username = client.getUsername();

                LocalTime currentTime = LocalTime.now();
                String time = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                client.sendMessage(username, message,time);

                MessageTextField.clear();
            }
        }
    }

    private HBox duplicateHBox() {
        HBox newHBox = new HBox();
        newHBox.setAlignment(se_hbox.getAlignment());
        newHBox.setPrefHeight(se_hbox.getPrefHeight());
        newHBox.setPrefWidth(se_hbox.getPrefWidth());

        for (Node child : se_hbox.getChildren()) {
            if (child instanceof Label) {
                Label oldLabel = (Label) child;
                Label newLabel = new Label(oldLabel.getText());
                if (oldLabel == se_time) {
                    HBox.setMargin(newLabel, new Insets(10, 8, 0, 0));
                }
                newLabel.setFont(oldLabel.getFont());
                newLabel.setTextFill(oldLabel.getTextFill());
                newHBox.getChildren().add(newLabel);
            } else if (child instanceof Pane) {
                Pane oldPane = (Pane) child;
                Pane newPane = new Pane();
                newPane.setPrefHeight(oldPane.getPrefHeight());
                newPane.setPrefWidth(oldPane.getPrefWidth());


                for (Node paneChild : oldPane.getChildren()) {
                    if (paneChild instanceof ImageView) {
                        ImageView oldImageView = (ImageView) paneChild;
                        ImageView newImageView = new ImageView(oldImageView.getImage());
                        newImageView.setFitHeight(oldImageView.getFitHeight());
                        newImageView.setFitWidth(oldImageView.getFitWidth());
                        newPane.getChildren().add(newImageView);
                    } else if (paneChild instanceof Label) {
                        Label oldLabel = (Label) paneChild;
                        Label newLabel = new Label(oldLabel.getText());
                        newLabel.setLayoutX(oldLabel.getLayoutX());
                        newLabel.setLayoutY(oldLabel.getLayoutY());
                        newLabel.setFont(oldLabel.getFont());
                        newLabel.setTextFill(oldLabel.getTextFill());

                        newPane.getChildren().add(newLabel);
                    }
                }

                newHBox.getChildren().add(newPane);
            } else if (child instanceof ImageView) {
                ImageView oldImageView = (ImageView) child;
                ImageView newImageView = new ImageView(oldImageView.getImage());
                newImageView.setFitHeight(oldImageView.getFitHeight());
                newImageView.setFitWidth(oldImageView.getFitWidth());
                if (oldImageView == se_chat) {
                    newImageView.setLayoutX(oldImageView.getLayoutX());
                    newImageView.setLayoutY(oldImageView.getLayoutY());
                    HBox.setMargin(newImageView, new Insets(0, 5, 0, 0));

                }

                newHBox.getChildren().add(newImageView);
            }
        }

        mainVBox.getChildren().add(newHBox);
        Platform.runLater(() -> mainVBox.layout());
        userMessagesMap.computeIfAbsent(recipient_name_l.getText(), k -> new ArrayList<>()).add(newHBox);

        return newHBox;
    }

    public void ExitbuttonHandle(MouseEvent mouseEvent) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(mouseEvent,"feed.fxml");
    }
}

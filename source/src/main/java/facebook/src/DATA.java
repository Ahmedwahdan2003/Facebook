package facebook.src;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DATA extends root_Data {

    static final String usersFilePath="Text Files/Users.txt";
    static final String friendsFilePath="Text Files/Friends.txt";
    static final String restrictedUsersFilePath="Text Files/restricted_users.txt";
    static final String photosFilePath = "Text Files/Profile_Photo.txt";
    static final String postsFilePath = "Text Files/posts.txt";
    static final String interactionsFilePath = "Text Files/interactions.txt";

    public static void read() throws FacebookExceptions {
        if(!(new File(usersFilePath).exists())){
            throw new FacebookExceptions(usersFilePath);
        } else if (!(new File(friendsFilePath).exists())) {
            throw new FacebookExceptions(friendsFilePath);
        } else if (!(new File(restrictedUsersFilePath).exists())) {
            throw new FacebookExceptions(restrictedUsersFilePath);
        } else if (!(new File(photosFilePath).exists())) {
            throw new FacebookExceptions(photosFilePath);
        } else if (!(new File(postsFilePath).exists())) {
            throw new FacebookExceptions(postsFilePath);
        } else if (!(new File(interactionsFilePath).exists())) {
            throw new FacebookExceptions(interactionsFilePath);
        } else {
            read_users();
            readPosts();
            read_interactions();
        }
    }
    public static void writeDataToFile() throws FacebookExceptions {
        if(!(new File(usersFilePath).exists())){
            throw new FacebookExceptions(usersFilePath);
        } else if (!(new File(friendsFilePath).exists())) {
            throw new FacebookExceptions(friendsFilePath);
        } else if (!(new File(restrictedUsersFilePath).exists())) {
            throw new FacebookExceptions(restrictedUsersFilePath);
        } else if (!(new File(photosFilePath).exists())) {
            throw new FacebookExceptions(photosFilePath);
        } else if (!(new File(postsFilePath).exists())) {
            throw new FacebookExceptions(postsFilePath);
        } else if (!(new File(interactionsFilePath).exists())) {
            throw new FacebookExceptions(interactionsFilePath);
        } else {
            clearFile("Text Files/Users.txt");
            clearFile("Text Files/Friends.txt");
            clearFile("Text Files/restricted_users.txt");
            clearFile("Text Files/Profile_Photo.txt");
            clearFile("Text Files/posts.txt");
            clearFile("Text Files/interactions.txt");

            writeUsersToFile();
            writeFriendsToFile();
            writeRestrictedUsersToFile();
            writePhotosToFile();
            writePostsToFile();
            writeInteractionsToFile();
        }
    }

    private static void read_users() {
        try {
            // Read users
            BufferedReader reader = Files.newBufferedReader(Paths.get(usersFilePath), StandardCharsets.UTF_8);
            {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" ");
                        User user;
                    if (parts.length == 6) {
                        int id = Integer.parseInt(parts[0]);
                        String email = parts[1];
                        String name = parts[2].replace("_", " ");
                        String password = parts[3];
                        String gender = parts[4];
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate date = LocalDate.parse(parts[5], formatter);
                        user = new User(id, name, email, gender, date, password, new ArrayList<>(), new ArrayList<>());
                        user.likedPosts = new ArrayList<>();
                        user.profile_photo_path = "file:/D:/projects/Java/Facebook/source/src/main/resources/images/profile.png";
                        DATA.users.add(user);
                    }
                }
            }

            // Read friends
            File friendsFile = new File(friendsFilePath);
            Scanner friendsScanner = new Scanner(friendsFile);

            while (friendsScanner.hasNextLine()) {
                String line = friendsScanner.nextLine();
                String[] friendData = line.split(":");
                int userId = Integer.parseInt(friendData[0]);

                if (userId <= DATA.users.size() && friendData.length > 1) {
                    User user = DATA.users.get(userId - 1);
                    String[] friends = friendData[1].split(" ");

                    for (String friendId : friends) {
                        int friendUserId = Integer.parseInt(friendId);
                        if (friendUserId <= DATA.users.size()) {
                            user.friends.add(friendUserId);
                        }
                    }
                }
            }

            friendsScanner.close();

            // Read restricted users
            File restrictedUsersFile = new File(restrictedUsersFilePath);
            Scanner restrictedUsersScanner = new Scanner(restrictedUsersFile);

            while (restrictedUsersScanner.hasNextLine()) {
                String line = restrictedUsersScanner.nextLine();
                String[] restrictedUserData = line.split(":");
                int userId = Integer.parseInt(restrictedUserData[0]);

                if (userId <= DATA.users.size() && restrictedUserData.length > 1) {
                    User user = DATA.users.get(userId - 1);
                    String[] restrictedUserId = restrictedUserData[1].split(" ");

                    for (String Id : restrictedUserId) {
                        int UserId = Integer.parseInt(Id);
                        if (UserId <= DATA.users.size()) {
                            user.friends.add(UserId);
                        }
                    }
                }
            }

            restrictedUsersScanner.close();

            // Read users
            File photosFile = new File(photosFilePath);
            Scanner PhotosScanner = new Scanner(photosFile);

            while (PhotosScanner.hasNextLine()) {
                String line = PhotosScanner.nextLine();
                String[] userData = line.split(" ");

                int id = Integer.parseInt(userData[0]);
                User user = DATA.users.get(id - 1);
                user.profile_photo_path = userData[1];
            }

            PhotosScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    private static void readPosts() {
        try (Scanner scanner = new Scanner(new File(postsFilePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s+");

                int post_id = Integer.parseInt(parts[0]);
                boolean is_public = Boolean.parseBoolean(parts[1]);
                int author_id = Integer.parseInt(parts[2]);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(parts[3], formatter);

                int i = 3;
                ArrayList<Integer> tags = new ArrayList<>();
                while(!parts[++i].equals(":")){
                    tags.add(Integer.parseInt(parts[i]));
                }

                // Join the remaining parts to form the content
                StringBuilder contentBuilder = new StringBuilder();
                for (i = i + 1; i < parts.length; i++) {
                    contentBuilder.append(parts[i]).append(" ");
                }
                String content = contentBuilder.toString().trim();

                Post post = new Post(post_id, date, is_public, content, author_id, tags);
                post.likesCount = 0;
                DATA.Posts.add(post);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
    private static void read_interactions() {

        try (BufferedReader reader = new BufferedReader(new FileReader("Text Files/interactions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int userId = Integer.parseInt(parts[0]);
                int postId = Integer.parseInt(parts[1]);
                DATA.Posts.get(postId - 1).likesCount++;
                DATA.users.get(userId - 1).likedPosts.add(postId);
                DATA.interactionList.add(new interactions(userId, postId));
            }
        } catch (IOException | NumberFormatException exception) {
            exception.getLocalizedMessage();
        }
    }


    private static void writeUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFilePath))) {
            for (User user : users) {
                writer.write(user.id + " " + user.Email + " " + user.getName().replace(' ', '_') + " " + user.password + " " + user.gender + " " + user.Date.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFriendsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(friendsFilePath))) {
            for (User user : users) {
                int userId = user.id;
                ArrayList<Integer> friends = user.friends;
                writer.write(userId + ":" + String.join(" ", friends.stream().map(String::valueOf).toArray(String[]::new)) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeRestrictedUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(restrictedUsersFilePath))) {
            for (User user : users) {
                writer.write(user.id + ":" + String.join(" ", user.restricted_users.stream().map(String::valueOf).toArray(String[]::new)) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writePhotosToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(photosFilePath))) {
            for (User user : users) {
                int userId = user.id;
                String profilePhotoPath = user.profile_photo_path;
                writer.write(userId + " " + profilePhotoPath + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writePostsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(postsFilePath))) {
            for (Post post : Posts) {
                if(!post.tagged_users_ids.isEmpty()) {
                    writer.write(post.post_id + " " + post.is_public + " " + post.author_id + " " +
                            post.Date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + post.tagged_users_ids.stream().map(Object::toString)
                            .collect(Collectors.joining(" ")) + " : " + post.content + "\n");
                }
                else {
                    writer.write(post.post_id + " " + post.is_public + " " + post.author_id + " " +
                            post.Date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " : " + post.content + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeInteractionsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(interactionsFilePath))) {
            for (interactions interaction : interactionList) {
                writer.write(interaction.getUser_id() + "," + interaction.getPost_id() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void clearFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
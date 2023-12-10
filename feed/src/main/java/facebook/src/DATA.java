package facebook.src;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DATA extends root_Data {

    public static void read(){
        read_users();
        readPosts();
        read_interactions();
    }

    private static void read_users() {

       final  String usersFilePath="Text Files/Users.txt";
       final String friendsFilePath="Text Files/Friends.txt";
       final String restrictedUsersFilePath="Text Files/restricted_users.txt";
        try {
            // Read users
            File usersFile = new File(usersFilePath);
            Scanner usersScanner = new Scanner(usersFile);

            while (usersScanner.hasNextLine()) {
                String line = usersScanner.nextLine();
                String[] userData = line.split(" ");

                int id = Integer.parseInt(userData[0]);
                String name = userData[1];
                String password = userData[2];

                User user = new User(id, name, password, new ArrayList<>(), new ArrayList<>());
                DATA.users.add(user);
            }

            usersScanner.close();

            // Read friends
            File friendsFile = new File(friendsFilePath);
            Scanner friendsScanner = new Scanner(friendsFile);

            while (friendsScanner.hasNextLine()) {
                String line = friendsScanner.nextLine();
                String[] friendData = line.split(":");
                int userId = Integer.parseInt(friendData[0]);

                if (userId <= DATA.users.size()) {
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

                if (userId <= DATA.users.size()) {
                    User user = DATA.users.get(userId - 1);
                    int restrictedUserId = Integer.parseInt(restrictedUserData[1]);

                    if (restrictedUserId <= DATA.users.size()) {
                        user.restricted_users.add(restrictedUserId);
                    }
                }
            }

            restrictedUsersScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
    private static void readPosts() {

        String filePath = "Text Files/posts.txt";
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s+");

                int post_id = Integer.parseInt(parts[0]);
                boolean is_public = Boolean.parseBoolean(parts[1]);
                int author_id = Integer.parseInt(parts[2]);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(parts[3], formatter);


                // Join the remaining parts to form the content
                StringBuilder contentBuilder = new StringBuilder();
                for (int i = 4; i < parts.length; i++) {
                    contentBuilder.append(parts[i]).append(" ");
                }
                String content = contentBuilder.toString().trim();

                post postt = new post(post_id, date, is_public, content, author_id);
                DATA.posts.add(postt);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
    private static void read_interactions() {

        try (BufferedReader reader = new BufferedReader(new FileReader("Text Files/Friends.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int userId = Integer.parseInt(parts[0]);
                int postId = Integer.parseInt(parts[1]);
                DATA.interactionList.add(new interactions(userId, postId));
            }
        } catch (IOException | NumberFormatException exception) {
            exception.getLocalizedMessage();
        }
    }
}

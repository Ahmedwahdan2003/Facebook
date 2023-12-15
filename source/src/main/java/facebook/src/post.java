package facebook.src;

import java.time.LocalDate;
import java.util.ArrayList;

public class Post {
    int post_id;
    boolean is_public;

    int author_id;
    LocalDate Date;
    String content;
    int number_of_likes;
    public ArrayList<Integer> tagged_users_ids;

    Post(int post_id, LocalDate Date, boolean is_public, String content, int author_id){
        this.post_id=post_id;
        this.Date=Date;
        this.is_public=is_public;
        this.author_id=author_id;
        this.content=content;
    }



}

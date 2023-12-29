package facebook.src;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The Post class represents a post in the Facebook application.
 */
public class Post {
    /** The unique identifier of the post. */
    int post_id;

    /** Indicates whether the post is public or not. */
    boolean is_public;

    /** The unique identifier of the post author. */
    int author_id;

    /** The date when the post was created. */
    LocalDate Date;

    /** The content of the post. */
    String content;

    /** The number of likes the post has received. */
    public int likesCount = 0;

    /** The list of user IDs who are tagged in the post. */
    public ArrayList<Integer> tagged_users_ids;

    /**
     * Constructs a new Post with the specified attributes.
     *
     * @param post_id           The unique identifier of the post.
     * @param Date              The date when the post was created.
     * @param is_public         Indicates whether the post is public or not.
     * @param content           The content of the post.
     * @param author_id         The unique identifier of the post author.
     * @param tagged_users_ids  The list of user IDs who are tagged in the post.
     */
    Post(int post_id, LocalDate Date, boolean is_public, String content, int author_id, ArrayList<Integer> tagged_users_ids) {
        this.post_id = post_id;
        this.Date = Date;
        this.is_public = is_public;
        this.author_id = author_id;
        this.content = content;
        this.tagged_users_ids = tagged_users_ids;
    }
}

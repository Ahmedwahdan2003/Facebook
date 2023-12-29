package facebook.src;

import java.util.*;

/**
 * The recommendation_system class provides functionality for generating the logged-in user's feed,
 * including their own posts, posts from friends, and recommended posts using collaborative filtering.
 */
public class recommendation_system {

    private final int max_user_id = DATA.users.size();//number of users in the system
    private final int max_post_id = DATA.Posts.size();// number of posts in the system
    private final int[][] useritemmatrix = new int[max_user_id + 100][max_post_id + 100];


    //get all feed (frineds posts + recommended posts)
    /**
     * Gets the logged-in user's feed, including their own posts, posts from friends, and recommended posts.
     *
     * @return An ArrayList of Post objects representing the user's feed.
     */
    public ArrayList<Post>get_logged_in_user_feed(){
        // we will have 2ArrayLists of integers (recommended_posts_ids,user_friends_post_ids)
        //1- remove duplicates
        //2- convert ids to posts objects
        //3- check restriction and date and post privacy(important)

           ArrayList<Post>user_published_posts = new ArrayList<>(); // make the user also see his posts
            for(Post p:DATA.Posts){
                if(p.author_id== DATA.currentUser.id)
                    user_published_posts.add(p);
            }
        ArrayList<Integer>recommended_posts = getRecommendedPosts();
        ArrayList<Integer>friends_posts = get_logged_in_user_friends_posts();
         Set<Integer>feed_set = new HashSet<>(friends_posts);
         feed_set.addAll(recommended_posts);//remove duplicate posts

        ArrayList<Integer>posts_ids = new ArrayList<>(feed_set);
        ArrayList<Post>posts_objects = new ArrayList<>();
         for(Integer post_id:posts_ids){                        //converting ids to actual posts objects
             posts_objects.add(DATA.getPostById(post_id));
         }

        Iterator<Post> iterator = posts_objects.iterator();

        while (iterator.hasNext()) {                            // using iterators to avoid ConcurrentModificationException
            Post POST = iterator.next();
            User post_author = DATA.getUserById(POST.author_id);

            if ((!POST.is_public && Objects.requireNonNull(post_author).restricted_users.contains(DATA.currentUser.id)) //throws null ptr exception if the post object is null
                    || (!POST.is_public && !post_author.friends.contains(DATA.currentUser.id))) {
                //System.out.println("restriction found in post "+ POST.post_id+" logged_in_user_id: "+logged_in_user.id+" author_id: "+ post_author.id);
                iterator.remove();
            }
        }
        posts_objects.addAll(user_published_posts);
        return posts_objects;
    }
    /**
     * Gets posts from the logged-in user's friends.
     *
     * @return An ArrayList of integers representing post IDs from the user's friends.
     */

    // get posts from logged_in_user friends
    private ArrayList<Integer>get_logged_in_user_friends_posts(){//1-
       ArrayList<Integer>friends_feed = new ArrayList<>();
       for(Integer friend: DATA.currentUser.friends){
           for(Post p:DATA.Posts){
               if(p.author_id==friend){
                   friends_feed.add(p.post_id);
               }
           }
       }


        return friends_feed;
    }
    /**
     * Gets recommended posts for the logged-in user using collaborative filtering.
     *
     * @return An ArrayList of integers representing recommended post IDs.
     */
    private ArrayList<Integer> getRecommendedPosts() {
        construct_useritem_matrix();
       ArrayList<Integer> recommendedPosts = new ArrayList<>();
        Set<Integer> seenPosts = new HashSet<>();

        // Identify posts already seen by the target user
        for (interactions interaction : DATA.interactionList) {
            if (interaction.getUser_id() == DATA.currentUser.id) {
                seenPosts.add(interaction.getPost_id());
            }
        }

        // applying collabortiveflitering algorithm which calculate the similarity between target user and all other users then recommend posts based on that
        for (int userId = 0; userId < useritemmatrix.length; userId++) {
            if (userId != DATA.currentUser.id) {
                double similarity = calculateCosineSimilarity(DATA.currentUser.id, userId);
                if (similarity >= 0.7) { // fair enough similarity
                    for (int postId = 0; postId < useritemmatrix[userId].length; postId++) {
                        if (useritemmatrix[userId][postId] == 1 && !seenPosts.contains(postId)) {
                            recommendedPosts.add(postId);
                        }
                    }
                }
            }
        }





        return recommendedPosts;
    }

            // cousine similarity is used to predict the similarity of two vectors (target_user likes and other_user likes) 
            // the equation is normalized here but we dont need it because all vectors have binary values the normalization works when we have more complex matrix with variety of numbers)
    private double calculateCosineSimilarity(int logged_in_user_id, int otherUserId) {
        double dotProduct = 0.0;
        double normTarget = 0.0;
        double normOther = 0.0;

        for (int postId = 0; postId < useritemmatrix[logged_in_user_id].length; postId++) {
            dotProduct += useritemmatrix[logged_in_user_id][postId] * useritemmatrix[otherUserId][postId];
            normTarget += Math.pow(useritemmatrix[logged_in_user_id][postId], 2);
            normOther += Math.pow(useritemmatrix[otherUserId][postId], 2);
        }

        if (normTarget == 0 || normOther == 0) {
            return 0.0; // To avoid division by zero
        }
        // the dot product of the two vectors over the magnitude product of the vector 
        return dotProduct / (Math.sqrt(normTarget) * Math.sqrt(normOther));
    }

    /**
     * Constructs the user-item matrix based on user interactions with posts.
     */
    private void construct_useritem_matrix() {
        for (interactions i : DATA.interactionList) {
            int user_id = i.getUser_id();
            int post_id = i.getPost_id();
            useritemmatrix[user_id][post_id] = 1;
        }
    }
}

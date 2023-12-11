package facebook.src;

import java.util.*;

public class recommendation_system {




    private final User logged_in_user;
    recommendation_system(User logged_in_user){
        this.logged_in_user=logged_in_user;
    }


    private final int max_user_id = 10;//number of users in the system
    private final int max_post_id = 10;// number of posts in the system
    private final int[][] useritemmatrix = new int[max_user_id + 1][max_post_id + 1];


    //get all feed (frineds posts + recommended posts)
    public ArrayList<post>get_logged_in_user_feed(){
        // we will have 2ArrayLists of integers (recommended_posts_ids,user_friends_post_ids)
        //1- remove duplicates
        //2- convert ids to posts objects
        //3- check restriction and date and post privacy(important)

           ArrayList<post>user_published_posts = new ArrayList<>(); // make the user also see his posts
            for(post p:DATA.posts){
                if(p.author_id== logged_in_user.id)
                    user_published_posts.add(p);
            }
        ArrayList<Integer>recommended_posts = getRecommendedPosts();
        ArrayList<Integer>friends_posts = get_logged_in_user_friends_posts();
         Set<Integer>feed_set = new HashSet<>(friends_posts);
         feed_set.addAll(recommended_posts);//remove duplicate posts

        ArrayList<Integer>posts_ids = new ArrayList<>(feed_set);
        ArrayList<post>posts_objects = new ArrayList<>();
         for(Integer post_id:posts_ids){                        //converting ids to actual posts objects
             posts_objects.add(DATA.getPostById(post_id));
         }

        Iterator<post> iterator = posts_objects.iterator();

        while (iterator.hasNext()) {                            // using iterators to avoid ConcurrentModificationException
            post POST = iterator.next();
            User post_author = DATA.getUserById(POST.author_id);

            if ((!POST.is_public && Objects.requireNonNull(post_author).restricted_users.contains(logged_in_user.id)) //throws null ptr exception if the post object is null
                    || (!POST.is_public && !post_author.friends.contains(logged_in_user.id))) {
                System.out.println("restriction found in post "+ POST.post_id+" logged_in_user_id: "+logged_in_user.id+" author_id: "+ post_author.id);
                iterator.remove();
            }
        }
        posts_objects.addAll(user_published_posts);
        return posts_objects;
    }

    // get posts from logged_in_user friends
    private ArrayList<Integer>get_logged_in_user_friends_posts(){//1-
       ArrayList<Integer>friends_feed = new ArrayList<>();
       for(Integer friend: logged_in_user.friends){
           for(post p:DATA.posts){
               if(p.author_id==friend){
                   friends_feed.add(p.post_id);
               }
           }
       }


        return friends_feed;
    }
    private ArrayList<Integer> getRecommendedPosts() {
        construct_useritem_matrix();
       ArrayList<Integer> recommendedPosts = new ArrayList<>();
        Set<Integer> seenPosts = new HashSet<>();

        // Identify posts already seen by the target user
        for (interactions interaction : DATA.interactionList) {
            if (interaction.getUser_id() == logged_in_user.id) {
                seenPosts.add(interaction.getPost_id());
            }
        }

        // applying collabortiveflitering algorithm which calculate the similarity between target user and all other users then recommend posts based on that
        for (int userId = 0; userId < useritemmatrix.length; userId++) {
            if (userId != logged_in_user.id) {
                double similarity = calculateCosineSimilarity(logged_in_user.id, userId);
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


    private void construct_useritem_matrix() {
        for (interactions i : DATA.interactionList) {
            int user_id = i.getUser_id();
            int post_id = i.getPost_id();
            useritemmatrix[user_id][post_id] = 1;
        }
    }
}

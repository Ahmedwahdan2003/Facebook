package facebook.src;


    public class Reply extends Comment{
        private int replyId;
        public Reply(String commentText,int post_id) {
            super(commentText,post_id);
        }

        public int getReplyId()
        {return replyId;}
}

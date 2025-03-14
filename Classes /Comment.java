class Comment {
    private int commentID;
    private int userID;
    private int recipeID;
    private String text;
    private Date timestamp;

    public Comment(int commentID, int userID, int recipeID, String text) {
        this.commentID = commentID;
        this.userID = userID;
        this.recipeID = recipeID;
        this.text = text;
        this.timestamp = new Date();
    }

    public void addComment() {
        System.out.println("Comment added: " + text);
    }

    public void deleteComment() {
        System.out.println("Comment deleted.");
    }
}

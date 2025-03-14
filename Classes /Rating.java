Rating {
    private int ratingID;
    private int userID;
    private int recipeID;
    private int stars;

    public Rating(int ratingID, int userID, int recipeID, int stars) {
        this.ratingID = ratingID;
        this.userID = userID;
        this.recipeID = recipeID;
        this.stars = stars;
    }

    public void giveRating() {
        System.out.println("Recipe rated with " + stars + " stars.");
    }

    public void editRating(int newStars) {
        this.stars = newStars;
        System.out.println("Rating updated to " + stars + " stars.");
    }

    public void deleteRating() {
        System.out.println("Rating deleted.");
    }
}

class Recipe {
    private int recipeID;
    private String title;
    private String description;
    private List<String> ingredients;
    private String instructions;
    private int authorID;
  
    public Recipe(int recipeID, String title, String description, String instructions, int authorID) {
        this.recipeID = recipeID;
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.authorID = authorID;
        this.ingredients = new ArrayList<>();
    }
    public void viewRecipe() {
        System.out.println("Recipe: " + title);
        System.out.println("Description: " + description);
        System.out.println("Instructions: " + instructions);
        System.out.println("Ingredients: " + ingredients);
    }

    public void editRecipe(String newTitle, String newDescription) {
        this.title = newTitle;
        this.description = newDescription;
        System.out.println("Recipe updated.");
    }
  
    public void deleteRecipe() {
        System.out.println("Recipe deleted.");
    }

    public void shareRecipe() {
        System.out.println("Recipe shared: " + title);
        System.out.println("Description: " + description);
        System.out.println("Instructions: " + instructions);
        System.out.println("Ingredients: " + ingredients);
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public int getRecipeID() {
        return recipeID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructions() {
        return instructions;
    }

    public int getAuthorID() {
        return authorID;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}

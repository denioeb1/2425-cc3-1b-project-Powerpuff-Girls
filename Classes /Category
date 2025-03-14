class Category {
    private int categoryID;
    private String name;
    private String description;
    private List<Recipe> recipes;  // List of recipes in this category
  
    public Category(int categoryID, String name, String description) {
        this.categoryID = categoryID;
        this.name = name;
        this.description = description;
        this.recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        System.out.println("Recipe added to category: " + name);
    }
    public void viewCategory() {
        System.out.println("Category ID: " + categoryID);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
    }
    public void listRecipes() {
        System.out.println("Listing recipes for category: " + name);
        for (Recipe recipe : recipes) {
            recipe.viewRecipe();
        }
    }
}

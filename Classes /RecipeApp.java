 class RecipeApp {
    private String appName;
    private List<User> users;

    // Constructor for RecipeApp
    public RecipeApp(String appName) {
        this.appName = appName;
        this.users = new ArrayList<>();
    }

    // Method to start the app
    public void startApp() {
        System.out.println("Welcome to " + appName + "!");
    }

    // Method to add a user to the app
    public void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user.getUsername());
    }

    // Method to show the welcome screen
    public void showWelcomeScreen() {
        System.out.println("Welcome to the Recipe App! Explore and share your favorite recipes.");
    }
}

 class RecipeApp {
    private String appName;
    private List<User> users;

    public RecipeApp(String appName) {
        this.appName = appName;
        this.users = new ArrayList<>();
    }

    public void startApp() {
        System.out.println("Welcome to " + appName + "!");
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user.getUsername());
    }

    public void showWelcomeScreen() {
        System.out.println("Welcome to the Recipe App! Explore and share your favorite recipes.");
    }
}

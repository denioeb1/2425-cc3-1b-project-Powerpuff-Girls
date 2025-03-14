class User {
    private int userID;
    private String username;
    private String password;
    private String email;
    private String profilePicture;

    public User(int userID, String username, String password, String email, String profilePicture) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profilePicture = profilePicture;
    }

    public void register() {
        System.out.println("User registered successfully!");
    }

    public void login() {
        System.out.println("User logged in.");
    }

    public void updateProfile(String newEmail, String newProfilePicture) {
        this.email = newEmail;
        this.profilePicture = newProfilePicture;
        System.out.println("Profile updated.");
    }

    public void logout() {
        System.out.println("User logged out.");
    }
}

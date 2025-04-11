import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Recipe extends JFrame implements ActionListener {
    CardLayout cardLayout;
    JPanel cardPanel;
    ArrayList<String> favoriteRecipes = new ArrayList<>();
    ArrayList<String> recipes = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    int recipeCount = 1; // To keep track of the recipe number

    // Temporary hardcoded users for testing
    class User {
        String username;
        String password;

        User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    // Constructor to initialize the app
    public Recipe() {
        setTitle("Recipe App");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the CardLayout and the main panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Splash Screen
        JPanel splashPanel = new JPanel(new BorderLayout());
        JLabel photoLabel = new JLabel("[Logo Placeholder]", JLabel.CENTER);
        splashPanel.add(photoLabel, BorderLayout.CENTER);
        JLabel appName = new JLabel("Welcome to RecipeApp!", JLabel.CENTER);
        appName.setFont(new Font("Arial", Font.BOLD, 24));
        splashPanel.add(appName, BorderLayout.NORTH);

        // Button Panel for Splash Screen
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signInButton = new JButton("Sign In");
        JButton signUpButton = new JButton("Sign Up");
        signInButton.addActionListener(this);
        signUpButton.addActionListener(this);

        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(signInButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(signUpButton);
        buttonPanel.add(Box.createVerticalStrut(20));

        splashPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Sign In Panel
        JPanel signInPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        signInPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        JTextField usernameField = new JTextField(15);
        signInPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        signInPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        signInPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JButton submitSignInButton = new JButton("Sign In");
        submitSignInButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            boolean signedIn = false;

            // Check if the user credentials are correct
            for (User user : users) {
                if (user.username.equals(username) && user.password.equals(password)) {
                    signedIn = true;
                    break;
                }
            }

            if (signedIn) {
                cardLayout.show(cardPanel, "home");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        });
        signInPanel.add(submitSignInButton, gbc);

        // Sign Up Panel
        JPanel signUpPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0; gbc.gridy = 0;
        signUpPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        JTextField newUsernameField = new JTextField(15);
        signUpPanel.add(newUsernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        signUpPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField newPasswordField = new JPasswordField(15);
        signUpPanel.add(newPasswordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JButton submitSignUpButton = new JButton("Sign Up");
        submitSignUpButton.addActionListener(e -> {
            String username = newUsernameField.getText();
            String password = new String(newPasswordField.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                users.add(new User(username, password));
                JOptionPane.showMessageDialog(this, "Sign Up successful! You can now Sign In.");
                cardLayout.show(cardPanel, "splash"); // Show splash screen after signup
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        });
        signUpPanel.add(submitSignUpButton, gbc);

        // Home Panel
        JPanel homePanel = new JPanel(new BorderLayout());

        // Category Panel with categories
        JPanel categoryPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        String[] categoryNames = {"Pastry Type", "Preparation", "Difficulty Level", "Popularity", "Occasion"};
        for (String category : categoryNames) {
            JButton categoryButton = new JButton(category);
            categoryButton.addActionListener(e -> showCategoryRecipes(category));
            categoryPanel.add(categoryButton);
        }
        homePanel.add(categoryPanel, BorderLayout.NORTH);

        // Content Panel with recipe boxes
        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        for (int i = 1; i <= 8; i++) {
            JPanel recipeBox = new JPanel();
            recipeBox.setPreferredSize(new Dimension(150, 150));
            recipeBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            recipeBox.add(new JLabel("Recipe " + i));
            contentPanel.add(recipeBox);
        }
        homePanel.add(contentPanel, BorderLayout.CENTER);

        // Footer Panel with buttons
        JPanel footerPanel = new JPanel(new FlowLayout());

        JButton homeButton = new JButton("🏠 Home");
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "home"));
        footerPanel.add(homeButton);

        // Add Recipe button functionality
        JButton addRecipeButton = new JButton("➕ Add Recipe");
        addRecipeButton.addActionListener(e -> showAddRecipeDialog());
        footerPanel.add(addRecipeButton);

        homePanel.add(footerPanel, BorderLayout.SOUTH);

        // Add Panels to Card Layout
        cardPanel.add(splashPanel, "splash");
        cardPanel.add(signInPanel, "signIn");
        cardPanel.add(signUpPanel, "signUp");
        cardPanel.add(homePanel, "home");

        // Final setup
        add(cardPanel);
        cardLayout.show(cardPanel, "splash");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Sign In")) {
            cardLayout.show(cardPanel, "signIn");
        } else if (command.equals("Sign Up")) {
            cardLayout.show(cardPanel, "signUp");
        }
    }

    // Show recipes in the selected category
    public void showCategoryRecipes(String category) {
        JOptionPane.showMessageDialog(this, "Exploring recipes in " + category + " category.");
    }

    // Add Recipe Dialog
    private void showAddRecipeDialog() {
        JPanel addRecipePanel = new JPanel(new GridLayout(7, 2));

        JTextField recipeNameField = new JTextField();
        JTextArea recipeDescriptionField = new JTextArea();
        recipeDescriptionField.setRows(5);
        recipeDescriptionField.setLineWrap(true);

        // Add ComboBoxes for Category Selection
        String[] pastryTypes = {"Cake", "Pie", "Tart", "Cookie", "Croissant", "Muffin", "Eclair"};
        JComboBox<String> pastryTypeComboBox = new JComboBox<>(pastryTypes);

        String[] preparationMethods = {"Baking (20-30 minutes)", "Frying (5-10 minutes)", "Boiling (15-20 minutes)", "Steaming (10-15 minutes)"};
        JComboBox<String> preparationComboBox = new JComboBox<>(preparationMethods);

        String[] difficultyLevels = {"Easy", "Medium", "Hard"};
        JComboBox<String> difficultyComboBox = new JComboBox<>(difficultyLevels);

        String[] popularityLevels = {"Low", "Medium", "High"};
        JComboBox<String> popularityComboBox = new JComboBox<>(popularityLevels);

        String[] occasionTypes = {"Birthday", "Holiday", "Wedding", "Casual", "Celebration"};
        JComboBox<String> occasionComboBox = new JComboBox<>(occasionTypes);

        addRecipePanel.add(new JLabel("Recipe Name:"));
        addRecipePanel.add(recipeNameField);
        addRecipePanel.add(new JLabel("Description:"));
        addRecipePanel.add(new JScrollPane(recipeDescriptionField));

        addRecipePanel.add(new JLabel("Pastry Type:"));
        addRecipePanel.add(pastryTypeComboBox);

        addRecipePanel.add(new JLabel("Preparation Method:"));
        addRecipePanel.add(preparationComboBox);

        addRecipePanel.add(new JLabel("Difficulty Level:"));
        addRecipePanel.add(difficultyComboBox);

        addRecipePanel.add(new JLabel("Popularity:"));
        addRecipePanel.add(popularityComboBox);

        addRecipePanel.add(new JLabel("Occasion:"));
        addRecipePanel.add(occasionComboBox);

        JButton submitRecipeButton = new JButton("Add Recipe");
        submitRecipeButton.addActionListener(e -> {
            String recipeName = recipeNameField.getText();
            String description = recipeDescriptionField.getText();
            String pastryType = (String) pastryTypeComboBox.getSelectedItem();
            String preparation = (String) preparationComboBox.getSelectedItem();
            String difficulty = (String) difficultyComboBox.getSelectedItem();
            String popularity = (String) popularityComboBox.getSelectedItem();
            String occasion = (String) occasionComboBox.getSelectedItem();

            if (!recipeName.isEmpty() && !description.isEmpty()) {
                String recipe = "Recipe " + recipeCount++ + ": " + recipeName + " - " + description + "\n"
                        + "Pastry

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RecipeUI extends JFrame implements ActionListener {
    CardLayout cardLayout;
    JPanel cardPanel;
    ArrayList<String> favoriteRecipes = new ArrayList<>();
    ArrayList<String> recipes = new ArrayList<>();
    
    // Constructor to initialize the app
    public RecipeUI() {
        setTitle("Recipe App");
        setSize(400, 600);
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
        submitSignInButton.addActionListener(e -> cardLayout.show(cardPanel, "home"));
        signInPanel.add(submitSignInButton, gbc);

        // Forgot Password Link
        JLabel forgotPasswordLabel = new JLabel("Forgot Password?");
        forgotPasswordLabel.setForeground(Color.BLUE);
        gbc.gridy = 3;
        signInPanel.add(forgotPasswordLabel, gbc);

        forgotPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardLayout.show(cardPanel, "forgotPassword");
            }
        });

        // Forgot Password Panel
        JPanel forgotPasswordPanel = new JPanel(new FlowLayout());
        forgotPasswordPanel.add(new JLabel("Enter your Email:"));
        JTextField emailField = new JTextField(15);
        forgotPasswordPanel.add(emailField);
        JButton sendCodeButton = new JButton("Send Verification Code");
        forgotPasswordPanel.add(sendCodeButton);

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

        // Search button functionality
        JButton searchButton = new JButton("🔍 Search");
        searchButton.addActionListener(e -> showSearchDialog());
        footerPanel.add(searchButton);

        // Favorites button functionality
        JButton favoritesButton = new JButton("❤️ Favorites");
        favoritesButton.addActionListener(e -> showFavoritesDialog());
        footerPanel.add(favoritesButton);

        // Add Recipe button functionality
        JButton addRecipeButton = new JButton("➕ Add Recipe");
        addRecipeButton.addActionListener(e -> showAddRecipeDialog());
        footerPanel.add(addRecipeButton);

        homePanel.add(footerPanel, BorderLayout.SOUTH);

        // Add Panels to Card Layout
        cardPanel.add(splashPanel, "splash");
        cardPanel.add(signInPanel, "signIn");
        cardPanel.add(forgotPasswordPanel, "forgotPassword");
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

    // Search Dialog
    private void showSearchDialog() {
        String searchQuery = JOptionPane.showInputDialog(this, "Enter recipe name or ingredient:");
        if (searchQuery != null && !searchQuery.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Searching for: " + searchQuery);
        } else {
            JOptionPane.showMessageDialog(this, "Search query cannot be empty.");
        }
    }

    // Show Favorites Dialog
    private void showFavoritesDialog() {
        if (favoriteRecipes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You have no favorite recipes.");
        } else {
            JOptionPane.showMessageDialog(this, "Your favorite recipes: " + String.join(", ", favoriteRecipes));
        }
    }

    // Add Recipe Dialog
    private void showAddRecipeDialog() {
        JPanel addRecipePanel = new JPanel(new GridLayout(4, 2));

        JTextField recipeNameField = new JTextField();
        JTextArea recipeDescriptionField = new JTextArea();
        recipeDescriptionField.setRows(5);
        recipeDescriptionField.setLineWrap(true);

        addRecipePanel.add(new JLabel("Recipe Name:"));
        addRecipePanel.add(recipeNameField);
        addRecipePanel.add(new JLabel("Description:"));
        addRecipePanel.add(new JScrollPane(recipeDescriptionField));
        
        JButton submitRecipeButton = new JButton("Add Recipe");
        submitRecipeButton.addActionListener(e -> {
            String recipeName = recipeNameField.getText();
            String description = recipeDescriptionField.getText();
            if (!recipeName.isEmpty() && !description.isEmpty()) {
                recipes.add(recipeName + ": " + description);
                JOptionPane.showMessageDialog(this, "Recipe added!");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        });
        
        addRecipePanel.add(submitRecipeButton);

        JOptionPane.showOptionDialog(this, addRecipePanel, "Add Recipe", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
    }

    public static void main(String[] args) {
        new RecipeUI();
    }
}

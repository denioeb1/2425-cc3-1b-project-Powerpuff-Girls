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
    JPanel contentPanel;

    public Recipe() {
        setTitle("Recipe App");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel splashPanel = new JPanel(new BorderLayout());
        JLabel photoLabel = new JLabel("[Logo Placeholder]", JLabel.CENTER);
        splashPanel.add(photoLabel, BorderLayout.CENTER);
        JLabel appName = new JLabel("Welcome to RecipeApp!", JLabel.CENTER);
        appName.setFont(new Font("Arial", Font.BOLD, 24));
        splashPanel.add(appName, BorderLayout.NORTH);

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

        JLabel forgotPasswordLabel = new JLabel("Forgot Password?");
        forgotPasswordLabel.setForeground(Color.BLUE);
        gbc.gridy = 3;
        signInPanel.add(forgotPasswordLabel, gbc);

        forgotPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardLayout.show(cardPanel, "forgotPassword");
            }
        });

        JPanel forgotPasswordPanel = new JPanel(new FlowLayout());
        forgotPasswordPanel.add(new JLabel("Enter your Email:"));
        JTextField emailField = new JTextField(15);
        forgotPasswordPanel.add(emailField);
        JButton sendCodeButton = new JButton("Send Verification Code");
        forgotPasswordPanel.add(sendCodeButton);

        JPanel homePanel = new JPanel(new BorderLayout());

        JPanel categoryPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        String[] categoryNames = {"Pastry Type", "Preparation", "Difficulty Level", "Popularity", "Occasion"};
        for (String category : categoryNames) {
            JButton categoryButton = new JButton(category);
            categoryButton.addActionListener(e -> showCategoryRecipes(category));
            categoryPanel.add(categoryButton);
        }
        homePanel.add(categoryPanel, BorderLayout.NORTH);

        contentPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        refreshRecipeDisplay();
        homePanel.add(contentPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout());

        JButton homeButton = new JButton("🏠 Home");
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "home"));
        footerPanel.add(homeButton);

        JButton searchButton = new JButton("🔍 Search");
        searchButton.addActionListener(e -> showSearchDialog());
        footerPanel.add(searchButton);

        JButton favoritesButton = new JButton("❤️ Favorites");
        favoritesButton.addActionListener(e -> showFavoritesDialog());
        footerPanel.add(favoritesButton);

        JButton addRecipeButton = new JButton("➕ Add Recipe");
        addRecipeButton.addActionListener(e -> showAddRecipeDialog());
        footerPanel.add(addRecipeButton);

        homePanel.add(footerPanel, BorderLayout.SOUTH);

        cardPanel.add(splashPanel, "splash");
        cardPanel.add(signInPanel, "signIn");
        cardPanel.add(forgotPasswordPanel, "forgotPassword");
        cardPanel.add(homePanel, "home");

        add(cardPanel);
        cardLayout.show(cardPanel, "splash");
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Sign In")) {
            cardLayout.show(cardPanel, "signIn");
        } else if (command.equals("Sign Up")) {
            cardLayout.show(cardPanel, "signUp");
        }
    }

    public void showCategoryRecipes(String category) {
        JOptionPane.showMessageDialog(this, "Exploring recipes in " + category + " category.");
    }

    private void showSearchDialog() {
        String searchQuery = JOptionPane.showInputDialog(this, "Enter recipe name or ingredient:");
        if (searchQuery != null && !searchQuery.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Searching for: " + searchQuery);
        } else {
            JOptionPane.showMessageDialog(this, "Search query cannot be empty.");
        }
    }

    private void showFavoritesDialog() {
        if (favoriteRecipes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You have no favorite recipes.");
        } else {
            JOptionPane.showMessageDialog(this, "Your favorite recipes: " + String.join(", ", favoriteRecipes));
        }
    }

    private void showAddRecipeDialog() {
        JPanel addRecipePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField recipeNameField = new JTextField(20);
        JTextArea recipeDescriptionField = new JTextArea(5, 20);
        String[] pastryTypes = {"Croissant", "Danish", "Puff", "Strudel", "Pie", "Tart"};
        JComboBox<String> pastryTypeBox = new JComboBox<>(pastryTypes);
        JTextField prepTimeField = new JTextField(20);
        String[] difficultyLevels = {"Easy", "Medium", "Hard"};
        JComboBox<String> difficultyBox = new JComboBox<>(difficultyLevels);
        String[] popularityLevels = {"Low", "Medium", "High"};
        JComboBox<String> popularityBox = new JComboBox<>(popularityLevels);
        String[] occasionTypes = {"Breakfast", "Snack", "Dessert", "Party"};
        JComboBox<String> occasionBox = new JComboBox<>(occasionTypes);

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y; addRecipePanel.add(new JLabel("Recipe Name:"), gbc);
        gbc.gridx = 1; addRecipePanel.add(recipeNameField, gbc);

        gbc.gridx = 0; gbc.gridy = ++y; addRecipePanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1; addRecipePanel.add(new JScrollPane(recipeDescriptionField), gbc);

        gbc.gridx = 0; gbc.gridy = ++y; addRecipePanel.add(new JLabel("Pastry Type:"), gbc);
        gbc.gridx = 1; addRecipePanel.add(pastryTypeBox, gbc);

        gbc.gridx = 0; gbc.gridy = ++y; addRecipePanel.add(new JLabel("Preparation Time (mins):"), gbc);
        gbc.gridx = 1; addRecipePanel.add(prepTimeField, gbc);

        gbc.gridx = 0; gbc.gridy = ++y; addRecipePanel.add(new JLabel("Difficulty Level:"), gbc);
        gbc.gridx = 1; addRecipePanel.add(difficultyBox, gbc);

        gbc.gridx = 0; gbc.gridy = ++y; addRecipePanel.add(new JLabel("Popularity Level:"), gbc);
        gbc.gridx = 1; addRecipePanel.add(popularityBox, gbc);

        gbc.gridx = 0; gbc.gridy = ++y; addRecipePanel.add(new JLabel("Occasion:"), gbc);
        gbc.gridx = 1; addRecipePanel.add(occasionBox, gbc);

        int result = JOptionPane.showConfirmDialog(this, addRecipePanel, "Add Recipe", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = recipeNameField.getText();
            String desc = recipeDescriptionField.getText();
            if (!name.isEmpty() && !desc.isEmpty()) {
                recipes.add(name);
                refreshRecipeDisplay();
                JOptionPane.showMessageDialog(this, "Recipe added!");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        }
    }

    private void refreshRecipeDisplay() {
        contentPanel.removeAll();
        for (int i = 0; i < 8; i++) {
            JPanel recipeBox = new JPanel(new BorderLayout());
            recipeBox.setPreferredSize(new Dimension(150, 150));
            recipeBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel label = new JLabel("Recipe " + (i + 1), JLabel.CENTER);
            recipeBox.add(label, BorderLayout.CENTER);

            if (i < recipes.size()) {
                JButton favBtn = new JButton("❤️");
                String recipeName = recipes.get(i);
                favBtn.addActionListener(e -> {
                    if (!favoriteRecipes.contains(recipeName)) {
                        favoriteRecipes.add(recipeName);
                        JOptionPane.showMessageDialog(this, recipeName + " added to favorites.");
                    }
                });
                recipeBox.add(favBtn, BorderLayout.SOUTH);
            }

            contentPanel.add(recipeBox);
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        new Recipe();
    }
}

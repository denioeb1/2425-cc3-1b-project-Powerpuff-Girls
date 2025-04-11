// Recipe.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Recipe extends JFrame implements ActionListener {
    CardLayout cardLayout;
    JPanel cardPanel;
    ArrayList<String> favoriteRecipes = new ArrayList<>();
    ArrayList<String> recipes = new ArrayList<>();
    int recipeCount = 1;
    JPanel contentPanel;

    public Recipe() {
        setTitle("Recipe App");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Splash
        JPanel splashPanel = new JPanel(new BorderLayout());
        splashPanel.add(new JLabel("Welcome to Recipe App!", JLabel.CENTER), BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        JButton signIn = new JButton("Sign In");
        JButton signUp = new JButton("Sign Up");
        signIn.addActionListener(this);
        signUp.addActionListener(this);
        buttonPanel.add(signIn);
        buttonPanel.add(signUp);
        splashPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Sign In Panel
        JPanel signInPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

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
        JButton submitLogin = new JButton("Sign In");
        submitLogin.addActionListener(e -> cardLayout.show(cardPanel, "home"));
        signInPanel.add(submitLogin, gbc);

        // Forgot Password
        JLabel forgotPass = new JLabel("Forgot Password?");
        forgotPass.setForeground(Color.BLUE);
        forgotPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgotPass.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "forgotPassword");
            }
        });
        gbc.gridy = 3;
        signInPanel.add(forgotPass, gbc);

        // Forgot Password Panel
        JPanel forgotPasswordPanel = new JPanel();
        forgotPasswordPanel.add(new JLabel("Enter Email:"));
        forgotPasswordPanel.add(new JTextField(15));

        // Home Panel
        JPanel homePanel = new JPanel(new BorderLayout());
        JPanel categoryPanel = new JPanel(new GridLayout(1, 5));
        String[] categories = {"Pastry Type", "Preparation", "Difficulty Level", "Popularity", "Occasion"};
        for (String cat : categories) {
            JButton b = new JButton(cat);
            b.addActionListener(e -> JOptionPane.showMessageDialog(this, "Filtering: " + cat));
            categoryPanel.add(b);
        }
        homePanel.add(categoryPanel, BorderLayout.NORTH);

        contentPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        for (int i = 0; i < 8; i++) {
            JPanel recipeBox = new JPanel();
            recipeBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            recipeBox.add(new JLabel("Recipe " + (i + 1)));
            contentPanel.add(recipeBox);
        }
        homePanel.add(contentPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        JButton homeBtn = new JButton("🏠 Home");
        JButton searchBtn = new JButton("🔍 Search");
        JButton favBtn = new JButton("❤️ Favorites");
        JButton addBtn = new JButton("➕ Add Recipe");

        searchBtn.addActionListener(e -> {
            String q = JOptionPane.showInputDialog(this, "Search:");
            if (q != null && !q.isEmpty()) JOptionPane.showMessageDialog(this, "Searching: " + q);
        });

        favBtn.addActionListener(e -> {
            if (favoriteRecipes.isEmpty()) JOptionPane.showMessageDialog(this, "No favorites.");
            else JOptionPane.showMessageDialog(this, String.join(", ", favoriteRecipes));
        });

        addBtn.addActionListener(e -> showAddRecipeDialog());

        footerPanel.add(homeBtn);
        footerPanel.add(searchBtn);
        footerPanel.add(favBtn);
        footerPanel.add(addBtn);
        homePanel.add(footerPanel, BorderLayout.SOUTH);

        // Card Layout Panels
        cardPanel.add(splashPanel, "splash");
        cardPanel.add(signInPanel, "signIn");
        cardPanel.add(forgotPasswordPanel, "forgotPassword");
        cardPanel.add(homePanel, "home");
        add(cardPanel);
        cardLayout.show(cardPanel, "splash");
        setVisible(true);
    }

    public void showAddRecipeDialog() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nameField = new JTextField(20);
        JTextArea descField = new JTextArea(3, 20);
        descField.setWrapStyleWord(true);
        descField.setLineWrap(true);
        JScrollPane descScroll = new JScrollPane(descField);

        String[] pastryTypes = {"Croissant", "Danish", "Tart", "Strudel", "Eclair", "Macaron"};
        JComboBox<String> pastryBox = new JComboBox<>(pastryTypes);

        JTextField prepTimeField = new JTextField(20);

        String[] diff = {"Easy", "Medium", "Hard"};
        JComboBox<String> diffBox = new JComboBox<>(diff);

        String[] pop = {"Low", "Medium", "High"};
        JComboBox<String> popBox = new JComboBox<>(pop);

        String[] occ = {"Breakfast", "Lunch", "Dinner", "Dessert", "Party"};
        JComboBox<String> occBox = new JComboBox<>(occ);

        JButton submit = new JButton("Add Recipe");
        submit.addActionListener(e -> {
            String name = nameField.getText();
            if (!name.isEmpty()) {
                JPanel recipePanel = new JPanel();
                recipePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                recipePanel.add(new JLabel("Recipe " + (++recipeCount) + ": " + name));
                contentPanel.add(recipePanel);
                contentPanel.revalidate();
                contentPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a recipe name.");
            }
        });

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Recipe Name:"), gbc);
        gbc.gridx = 1; panel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1; panel.add(descScroll, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Pastry Type:"), gbc);
        gbc.gridx = 1; panel.add(pastryBox, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Preparation Time (mins):"), gbc);
        gbc.gridx = 1; panel.add(prepTimeField, gbc);
        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Difficulty Level:"), gbc);
        gbc.gridx = 1; panel.add(diffBox, gbc);
        gbc.gridx = 0; gbc.gridy = 5; panel.add(new JLabel("Popularity Level:"), gbc);
        gbc.gridx = 1; panel.add(popBox, gbc);
        gbc.gridx = 0; gbc.gridy = 6; panel.add(new JLabel("Occasion:"), gbc);
        gbc.gridx = 1; panel.add(occBox, gbc);
        gbc.gridx = 1; gbc.gridy = 7; panel.add(submit, gbc);

        JOptionPane.showOptionDialog(this, panel, "Add Recipe", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Sign In")) cardLayout.show(cardPanel, "signIn");
        else if (e.getActionCommand().equals("Sign Up")) JOptionPane.showMessageDialog(this, "Sign Up not implemented.");
    }

    public static void main(String[] args) {
        new Recipe();
    }
}

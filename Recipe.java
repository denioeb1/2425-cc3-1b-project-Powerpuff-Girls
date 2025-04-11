import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RecipeApp extends JFrame {
    private ArrayList<Recipe> recipes;
    private JPanel recipePanel;
    private int recipeCount = 1;  // Counter to automatically number recipes

    // Inner class for recipe structure
    static class Recipe {
        private String name;
        private String type;
        private String preparation;
        private String difficulty;
        private String popularity;
        private String occasion;

        public Recipe(String name, String type, String preparation, String difficulty, String popularity, String occasion) {
            this.name = name;
            this.type = type;
            this.preparation = preparation;
            this.difficulty = difficulty;
            this.popularity = popularity;
            this.occasion = occasion;
        }

        public String getDetails() {
            return "<html><b>" + name + "</b><br>" +
                   "Type: " + type + "<br>" +
                   "Preparation: " + preparation + "<br>" +
                   "Difficulty: " + difficulty + "<br>" +
                   "Popularity: " + popularity + "<br>" +
                   "Occasion: " + occasion + "</html>";
        }
    }

    // Constructor
    public RecipeApp() {
        recipes = new ArrayList<>();
        recipePanel = new JPanel();
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(recipePanel);
        add(scrollPane, BorderLayout.CENTER);

        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Recipe App");
        setVisible(true);
    }

    // Method to add recipe
    public void addRecipe(String name, String type, String preparation, String difficulty, String popularity, String occasion) {
        Recipe newRecipe = new Recipe(name, type, preparation, difficulty, popularity, occasion);
        recipes.add(newRecipe);
        updateRecipeDisplay();
    }

    // Method to update the display with recipes
    public void updateRecipeDisplay() {
        recipePanel.removeAll();
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            JLabel recipeLabel = new JLabel("Recipe " + (i + 1) + ": " + recipe.getDetails());
            recipeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            recipePanel.add(recipeLabel);
        }
        recipePanel.revalidate();
        recipePanel.repaint();
    }

    // Method to handle the user input and add recipe
    public void displayInputForm() {
        String name = JOptionPane.showInputDialog("Enter Recipe Name:");

        // Pastry Type Dropdown (JComboBox)
        String[] pastryTypes = {"Cake", "Cookie", "Pie", "Pastry", "Brownie"};
        JComboBox<String> pastryTypeComboBox = new JComboBox<>(pastryTypes);
        int pastryTypeChoice = JOptionPane.showOptionDialog(this, pastryTypeComboBox, "Select Pastry Type", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        String type = pastryTypes[pastryTypeChoice];

        String preparation = getPreparationForType(type);
        String difficulty = JOptionPane.showInputDialog("Enter Difficulty Level (Easy/Medium/Hard):");
        String popularity = JOptionPane.showInputDialog("Enter Popularity (e.g. Popular, Niche, Trendy):");
        String occasion = JOptionPane.showInputDialog("Enter Occasion (e.g. Birthday, Holiday):");

        addRecipe(name, type, preparation, difficulty, popularity, occasion);
    }

    // Get cooking/preparation times based on the pastry type
    private String getPreparationForType(String type) {
        switch (type) {
            case "Cake":
                return "Bake at 350°F for 30-35 minutes.";
            case "Cookie":
                return "Bake at 375°F for 8-10 minutes.";
            case "Pie":
                return "Bake at 425°F for 40-45 minutes.";
            case "Pastry":
                return "Bake at 400°F for 20-25 minutes.";
            case "Brownie":
                return "Bake at 350°F for 25-30 minutes.";
            default:
                return "Unknown preparation time.";
        }
    }

    public static void main(String[] args) {
        RecipeApp app = new RecipeApp();

        // Simulating adding a recipe
        app.displayInputForm(); // Opens dialog for the user to input a recipe

        // Optionally, allow users to add more recipes or interact with the app here.
    }
}

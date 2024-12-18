import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class WeatherForecastingSystem extends Frame implements ActionListener {
    Label locationLabel, outputLabel;
    TextField locationInput;
    Button fetchButton, clearButton, closeButton;
    TextArea outputArea;

    public WeatherForecastingSystem() {
        // Set up the frame
        setTitle("Weather Forecasting System");
        setSize(700, 600);
        setLayout(new FlowLayout());
        setBackground(Color.darkGray);

        // Create and add components
        locationLabel = new Label("Enter Location:");
        locationLabel.setForeground(Color.white);
        add(locationLabel);

        locationInput = new TextField(20);
        locationInput.setBackground(Color.white);
        locationInput.setForeground(Color.black);
        add(locationInput);

        fetchButton = new Button("Fetch Weather");
        fetchButton.setBackground(Color.blue);
        fetchButton.setForeground(Color.white);
        fetchButton.addActionListener(this);
        add(fetchButton);

        clearButton = new Button("Clear");
        clearButton.setBackground(Color.orange);
        clearButton.setForeground(Color.white);
        clearButton.addActionListener(this);
        add(clearButton);

        closeButton = new Button("Close");
        closeButton.setBackground(Color.red);
        closeButton.setForeground(Color.white);
        closeButton.addActionListener(this);
        add(closeButton);

        outputLabel = new Label("Weather Details:");
        outputLabel.setForeground(Color.white);
        add(outputLabel);

        outputArea = new TextArea(20, 60);
        outputArea.setBackground(Color.black);
        outputArea.setForeground(Color.green);
        outputArea.setEditable(false);
        add(outputArea);

        // Add window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fetchButton) {
            String location = locationInput.getText().trim();
            if (location.isEmpty()) {
                outputArea.setText("Please enter a location.");
            } else if (!isValidLocation(location)) {
                outputArea.setText("Invalid location. Please enter a valid location.");
            } else {
                String weatherData = getWeatherData(location);
                outputArea.setText(weatherData);
            }
        } else if (e.getSource() == clearButton) {
            locationInput.setText("");
            outputArea.setText("");
        } else if (e.getSource() == closeButton) {
            dispose();
        }
    }

    // Simulated method to fetch weather data
    private String getWeatherData(String location) {
        Random random = new Random();
        String[] conditions = {"Sunny", "Cloudy", "Rainy", "Stormy", "Windy", "Snowy"};
        String[] days = {
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
            "Saturday", "Sunday", "Monday (Next Week)", "Tuesday (Next Week)", "Wednesday (Next Week)"
        };
        StringBuilder forecast = new StringBuilder();

        forecast.append("Location: ").append(location).append("\n\n");
        for (int i = 0; i < 10; i++) {
            String condition = conditions[random.nextInt(conditions.length)];
            int temperature = random.nextInt(35) + 5; // Random temperature between 5°C and 40°C
            int humidity = random.nextInt(50) + 30;  // Random humidity between 30% and 80%

            forecast.append(days[i]).append(":\n");
            forecast.append("Condition: ").append(condition).append("\n");
            forecast.append("Temperature: ").append(temperature).append("°C\n");
            forecast.append("Humidity: ").append(humidity).append("%\n\n");
        }

        return forecast.toString();
    }

    // Method to validate location input (basic validation)
    private boolean isValidLocation(String location) {
        return location.matches("^[a-zA-Z\\s]+$"); // Only allow letters and spaces
    }

    public static void main(String[] args) {
        WeatherForecastingSystem app = new WeatherForecastingSystem();
        app.setVisible(true);
    }
}
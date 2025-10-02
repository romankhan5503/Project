package clock;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DigitalClock extends JFrame implements Runnable {
    JLabel clockLabel, dateLabel, dayLabel, tempLabel;
    Thread t;

    DigitalClock() {
        // Layout manager
        setLayout(new GridLayout(4, 1));

        // Time label
        clockLabel = new JLabel();
        clockLabel.setFont(new Font("Verdana", Font.BOLD, 40));
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        clockLabel.setForeground(Color.WHITE);

        // Date label
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setForeground(Color.YELLOW);

        // Day label
        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        dayLabel.setHorizontalAlignment(JLabel.CENTER);
        dayLabel.setForeground(Color.CYAN);

        // Temperature label (simulated value)
        tempLabel = new JLabel();
        tempLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        tempLabel.setHorizontalAlignment(JLabel.CENTER);
        tempLabel.setForeground(Color.GREEN);

        // Add labels to frame
        add(clockLabel);
        add(dateLabel);
        add(dayLabel);
        add(tempLabel);

        // Frame settings
        setTitle("Digital Clock with Date, Day & Temperature");
        setSize(500, 300);
        getContentPane().setBackground(new Color(30, 30, 60)); // dark background
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Start thread
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            while (true) {
                Date d = new Date();

                // Time (hh:mm:ss AM/PM)
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
                clockLabel.setText(timeFormat.format(d));

                // Date (dd-MMM-yyyy)
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
                dateLabel.setText("Date: " + dateFormat.format(d));

                // Day of week
                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
                dayLabel.setText("Day: " + dayFormat.format(d));

                // Temperature (simulated between 20°C - 35°C)
                int temp = 20 + rand.nextInt(16);
                tempLabel.setText("Temperature: " + temp + " °C");

                Thread.sleep(1000); // update every 1 second
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DigitalClock();
    }
}

package clock;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class AnalogClock extends JPanel implements Runnable {
    Thread t = null;
    int hours = 0, minutes = 0, seconds = 0;

    public void start() {
        t = new Thread(this);
        t.start();
    }

    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000); // update every 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Enable anti-aliasing for smooth graphics
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 20;

        // Gradient background
        GradientPaint gp = new GradientPaint(0, 0, Color.CYAN, getWidth(), getHeight(), Color.LIGHT_GRAY);
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Clock border
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(4));
        g2.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        // Get current time
        Calendar cal = Calendar.getInstance();
        hours = cal.get(Calendar.HOUR);
        minutes = cal.get(Calendar.MINUTE);
        seconds = cal.get(Calendar.SECOND);

        // Date and time text
        String timeText = String.format("%02d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), minutes, seconds);
        String dateText = String.format("%1$tA, %1$td %1$tB %1$tY", cal);

        g2.setFont(new Font("Verdana", Font.BOLD, 18));
        g2.setColor(Color.BLACK);
        g2.drawString(timeText, centerX - 45, centerY + radius + 30);
        g2.drawString(dateText, centerX - 100, centerY + radius + 55);

        // ✅ Your name as background watermark
        g2.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
        g2.setColor(new Color(150, 0, 150, 50)); // Purple with transparency
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth("Roman");
        g2.drawString("Roman", centerX - textWidth / 2, centerY + 15);

        // Numbers on clock
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.setColor(Color.BLACK);
        for (int i = 1; i <= 12; i++) {
            double angle = Math.toRadians(30 * i - 90);
            int x = (int) (centerX + (radius - 30) * Math.cos(angle));
            int y = (int) (centerY + (radius - 30) * Math.sin(angle));
            g2.drawString("" + i, x - 5, y + 5);
        }

        // Tick marks
        for (int i = 0; i < 60; i++) {
            double angle = Math.toRadians(6 * i - 90);
            int inner = (i % 5 == 0) ? radius - 15 : radius - 8;
            int outer = radius - 2;
            int x1 = (int) (centerX + inner * Math.cos(angle));
            int y1 = (int) (centerY + inner * Math.sin(angle));
            int x2 = (int) (centerX + outer * Math.cos(angle));
            int y2 = (int) (centerY + outer * Math.sin(angle));
            g2.drawLine(x1, y1, x2, y2);
        }

        // Hour hand
        double hourAngle = Math.toRadians((hours % 12 + minutes / 60.0) * 30 - 90);
        int hourX = (int) (centerX + (radius - 100) * Math.cos(hourAngle));
        int hourY = (int) (centerY + (radius - 100) * Math.sin(hourAngle));
        g2.setColor(new Color(50, 50, 150));
        g2.setStroke(new BasicStroke(6));
        g2.drawLine(centerX, centerY, hourX, hourY);

        // Minute hand
        double minuteAngle = Math.toRadians((minutes + seconds / 60.0) * 6 - 90);
        int minX = (int) (centerX + (radius - 60) * Math.cos(minuteAngle));
        int minY = (int) (centerY + (radius - 60) * Math.sin(minuteAngle));
        g2.setColor(new Color(0, 128, 0));
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(centerX, centerY, minX, minY);

        // Second hand
        double secondAngle = Math.toRadians(seconds * 6 - 90);
        int secX = (int) (centerX + (radius - 40) * Math.cos(secondAngle));
        int secY = (int) (centerY + (radius - 40) * Math.sin(secondAngle));
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(centerX, centerY, secX, secY);

        // Center circle
        g2.setColor(Color.BLACK);
        g2.fillOval(centerX - 6, centerY - 6, 12, 12);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Colorful Analog Clock with Roman");
        AnalogClock clock = new AnalogClock();
        f.add(clock);
        f.setSize(500, 550);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        clock.start();
    }
}


/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.thread.simplesample;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.Serial;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class StopWatchFrame extends JFrame {

    @Serial
    private static final long serialVersionUID = -8186549854034665566L;
    private JLabel label;
    private JButton buttonPause;
    private JButton buttonResume;
    private JButton buttonStop;
    private volatile boolean running = true;
    private Thread mainThread;

    public StopWatchFrame() {
        this.initComponents();
        this.initThreads();
    }

    static void main() {
        SwingUtilities.invokeLater(() -> new StopWatchFrame().setVisible(true));
    }

    private void initThreads() {
        this.mainThread = new Thread() {

            @Override
            public void run() {
                while (!this.isInterrupted()) {
                    System.out.println("Checking running...");
                    if (StopWatchFrame.this.running) {
                        SwingUtilities.invokeLater(() -> StopWatchFrame.this.label.setText(new Date().toString()));
                    }

                    if (!this.isInterrupted()) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            this.interrupt();
                        }
                    }
                }
            }
        };
        this.mainThread.start();
    }

    private void initComponents() {
        this.label = new JLabel();
        this.buttonPause = new JButton("Pause");
        this.buttonResume = new JButton("Resume");
        this.buttonStop = new JButton("Stop");

        var panelBottom = new JPanel();
        this.buttonPause.addActionListener(e -> {
            this.running = false;
            this.buttonPause.setEnabled(this.running);
            this.buttonResume.setEnabled(!this.running);
            this.buttonStop.setEnabled(this.running);
        });
        panelBottom.add(this.buttonPause);

        this.buttonResume.addActionListener(_ -> {
            this.running = true;
            this.buttonPause.setEnabled(this.running);
            this.buttonResume.setEnabled(!this.running);
            this.buttonStop.setEnabled(this.running);
        });
        this.buttonResume.setEnabled(false);
        panelBottom.add(this.buttonResume);

        this.buttonStop.addActionListener(_ -> {
            this.mainThread.interrupt();
            this.buttonPause.setEnabled(false);
            this.buttonResume.setEnabled(false);
            this.buttonStop.setEnabled(false);
        });
        panelBottom.add(this.buttonStop);

        this.add(panelBottom, BorderLayout.SOUTH);

        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        this.label.setFont(this.label.getFont().deriveFont(32.0F));
        this.add(this.label, BorderLayout.CENTER);

        this.setTitle("Stop Watch");
        this.setSize(600, 200);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

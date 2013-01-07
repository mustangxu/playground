/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.thread.simplesample;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * @author ijay
 */
public class StopWatchFrame extends JFrame {

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

    private void initThreads() {
        this.mainThread = new Thread() {

            @Override
            public void run() {
                while (!this.isInterrupted()) {
                    if (StopWatchFrame.this.running) {
                        SwingUtilities.invokeLater(new Runnable() {

                            @Override
                            public void run() {
                                StopWatchFrame.this.label.setText(new Date()
                                    .toString());
                            }
                        });
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        this.interrupt();
                    }
                }
            }
        };
        this.mainThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new StopWatchFrame().setVisible(true);
            }
        });
    }

    private void initComponents() {
        this.label = new JLabel();
        this.buttonPause = new JButton("Pause");
        this.buttonResume = new JButton("Resume");
        this.buttonStop = new JButton("Stop");

        JPanel panelBottom = new JPanel();
        this.buttonPause.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    throw new Exception();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                StopWatchFrame.this.running = false;
                StopWatchFrame.this.buttonPause
                    .setEnabled(StopWatchFrame.this.running);
                StopWatchFrame.this.buttonResume
                    .setEnabled(!StopWatchFrame.this.running);
                StopWatchFrame.this.buttonStop
                    .setEnabled(StopWatchFrame.this.running);
            }
        });
        panelBottom.add(this.buttonPause);

        this.buttonResume.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StopWatchFrame.this.running = true;
                StopWatchFrame.this.buttonPause
                    .setEnabled(StopWatchFrame.this.running);
                StopWatchFrame.this.buttonResume
                    .setEnabled(!StopWatchFrame.this.running);
                StopWatchFrame.this.buttonStop
                    .setEnabled(StopWatchFrame.this.running);
            }
        });
        this.buttonResume.setEnabled(false);
        panelBottom.add(this.buttonResume);

        this.buttonStop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StopWatchFrame.this.mainThread.interrupt();
                StopWatchFrame.this.buttonPause.setEnabled(false);
                StopWatchFrame.this.buttonResume.setEnabled(false);
                StopWatchFrame.this.buttonStop.setEnabled(false);
            }
        });
        panelBottom.add(this.buttonStop);

        this.add(panelBottom, BorderLayout.SOUTH);

        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        this.label.setFont(this.label.getFont().deriveFont(32F));
        this.add(this.label, BorderLayout.CENTER);

        this.setTitle("Stop Watch");
        this.setSize(600, 200);
        this.setLocation(
            (Toolkit.getDefaultToolkit().getScreenSize().width - this
                .getWidth()) / 2,
            (Toolkit.getDefaultToolkit().getScreenSize().height - this
                .getHeight()) / 2);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

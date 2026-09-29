/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.thread.concurrentsample;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.Serial;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class LotteryFrame extends JFrame {
    @Serial
    private static final long serialVersionUID = -8186549854034665566L;
    private final transient Lock readLockA;
    private final transient Lock readLockB;
    private final transient Lock writeLock;
    private final Random r = new Random();
    private JLabel label;
    private JButton buttonWrite;
    private JButton buttonRead;
    private transient Thread mainThread;

    public LotteryFrame() {
        var lock = new ReentrantReadWriteLock();
        this.writeLock = lock.writeLock();
        this.readLockA = lock.readLock();
        this.readLockB = lock.readLock();

        this.initComponents();
        this.initThreads();
    }

    static void main() {
        SwingUtilities.invokeLater(() -> new LotteryFrame().setVisible(true));
    }

    private void initThreads() {
        this.mainThread = new Thread(() -> {
            while (true) {
                this.readLockA.lock();

                SwingUtilities.invokeLater(() -> LotteryFrame.this.label.setText("" + this.r.nextInt(100)));

                this.readLockA.unlock();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        this.mainThread.start();
    }

    private void initComponents() {
        this.label = new JLabel();
        this.buttonWrite = new JButton("Write");
        this.buttonRead = new JButton("Read");

        var panelBottom = new JPanel();
        this.buttonWrite.addActionListener(_ -> new Thread(() -> {
            this.writeLock.lock();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            this.writeLock.unlock();
        }).start());
        panelBottom.add(this.buttonWrite);

        this.buttonRead.addActionListener(_ -> new Thread(() -> {
            this.readLockB.lock();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            this.readLockB.unlock();
        }).start());
        panelBottom.add(this.buttonRead);

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

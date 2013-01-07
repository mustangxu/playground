/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.thread.concurrentsample;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * @author ijay
 * 
 */
public class LotteryFrame extends JFrame {
    private static final long serialVersionUID = -8186549854034665566L;

    private JLabel label;
    private JButton buttonWrite;
    private JButton buttonRead;
    private Thread mainThread;
    private Lock readLockA;
    private Lock readLockB;
    private Lock writeLock;
    private Random r = new Random();

    public LotteryFrame() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        this.writeLock = lock.writeLock();
        this.readLockA = lock.readLock();
        this.readLockB = lock.readLock();

        this.initComponents();
        this.initThreads();
    }

    private void initThreads() {
        this.mainThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    LotteryFrame.this.readLockA.lock();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            LotteryFrame.this.label.setText(""
                                + LotteryFrame.this.r.nextInt(100));
                        }
                    });

                    LotteryFrame.this.readLockA.unlock();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
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
                new LotteryFrame().setVisible(true);
            }
        });
    }

    private void initComponents() {
        this.label = new JLabel();
        this.buttonWrite = new JButton("Write");
        this.buttonRead = new JButton("Read");

        JPanel panelBottom = new JPanel();
        this.buttonWrite.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        LotteryFrame.this.writeLock.lock();

                        try {
                            sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        LotteryFrame.this.writeLock.unlock();
                    }
                }.start();
            }
        });
        panelBottom.add(this.buttonWrite);

        this.buttonRead.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        LotteryFrame.this.readLockB.lock();

                        try {
                            sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        LotteryFrame.this.readLockB.unlock();
                    }
                }.start();
            }
        });
        panelBottom.add(this.buttonRead);

        this.add(panelBottom, BorderLayout.SOUTH);

        this.label.setHorizontalAlignment(JLabel.CENTER);
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

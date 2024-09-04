package com.ngwingallah.inventory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomeWindow extends JFrame {
    public HomeWindow() {
        setTitle("ICT Canteen Inventory Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 1024);
        setLocationRelativeTo(null);

        Color primaryColor = new Color(70, 130, 180);
        Color secondaryColor = new Color(240, 248, 255);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 40);
        Font subtitleFont = new Font("Segoe UI", Font.PLAIN, 20);
        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 16);
        Icon addIcon = new ImageIcon("src/resources/icons8-plus-50.png");
        Icon searchIcon = new ImageIcon("src/resources/icons8-search-50.png");
        Icon logoImage = new ImageIcon("src/resources/logoImage.png");

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setBackground(primaryColor);
        jMenuBar.setForeground(Color.WHITE);

        JMenu homeMenu = new JMenu("Home");
        homeMenu.setToolTipText("Click to go to Home Page");
        JMenu addItemMenu = new JMenu("Add Item");
        homeMenu.setToolTipText("Click to go to Add Items Page");
        JMenu searchItemMenu = new JMenu("Search Item");
        homeMenu.setToolTipText("Click to go to Search Item Page");

        homeMenu.setForeground(Color.WHITE);
        addItemMenu.setForeground(Color.WHITE);
        searchItemMenu.setForeground(Color.WHITE);

        jMenuBar.add(homeMenu);
        jMenuBar.add(addItemMenu);
        jMenuBar.add(searchItemMenu);
        setJMenuBar(jMenuBar);

        JMenuItem addItemMenuItem = new JMenuItem("Add Item");
        addItemMenu.add(addItemMenuItem);

        JMenuItem searchItemMenuItem = new JMenuItem("Search Item");
        searchItemMenu.add(searchItemMenuItem);

        addItemMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openAddItemWindow();
            }
        });

        searchItemMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openSearchItemWindow();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(secondaryColor);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel logoLabel = new JLabel(logoImage, SwingConstants.CENTER);
        logoLabel.setPreferredSize(new Dimension(200, 200));
        logoLabel.setBorder(BorderFactory.createLineBorder(primaryColor));
        mainPanel.add(logoLabel, BorderLayout.NORTH);

        JPanel titlePanel = new JPanel(new GridLayout(2,2));
        titlePanel.setBackground(secondaryColor);
        titlePanel.setPreferredSize(new Dimension(300,300));
        titlePanel.setBorder(BorderFactory.createLineBorder(primaryColor));

        JLabel titleLabel = new JLabel("Welcome to ICTU Canteen", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(primaryColor);

        JLabel subtitleLabel = new JLabel("Inventory Management System", SwingConstants.CENTER);
        subtitleLabel.setFont(subtitleFont);
        subtitleLabel.setForeground(Color.DARK_GRAY);

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);
        mainPanel.add(titlePanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsPanel.setBackground(secondaryColor);
        buttonsPanel.setBorder(BorderFactory.createLineBorder(primaryColor));
        buttonsPanel.setPreferredSize(new Dimension(100,100));

        JButton addItemButton = new JButton("Add Item");
        addItemButton.setFont(buttonFont);
        addItemButton.setIcon(addIcon);
        addItemButton.setPreferredSize(new Dimension(200, 50));
        addItemButton.setToolTipText("Click to add a new item to the inventory");

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openAddItemWindow();
            }
        });

        JButton searchItemButton = new JButton("Search Item");
        searchItemButton.setFont(buttonFont);
        searchItemButton.setIcon(searchIcon);
        searchItemButton.setPreferredSize(new Dimension(200, 50));
        searchItemButton.setToolTipText("Click to search for an item in the inventory");

        searchItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openSearchItemWindow();
            }
        });

        buttonsPanel.add(addItemButton);
        buttonsPanel.add(searchItemButton);

        JLabel footerLabel = new JLabel("© ICT University 2024", SwingConstants.CENTER);
        footerLabel.setFont(subtitleFont);
        footerLabel.setForeground(Color.GRAY);

        JPanel southPanel = new JPanel(new BorderLayout(50, 50));
        southPanel.setBackground(secondaryColor);
        southPanel.add(buttonsPanel, BorderLayout.CENTER);
        southPanel.add(footerLabel, BorderLayout.SOUTH);
        southPanel.setPreferredSize(new Dimension(200,200));

        mainPanel.add(southPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void openSearchItemWindow() {
        SearchItemWindow searchItemWindow = new SearchItemWindow();
        searchItemWindow.setVisible(true);
    }

    private void openAddItemWindow() {
        AddItemWindow addItemWindow = new AddItemWindow();
        addItemWindow.setVisible(true);
    }
}

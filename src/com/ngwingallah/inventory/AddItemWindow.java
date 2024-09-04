package com.ngwingallah.inventory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemWindow extends JFrame {
    public AddItemWindow(){
        setTitle("ICT Canteen Inventory Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 1024);
        setLocationRelativeTo(null);

        Color primaryColor = new Color(70, 130, 180);
        Color secondaryColor = new Color(240, 248, 255);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 28);
        Font subtitleFont = new Font("Segoe UI", Font.PLAIN, 20);
        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 16);
        Icon saveIcon = new ImageIcon("src/resources/icons8-save-50.png");
        Icon cancelIcon = new ImageIcon("src/resources/icons8-cancel-50.png");
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

        JMenuItem homeMenuItem = new JMenuItem("Home Page");
        homeMenu.add(homeMenuItem);

        JMenuItem searchItemMenuItem = new JMenuItem("Search Item");
        searchItemMenu.add(searchItemMenuItem);

        homeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openHomeWindow();
            }
        });
        
        searchItemMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openSearchWindow();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(secondaryColor);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        topPanel.setBackground(secondaryColor);

        JLabel logoLabel = new JLabel(logoImage, SwingConstants.CENTER);
        logoLabel.setPreferredSize(new Dimension(100, 100));
        logoLabel.setBorder(BorderFactory.createLineBorder(primaryColor));

        JLabel titleLabel = new JLabel("Add New Item to Canteen", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);

        topPanel.add(logoLabel, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setBorder(BorderFactory.createLineBorder(primaryColor));
        middlePanel.setBackground(secondaryColor);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;


        gridBagConstraints.gridx = 0; //Item name
        gridBagConstraints.gridy = 0;
        middlePanel.add(new JLabel("Item Name:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        JTextField itemNameField = new JTextField(20);
        middlePanel.add(itemNameField, gridBagConstraints);

        gridBagConstraints.gridx = 2; //Item ID
        middlePanel.add(new JLabel("Item ID:"), gridBagConstraints);

        gridBagConstraints.gridx = 3;
        JTextField itemIdField = new JTextField(20);
        middlePanel.add(itemIdField, gridBagConstraints);

        gridBagConstraints.gridx = 0; //Item Category
        gridBagConstraints.gridy = 1;
        middlePanel.add(new JLabel("Item Category:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        JTextField itemCategoryField = new JTextField(20);
        middlePanel.add(itemCategoryField, gridBagConstraints);

        gridBagConstraints.gridx = 2; //Supplier
        middlePanel.add(new JLabel("Supplier:"), gridBagConstraints);

        gridBagConstraints.gridx = 3;
        JTextField supplierField = new JTextField(20);
        middlePanel.add(supplierField, gridBagConstraints);

        gridBagConstraints.gridx = 0; //Item quantity
        gridBagConstraints.gridy = 2;
        middlePanel.add(new JLabel("Item Quantity:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        JTextField itemQuantityField = new JTextField(20);
        middlePanel.add(itemQuantityField, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        middlePanel.add(new JLabel("Price:"), gridBagConstraints);

        gridBagConstraints.gridx = 3;
        JTextField priceField = new JTextField(20);
        middlePanel.add(priceField, gridBagConstraints);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Save");
        saveButton.setFont(buttonFont);
        saveButton.setPreferredSize(new Dimension(150, 50));
        saveButton.setIcon(saveIcon);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.setPreferredSize(new Dimension(150,50));
        cancelButton.setIcon(cancelIcon);

        buttonsPanel.setBackground(secondaryColor);

        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);

        JLabel footerLabel = new JLabel("© ICT University 2024", SwingConstants.CENTER);
        footerLabel.setFont(subtitleFont);

        JPanel southPanel = new JPanel(new BorderLayout(50, 50));
        southPanel.setBackground(secondaryColor);
        southPanel.add(buttonsPanel, BorderLayout.CENTER);
        southPanel.add(footerLabel, BorderLayout.SOUTH);
        southPanel.setPreferredSize(new Dimension(200,200));
        southPanel.setBorder(BorderFactory.createLineBorder(primaryColor));


        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void openSearchWindow() {
        SearchItemWindow searchItemWindow = new SearchItemWindow();
        searchItemWindow.setVisible(true);
    }

    private void openHomeWindow() {
        HomeWindow homeWindow = new HomeWindow();
        homeWindow.setVisible(true);
    }
}

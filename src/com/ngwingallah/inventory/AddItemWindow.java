package com.ngwingallah.inventory;

import java.sql.Connection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddItemWindow extends JFrame {
    private final JTextField itemIdField = new JTextField(20);
    private final JTextField itemNameField = new JTextField(20);
    private final JTextField itemCategoryField = new JTextField(20);
    private final JTextField itemSupplierField = new JTextField(20);
    private final JTextField itemQuantityField = new JTextField(20);
    private final JTextField priceField = new JTextField(20);
    private final JButton saveButton = new JButton("Save");
    private final JButton cancelButton = new JButton("Cancel");

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
        middlePanel.add(itemNameField, gridBagConstraints);

        gridBagConstraints.gridx = 2; //Item ID
        middlePanel.add(new JLabel("Item ID:"), gridBagConstraints);

        gridBagConstraints.gridx = 3;
        middlePanel.add(itemIdField, gridBagConstraints);

        gridBagConstraints.gridx = 0; //Item Category
        gridBagConstraints.gridy = 1;
        middlePanel.add(new JLabel("Item Category:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        middlePanel.add(itemCategoryField, gridBagConstraints);

        gridBagConstraints.gridx = 2; //Supplier
        middlePanel.add(new JLabel("Supplier:"), gridBagConstraints);

        gridBagConstraints.gridx = 3;
        middlePanel.add(itemSupplierField, gridBagConstraints);

        gridBagConstraints.gridx = 0; //Item quantity
        gridBagConstraints.gridy = 2;
        middlePanel.add(new JLabel("Item Quantity:"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        middlePanel.add(itemQuantityField, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        middlePanel.add(new JLabel("Price:"), gridBagConstraints);

        gridBagConstraints.gridx = 3;
        middlePanel.add(priceField, gridBagConstraints);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Save");
        saveButton.setFont(buttonFont);
        saveButton.setPreferredSize(new Dimension(150, 50));
        saveButton.setIcon(saveIcon);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addItem();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.setPreferredSize(new Dimension(150,50));
        cancelButton.setIcon(cancelIcon);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cancel();
            }
        });

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

    private void cancel() {
        itemIdField.setText("");
        itemNameField.setText("");
        itemCategoryField.setText("");
        itemSupplierField.setText("");
        itemQuantityField.setText("");
        priceField.setText("");
    }

    private void addItem() {
        String itemId = itemIdField.getText().trim();
        String name = itemNameField.getText().trim();
        String category = itemCategoryField.getText().trim();
        String quantityStr = itemQuantityField.getText().trim();
        String supplier = itemSupplierField.getText().trim();
        String priceStr = priceField.getText().trim();


        if (itemId.isEmpty() || name.isEmpty() || category.isEmpty() || quantityStr.isEmpty() || supplier.isEmpty() || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int quantity;
        double price;
        try {
            quantity = Integer.parseInt(quantityStr);
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantity must be an integer and price must be a number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Database insertion
        String sql = "INSERT INTO Items(ID, Name, Category, Supplier, Quantity, Price) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, itemId);
            pstmt.setString(2, name);
            pstmt.setString(3, category);
            pstmt.setString(4, supplier);
            pstmt.setInt(5, quantity);
            pstmt.setDouble(6, price);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Item added successfully!");

            // Clear fields after successful addition
            itemIdField.setText("");
            itemNameField.setText("");
            itemCategoryField.setText("");
            itemSupplierField.setText("");
            itemQuantityField.setText("");
            priceField.setText("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saving item: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
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

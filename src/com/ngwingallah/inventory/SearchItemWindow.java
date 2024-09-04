package com.ngwingallah.inventory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchItemWindow extends JFrame {
    public SearchItemWindow(){
        setTitle("Search Items");
        setSize(1024, 1024);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color primaryColor = new Color(70, 130, 180);
        Color secondaryColor = new Color(240, 248, 255);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 28);
        Font subtitleFont = new Font("Segoe UI", Font.PLAIN, 20);
        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 18);
        Icon searchIcon = new ImageIcon("src/resources/icons8-search-30.png");
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

        JMenuItem addItemMenuItem = new JMenuItem("Add Item");
        addItemMenu.add(addItemMenuItem);

        homeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openHomeWindow();
            }
        });

        addItemMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openAddItemWindow();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(secondaryColor);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(secondaryColor);

        JLabel logoLabel = new JLabel(logoImage, SwingConstants.CENTER);
        logoLabel.setPreferredSize(new Dimension(100, 100));
        logoLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel titleLabel = new JLabel("Search Items", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);

        topPanel.add(logoLabel, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Use FlowLayout instead of BorderLayout
        searchPanel.setBorder(BorderFactory.createLineBorder(primaryColor));
        searchPanel.setBackground(secondaryColor);

        JPanel textFieldPanel = new JPanel(null); // Use null layout for absolute positioning
        textFieldPanel.setPreferredSize(new Dimension(400, 40));
        textFieldPanel.setBackground(secondaryColor);

        JTextField searchField = new JTextField("e.g. Maggi");
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        searchField.setBounds(40, 0, 360, 40); // Leave space for icon on the left

        // Create a JLabel for the search icon
        JLabel searchIconLabel = new JLabel();
        searchIconLabel.setIcon(searchIcon);
        searchIconLabel.setBounds(5, 5, 40, 40);

        // Add components to textFieldPanel
        textFieldPanel.add(searchField);
        textFieldPanel.add(searchIconLabel);

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 40));

        searchPanel.add(textFieldPanel);
        searchPanel.add(searchButton);

        String[] columnNames = {"#", "Name", "Item ID", "Category", "Quantity", "Supplier"};
        Object[][] data = {
                {"1", "Maggi (packet)", "CX2024", "essentials", "2", "Dovv Messasi"}
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable resultsTable = new JTable(tableModel);
        resultsTable.setRowHeight(30);
        resultsTable.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel footerLabel = new JLabel("© ICT University 2024", SwingConstants.CENTER);
        footerLabel.setFont(subtitleFont);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(searchPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void openAddItemWindow() {
        AddItemWindow addItemWindow =new AddItemWindow();
        addItemWindow.setVisible(true);
    }

    private void openHomeWindow() {
        HomeWindow homeWindow = new HomeWindow();
        homeWindow.setVisible(true);
    }
}

package com.ngwingallah.inventory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchItemWindow extends JFrame {
    private final JTextField searchTextField = new JTextField(20);
    private final DefaultTableModel tableModel;

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

        JPanel topAndSearchPanel = new JPanel();
        topAndSearchPanel.setLayout(new BoxLayout(topAndSearchPanel, BoxLayout.Y_AXIS));
        topAndSearchPanel.setBackground(secondaryColor);

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

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBorder(BorderFactory.createLineBorder(primaryColor));
        searchPanel.setBackground(secondaryColor);

        JPanel textFieldPanel = new JPanel(null);
        textFieldPanel.setPreferredSize(new Dimension(400, 40));
        textFieldPanel.setBackground(secondaryColor);

        searchTextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        searchTextField.setBounds(40, 0, 360, 40);
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                searchItem();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                searchItem();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                searchItem();
            }
        });

        JLabel searchIconLabel = new JLabel();
        searchIconLabel.setIcon(searchIcon);
        searchIconLabel.setBounds(5, 5, 40, 40);

        textFieldPanel.add(searchTextField);
        textFieldPanel.add(searchIconLabel);

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 40));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchItem();
            }
        });

        searchPanel.add(textFieldPanel);
        searchPanel.add(searchButton);
        topAndSearchPanel.add(topPanel);
        topAndSearchPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
        topAndSearchPanel.add(searchPanel);

        String[] columnNames = {"#", "Name", "Item ID", "Category", "Quantity", "Supplier"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable resultsTable = new JTable(tableModel);
        resultsTable.setRowHeight(30);
        resultsTable.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        JLabel footerLabel = new JLabel("© ICT University 2024", SwingConstants.CENTER);
        footerLabel.setFont(subtitleFont);

        mainPanel.add(topAndSearchPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void searchItem() {
        String searchText = searchTextField.getText().trim();

        if (searchText.isEmpty()) {
            tableModel.setRowCount(0);
            return;
        }

        String sql = "SELECT ID, Name, Category, Quantity, Supplier FROM Items WHERE Name LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, searchText + "%");
            ResultSet rs = pstmt.executeQuery();

            tableModel.setRowCount(0); // Clear existing rows
            int index = 1;

            while (rs.next()) {
                Object[] row = {
                        index++,
                        rs.getString("Name"),
                        rs.getString("ID"),
                        rs.getString("Category"),
                        rs.getInt("Quantity"),
                        rs.getString("Supplier")
                };
                tableModel.addRow(row);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error searching for items: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fulltictactoegame;

/**
 *
 * @author MLabayen2026
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FullTicTacToeGame implements ActionListener { //detects every button pressed
    // Main components for managing views and game state
    private JFrame window = new JFrame("Tic-Tac-Toe Game"); 
    private CardLayout cardLayout = new CardLayout(); //allows for the switching between the menu panel and the game panel
    private JPanel mainPanel; // Container for menu and game views
    private JPanel gamePanel; // the game panel that will have the status bar and such
    
    // Game-specific components
    private JButton[][] buttons = new JButton[3][3]; // 3 x 3 gird array of buttons
    private JLabel statusBar; //this will tell the player what is happening and who's turn it is
    private String currentPlayer = "X"; //initially the it is play X's turn
    private int movesCount = 0; // establish which move it is, and if there are 9 moves the game will know it is a draw since there was no win

    public FullTicTacToeGame() {
        window.setSize(400, 450); // Slightly taller for status bar
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(cardLayout);

        // 1. Create the menu panel
        JPanel startPanel = createStartPanel();
        mainPanel.add(startPanel, "Menu");

        // 2. Create the fully functional game panel
        gamePanel = createGamePanel();
        mainPanel.add(gamePanel, "Game");

        window.add(mainPanel);
        window.setVisible(true);
    }

    // --- Panel Creation Methods ---

    private JPanel createStartPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Centers items
        JButton startButton = new JButton("Local Game");
        JButton exitButton = new JButton("Exit");
        JButton aiButton = new JButton("AI Game");

        startButton.setActionCommand("StartGame");
        exitButton.setActionCommand("ExitGame");
        aiButton.setActionCommand("AIGame");
        
        startButton.addActionListener(this);
        aiButton.addActionListener(this);
        exitButton.addActionListener(this);

        panel.add(startButton);
        panel.add(aiButton);
         panel.add(exitButton);
        
        return panel;
    }

    private JPanel createGamePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        
        statusBar = new JLabel("Player X's turn", SwingConstants.CENTER);
        statusBar.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(statusBar, BorderLayout.SOUTH);
        panel.add(boardPanel, BorderLayout.CENTER);

        // Initialize the 3x3 buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].addActionListener(this); // Game logic listens to these
                boardPanel.add(buttons[i][j]);
            }
        }
        return panel;
    }

    // --- Event Handling and Game Logic ---

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("StartGame".equals(command)) {
            cardLayout.show(mainPanel, "Game");
            resetGame(); // Reset state for a new game
        } else if ("ExitGame".equals(command)) {
            System.exit(0);
        } else {
            // Assume the action came from one of the game board buttons
            JButton clickedButton = (JButton) e.getSource();
            handleGameMove(clickedButton);
        }
    }
    private void showMainMenuWithDelay() {
    // Use a Swing Timer to wait a few seconds before switching views
    Timer timer = new Timer(3000, new ActionListener() { // Wait 3000ms (3 seconds)
        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(mainPanel, "Menu");
            // Stop the timer after it runs once
            ((Timer)e.getSource()).stop(); 
        }
    });
    timer.setRepeats(false); // Only run once
    timer.start();
}
    private void handleGameMove(JButton clickedButton) {
    if (clickedButton.getText().equals("")) {
        clickedButton.setText(currentPlayer);
        clickedButton.setEnabled(false);
        movesCount++;

        boolean gameOver = false;

        if (checkForWin()) {
            statusBar.setText("Player " + currentPlayer + " wins! Game Over.");
            disableAllButtons();
            gameOver = true;
        } else if (movesCount == 9) {
            statusBar.setText("It's a draw! Game Over.");
            gameOver = true;
        } else {
            switchPlayer();
        }

        if (gameOver) {
            // *** ADDED: Call this method when game is over ***
            showMainMenuWithDelay(); 
        }
    }
}

    private void switchPlayer() {
        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
        statusBar.setText("Player " + currentPlayer + "'s turn");
    }

    private boolean checkForWin() {
        // This logic is simplified using direct button access for clarity
        String c;

        // Check Rows
        for (int i = 0; i < 3; i++) {
            c = buttons[i][0].getText();
            if (!c.equals("") && c.equals(buttons[i][1].getText()) && c.equals(buttons[i][2].getText())) {
                return true;
            }
        }
        // Check Columns
        for (int j = 0; j < 3; j++) {
            c = buttons[0][j].getText();
            if (!c.equals("") && c.equals(buttons[1][j].getText()) && c.equals(buttons[2][j].getText())) {
                return true;
            }
        }
        // Check Diagonals
        c = buttons[0][0].getText();
        if (!c.equals("") && c.equals(buttons[1][1].getText()) && c.equals(buttons[2][2].getText())) {
            return true;
        }
        c = buttons[0][2].getText();
        if (!c.equals("") && c.equals(buttons[1][1].getText()) && c.equals(buttons[2][0].getText())) {
            return true;
        }

        return false;
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    
    private void resetGame() {
        movesCount = 0;
        currentPlayer = "X";
        statusBar.setText("Player X's turn");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true); // Re-enable buttons for a new game
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FullTicTacToeGame());
    }
}

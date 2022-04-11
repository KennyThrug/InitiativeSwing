package SwingStuff;
import data.GamePiece;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Window {
    private int width; int height;
    JFrame frame;
    GridBagConstraints gridBagConstraints;
    private String name;
    public ArrayList<GamePiece> characters;
    ArrayList<JButton> buttons;
    ArrayList<JLabel> labels;
    ArrayList<JTextArea> textAreas;
    int curIndex = 0;

    public Window(int Width, int Height, String name){
        width = Width;
        height = Height;
        this.name = name;
        createFrame();
        makeControlButtons();
        characters = new ArrayList<GamePiece>();
        buttons = new ArrayList<JButton>();
        labels = new ArrayList<JLabel>();
        textAreas = new ArrayList<JTextArea>();

        frame.setVisible(true);
    }

    private void saveData(){
        for(int i = 0; i < characters.size(); i++){
            characters.get(i).setData(textAreas.get(i).getText());
        }
    }

    /**
     * Creates the Frame
     */
    private void createFrame(){
        //Make the Frame
        frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        frame.setSize(width,height);
    }

    /**
     * Makes the Three top buttons that you can use at any time
     */
    private void makeControlButtons(){
        JButton addKennyPlayers = new JButton();
        addKennyPlayers.setSize(50,50);
        addKennyPlayers.setText("Kenny's Players");
        addKennyPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addKennyPlayers();
            }
        });
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        frame.add(addKennyPlayers);

        JButton clear = new JButton();
        clear.setSize(50,50);
        clear.setText("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;

        frame.add(clear);

        JButton addSkylarPlayers = new JButton();
        addSkylarPlayers.setSize(50,50);
        addSkylarPlayers.setText("Skylar's Players");
        addSkylarPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSkylarPlayers();
            }
        });
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;

        frame.add(addSkylarPlayers);

        JButton addChar = new JButton();
        addChar.setSize(50,50);
        addChar.setText("Add Character");
        addChar.addActionListener(new addChar(-1));

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;

        frame.add(addChar,gridBagConstraints);

        JButton nextTurn = new JButton();
        nextTurn.setSize(50,50);
        nextTurn.setText("Next Turn");
        nextTurn.addActionListener(new moveList(true));

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;

        frame.add(nextTurn,gridBagConstraints);


        JButton prevTurn = new JButton();
        prevTurn.setSize(50,50);
        prevTurn.setText("Previous Turn");
        prevTurn.addActionListener(new moveList(false));

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;

        frame.add(prevTurn,gridBagConstraints);
    }

    private boolean playersAdded = false;

    private void addKennyPlayers(){
        if(playersAdded == false) {
            characters.add(new GamePiece("Zim",0));
            characters.add(new GamePiece("Aendul",0));
            characters.add(new GamePiece("Quirk",0));
            playersAdded = true;
        }
        updateOrder();
        frame.setVisible(false);
        frame.setVisible(true);
    }
    private void addSkylarPlayers(){
        if(playersAdded == false) {
            characters.add(new GamePiece("Hunts",0));
            characters.add(new GamePiece("Amy",0));
            characters.add(new GamePiece("Raehann",0));
            characters.add(new GamePiece("7",0));
            characters.add(new GamePiece("Pho",0));
            playersAdded = true;
        }
        updateOrder();
        frame.setVisible(false);
        frame.setVisible(true);
    }
    private void clear(){
        characters = new ArrayList<GamePiece>();
        buttons = new ArrayList<JButton>();
        labels = new ArrayList<JLabel>();
        textAreas = new ArrayList<JTextArea>();
        frame.getContentPane().removeAll();
        makeControlButtons();
        updateOrder();
        frame.setVisible(false); frame.setVisible(true);
        playersAdded = false;
        curIndex = 0;
    }


    /**
     * Makes the Buttons that signify each game piece and their initiative
     */
    private void makeInitiativeButtons(){
        for(int i = 0; i < characters.size(); i++){
            if(i >= buttons.size()) {
                //Initiative Label
                    JLabel tempInit = new JLabel();
                    tempInit.setText(characters.get(i).getInitiative() + "");

                    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
                    gridBagConstraints.gridx = 0;
                    gridBagConstraints.gridy = i + 2;
                    labels.add(tempInit);
                    frame.add(tempInit, gridBagConstraints);

                //Button (Name)
                    JButton piece = new JButton();
                    piece.setSize(100, 50);
                    piece.setText(characters.get(i).getName());
                    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
                    gridBagConstraints.gridx = 1;
                    gridBagConstraints.gridy = i + 2;
                    piece.addActionListener(new addChar(i));
                    buttons.add(piece);
                    frame.add(piece, gridBagConstraints);

                //Text Area (HP)
                    JTextArea hp = new JTextArea();
                    tempInit.setText(characters.get(i).getInitiative() + "");

                    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
                    gridBagConstraints.gridx = 2;
                    gridBagConstraints.gridy = i + 2;
                    textAreas.add(hp);
                    frame.add(hp, gridBagConstraints);
            }
            else{
                labels.get(i).setText(characters.get(i).getInitiative() + "");
                buttons.get(i).setText(characters.get(i).getName());
                //Figure Out Text Areas
                textAreas.get(i).setText(characters.get(i).getData());
            }
            if(curIndex == i){
                buttons.get(i).setBackground(Color.CYAN);
            }
            else{
                buttons.get(i).setBackground(Color.WHITE);
            }
        }
    }

    /**
     * Updates the order in the ArrayList
     */
    private void updateOrder(){
        Collections.sort(characters);
        updateVisuals();
    }

    /**
     * Updates the visuals whenever you add a character
     */
    private void updateVisuals(){
        makeInitiativeButtons();
        frame.repaint();
    }


    private class addChar implements ActionListener{
        JFrame innerFrame;
        GridBagConstraints innerGrid;
        JFormattedTextField spinner;
        JTextField field;
        JButton acceptbutton;
        int num;

        /**
         *
         * @param num num that will be edited (-1 if adding)
         */
        private addChar(int num){
            this.num = num;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveData();
            frame.setVisible(false);
            makeInnerFrame();
            createInnerButtons();
            setValues();
            innerFrame.setVisible(true);
        }

        private void setValues(){
            if(num != -1){
                field.setText(characters.get(num).getName());
                spinner.setValue(characters.get(num).getInitiative());
            }
        }

        public void acceptCharacter() {
            String TempName = field.getText();
            int TempInit = 0;
            try{
                TempInit = Integer.parseInt(spinner.getText());
            }
            catch (NumberFormatException e){
                TempInit = 0;
            }
            if(TempInit != -1) {
                if(num == -1) {
                    characters.add(new GamePiece(TempName, TempInit));
                }
                else {
                    characters.set(num,new GamePiece(TempName,TempInit));
                }
            }
            updateOrder();
            updateVisuals();
            frame.setVisible(true);
            innerFrame.dispose();
        }

        private void makeInnerFrame(){
            innerFrame = new JFrame();
            innerFrame.setLayout(new GridBagLayout());
            innerGrid = new GridBagConstraints();
            innerGrid.fill = GridBagConstraints.HORIZONTAL;
            innerFrame.setSize(200,350);
        }
        private void createInnerButtons(){
            JTextField name = new JTextField();
            name.setSize(100,50);
            innerGrid.fill = GridBagConstraints.HORIZONTAL;
            innerGrid.gridx = 0;
            innerGrid.gridy = 0;
            this.field = name;
            innerFrame.add(name,innerGrid);

            name.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    System.out.println(e.getKeyCode());
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        acceptCharacter();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    System.out.println(e.getKeyCode());
                }
            });


            JFormattedTextField init = new JFormattedTextField();
            init.setSize(100,50);
            innerGrid.fill = GridBagConstraints.HORIZONTAL;
            innerGrid.gridx = 0;
            innerGrid.gridy = 1;
            this.spinner = init;
            init.setFormatterFactory(
                    new JFormattedTextField.AbstractFormatterFactory() {
                        private NumberFormatter formatter = null;
                        public JFormattedTextField.AbstractFormatter
                        getFormatter(JFormattedTextField jft) {
                            if (formatter == null) {
                                formatter = new NumberFormatter(new DecimalFormat("#00"));
                                formatter.setValueClass(Integer.class);
                            }
                            return formatter;
                        }
                    });
            innerFrame.add(init,innerGrid);
            init.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }

                @Override
                public void focusLost(FocusEvent e) {

                }
            });
            init.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        acceptCharacter();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    System.out.println(e.getKeyCode());
                }
            });


            JButton button = new JButton();
            button.setSize(100,50);
            button.setText("Confirm");
            innerGrid.fill = GridBagConstraints.HORIZONTAL;
            button.addActionListener(new acceptCharacter(name,init));
            innerGrid.gridx = 0;
            innerGrid.gridy = 2;
            innerFrame.add(button,innerGrid);
            acceptbutton = button;
            button.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        acceptCharacter();
                    }
                    if(e.getKeyCode() == KeyEvent.VK_TAB){
                        spinner.requestFocus();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    System.out.println(e.getKeyCode());
                }
            });

            JButton delete = new JButton();
            delete.setSize(100,50);
            delete.setText("Delete");
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<GamePiece> temp = new ArrayList<GamePiece>();
                    for(int i = 0; i < characters.size(); i++){
                        if(i != num){
                            temp.add(characters.get(i));
                        }
                    }
                    clear();
                    characters = temp;
                    updateOrder();
                    frame.setVisible(true);
                    innerFrame.dispose();
                }
            });

            innerGrid.fill = GridBagConstraints.HORIZONTAL;
            innerGrid.gridx = 0;
            innerGrid.gridy = 3;
            innerFrame.add(delete);

            if (num != -1){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        spinner.grabFocus();
                    }
                });
            }
        }

        private class acceptCharacter implements ActionListener{

            JTextField textBox;
            JFormattedTextField spinner;
            public acceptCharacter(JTextField textBox, JFormattedTextField spinner){
                this.textBox = textBox;
                this.spinner = spinner;
            }


            @Override
            public void actionPerformed(ActionEvent e) {
                acceptCharacter();
            }
        }
    }

    private class moveList implements ActionListener{

        private boolean up;

        /**
         *
         * @param up true if moves up, false if moves down
         */
        private moveList(boolean up){
            this.up = up;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            if(up){
                if(curIndex == characters.size()-1){
                    curIndex = 0;
                }
                else{
                    curIndex++;
                }
            }
            else {
                if(curIndex == 0){
                    curIndex = characters.size()-1;
                }
                else {
                    curIndex--;
                }
            }
            saveData();
            updateVisuals();
        }
    }

}

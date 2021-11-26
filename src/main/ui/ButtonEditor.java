package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
    Credit: http://www.java2s.com/Code/Java/Swing-Components/ButtonTableExample.htm,
    however, I modified it a little by creating custom ActionListener

    ButtonEditor Handles the interaction with the button in the ChoiceList Selection Panel.
    I also got this from the link above, but the way it works is by monitoring whether the button is pushed (uses
    checkbox)
 */

class ButtonEditor extends DefaultCellEditor implements ActionListener {

    protected JButton button;
    private String label;
    private boolean isPushed;

    /*
        Constructs an invisible checkbox in the table cell.
     */
    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(this);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        button.setFont(new Font("Dialog", Font.BOLD, 12));
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            JOptionPane.showMessageDialog(button, label + ": Ouch!");
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return true;
    }

    /*
        EFFECTS: Since the numbers between the square brackets are in increasing order in the table,
                this function determines which routine was clicked, and calls WorkoutAppUI to display
                an exerciseTable Panel for the correct routine (takes into account favoriteView
     */
    private void processRoutineChoice(String buttonLabel) {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < buttonLabel.length(); i++) {
            if (buttonLabel.charAt(i) == '[') {
                startIndex = i;
            } else if (buttonLabel.charAt(i) == ']') {
                endIndex = i;
            }
        }
        int routinePos = Integer.parseInt(buttonLabel.substring(startIndex + 1, endIndex)) - 1;
        WorkoutAppUI.displayIncludedExercises(routinePos, RoutinePanel.isFavoriteView());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String buttonLabel = e.getActionCommand();
            processRoutineChoice(buttonLabel);
        }
    }
}

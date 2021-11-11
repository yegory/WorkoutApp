/*
Credit:
http://www.java2s.com/Code/Java/Swing-Components/ButtonTableExample.htm
 */

package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonLabel = e.getActionCommand();
                int startIndex = 0;
                int endIndex = 0;
                for (int i = 0; i < buttonLabel.length(); i++) {
                    if (buttonLabel.charAt(i) == '[') {
                        startIndex = i;
                    } else if (buttonLabel.charAt(i) == ']') {
                        endIndex = i;
                    }
                }
                WorkoutAppUI.displayIncludedExercises(
                        Integer.parseInt(buttonLabel.substring(startIndex + 1, endIndex)) - 1);
            }
        });
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
        return super.stopCellEditing();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.develnottie.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * @author adam (adam@devel-nottie.com)
 * @version 1.0
 */
public class JAutoCompleteComboBox extends JComboBox {

    private static final String uiClassID = "ComboBoxUI";
    private ArrayList<Object> comboList = new ArrayList<Object>();
    private ArrayList<Object> keys = new ArrayList<Object>();
    private int prevKeyCode = 0;
    private int prevKeyModifier = 0;
    private boolean showArrow = true;
    private Dimension minimumSize = new Dimension(53, 22);
    private Dimension preferredSize = new Dimension(57, 22);
    private Dimension maximumSize = new Dimension(32767, 32767);
    private ArrayList<Object> tempKeys = new ArrayList<Object>();
    private ComboBoxUI comboBoxUi = null;

    /**
     * Creates a <code>JAutoCompleteComboBox</code> 
     */
    public JAutoCompleteComboBox() {
        super();
        init();
    }

    /**
     * Creates a <code>JAutoCompleteComboBox</code> that takes its items from an
     * existing <code>ComboBoxModel</code>. 
     *
     * @param ComboBoxModel model the <code>ComboBoxModel</code> that provides the displayed list of items
     */
    public JAutoCompleteComboBox(ComboBoxModel model) {
        super.setModel(model);
        populate();
        init();
    }

    /**
     * Creates a <code>JAutoCompleteComboBox</code> that takes its items from an
     * array 
     *
     * @param Object[] items 
     */
    public JAutoCompleteComboBox(Object[] items) {
        super(items);
        populate();
        init();
    }

    @Override
    public void setModel(ComboBoxModel model) {
        super.setModel(model);
        populate();
    }

    @Override
    public void addItem(Object item) {
        super.addItem(item);
    }

    @Override
    public void removeAllItems() {
        super.removeAllItems();
    }

    @Override
    public void removeItem(Object item) {
        super.removeItem(item);
    }

    @Override
    public void removeItemAt(int index) {
        super.removeItemAt(index);
    }

    @Override
    public void setSelectedItem(Object item) {
        super.setSelectedItem(item);
        setText(item.toString());
    }

    @Override
    public String getUIClassID() {
        return uiClassID;
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if (getEditor() != null) {
            if (getEditor().getEditorComponent() != null) {
                getEditor().getEditorComponent().setBackground(bg);
            }
        }
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        if (getEditor() != null) {
            if (getEditor().getEditorComponent() != null) {
                getEditor().getEditorComponent().setPreferredSize(preferredSize);
            }
        }
    }

    @Override
    public void setMinimumSize(Dimension minimumSize) {
        super.setMinimumSize(minimumSize);
        if (getEditor() != null) {
            if (getEditor().getEditorComponent() != null) {
                getEditor().getEditorComponent().setMinimumSize(minimumSize);
            }
        }
    }

    @Override
    public void setMaximumSize(Dimension maximumSize) {
        super.setMaximumSize(maximumSize);
        if (getEditor() != null) {
            if (getEditor().getEditorComponent() != null) {
                getEditor().getEditorComponent().setMaximumSize(maximumSize);
            }
        }
    }

    @Override
    public void setSize(Dimension size) {
        super.setSize(size);
        if (getEditor() != null) {
            if (getEditor().getEditorComponent() != null) {
                getEditor().getEditorComponent().setSize(size);
            }
        }
        super.repaint();
    }

    /** 
     * Adds an item in (key,value) pair to the item list.
     *
     * @param Object key 
     * @param Object value 
     */
    public void addItem(Object key, Object value) {
        super.addItem(value);
        keys.add(key);
    }

    /** 
     * Set keys for <code>JAutoCompleteComboBox</code> items from <code>ArrayList</code>
     *
     * @param ArrayList keys
     */
    public void setKeys(ArrayList<Object> keys) {
        this.keys = keys;
    }

    private void init() {
        setEditor(new AutoCompleteEditor(this));
        setEditable(true);
        comboBoxUi = getUI();
    }

    /** 
     * Remove all items from <code>JAutoCompleteComboBox</code>
     */
    private void clearItems() {
        super.removeAllItems();
    }

    private void populate() {
        if (comboList != null) {
            comboList.clear();
        }
        for (int i = 0; i < this.getItemCount(); i++) {
            comboList.add(this.getItemAt(i));
            tempKeys.add(keys.get(i));
        }
    }

    private void addTempItem(Object item) {
        super.addItem(item);
    }

    /** 
     * Populate <code>JAutoCompleteComboBox</code>
     */
    public void doPopulate() {
        this.populate();
    }

    /** 
     * Get selected key
     *
     * @return Object
     */
    public Object getSelectedKey() {
        try {
            return tempKeys.get(this.getSelectedIndex());
        } catch (java.lang.IndexOutOfBoundsException e) {
            return "-1";
        }
    }

    /** 
     * Get Text from selected item
     *
     * @return String
     */
    public String getText() {
        JTextField textField = (JTextField) this.getEditor().getEditorComponent();
        return textField.getText();
    }

    /** 
     * Set Text to <code>JAutoCompleteComboBox</code> editor
     *
     * @param String s
     */
    public void setText(String s) {
        JTextField textField = (JTextField) this.getEditor().getEditorComponent();
        textField.setText(s);
    }

    /** 
     * Get <code>JAutoCompleteComboBox</code> arrow status 
     *
     * @return boolean
     */
    public boolean isShowArrow() {
        return showArrow;
    }

    /** 
     * Toggle <code>JAutoCompleteComboBox</code> arrow
     *
     * @param boolean b
     */
    public void setShowArrow(boolean b) {
        this.showArrow = b;
        if (!b) {
            setUI(new BasicComboBoxUI() {

                @Override
                protected JButton createArrowButton() {
                    JButton btn = new JButton() {

                        @Override
                        public int getWidth() {
                            return 0;
                        }

                        @Override
                        public int getHeight() {
                            return 0;
                        }

                        @Override
                        public boolean isVisible() {
                            return false;
                        }

                        @Override
                        public boolean isRequestFocusEnabled() {
                            return false;
                        }
                    };
                    return btn;
                }
            });
        } else {
            setUI(comboBoxUi);
        }
    }

    private class AutoCompleteEditor extends BasicComboBoxEditor {

        public AutoCompleteEditor(final JAutoCompleteComboBox combo) {

            KeyAdapter listener = new KeyAdapter() {

                @Override
                public void keyReleased(java.awt.event.KeyEvent e) {
                    if ((prevKeyCode != KeyEvent.VK_TAB && prevKeyCode != KeyEvent.VK_ENTER
                            && prevKeyModifier != KeyEvent.CTRL_DOWN_MASK
                            && prevKeyModifier != KeyEvent.SHIFT_DOWN_MASK
                            && prevKeyModifier != KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK
                            && prevKeyCode != KeyEvent.VK_UP && prevKeyCode != KeyEvent.VK_DOWN
                            && prevKeyCode != KeyEvent.VK_PAGE_DOWN && prevKeyCode != KeyEvent.VK_PAGE_UP
                            && prevKeyCode != KeyEvent.VK_HOME && prevKeyCode != KeyEvent.VK_END)
                            || (prevKeyModifier == KeyEvent.CTRL_DOWN_MASK && prevKeyCode == KeyEvent.VK_X)
                            || (prevKeyModifier == KeyEvent.CTRL_DOWN_MASK && prevKeyCode == KeyEvent.VK_V)) {

                        int pos = editor.getCaretPosition();
                        if (editor.getSelectedText() != null) {
                            int startPos = editor.getSelectionStart();
                            int lastPos = editor.getSelectionEnd();
                            String temp1 = editor.getText();

                            String temp2 = temp1.substring(0, startPos);
                            temp2 += temp1.substring(lastPos, temp1.length());

                            editor.setText(temp2);
                            pos = startPos;
                        }

                        String searchPattern = editor.getText();

                        combo.hidePopup();
                        combo.clearItems();
                        combo.addTempItem(" ");

                        tempKeys.clear();
                        tempKeys.add("-1");
                        if (searchPattern != null && !searchPattern.trim().isEmpty()) {
                            for (int i = 0; i < comboList.size(); i++) {
                                if (comboList.get(i).toString().toLowerCase().contains(searchPattern.toLowerCase())) {
                                    combo.addTempItem(comboList.get(i));
                                    tempKeys.add(keys.get(i));
                                }
                            }
                        } else {
                            for (int i = 0; i < comboList.size(); i++) {
                                if (!comboList.get(i).toString().trim().isEmpty()) {
                                    combo.addTempItem(comboList.get(i));
                                    tempKeys.add(keys.get(i));
                                }
                            }
                        }
                        combo.showPopup();
                        editor.setText(searchPattern);
                        try {
                            editor.setCaretPosition(pos);
                        } catch (Exception ex) {
                            editor.setCaretPosition(searchPattern.length());
                        }
                    } else if (prevKeyModifier != KeyEvent.CTRL_DOWN_MASK
                            && prevKeyCode != KeyEvent.VK_PAGE_DOWN && prevKeyCode != KeyEvent.VK_PAGE_UP
                            && prevKeyCode != KeyEvent.VK_HOME && prevKeyCode != KeyEvent.VK_END
                            && prevKeyCode != KeyEvent.VK_UP && prevKeyCode != KeyEvent.VK_DOWN) {
                        combo.hidePopup();
                    }
                }

                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    prevKeyCode = evt.getKeyCode();
                    prevKeyModifier = evt.getModifiersEx();
                    if (evt.getKeyCode() == KeyEvent.VK_TAB && evt.getModifiersEx() == KeyEvent.SHIFT_DOWN_MASK) {
                        combo.transferFocusBackward();
                        evt.consume();
                    } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
                        combo.transferFocus();
                        evt.consume();
                    }
                }
            };

            editor.addKeyListener(listener);
        }
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.develnottie.ui;

import java.beans.BeanDescriptor;

/**
 * @author adam (adam@devel-nottie.com)
 * @version 1.0
 */
public class JAutoCompleteComboBoxBeanInfo extends java.beans.SimpleBeanInfo {

    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor desc = new BeanDescriptor(JAutoCompleteComboBox.class);
        desc.setDisplayName("Auto Complete Combo Box");
        return desc;
    }

    @Override
    public java.awt.Image getIcon(int iconKind) {
        String image = (iconKind == ICON_COLOR_32x32) ? "/org/develnottie/ui/icons/combobox_32.gif" : "/org/develnottie/ui/icons/combobox_16.gif";
        return loadImage(image);
    }
}

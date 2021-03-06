package magic.ui.widget;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TabSelector extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static final Dimension HORIZONTAL_BUTTON_DIMENSION=new Dimension(28,20);
    private static final Dimension VERTICAL_BUTTON_DIMENSION=new Dimension(24,24);

    private final JPanel buttonPanel;
    private final List<JToggleButton> buttons;
    private final ChangeListener listener;
    private int selectedTab;
    private final Dimension buttonDimension;
    private final Color backgroundColor;
    private boolean isUserClick = false;

    public TabSelector(final ChangeListener listener, final boolean vertical, final Color backgroundColor0) {

        this.listener=listener;
        this.backgroundColor = (backgroundColor0 == null ? getBackground() : backgroundColor0);
        selectedTab=0;

        setOpaque(false);
        setLayout(new BorderLayout());

        buttonPanel=new JPanel();
        buttonPanel.setBackground(backgroundColor0);

        if (vertical) {
            buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
            add(buttonPanel,BorderLayout.NORTH);
            buttonDimension=VERTICAL_BUTTON_DIMENSION;
        } else {
            buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
            add(buttonPanel,BorderLayout.WEST);
            buttonDimension=HORIZONTAL_BUTTON_DIMENSION;
        }

        buttons=new ArrayList<JToggleButton>();

    }

    public TabSelector(final ChangeListener listener,final boolean vertical) {
        this(listener, vertical, null);
    }

    public int getSelectedTab() {

        return selectedTab;
    }

    public void setSelectedTab(final int selectedTab) {

        this.selectedTab=selectedTab;
        showTab(buttons.get(selectedTab));
    }

    public void addTab(final ImageIcon icon,final String toolTip) {

        final JToggleButton button=new JToggleButton(icon);
        if (toolTip!=null) {
            button.setToolTipText(toolTip);
        }
        button.setBackground(this.backgroundColor);
        button.setFocusable(false);
        button.setPreferredSize(buttonDimension);
        button.setActionCommand(Integer.toString(buttons.size()));
        button.addActionListener(this);
        buttons.add(button);
        buttonPanel.add(button);

        if (buttons.size()==1) {
            showTab(button);
        }
    }

//    public void addTab(final ImageIcon icon) {
//
//        addTab(icon,null);
//    }

    private void showTab(final JToggleButton selectedButton) {
        showTab(selectedButton, false);
    }
    private void showTab(final JToggleButton selectedButton, final boolean userClick) {
        this.isUserClick = userClick;
        for (final JToggleButton button : buttons) {

            button.setSelected(button==selectedButton);
        }

        selectedTab=Integer.parseInt(selectedButton.getActionCommand());
        listener.stateChanged(new ChangeEvent(selectedButton));
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        showTab((JToggleButton)event.getSource(), true);
    }

    public boolean isUserClick() {
        return isUserClick;
    }

}

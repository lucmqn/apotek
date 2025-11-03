package clss;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlaceholderTextField extends JTextField {
    
    private String placeholder;
    private PropertyChangeSupport pcs;

    public PlaceholderTextField() {
        this.pcs = new PropertyChangeSupport(this);
        setupFocusListener();
        setDragEnabled(true); // Enable dragging
        setTransferHandler(new ValueTransferHandler());
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        String oldPlaceholder = this.placeholder;
        this.placeholder = placeholder;
        pcs.firePropertyChange("placeholder", oldPlaceholder, placeholder);
        repaint(); // Repaint to show the new placeholder if necessary
    }

    private void setupFocusListener() {
        setForeground(Color.GRAY); // Set placeholder color

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().isEmpty() && placeholder != null) {
                    setForeground(Color.BLACK);
                    setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty() && placeholder != null) {
                    setForeground(Color.GRAY);
                    setText(placeholder);
                }
            }
        });

        setText(placeholder);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty() && placeholder != null && !isFocusOwner()) {
            g.drawString(placeholder, getInsets().left, g.getFontMetrics().getAscent() + getInsets().top);
        }
    }

    @Override
    public void setText(String t) {
        if (t.equals(placeholder)) {
            super.setText("");
        } else {
            super.setText(t);
        }
    }

    private class ValueTransferHandler extends TransferHandler {
        @Override
        public Transferable createTransferable(JComponent c) {
            return new StringSelection(placeholder);
        }

        @Override
        public int getSourceActions(JComponent c) {
            return COPY_OR_MOVE;
        }

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }
            try {
                String data = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                if (data.equals(placeholder)) {
                    PlaceholderTextField newField = new PlaceholderTextField();
                    newField.setPlaceholder(data);
                    newField.setText(data);
                    DropPanel panel = (DropPanel) support.getComponent();
                    panel.add(newField);
                    panel.revalidate();
                    panel.repaint();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }
}

class DropPanel extends JPanel {
    public DropPanel() {
        setLayout(new GridLayout(0, 1)); // Layout for stacking text fields
        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.LIGHT_GRAY);

        // Enable drop on the panel
        setDropTarget(new DropTarget(this, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {}

            @Override
            public void dragOver(DropTargetDragEvent dtde) {}

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {}

            @Override
            public void dragExit(DropTargetEvent dte) {}

            @Override
            public void drop(DropTargetDropEvent dtde) {
                dtde.acceptDrop(DnDConstants.ACTION_COPY);
                try {
                    PlaceholderTextField newField = new PlaceholderTextField();
                    newField.setPlaceholder("Dropped Field");
                    add(newField);
                    revalidate();
                    repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}

class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Drag and Drop PlaceholderTextField Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        DropPanel dropPanel = new DropPanel();
        add(dropPanel, BorderLayout.CENTER);

        PlaceholderTextField placeholderField = new PlaceholderTextField();
        placeholderField.setPlaceholder("Drag me!");
        add(placeholderField, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

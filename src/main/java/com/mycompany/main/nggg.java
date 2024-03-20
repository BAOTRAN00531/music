// này test resize
package com.mycompany.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class nggg {
    private static final int RESIZE_BORDER_SIZE = 8;
    private static final int MINIMUM_WIDTH = 200;
    private static final int MINIMUM_HEIGHT = 200;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setUndecorated(true); // Set JFrame to undecorated

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        // Add some content to the frame
        JLabel label = new JLabel("Drag the edges/corners to resize the window");
        contentPane.add(label, BorderLayout.CENTER);

        frame.setContentPane(contentPane);

        // Enable resizing
        ResizeMouseListener resizeMouseListener = new ResizeMouseListener(frame);
        frame.addMouseListener(resizeMouseListener);
        frame.addMouseMotionListener(resizeMouseListener);

        // Set initial size and make frame visible
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static class ResizeMouseListener extends MouseAdapter {
        private final JFrame frame;
        private int startX, startY;
        private boolean isResizing = false;
        private int direction;

        public ResizeMouseListener(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Point p = e.getPoint();
            SwingUtilities.convertPointToScreen(p, frame);
            startX = p.x;
            startY = p.y;
            if (isInResizeBorder(p)) {
                isResizing = true;
                direction = getDirection(p);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            isResizing = false;
            frame.setCursor(Cursor.getDefaultCursor());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (!isResizing)
                return;
            Point p = e.getPoint();
            SwingUtilities.convertPointToScreen(p, frame);
            int deltaX = p.x - startX;
            int deltaY = p.y - startY;
            Dimension size = frame.getSize();
            Dimension newSize = new Dimension(size.width, size.height);
            switch (direction) {
                case Cursor.NW_RESIZE_CURSOR:
                    newSize.width -= deltaX;
                    newSize.height -= deltaY;
                    frame.setLocation(frame.getX() + deltaX, frame.getY() + deltaY);
                    break;
                case Cursor.N_RESIZE_CURSOR:
                    newSize.height -= deltaY;
                    frame.setLocation(frame.getX(), frame.getY() + deltaY);
                    break;
                case Cursor.NE_RESIZE_CURSOR:
                    newSize.width += deltaX;
                    newSize.height -= deltaY;
                    frame.setLocation(frame.getX(), frame.getY() + deltaY);
                    break;
                case Cursor.W_RESIZE_CURSOR:
                    newSize.width -= deltaX;
                    frame.setLocation(frame.getX() + deltaX, frame.getY());
                    break;
                case Cursor.E_RESIZE_CURSOR:
                    newSize.width += deltaX;
                    break;
                case Cursor.SW_RESIZE_CURSOR:
                    newSize.width -= deltaX;
                    newSize.height += deltaY;
                    frame.setLocation(frame.getX() + deltaX, frame.getY());
                    break;
                case Cursor.S_RESIZE_CURSOR:
                    newSize.height += deltaY;
                    break;
                case Cursor.SE_RESIZE_CURSOR:
                    newSize.width += deltaX;
                    newSize.height += deltaY;
                    break;
                default:
                    break;
            }
            // Limit minimum size
            newSize.width = Math.max(MINIMUM_WIDTH, newSize.width);
            newSize.height = Math.max(MINIMUM_HEIGHT, newSize.height);
            frame.setSize(newSize);
            startX = p.x;
            startY = p.y;
        }

        private boolean isInResizeBorder(Point p) {
            Rectangle bounds = frame.getBounds();
            return p.x < bounds.x + RESIZE_BORDER_SIZE || p.x > bounds.x + bounds.width - RESIZE_BORDER_SIZE
                    || p.y < bounds.y + RESIZE_BORDER_SIZE || p.y > bounds.y + bounds.height - RESIZE_BORDER_SIZE;
        }

        private int getDirection(Point p) {
            int x = p.x;
            int y = p.y;
            int w = frame.getWidth();
            int h = frame.getHeight();

            if (x < RESIZE_BORDER_SIZE && y < RESIZE_BORDER_SIZE)
                return Cursor.NW_RESIZE_CURSOR;
            if (x < RESIZE_BORDER_SIZE && y > h - RESIZE_BORDER_SIZE)
                return Cursor.SW_RESIZE_CURSOR;
            if (x > w - RESIZE_BORDER_SIZE && y < RESIZE_BORDER_SIZE)
                return Cursor.NE_RESIZE_CURSOR;
            if (x > w - RESIZE_BORDER_SIZE && y > h - RESIZE_BORDER_SIZE)
                return Cursor.SE_RESIZE_CURSOR;
            if (x < RESIZE_BORDER_SIZE)
                return Cursor.W_RESIZE_CURSOR;
            if (x > w - RESIZE_BORDER_SIZE)
                return Cursor.E_RESIZE_CURSOR;
            if (y < RESIZE_BORDER_SIZE)
                return Cursor.N_RESIZE_CURSOR;
            if (y > h - RESIZE_BORDER_SIZE)
                return Cursor.S_RESIZE_CURSOR;
            return Cursor.DEFAULT_CURSOR;
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (isInResizeBorder(e.getPoint())) {
                frame.setCursor(getResizeCursor(e.getPoint()));
            } else {
                frame.setCursor(Cursor.getDefaultCursor());
            }
        }

        private int getResizeCursor(Point p) {
            int x = p.x;
            int y = p.y;
            int w = frame.getWidth();
            int h = frame.getHeight();

            if (x < RESIZE_BORDER_SIZE && y < RESIZE_BORDER_SIZE)
                return Cursor.NW_RESIZE_CURSOR;
            if (x < RESIZE_BORDER_SIZE && y > h - RESIZE_BORDER_SIZE)
                return Cursor.SW_RESIZE_CURSOR;
            if (x > w - RESIZE_BORDER_SIZE && y < RESIZE_BORDER_SIZE)
                return Cursor.NE_RESIZE_CURSOR;
            if (x > w - RESIZE_BORDER_SIZE && y > h - RESIZE_BORDER_SIZE)
                return Cursor.SE_RESIZE_CURSOR;
            if (x < RESIZE_BORDER_SIZE)
                return Cursor.W_RESIZE_CURSOR;
            if (x > w - RESIZE_BORDER_SIZE)
                return Cursor.E_RESIZE_CURSOR;
            if (y < RESIZE_BORDER_SIZE)
                return Cursor.N_RESIZE_CURSOR;
            if (y > h - RESIZE_BORDER_SIZE)
                return Cursor.S_RESIZE_CURSOR;
            return Cursor.DEFAULT_CURSOR;
        }
    }
}

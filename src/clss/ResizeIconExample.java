/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clss;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ResizeIconExample {

    public static void main(String[] args) {
        // Membuat frame
        JFrame frame = new JFrame("Resize Icon Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Membaca dan mengubah ukuran ikon
        ImageIcon originalIcon = new ImageIcon(ResizeIconExample.class.getResource("/resources/icon.png"));
        Image resizedImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Menambahkan ikon ke JLabel
        JLabel label = new JLabel(resizedIcon);

        // Menambahkan JLabel ke frame
        frame.getContentPane().add(label);

        // Menampilkan frame
        frame.setVisible(true);
    }
}

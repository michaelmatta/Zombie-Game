package com.zomDemo;

import com.zomDemo.zombies.Zombie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Client extends JApplet implements KeyListener {

    private Graphics bg;
    private Image offscreen;
    Player player;
    Random random;
    private ArrayList<String> dirs;
    private ArrayList<Zombie> zombieArray;

    public void init() {
        setSize(1914, 910);
        Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        offscreen = createImage(screen.width, screen.height);
        bg = offscreen.getGraphics();
        addKeyListener(this);
        setFocusable(true);
        zombieArray = new ArrayList<>();
        player = new Player();

        random = new Random();
        for (int i = 0; i < 10; i++) {
            zombieArray.add(new Zombie(new Rectangle(random.nextInt(screen.width), random.nextInt(screen.height), 10, 10)));
        }

        dirs = new ArrayList<>();
        Timer timer = new Timer(30, ae -> {
            player.move(dirs);
            for(Zombie zomb: zombieArray) {
                zomb.move(player);
                zomb.act(player);
            }
            repaint();
        });
        timer.start();
    }

    public void paint(Graphics g) {
        bg.clearRect(0, 0, offscreen.getWidth(this), offscreen.getHeight(this));
        player.draw(bg);
        for(Zombie zomb: zombieArray) {
            zomb.draw(bg);
        }
        g.drawImage(offscreen, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if(c == 'w' && !dirs.contains("up")) {
            dirs.add("up");
        }else if(c == 'a' && !dirs.contains("left")) {
            dirs.add("left");
        }else if(c == 's' && !dirs.contains("down")) {
            dirs.add("down");
        }else if(c == 'd' && !dirs.contains("right")) {
            dirs.add("right");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
        if(c == 'w') {
            dirs.remove("up");
        }else if(c == 'a') {
            dirs.remove("left");
        }else if(c == 's') {
            dirs.remove("down");
        }else if(c == 'd') {
            dirs.remove("right");
        }
    }
}

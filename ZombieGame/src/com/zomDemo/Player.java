package com.zomDemo;

import com.zomDemo.zombies.Zombie;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    double vx, vy, accel, health;
    int maxSpeed;
    public Rectangle bounds;
    public boolean alive;

    public Player() {
        bounds = new Rectangle(20, 20, 10, 10);
        vx = 0;
        vy = 0;
        maxSpeed = 4;
        accel = 0.5;
        alive = true;
        health = 100;
    }

    public void draw(Graphics g) {
        if(alive) {
            g.setColor(Color.BLUE);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

//    public void setDir(String dir) {
//        if(dir.equals("up")) {
//            vy -= speed;
//        }else if(dir.equals("left")) {
//            vx -= speed;
//        }else if(dir.equals("right")) {
//            vx += speed;
//        }else if(dir.equals("down")) {
//            vy += speed;
//        }
//    }

    public void hit(Zombie z) {
        health -= z.damage;
        if(health <= 0) {
            alive = false;
        }
    }

    public void move(ArrayList<String> dirs) {
        if (alive) {
            for (int i = 0; i < dirs.size(); i++) {
                if (dirs.get(i).equals("up")) {
                    vy -= accel;
                } else if (dirs.get(i).equals("left")) {
                    vx -= accel;
                } else if (dirs.get(i).equals("right")) {
                    vx += accel;
                } else if (dirs.get(i).equals("down")) {
                    vy += accel;
                }
            }

            vx *= .9;
            vy *= .9;

            if (vx > maxSpeed) {
                vx = maxSpeed;
            }
            if (vx < -maxSpeed) {
                vx = -maxSpeed;
            }
            if (vy > maxSpeed) {
                vy = maxSpeed;
            }
            if (vy < -maxSpeed) {
                vy = -maxSpeed;
            }

            bounds.x += (int) vx;
            bounds.y += (int) vy;
        }
    }
}

package com.zomDemo.zombies;

import com.zomDemo.Player;

import java.awt.*;

public class Zombie {

    Rectangle bounds;
    int dx, dy, maxSpeed;
    double vx, vy, accel;
    public int damage;

    public Zombie(Rectangle bounds) {
        this.bounds = bounds;
        maxSpeed = 2;
        vx = 0;
        vy = 0;
        accel = 0.5;
        damage = 1;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void move(Player p) {
        if(isPlayerInRange(p)) {
            dx = p.bounds.x - bounds.x;
            dy = p.bounds.y - bounds.y;
            if (dx > 0) {
                vx += accel;
            } else if (dx < 0) {
                vx -= accel;
            }
            if (dy > 0) {
                vy += accel;
            } else if (dy < 0) {
                vy -= accel;
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

        } else {
            if(Math.random() < .02) {
                vx = Math.random() * 5 - 2;
                vy = Math.random() * 5 - 2;
            }
        }
        bounds.x += (int) vx;
        bounds.y += (int) vy;
    }

    public boolean isPlayerInRange(Player p) {
        dx = p.bounds.x - bounds.x;
        dy = p.bounds.y - bounds.y;

        double dx2 = Math.pow(dx, 2);
        double dy2 = Math.pow(dy, 2);

        double distance = Math.sqrt((dx2 + dy2));

        if(distance > 200 || !p.alive) {
            return false;
        } else {
            return true;
        }
    }

    public void act(Player p) {
        if(contains(p.bounds)) {
            p.hit(this);
        }
    }

    public boolean contains(Rectangle rect) {
        return bounds.intersects(rect);
    }
}

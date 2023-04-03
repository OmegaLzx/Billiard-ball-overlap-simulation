package pri.lzx.gui.bean;


import lombok.Data;

import java.awt.*;

@Data
public class Circle implements Shape {
    private int x;
    private int y;
    private int radius = 50;

    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }
}

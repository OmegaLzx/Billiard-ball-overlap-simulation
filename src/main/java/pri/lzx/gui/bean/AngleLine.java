package pri.lzx.gui.bean;

import lombok.Data;

import java.awt.*;

@Data
public class AngleLine implements Shape {
    private int x;
    private int y;
    private int length = 100;
    private double angle = Math.PI / 2;

    public AngleLine(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
        // 绘制角度
        int x1 = x;
        int y1 = y - length;
        int x2 = (int) (x - length * Math.sin(angle));
        int y2 = (int) (y - length * Math.cos(angle));

        g.drawLine(x, y, x1, y1);
        g.drawLine(x, y, x2, y2);
    }
}

package pri.lzx.gui.panel;

import lombok.extern.slf4j.Slf4j;
import pri.lzx.gui.bean.AngleLine;
import pri.lzx.gui.bean.Circle;

import javax.swing.*;
import java.awt.*;

@Slf4j(topic = "draw")
public class LearnPanel extends JPanel {
    public static LearnPanel instance = new LearnPanel();
    private final Circle circle1 = new Circle(100, 100);
    private final Circle circle2 = new Circle(200, 100);
    private final AngleLine angleLine = new AngleLine(350, 150);
    private final int length = 130; // 角度线段的长度
    private int radius = 50; //圆的半径
    private int L = 100; // 两个圆的圆心距离
    private double angle = Math.PI / 2; // 角度的默认值

    public void draw() {
        JFrame frame = new JFrame("CircleGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        LearnPanel testPanel = new LearnPanel();
        frame.add(testPanel);

        // 创建一个滑块，用于控制两个圆的圆心距离
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, 2 * testPanel.radius, testPanel.L);
        slider.setMajorTickSpacing(testPanel.radius / 2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        // 添加slider监听器
        slider.addChangeListener(e -> {
            testPanel.L = ((JSlider) e.getSource()).getValue();
            double v = (2.0 * testPanel.radius - testPanel.L) / (2.0 * testPanel.radius);
            testPanel.angle = Math.asin(1 - v);
            log.info("度数:{} 重合度: {}", String.format("%.1f", testPanel.angle / Math.PI * 180), v);
            testPanel.repaint();
        });
        frame.add(slider, "South");

        frame.setVisible(true);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 根据圆心距离计算两个圆的横坐标
        int d = Math.abs(circle2.getX() - circle1.getX());
        circle2.setX(circle2.getX() - (d - L));

        g.setColor(Color.BLUE);
        circle1.draw(g);

        g.setColor(Color.BLACK);
        circle2.draw(g);

        g.setColor(Color.BLACK);
        angleLine.setLength(length);
        angleLine.setAngle(angle);
        angleLine.draw(g);
    }
}

package pri.lzx.gui.panel;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import pri.lzx.gui.bean.AngleLine;
import pri.lzx.gui.bean.Circle;

import javax.swing.*;
import java.awt.*;

@Slf4j(topic = "test")
public class TestPanel extends JPanel {
    private final AngleLine angleLine = new AngleLine(800, 300);
    private final int length = 200; // 角度线段的长度
    private final double diff = 0.1; // 偏差
    private int radius = 100; //圆的半径
    private final Circle circle1 = new Circle(100, 100, radius);
    private final Circle circle2 = new Circle(200, 100, radius);
    private int L = 200; // 两个圆的圆心距离
    private double angle = RandomUtil.randomDouble(1) * Math.PI / 2; // 角度

    public void draw() {
        JFrame frame = new JFrame("CircleGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        TestPanel testPanel = new TestPanel();
        frame.add(testPanel);

        // 创建一个滑块，用于控制两个圆的圆心距离
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, 2 * testPanel.radius, testPanel.L);
        slider.setMajorTickSpacing(testPanel.radius / 2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        // 添加slider监听器
        slider.addChangeListener(e -> {
            testPanel.L = ((JSlider) e.getSource()).getValue();
            testPanel.repaint();
        });
        JButton jButton = new JButton("确定");
        jButton.addActionListener(e -> {
            double v = (2.0 * testPanel.radius - testPanel.L) / (2.0 * testPanel.radius);
            double myAngel = Math.asin(1 - v);
            double currDiff = Math.abs(1 - testPanel.angle / myAngel);
            log.info("目标角度 {}，您给出的角度 {}，偏差 {} %", getRealAngel(testPanel.angle), getRealAngel(myAngel),
                     String.format("%.2f", currDiff)
            );
            if (currDiff <= diff) {
                testPanel.angle = RandomUtil.randomDouble(1) * Math.PI / 2;
                log.info("恭喜你！通过测试！！");
                testPanel.repaint();
            } else {
                if (myAngel > testPanel.angle) {
                    log.info("您打薄了~");
                } else {
                    log.info("您打厚了~");
                }
            }
        });
        frame.add(jButton, BorderLayout.NORTH);
        frame.add(slider, BorderLayout.SOUTH);

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
        angleLine.setLength(length);
        angleLine.setAngle(angle);
        angleLine.draw(g);
    }

    private String getRealAngel(double angle) {
        return String.format("%.2f", angle / Math.PI * 180);
    }
}

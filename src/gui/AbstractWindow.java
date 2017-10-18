package gui;

import javax.swing.*;

/*
* 利用工厂模式来实现不同窗口之间的变换
* 创建一个抽象的接口
* */
public interface AbstractWindow {
    JPanel getPanel();
    String getTitle();

}

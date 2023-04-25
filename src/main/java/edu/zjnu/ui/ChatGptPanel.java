package edu.zjnu.ui;

import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import edu.zjnu.gpt.service.GptService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import javax.swing.*;

/**
 * @author: 杨海波
 * @date: 2023-04-25 19:11:16
 * @description: ChatGptPanel
 */
public class ChatGptPanel extends JPanel {

    GptService chatService = new GptService();

    private static final long serialVersionUID = 1L;
    private JList<String> userList;
    private JTextArea textArea;
    private JTextField textField;

    public ChatGptPanel() {
        super(new BorderLayout());
        initUI();
    }

    private void initUI() {
        // 右侧聊天面板
        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.setBorder(new CompoundBorder(
                new LineBorder(JBColor.GRAY),
                JBUI.Borders.empty(5)));

        // 聊天显示区域
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(textArea);

        // 输入区域
        JPanel inputPanel = new JPanel(new BorderLayout());

        textField = new JTextField();
        inputPanel.add(textField, BorderLayout.CENTER);

        JButton sendButton = new JButton("发送");
        sendButton.addActionListener(e -> sendMessage());
        inputPanel.add(sendButton, BorderLayout.EAST);

        chatPanel.add(chatScrollPane, BorderLayout.CENTER);
        chatPanel.add(inputPanel, BorderLayout.SOUTH);

        // 将聊天面板添加到主面板中
        add(chatPanel, BorderLayout.CENTER);
    }

    private void sendMessage() {
        String message = textField.getText();
        if (!message.isEmpty()) {
            String response = chatService.doChat(textField.getText());
            textArea.append(response + "\n");
            textField.setText("");
        }
    }
}

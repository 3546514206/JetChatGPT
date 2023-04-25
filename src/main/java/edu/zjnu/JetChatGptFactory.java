package edu.zjnu;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import edu.zjnu.ui.ChatGptPanel;
import org.jetbrains.annotations.NotNull;

/**
 * @author: 杨海波
 * @date: 2023-04-25 18:58:08
 * @description: JetChatGptFactory
 */
public class JetChatGptFactory implements ToolWindowFactory {

    /**
     * 全局静态唯一的配置类
     */
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // 获取内容工厂的实例
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        // 构建聊天面板
        ChatGptPanel chatGptPanel = new ChatGptPanel();
        // 获取 ToolWindow 显示的内容
        Content content = contentFactory.createContent(chatGptPanel, "", false);
        // 设置 ToolWindow 显示的内容
        toolWindow.getContentManager().addContent(content);
    }
}


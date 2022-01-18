package com.mcritic.idea.plugin.windows;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.JComponent;
import javax.swing.JEditorPane;

import java.io.IOException;

import java.net.URL;

public class PluginToolWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        JComponent jComponent = new SimpleToolWindowPanel(true, true);
        final ContentManager contentManager = toolWindow.getContentManager();
        final Content content        = contentManager.getFactory()
                .createContent(
                        jComponent, null,
                        false);

        contentManager.addContent(content);

        JEditorPane jEditorPane = new JEditorPane();
        jEditorPane.setEditable(false);
        jEditorPane.setContentType("text/html; charset=UTF-8");
        URL url= getClass().getClassLoader().getResource("help/help.html");

        try {
            jEditorPane.setPage(url);
        } catch (IOException e) {
            jEditorPane.setText("<html>Page not found.</html>");
        }

        JBScrollPane jScrollPane = new JBScrollPane(jEditorPane);
        contentManager.addContent(
                contentManager.getFactory().createContent(jScrollPane, "Help", false));
    }
}

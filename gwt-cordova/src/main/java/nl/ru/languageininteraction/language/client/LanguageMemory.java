package nl.ru.languageininteraction.language.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LanguageMemory implements EntryPoint {

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        final RootPanel widgetTag = RootPanel.get("widgetTag");
        final AppController appController = new AppController(widgetTag);
        appController.start();
    }
}

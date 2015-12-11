package ru.sadv1r.afc.ideaPlugin;

import com.intellij.openapi.components.ServiceManager;

/**
 * Created on 12/10/15.
 *
 * @author sadv1r
 * @version 0.1
 */
public interface Achievable {
    Stats stats = ServiceManager.getService(Stats.class);

    String getName();
    String getText();
    int getProgress();
    int getFinish();
}

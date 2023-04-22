package ru.sadv1r.afc.ideaPlugin;

import com.intellij.codeInsight.editorActions.CopyPastePostProcessor;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.awt.datatransfer.Transferable;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * @author sadv1r
 */
public class MyCopyPastePostProcessor extends CopyPastePostProcessor {

    private final Stats stats = ServiceManager.getService(Stats.class);

    @NotNull
    @Override
    public List collectTransferableData(PsiFile file, Editor editor, int[] startOffsets, int[] endOffsets) {
        return Collections.emptyList();
    }

    public List extractTransferableData(final Transferable content) {
        if (stats.getLastDateDayWithoutCopyPaste() - Calendar.getInstance().getTimeInMillis() < 86_400_000) {
            //ууууу. нужен периодический джоб, проверяющий, число, которое будет сбрасываться в этом методе
        }
        return Collections.emptyList();
    }

}

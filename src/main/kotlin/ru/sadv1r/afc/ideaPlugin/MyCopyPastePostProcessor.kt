package ru.sadv1r.afc.ideaPlugin

import com.intellij.openapi.components.ServiceManager
import com.intellij.codeInsight.editorActions.CopyPastePostProcessor
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import java.awt.datatransfer.Transferable
import java.util.*

//class MyCopyPastePostProcessor: CopyPastePostProcessor {
//
//    private val stats = ServiceManager.getService(Stats::class.java)
//
//    @Override
//    fun collectTransferableData(file: PsiFile, editor: Editor, startOffsets: Array<Int>, endOffsets: Array<Int>): List<Any>{
//        return emptyList()
//    }
//
//    fun extractTransferableData(content: Transferable): List<Any> {
//        if (stats.lastDateDayWithoutCopyPaste - Calendar.getInstance().timeInMillis < 86_400_000) {
//            //ууууу. нужен периодический джоб, проверяющий, число, которое будет сбрасываться в этом методе
//        }
//        return emptyList()
//    }
//
//}
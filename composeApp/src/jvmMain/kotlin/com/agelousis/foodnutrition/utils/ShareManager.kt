package com.agelousis.foodnutrition.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

class JvmShareManager : ShareManager {
    override fun share(
        text: String,
        completion: () -> Unit
    ) {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val selection = StringSelection(text)
        clipboard.setContents(selection, selection)
        completion()
    }
}

@Composable
actual fun rememberShareManager(): ShareManager = remember {
    JvmShareManager()
}
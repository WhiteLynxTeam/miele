package ru.miel.utils.uiextensions

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import ru.miel.domain.models.enummodel.SortOption

fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.hide(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun View.gone(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}
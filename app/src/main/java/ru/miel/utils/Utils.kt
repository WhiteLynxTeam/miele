package ru.miel.utils

import android.content.Context
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import ru.miel.domain.models.HasUuid
import ru.miel.domain.models.enummodel.SortOption
import java.util.UUID

fun List<HasUuid>.randomUuid() {
    forEach { instance ->
        instance.uuid = UUID.randomUUID()
    }
}

fun String.replaceAfterLastSpace(replacement: String): String {
    val lastSpaceIndex = this.lastIndexOf(' ')

    return if (lastSpaceIndex != -1) {
        this.substring(0, lastSpaceIndex) + " " + replacement
    } else {
        "$this $replacement"
    }
}

fun String.replaceAfterLastSpace(digit: Int): String {
    val lastSpaceIndex = this.lastIndexOf(' ')

    return if (lastSpaceIndex != -1) {
        this.substring(0, lastSpaceIndex) + " $digit"
    } else {
        "$this $digit"
    }
}

fun TextView.setTextColorRes(@ColorRes colorRes: Int) {
    val color = ContextCompat.getColor(context, colorRes)
    setTextColor(color)
}

//    private fun showRadioDialog(callback: (Int) -> Unit) {
fun showRadioDialog(context: Context, callback: () -> Unit) {
    val options = SortOption.entries.map { it.text }.toTypedArray()

    AlertDialog.Builder(context)
        .setTitle("Сортировать")
        .setSingleChoiceItems(options, SortOption.getSelected()) { dialog, which ->
            SortOption.setSelected(which)
            callback()

            dialog.dismiss()
        }
        .show()
}

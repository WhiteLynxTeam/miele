package ru.miel.utils

import android.content.Context
import android.text.InputFilter
import android.widget.EditText
import android.widget.FrameLayout
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

fun showQuotesDialog(context: Context, callback: (Int) -> Unit) {
    val input = EditText(context).apply {
        inputType = android.text.InputType.TYPE_CLASS_NUMBER // Ограничиваем ввод только цифрами
        setPadding(50, 20, 50, 20)
        filters = arrayOf(InputFilter.LengthFilter(2))
    }

    // 🔥 Оборачиваем EditText в FrameLayout и задаем отступы
    val container = FrameLayout(context).apply {
        setPadding(50, 20, 50, 20) // Добавляем отступы внутри контейнера
        addView(input)
    }

    AlertDialog.Builder(context)
        .setTitle("Запрос квот")
        .setMessage("Квоты:")
        .setView(container)  // Устанавливаем EditText в качестве view
        .setPositiveButton("Ок") { dialog, which ->
            val enteredValue = input.text.toString().trim()

            // Проверяем, что введенная строка - это двухзначное число
            if (enteredValue.toIntOrNull() != null) {
                val number = enteredValue.toInt()
                // Действие, если число введено корректно
                callback(number)
            } else {
                // Обработка ошибки ввода, например, покажем сообщение
                input.error = "Пожалуйста, введите двухзначное число"
            }

            dialog.dismiss()
        }
        .setNegativeButton("Отмена") { dialog, which ->
            dialog.dismiss()  // Просто закрываем диалог
        }
        .show()
}


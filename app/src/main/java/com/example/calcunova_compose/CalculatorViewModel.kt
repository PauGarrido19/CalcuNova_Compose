package com.example.calcunova_compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.sqrt

class CalculatorViewModel : ViewModel() {

    var displayText by mutableStateOf("0")
        private set

    var historyText by mutableStateOf("")
        private set

    private var firstOperand: Double? = null
    private var pendingOperation: String? = null
    private var isNewOp = true

    fun onButtonClick(symbol: String) {
        when (symbol) {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> onNumberClick(symbol)
            "," -> onDecimalClick()
            "C" -> onClearAll()
            "CE" -> onClearEntry()
            "⌫" -> onDeleteClick()
            "+", "-", "x", "÷", "%" -> onOperationClick(symbol)
            "=" -> onEqualsClick()
            "±" -> onSignToggle()
            "x²" -> onSquareClick()
            "√x" -> onSqrtClick()
            "1/x" -> onReciprocalClick()
        }
    }

    private fun onNumberClick(number: String) {
        if (isNewOp || displayText == "0") {
            displayText = number
            isNewOp = false
        } else {
            displayText += number
        }
    }

    private fun onDecimalClick() {
        if (isNewOp) {
            displayText = "0,"
            isNewOp = false
        } else if (!displayText.contains(",")) {
            displayText += ","
        }
    }

    private fun onOperationClick(op: String) {
        if (firstOperand != null && pendingOperation != null && !isNewOp) {
            evaluate()
        }
        firstOperand = parseDouble(displayText)
        pendingOperation = op
        historyText = "$displayText $op"
        isNewOp = true
    }

    fun onEqualsClick() {
        if (firstOperand != null && pendingOperation != null) {
            val secondStr = displayText
            val primero = formatResult(firstOperand!!)
            val op = pendingOperation
            evaluate()
            historyText = "$primero $op $secondStr ="
            firstOperand = null
            pendingOperation = null
            isNewOp = true
        }
    }

    private fun evaluate() {
        val secondOperand = parseDouble(displayText)
        val result = when (pendingOperation) {
            "+" -> firstOperand!! + secondOperand
            "-" -> firstOperand!! - secondOperand
            "x" -> firstOperand!! * secondOperand
            "÷" -> if (secondOperand != 0.0) firstOperand!! / secondOperand else Double.NaN
            "%" -> firstOperand!! * (secondOperand / 100.0)
            else -> secondOperand
        }
        displayText = formatResult(result)
    }

    private fun onClearAll() {
        displayText = "0"
        historyText = ""
        firstOperand = null
        pendingOperation = null
        isNewOp = true
    }

    private fun onClearEntry() {
        displayText = "0"
        isNewOp = true
    }

    private fun onDeleteClick() {
        if (displayText.length > 1 && !isNewOp) {
            displayText = displayText.dropLast(1)
        } else {
            displayText = "0"
            isNewOp = true
        }
    }

    private fun onSignToggle() {
        val value = parseDouble(displayText)
        if (value != 0.0) {
            displayText = formatResult(-value)
        }
    }

    private fun onSquareClick() {
        val value = parseDouble(displayText)
        displayText = formatResult(value * value)
        isNewOp = true
    }

    private fun onSqrtClick() {
        val value = parseDouble(displayText)
        displayText = if (value >= 0) formatResult(sqrt(value)) else "Error"
        isNewOp = true
    }

    private fun onReciprocalClick() {
        val value = parseDouble(displayText)
        displayText = if (value != 0.0) formatResult(1.0 / value) else "Error"
        isNewOp = true
    }

    private fun parseDouble(str: String): Double {
        return str.replace(".", "").replace(",", ".").toDoubleOrNull() ?: 0.0
    }

    private fun formatResult(result: Double): String {
        if (result.isNaN()) return "Error"
        return if (result % 1.0 == 0.0) {
            result.toLong().toString()
        } else {
            result.toString().replace(".", ",")
        }
    }
}
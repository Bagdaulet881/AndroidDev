package com.example.calcbybakosta

import java.util.*

class Calculator {
    enum class Operator {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, BLANK, DOT, DBLE, SQRT, FACT, PERCENT, SIN, COS, TAN, LN, LOG, E, XN, SQRTN
    }

    fun compute(sequence: String): Double {
        val numberStack = Stack<Double>()
        val operatorStack = Stack<Operator>()
        var i = 0
        while (i < sequence.length) {
            try {
                val number = parseNumber(sequence, i)
                numberStack.push(number.toDouble())
                i += Integer.toString(number).length
                if (i >= sequence.length) {
                    break
                }
                val op = parseOperator(sequence, i)
                collapseTop(numberStack, operatorStack, op)
                operatorStack.push(op)
            } catch (ex: NumberFormatException) {
                return Int.MIN_VALUE.toDouble()
            }
            i++
        }
        collapseTop(numberStack, operatorStack, Operator.BLANK)
        return if (numberStack.size == 1 && operatorStack.size == 0) {
            numberStack.pop()
        } else
            return 0.0
    }

    //operatorsStack peek futureTop
    private fun collapseTop(numberStack: Stack<Double>, operatorStack: Stack<Operator>, futureTop: Operator) {
        while (numberStack.size >= 2 && operatorStack.size >= 1) {
            if (priorityOfOperator(futureTop) <= priorityOfOperator(operatorStack.peek())) {
                val second = numberStack.pop()
                val first = numberStack.pop()
                val op = operatorStack.pop()
                val result = applyOp(first, op, second)
                numberStack.push(result)
            } else {
                break
            }
        }
    }

    private fun setMaxSize(answer: Double): Double {
        return String.format("%.3f", answer).toDouble()
    }

    private fun applyOp(left: Double, op: Operator, right: Double): Double {
        val left1 = left.toInt()
        val right1 = right.toInt() //secret 2.06!
        val dotcase = "$left1.$right1"
        val res = dotcase.toDouble()
        return when (op) {
            Operator.ADD -> setMaxSize(left + right)
            Operator.SUBTRACT -> setMaxSize(left - right)
            Operator.MULTIPLY -> setMaxSize(left * right)
            Operator.DIVIDE -> setMaxSize(left / right)
            Operator.DBLE -> setMaxSize(left * left)
            Operator.DOT -> res
            Operator.SQRT -> setMaxSize(Math.sqrt(left))
            Operator.FACT -> setMaxSize(fact(left).toDouble())
            Operator.PERCENT -> right * left / 100
            Operator.SIN -> Math.sin(right)
            Operator.COS -> Math.cos(right)
            Operator.TAN -> Math.tan(right)
            Operator.LN -> Math.log(right)
            Operator.LOG -> Math.log10(right)
            Operator.E -> 2.7182
            Operator.XN -> Math.pow(left, right)
            Operator.SQRTN -> sqrtN(left, right)
            else -> right
        }
    }

    private fun priorityOfOperator(op: Operator): Int {
        return when (op) {
            Operator.ADD -> 1
            Operator.SUBTRACT -> 1
            Operator.MULTIPLY -> 2
            Operator.DIVIDE -> 2
            Operator.DBLE -> 3
            Operator.FACT -> 3
            Operator.SQRT -> 3
            Operator.DOT -> 4
            Operator.BLANK -> 0
            Operator.PERCENT -> 3
            Operator.SIN -> 3
            Operator.COS -> 3
            Operator.TAN -> 3
            Operator.LN -> 3
            Operator.LOG -> 3
            Operator.E -> 5
            Operator.XN -> 2
            Operator.SQRTN -> 3
        }
        return 0
    }

    private fun parseNumber(sequence: String, offset: Int): Int {
        var offset = offset
        val sb = StringBuilder()
        while (offset < sequence.length && Character.isDigit(sequence[offset])) {
            sb.append(sequence[offset])
            offset++
        }
        return sb.toString().toInt()
    }

    private fun parseOperator(sequence: String, offset: Int): Operator {
        if (offset < sequence.length) {
            val op = sequence[offset]
            when (op) {
                '+' -> return Operator.ADD
                '-' -> return Operator.SUBTRACT
                '*' -> return Operator.MULTIPLY
                '/' -> return Operator.DIVIDE
                '.' -> return Operator.DOT
                'x' -> return Operator.DBLE
                '√' -> return Operator.SQRT
                '!' -> return Operator.FACT
                '%' -> return Operator.PERCENT
                's' -> return Operator.SIN
                'c' -> return Operator.COS
                't' -> return Operator.TAN
                'l' -> return Operator.LN
                'g' -> return Operator.LOG
                'e' -> return Operator.E
                '^' -> return Operator.XN
                'n' -> return Operator.SQRTN
            }
        }
        return Operator.BLANK
    }

    private fun sqrtN(left: Double, right: Double): Double {
        return if (right == 0.0) {
            return 1.0
        } else Math.pow(left, 1.0 / right)
    }

    private fun fact(left: Double): Long {
        val n = left.toInt()
        var fact: Long = 1
        return if (n == 0) 1 else {
            for (i in 1..n) {
                fact = fact * i
            }
            fact
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val expression = "2-6-7*8/2+5"
            val calc = Calculator()
            println(calc.compute(expression))
        }
    }
}
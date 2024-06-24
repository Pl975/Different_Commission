package ru.netology

import kotlin.math.max

const val ERROR_TYPE = -1
const val ERROR_LIMIT = -2
const val dailyLimit = 150_000
const val monthlyLimit = 600_000

fun main() {
    val commission = calculateCommission("MasterCard", 150_000, 74_000)
    if (commission != ERROR_LIMIT) {
        println("Комиссия: $commission")
    } else {
        println("Превышен лимит. Перевод отменен")
        return
    }
}

fun calculateCommission(typeCard: String = "Мир", transfer: Int, lastTransfers: Int = 0):
        Int {
    if (transfer > dailyLimit || (transfer + lastTransfers) > monthlyLimit) {
        return ERROR_LIMIT
    }
    return when (typeCard) {
        "Мир" -> return 0
        "MasterCard" -> return if (lastTransfers > 75_000) (transfer * 0.006).toInt()
        else if (transfer + lastTransfers > 75_000) ((transfer + lastTransfers - 75_000) * 0.006).toInt() + 20 else 0

        "Visa" -> max(35, (transfer * 0.0075).toInt())
        else -> return ERROR_TYPE

    }

}



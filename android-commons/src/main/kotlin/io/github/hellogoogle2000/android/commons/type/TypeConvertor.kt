package io.github.hellogoogle2000.android.commons.type

import BooleanList
import DoubleList
import FloatList
import IntList
import LongList
import StringList

fun List<Int>?.toIntList(): IntList {
    val src = this
    return IntList().apply {
        if (src != null)
            addAll(src)
    }
}

fun List<Long>?.toLongList(): LongList {
    val src = this
    return LongList().apply {
        if (src != null)
            addAll(src)
    }
}

fun List<Float>?.toFloatList(): FloatList {
    val src = this
    return FloatList().apply {
        if (src != null)
            addAll(src)
    }
}

fun List<Double>?.toDoubleList(): DoubleList {
    val src = this
    return DoubleList().apply {
        if (src != null)
            addAll(src)
    }
}

fun List<Boolean>?.toBooleanList(): BooleanList {
    val src = this
    return BooleanList().apply {
        if (src != null)
            addAll(src)
    }
}

fun List<String>?.toStringList(): StringList {
    val src = this
    return StringList().apply {
        if (src != null)
            addAll(src)
    }
}

fun IntArray?.toIntList(): IntList {
    val src = this
    return IntList().apply {
        if (src != null)
            addAll(src.toList())
    }
}
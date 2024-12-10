package x.android.commons.`object`

import kotlin.reflect.KProperty

fun interface DelegatedValueProvider<CONTEXT, VALUE> {

    fun CONTEXT.provideValue(): VALUE

    operator fun getValue(context: CONTEXT, property: KProperty<*>) = context.provideValue()
}
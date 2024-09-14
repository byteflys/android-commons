import java.util.LinkedList

typealias NoArgCallbackFunc = () -> Unit
typealias SingleArgCallbackFunc<T> = (T) -> Unit
typealias DoubleArgCallbackFunc<T, R> = (T, R) -> Unit
typealias ThreeArgCallbackFunc<T, R, S> = (T, R, S) -> Unit

val EmptyBiFunction = { o1: Any, o2: Any -> Unit }

class IntList : LinkedList<Int>()

class LongList : LinkedList<Long>()

class FloatList : LinkedList<Float>()

class DoubleList : LinkedList<Double>()

class BooleanList : LinkedList<Boolean>()

class StringList : LinkedList<String>()

class StringMap : LinkedHashMap<String, String>()
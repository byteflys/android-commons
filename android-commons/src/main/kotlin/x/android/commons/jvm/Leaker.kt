package x.android.commons.jvm

import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

object Leaker {

    class NamedWeakReference(obj: Any, val name: String) : WeakReference<Any>(obj, queue)

    private val queue = ReferenceQueue<Any>()
    private val refs = mutableListOf<NamedWeakReference>()

    init {
        thread {
            while (true) {
                Thread.sleep(1000L)
                val ref = queue.poll() as? NamedWeakReference
                if (ref != null) {
                    refs.remove(ref)
                    println("Leaker : ${ref.name} Recycled")
                }
            }
        }
    }

    fun add(obj: Any, name: String = obj.name()) {
        val ref = NamedWeakReference(obj, name)
        refs.add(ref)
    }

    private fun Any.name() = "${javaClass.simpleName}@${hashCode()}"
}
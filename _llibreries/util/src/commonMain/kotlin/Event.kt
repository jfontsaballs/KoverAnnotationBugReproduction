package es.test.util

annotation class Excluded

@Excluded
class MyClass{
    fun MyFun(){

    }
}

@Excluded
class Event<T> {
    private val subscriptions = mutableSetOf<(T) -> Unit>()

    fun subscribe(callback: (T) -> Unit) : () -> Unit {
        subscriptions.add(callback)
        return { unsubscribe(callback) }
    }

    fun unsubscribe(callback: (T) -> Unit){
        subscriptions.remove(callback)
    }

    fun dispatch(value: T) {
        subscriptions.forEach { it(value) }
    }

    operator fun invoke(value: T) = dispatch(value)

    operator fun plusAssign(callback: (T) -> Unit) {
        subscribe(callback)
    }

    operator fun minusAssign(callback: (T) -> Unit) {
        unsubscribe(callback)
    }
}

fun Event<Unit>.dispatch() = dispatch(Unit)

operator fun Event<Unit>.invoke() = this(Unit)
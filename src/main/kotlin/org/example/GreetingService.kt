package org.example

/**
 * A Kotlin service that demonstrates interoperability with Java.
 * This class can be called from Java code seamlessly.
 */
class GreetingService(private val prefix: String = "Hello") {

    /**
     * Returns a greeting message for the given name.
     */
    fun greet(name: String): String {
        return "$prefix, $name!"
    }

    // Ny metod som kan returnera null om namnet är tomt
    fun findCustomGreeting(name: String?): String? {
        if (name.isNullOrBlank()) {
            return null
        }
        return "$prefix, special greeting for $name!"
    }

    /**
     * Returns a list of greetings for multiple names.
     */
    fun greetAll(names: List<String>): List<String> {
        return names.map { greet(it) }
    }
}

/**
 * Top-level function demonstrating Kotlin's ability to define
 * functions outside of classes. Java can call this as a static method
 * on the `GreetingServiceKt` class.
 */
fun formatMessage(template: String, vararg args: Any?): String {
    return template.format(*args)
}

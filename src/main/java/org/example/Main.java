package org.example;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.NullUnmarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Main entry point demonstrating Java calling Kotlin code.
 * The org.example.GreetingService is written in Kotlin but used seamlessly from Java.
 */
@NullMarked
public class Main {

    public static void main(String[] args) {
        // Create a Kotlin service instance from Java
        GreetingService service = new GreetingService("Hello");

        // Call a Kotlin method from Java
        String greeting = service.greet("World");
        IO.println(greeting);

        // Kotlin-strängen är icke-nullbar (String).
        //service.greet(null);

        // 2. Anropa en metod som explicit tillåter och returnerar null (@Nullable)
        String customGreeting = service.findCustomGreeting(null);

        if (customGreeting != null) {
            IO.println(customGreeting);
        } else {
            IO.println("Ingen hälsning hittades (null hanterades säkert).");
        }

        // 3. Skicka en giltig sträng till samma metod
        String validGreeting = service.findCustomGreeting("Martin");
        if (validGreeting != null) {
            IO.println(validGreeting);
        }

        // Call another Kotlin method that returns a List
        List<String> names = List.of("Alice", "Bob", "Charlie");
        List<String> greetings = service.greetAll(names);
        for (String g : greetings) {
            IO.println(g);
        }

        // Call a Kotlin top-level function (exposed as a static method)
        String formatted = GreetingServiceKt.formatMessage("Java and Kotlin work together: %s", "success");
        IO.println(formatted);
    }
}

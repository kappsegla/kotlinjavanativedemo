package org.example;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.nio.file.Path;
/*
The Foreign Function & Memory (FFM) API (Projekt Panama):
Den moderna, rekommenderade standarden från Java 22+.
Den är supersnabb, kräver inga krångliga JNI-huvudfiler (jni.h) och
 låter dig anropa C-funktioner direkt via rena Java-objekt
  (Linker, SymbolLookup och MethodHandle).

JNI (Java Native Interface):
Det traditionella sättet som har funnits sedan Java 1.1.
Det kräver mer boilerplate, native-nyckelord och generering av C-headers.
 */
public class NativeDemo {
    public static void main(String[] args) throws Throwable {
        // 1. Hitta och ladda det delade biblioteket (libmath.so)
        Path libPath = Path.of("libmath.so").toAbsolutePath();
        System.load(libPath.toString());

        // 2. Skapa en Lookup för symboler i biblioteket
        SymbolLookup loader = SymbolLookup.loaderLookup();
        MemorySegment addSymbol = loader.find("add").orElseThrow();

        // 3. Definiera signaturen (returtyp och parametrar)
        Linker linker = Linker.nativeLinker();
        MethodHandle addHandle = linker.downcallHandle(
            addSymbol,
            FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
        );

        // 4. Anrop C-funktionen från Java!
        int result = (int) addHandle.invokeExact(10, 32);
        IO.println("Resultat från C-funktion: " + result); // Skriver ut 42
    }
}

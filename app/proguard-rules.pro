# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
# -keep,allowobfuscation,allowshrinking interface retrofit2.Call
# -keep,allowobfuscation,allowshrinking class retrofit2.Response

 # With R8 full mode generic signatures are stripped for classes that are not
 # kept. Suspend functions are wrapped in continuations where the type argument
 # is used.
#-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# Keep Retrofit classes and methods
-keep class retrofit2.** { *; }
-keepclassmembers class retrofit2.** { *; }
-keepattributes Signature

# Keep OkHttp classes and methods
-keep class okhttp3.** { *; }
-keep class okio.** { *; }

# If you're using Gson for serialization, keep Gson classes
-keep class com.google.gson.** { *; }

# Keep kotlinx.coroutines.* classes
-dontwarn kotlinx.coroutines.**
-keep class kotlin.coroutines.** { *; }
-keep class kotlinx.coroutines.** { *; }

# Keep the @Keep annotations
-keepattributes *Annotation*
-keepclasseswithmembers class * {
    @kotlin.Metadata <methods>;
}

# Keep Parcelize classed
-keep @kotlinx.android.parcel.Parcelize public class *

# Keep app packages and files
-keep class com.flexath.findit.main.data.** { *; }
-keep class com.flexath.findit.main.domain.model.** { *; }
-keep class com.flexath.findit.main.domain.repository.** { *; }
-keep class com.flexath.findit.news.data.** { *; }
-keep class com.flexath.findit.news.domain.model.** { *; }
-keep class com.flexath.findit.news.domain.repository.** { *; }

## Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
## JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
#-keep class * extends com.google.gson.TypeAdapter
#-keep class * implements com.google.gson.TypeAdapterFactory
#-keep class * implements com.google.gson.JsonSerializer
#-keep class * implements com.google.gson.JsonDeserializer
#
# -keep class kotlin.coroutines.** { *; }
# -keepnames class kotlinx.** { *; }
# -keep class org.jetbrains.kotlinx.** { *; }
#
# # ServiceLoader support
# -keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
# -keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
# -keepnames class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
# -keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory {}
#
# -keep class kotlinx.coroutines.internal.MainDispatcherFactory {}
# -keep class kotlinx.coroutines.CoroutineExceptionHandler {}
# -keep class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
# -keep class kotlinx.coroutines.android.AndroidDispatcherFactory {}
# -keep class kotlinx.coroutines.internal.DiagnosticCoroutineContextException {}
#
# # Most of volatile fields are updated with AFU and should not be mangled
# -keepclassmembernames class kotlinx.** {
#     volatile <fields>;
# }
#
# -keep class kotlinx.coroutines.** { *; }
# -keepnames class kotlinx.coroutines.** { *; }
# -keep class kotlin.coroutines.Continuation

#
# # Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
# -keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
# -keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken
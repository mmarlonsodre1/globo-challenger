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

-keepclassmembers enum * { *; }
-dontwarn android.support.v7.**
-dontwarn androidx.**
-keep class android.support.v7.** { *; }
-keep class androidx.** { *; }
-keep interface android.support.v7.** { *; }
-keep interface androidx.** { *; }


-keep class com.example.globo_challenger.model.** { *; }

-keep class org.threeten.bp.** { *; }

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Exceptions, Signature, InnerClasses, LineNumberTable
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

#okhhtp
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.**
-keep interface com.squareup.okhttp3.*
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**

# ConcurrentHashMapSample
samples a broken ConcurrentHashMap when using reflection and coreLibraryDesugaringEnabled on Android

# Stack traces

2020-07-10 09:04:33.095 24107-24107/com.marandaneto.concurrenthashmapsample E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.marandaneto.concurrenthashmapsample, PID: 24107
    java.lang.RuntimeException: Unable to start activity ComponentInfo{com.marandaneto.concurrenthashmapsample/com.marandaneto.concurrenthashmapsample.MainActivity}: java.lang.ArrayIndexOutOfBoundsException: length=0; index=0
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3270)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3409)
        at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:83)
        at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:135)
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:95)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2016)
        at android.os.Handler.dispatchMessage(Handler.java:107)
        at android.os.Looper.loop(Looper.java:214)
        at android.app.ActivityThread.main(ActivityThread.java:7356)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:930)
     Caused by: java.lang.ArrayIndexOutOfBoundsException: length=0; index=0
        at com.google.gson.internal.$Gson$Types.getGenericSupertype(:248)
        at com.google.gson.internal.$Gson$Types.getSupertype(:284)
        at com.google.gson.internal.$Gson$Types.getMapKeyAndValueTypes(:327)
        at com.google.gson.internal.bind.MapTypeAdapterFactory.create(:124)
        at com.google.gson.Gson.getDelegateAdapter(:541)
        at com.google.gson.internal.bind.TreeTypeAdapter.delegate(:89)
        at com.google.gson.internal.bind.TreeTypeAdapter.write(:74)
        at com.google.gson.internal.bind.TypeAdapterRuntimeTypeWrapper.write(:69)
        at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$1.write(:127)
        at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.write(:245)
        at com.marandaneto.concurrenthashmapsample.UnknownPropertiesTypeAdapterFactory$UnknownPropertiesTypeAdapter.write(:102)
        at com.marandaneto.concurrenthashmapsample.UnknownPropertiesTypeAdapterFactory$UnknownPropertiesTypeAdapter.write(:57)
        at com.google.gson.Gson.toJson(:704)
        at com.google.gson.Gson.toJson(:683)
        at com.google.gson.Gson.toJson(:638)
        at com.marandaneto.concurrenthashmapsample.MainActivity.onCreate(:38)
        at android.app.Activity.performCreate(Activity.java:7825)
        at android.app.Activity.performCreate(Activity.java:7814)
        at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1306)
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3245)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3409) 
        at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:83) 
        at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:135) 
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:95) 
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2016) 
        at android.os.Handler.dispatchMessage(Handler.java:107) 
        at android.os.Looper.loop(Looper.java:214) 
        at android.app.ActivityThread.main(ActivityThread.java:7356) 
        at java.lang.reflect.Method.invoke(Native Method) 
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492) 
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:930) 
        
# Findings

https://github.com/google/gson/blob/9d44cbc19a73b45971c4ecb33c8d34d673afa210/gson/src/main/java/com/google/gson/internal/%24Gson%24Types.java#L248

`rawType.getGenericInterfaces()` returns an empty array but `[i]` try to access the pos 0.

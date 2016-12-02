package com.grab.driver.lint;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.ClassContext;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yongkai.teo on 2/12/16.
 */

public class SystemTimeDetector extends Detector implements Detector.JavaScanner, Detector.ClassScanner {
    public static final Issue ISSUE = Issue.create("SystemTimeNotUsed",
            "You must use our `SystemTime`",
            "System.currentTimeMillis and SystemClock.elapsedRealtime should be avoided as they do not calculate the drift",
            Category.MESSAGES,
            9,
            Severity.ERROR,
            new Implementation(SystemTimeDetector.class,
                    Scope.CLASS_FILE_SCOPE));

    @Override
    public List<String> getApplicableCallNames() {
        return Arrays.asList("currentTimeMillis", "elapsedRealtime");
    }

    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("currentTimeMillis", "elapsedRealtime");
    }

    @Override
    public void checkCall(@NonNull ClassContext context,
                          @NonNull ClassNode classNode,
                          @NonNull MethodNode method,
                          @NonNull MethodInsnNode call) {
        String owner = call.owner;
        if (owner.startsWith("java/lang/System")) {
            context.report(ISSUE,
                    method,
                    call,
                    context.getLocation(call),
                    "You must use our `SystemTime`");
        }

        if (owner.startsWith("android/os/SystemClock")) {
            context.report(ISSUE,
                    method,
                    call,
                    context.getLocation(call),
                    "You must use our `SystemTime`");
        }
    }

}
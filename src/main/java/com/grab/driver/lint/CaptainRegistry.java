package com.grab.driver.lint;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Collections;
import java.util.List;

/**
 * Created by yongkai.teo on 2/12/16.
 */

public final class CaptainRegistry extends IssueRegistry {
    @Override
    public List<Issue> getIssues() {
        return Collections.singletonList(SystemTimeDetector.ISSUE);
    }
}
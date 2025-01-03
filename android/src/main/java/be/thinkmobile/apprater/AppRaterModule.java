package be.thinkmobile.apprater;

import android.app.Activity;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;

import androidx.annotation.NonNull;
import android.util.Log;

@ReactModule(name = AppRaterModule.NAME)
public class AppRaterModule extends ReactContextBaseJavaModule {
    public static final String NAME = "AppRater";
    public static final String TAG = "AppRaterModule";
    
    private final ReactApplicationContext reactContext;

    public AppRaterModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void requestReview() {
        Activity activity = getCurrentActivity();
        if (activity == null) return;

        ReviewManager manager = ReviewManagerFactory.create(activity);
        Log.d(TAG, "requestReview");
        com.google.android.gms.tasks.Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ReviewInfo reviewInfo = task.getResult();
                com.google.android.gms.tasks.Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
                flow.addOnCompleteListener(flowTask -> {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown.
                    Log.d(TAG, "requestReview success");
                });
            } else {
                Log.d(TAG, "Could not request review");
            }
        });
    }
} 
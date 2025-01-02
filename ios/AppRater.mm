#import "AppRater.h"
#import <StoreKit/StoreKit.h>

@implementation AppRater

RCT_EXPORT_MODULE()

-(std::shared_ptr<facebook::react::TurboModule>)getTurboModule:(const facebook::react::ObjCTurboModule::InitParams &)params {
    return std::make_shared<facebook::react::NativeAppRaterSpecJSI>(params);
}

- (void)requestReview
{
    if (@available(iOS 16.0, *)) {
        [SKStoreReviewController requestReviewInScene:UIApplication.sharedApplication.connectedScenes.allObjects.firstObject];
    } else if (@available(iOS 14.0, *)) {
        [SKStoreReviewController requestReview];
    }
}

@end

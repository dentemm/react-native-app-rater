#import "AppRater.h"
#import <StoreKit/StoreKit.h>

@implementation AppRater

- (void)requestReview
{
    if (@available(iOS 16.0, *)) {
        [SKStoreReviewController requestReviewInScene:UIApplication.sharedApplication.connectedScenes.allObjects.firstObject];
    } else if (@available(iOS 14.0, *)) {
        [SKStoreReviewController requestReview];
    }
}

@end

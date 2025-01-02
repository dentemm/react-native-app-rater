#import <AppRaterSpec/AppRaterSpec.h>
#import <Foundation/Foundation.h>
#import <StoreKit/StoreKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface AppRater : NSObject <NativeAppRaterSpec>

- (void)requestReview;

@end

NS_ASSUME_NONNULL_END

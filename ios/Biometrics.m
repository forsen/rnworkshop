//
//  Biometrics.m
//  rnworkshop
//
//  Created by Erik Haider Forsén on 12/08/2019.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "React/RCTBridgeModule.h"

@interface RCT_EXTERN_MODULE(Biometrics, NSObject)

RCT_EXTERN_METHOD(
                  isSupported: (RCTPromiseResolveBlock)resolve
                  rejecter: (RCTPromiseRejectBlock)reject
                  )
RCT_EXTERN_METHOD(
                  authenticate: (RCTPromiseResolveBlock)resolve
                  rejecter: (RCTPromiseRejectBlock)reject
                  )

@end

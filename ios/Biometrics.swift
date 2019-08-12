//
//  Biometrics.swift
//  rnworkshop
//
//  Created by Erik Haider Forsén on 12/08/2019.
//  Copyright © 2019 Facebook. All rights reserved.
//

import Foundation
import LocalAuthentication

class Biometrics: NSObject {
  
  func isSupported() -> Void {
    let context = LAContext()
    var authError: NSError?
    
    if (context.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &authError)) {
      // should probably let react native know
      
      print("Yes, it is supported!")
    } else {
      var errorReason: String
      
      guard let error = authError as NSError? else {
        // should probably let react native know that biometrics is not available on this device
        return
      }
      
      switch (error.code) {
      case LAError.touchIDNotAvailable.rawValue:
        errorReason = "BiometricsNotAvailable";
        break;
        
      case LAError.touchIDNotEnrolled.rawValue:
        errorReason = "BiometricsNotEnrolled";
        
      default:
        errorReason = "BiometricsNotSupported";
        break;
      }
      print(errorReason)
      // should probably let react native know why biometrics would not work
      return;
    }
  }
  
  @objc
  func authenticate() -> Void {
    let context = LAContext()
    
    var authError: NSError?
    
    if context.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &authError) {
      context.evaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, localizedReason: "For moro skyld") { success, evaluateError in
        if success {
          // successfully validated biometrics, should we let react native know?
          print("Successfully validated")
        }
        
        guard let error = evaluateError as NSError? else {
          return
        }
        
        var errorReason: String?
        
        switch error.code {
        case LAError.authenticationFailed.rawValue:
          errorReason = "AuthenticationFailed";
          break;
          
        case LAError.userCancel.rawValue:
          errorReason = "UserCancel";
          break;
          
        case LAError.userFallback.rawValue:
          errorReason = "UserFallback";
          break;
          
        case LAError.systemCancel.rawValue:
          errorReason = "SystemCancel";
          break;
          
        case LAError.passcodeNotSet.rawValue:
          errorReason = "PasscodeNotSet";
          break;
          
        case LAError.touchIDNotAvailable.rawValue:
          errorReason = "BiometricsNotAvailable";
          break;
          
        case LAError.touchIDNotEnrolled.rawValue:
          errorReason = "BiometricsNotEnrolled";
          break;
          
        default:
          errorReason = "BiometricsUnknownError";
          break;
        }
        
        print(errorReason!)
        
        // Let react native know the error reason
        return;
      }
    } else {
      print("authentication failed")
    }
  }
}



package com.rnworkshop

import android.util.Log
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import java.util.concurrent.Executors

class BiometricsModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
	private val promptInfo = BiometricPrompt.PromptInfo.Builder()
			.setTitle("Biometri")
			.setNegativeButtonText("Avbryt")
			.build()

	@ReactMethod
	fun authenticate(promise: Promise) {
		val activity = currentActivity
		if (activity is FragmentActivity) {
			val biometricPrompt = BiometricPrompt(activity, Executors.newSingleThreadExecutor(), object : BiometricPrompt.AuthenticationCallback() {
				override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
					super.onAuthenticationSucceeded(result)
					Log.i("Biometrics", "Biometrics authenticated successfully")
					promise.resolve("Successfully Authenticated")
				}

				override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
					super.onAuthenticationError(errorCode, errString)
					Log.i("Biometrics", "Authentication Error")
					promise.reject(errString.toString(), "Authentication Error")
				}

				override fun onAuthenticationFailed() {
					super.onAuthenticationFailed()
					Log.i("Biometrics", "Authentication Failed")
					promise.reject("Authentication Failed", "Authentication Failed")
				}
			})

			biometricPrompt.authenticate(promptInfo)
		}
	}

	@ReactMethod
	override fun getName(): String {
		return "Biometrics"
	}
}

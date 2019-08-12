package com.rnworkshop

import android.util.Log
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import java.util.concurrent.Executors

class BiometricsModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
	private val promptInfo = BiometricPrompt.PromptInfo.Builder()
			.setTitle("Biometri")
			.setNegativeButtonText("Avbryt")
			.build()

	fun authenticate() {
		val activity = currentActivity
		if (activity is FragmentActivity) {
			val biometricPrompt = BiometricPrompt(activity, Executors.newSingleThreadExecutor(), object : BiometricPrompt.AuthenticationCallback() {
				override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
					super.onAuthenticationSucceeded(result)
					Log.i("Biometrics", "Biometrics authenticated successfully")
				}

				override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
					super.onAuthenticationError(errorCode, errString)
					Log.i("Biometrics", "Authentication Error")
				}

				override fun onAuthenticationFailed() {
					super.onAuthenticationFailed()
					Log.i("Biometrics", "Authentication Failed")
				}
			})

			biometricPrompt.authenticate(promptInfo)
		}
	}

	override fun getName(): String {
		return "Biometrics"
	}
}

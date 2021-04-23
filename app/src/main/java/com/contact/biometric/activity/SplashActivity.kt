package com.contact.biometric.activity

import android.app.KeyguardManager
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.contact.biometric.R
import java.util.concurrent.Executor


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        fingerprint()

        }


    private fun fingerprint() {
        val executor = ContextCompat.getMainExecutor(this)
        val biometricManager = BiometricManager.from(this)

        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                authUser(executor)
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                PasscodeVerification();
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                PasscodeVerification();
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                PasscodeVerification();
        }

    }

    private fun PasscodeVerification() {
        val km = getSystemService(KEYGUARD_SERVICE) as KeyguardManager

        if (km.isKeyguardSecure) {
            val authIntent = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                km.createConfirmDeviceCredentialIntent(
                    getString(R.string.dialog_title_auth),
                    getString(R.string.dialog_msg_auth)
                )
            } else {
                TODO("VERSION.SDK_INT < LOLLIPOP")
            }
            startActivityForResult(authIntent, 1)
        }
    }

    private fun authUser(executor: Executor) {
// 1
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
// 2
            .setTitle("Authentication Required!")
// 3
            .setSubtitle("Important authentication")
// 4
            .setDescription("Please use fingerprint to be able to login your account")
// 5
            .setDeviceCredentialAllowed(true)
// 6
            .build()
// 1
        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                // 2
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
           NextActivity()
                    super.onAuthenticationSucceeded(result)

                }

                // 3
                override fun onAuthenticationError(
                    errorCode: Int, errString: CharSequence
                ) {
                    finish()
                    super.onAuthenticationError(errorCode, errString)
//                    Toast.makeText(requireContext(), errString, Toast.LENGTH_SHORT).show()
                }

                // 4
                override fun onAuthenticationFailed() {

                    super.onAuthenticationFailed()

                }
            })
        biometricPrompt.authenticate(promptInfo)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
          NextActivity()


            }else{
                finishAffinity()
            }
        }
    }

    private fun NextActivity() {

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 2000)
    }
}
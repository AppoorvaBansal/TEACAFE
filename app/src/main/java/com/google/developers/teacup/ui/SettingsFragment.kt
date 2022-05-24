package com.google.developers.teacup.ui

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.google.developers.teacup.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(bundle: Bundle?, s: String?) {
        setPreferencesFromResource(R.xml.preferences, s)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        val notificationKey = getString(R.string.pref_notification_key)
        if (preference?.key == notificationKey) {
            val on = (preference as SwitchPreference).isChecked
        }
        return super.onPreferenceTreeClick(preference)
    }

    companion object {
        const val WORKER_TAG = "notification_worker"
    }
}

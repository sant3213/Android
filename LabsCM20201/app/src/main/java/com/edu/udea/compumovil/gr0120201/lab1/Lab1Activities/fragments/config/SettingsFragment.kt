package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.utils.Prefs
import com.edu.udea.compumovil.gr0120201.lab1.R
import kotlinx.android.synthetic.main.fragment_settings.view.*


class SettingsFragment : Fragment() {

    companion object{
        lateinit var prefs: Prefs
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val NOTIFICATION_STATE = "notificationState"
        val NOTIFICATION_SOUND_STATE = "notificationSoundState"
        prefs = Prefs(requireContext().applicationContext)

        // Initialize check states
        val notificationCheckBox = view.findViewById(R.id.notifications) as CheckBox
        notificationCheckBox.isChecked = prefs.getPrefState(requireContext().applicationContext, NOTIFICATION_STATE)

        val notificationSoundCheckBox = view.findViewById(R.id.notifications_sound) as CheckBox
        notificationSoundCheckBox.isChecked = prefs.getPrefState(requireContext().applicationContext,NOTIFICATION_SOUND_STATE)


        view.notifications.setOnClickListener(View.OnClickListener {
            saveNotificationState(view, NOTIFICATION_STATE)
        })

        view.notifications_sound.setOnClickListener(View.OnClickListener {
            saveNotificationState(view, NOTIFICATION_SOUND_STATE)
        })

        return view
    }

    fun saveNotificationState(view: View, NOTIFICATION_STATE: String){
        if(view.notifications.isChecked)
            prefs.setPrefState(
                requireContext().applicationContext,
                NOTIFICATION_STATE,
                true
            )
        else
            prefs.setPrefState(
                requireContext().applicationContext,
                NOTIFICATION_STATE,
                false
            )
    }

    fun saveNotificationSoundState(view: View, NOTIFICATION_SOUND_STATE: String){
        if(view.notifications.isChecked)
            prefs.setPrefState(
                requireContext().applicationContext,
                NOTIFICATION_SOUND_STATE,
                true
            )
        else
            prefs.setPrefState(
                requireContext().applicationContext,
                NOTIFICATION_SOUND_STATE,
                false
            )
    }
}

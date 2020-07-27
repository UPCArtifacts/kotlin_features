package apps.jizzu.simpletodo.ui.view.settings.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import apps.jizzu.simpletodo.R
import apps.jizzu.simpletodo.ui.view.settings.fragment.base.BaseSettingsFragment
import apps.jizzu.simpletodo.utils.PreferenceHelper
import daio.io.dresscode.dressCodeStyleId
import kotlinx.android.synthetic.main.fragment_user_interface.*

class FragmentUI : BaseSettingsFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_interface, container, false)
    }

    override fun onResume() {
        super.onResume()
        setTitle(getString(R.string.settings_page_title_user_interface))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnimationSwitch()
    }

    private fun initAnimationSwitch() {
        val preferenceHelper = PreferenceHelper.getInstance() //#inference

        swAnimation.setOnTouchListener { _, event -> event.actionMasked == MotionEvent.ACTION_MOVE } //#lambda
        swAnimation.isChecked = preferenceHelper.getBoolean(PreferenceHelper.ANIMATION_IS_ON)

        swAnimation.setOnClickListener { //#lambda
            preferenceHelper.putBoolean(PreferenceHelper.ANIMATION_IS_ON, swAnimation.isChecked)
        }

        clAnimations.setOnClickListener { //#lambda
            swAnimation.isChecked = !swAnimation.isChecked
            preferenceHelper.putBoolean(PreferenceHelper.ANIMATION_IS_ON, swAnimation.isChecked)
        }

        clChooseTheme.setOnClickListener { //#lambda
            val listItems = resources.getStringArray(R.array.app_theme) //#inference
            var selectedItemPosition = when (activity?.dressCodeStyleId) { //#inference,when_expr,safe_call
                R.style.AppTheme_Light -> 0
                R.style.AppTheme_Dark -> 1
                R.style.AppTheme_Black -> 2
                else -> 0
            }

            val builder = when (activity?.dressCodeStyleId) { //#inference,when_expr,safe_call
                R.style.AppTheme_Light -> AlertDialog.Builder(activity as Context, R.style.AlertDialogStyle_Light)
                R.style.AppTheme_Dark -> AlertDialog.Builder(activity as Context, R.style.AlertDialogStyle_Dark)
                else -> AlertDialog.Builder(activity as Context, R.style.AlertDialogStyle_Dark)
            }
            builder.apply { //#lambda
                setTitle(getString(R.string.app_theme_dialog_title))
                setSingleChoiceItems(listItems, selectedItemPosition) { dialogInterface, i -> //#lambda
                    selectedItemPosition = i

                    activity?.dressCodeStyleId = when (selectedItemPosition) { //#safe_call,when_expr,lambda
                        0 -> R.style.AppTheme_Light
                        1 -> R.style.AppTheme_Dark
                        2 -> R.style.AppTheme_Black
                        else -> R.style.AppTheme_Light
                    }
                    isThemeChanged = true
                    dialogInterface.dismiss()
                }
            }
            builder.create().apply { //#lambda
                window?.attributes?.windowAnimations = R.style.DialogAnimation //#safe_call,safe_call
                show()
                window?.setLayout(resources.getDimensionPixelSize(R.dimen.dialog_picker_width), ViewGroup.LayoutParams.WRAP_CONTENT) //#safe_call
            }
        }
    }

    companion object { //#companion
        var isThemeChanged = false //#inference
    }
}

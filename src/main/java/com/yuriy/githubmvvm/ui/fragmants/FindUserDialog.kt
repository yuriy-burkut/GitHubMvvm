package com.yuriy.githubmvvm.ui.fragmants

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.DialogFragment
import com.yuriy.githubmvvm.R
import kotlinx.android.synthetic.main.find_user_dialog.view.*

class FindUserDialog(private val callback: InputDialogListener) : DialogFragment() {

    interface InputDialogListener {
        fun findUser(username: String)
    }

    private var inputString: String = ""
    private lateinit var mListener: InputDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.find_user_dialog, null)

        view.et_user_input_field.editText?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                callback.findUser(v.text.toString())
                this.dialog?.dismiss()
            }
            return@setOnEditorActionListener false
        }

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(view)
            .setPositiveButton("FIND") { dialog, _ ->
                inputString = view.et_user_input_field.editText?.text.toString()
                callback.findUser(inputString)
            }
            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mListener = requireActivity() as InputDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(requireActivity().toString() + "must implement InputDialogListener")
        }
    }

}

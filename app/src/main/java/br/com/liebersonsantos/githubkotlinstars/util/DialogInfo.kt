package br.com.liebersonsantos.githubkotlinstars.util

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import br.com.liebersonsantos.githubkotlinstars.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Created by lieberson on 12/26/21.
 * @author lieberson.xsantos@gmail.com
 */
class DialogInfo : DialogFragment() {
    private var okListener: (() -> Unit)? = null
    fun okListener(listener: () -> Unit) { okListener = listener }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            .setTitle(getString(R.string.internet))
            .setMessage(getString(R.string.check_your_internet))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                okListener?.let { ok ->
                    ok()
                }
            }
            .create()
}

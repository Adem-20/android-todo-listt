package com.example.yaplacaklarlistesi.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.yaplacaklarlistesi.databinding.BottomSheetBinding

class NewPlanBottomSheet : BottomSheetDialogFragment() {
    //Guncellenek degişkenler
    var defaultTitle: String? = null
    var defaultDescription: String? = null
    //Kaydetme callbacki
    var onSave : ((String,String)->Unit)? = null

    private var _binding: BottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       //varsayılan değerler
        binding.editTextTitle.setText(defaultTitle)
        binding.editTextDescription.setText(defaultDescription)

        binding.buttonKaydet.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            val description  = binding.editTextDescription.text.toString().trim()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                onSave?.invoke(title, description)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
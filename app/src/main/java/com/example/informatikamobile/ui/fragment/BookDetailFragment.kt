package com.example.informatikamobile.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.informatikamobile.R
import com.example.informatikamobile.databinding.FragmentBookDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// Kode template yang tidak terpakai sudah dihapus untuk kebersihan

class BookDetailFragment(
    private val title: String,
    private val author: String,
    private val year: String,
    private val coverId: Int
) : BottomSheetDialogFragment() {
    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!

    override fun getTheme(): Int = R.style.ThemeOverlay_App_BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // PERBAIKAN: Menghilangkan nama parameter 'parent' dan 'attachToParent'
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadData() {
        Toast.makeText(context, "$coverId", Toast.LENGTH_SHORT).show()

        binding.textViewTitle.text = title
        binding.textViewAuthor.text = author
        binding.textViewYear.text = year

        if (coverId != 0) {
            val url = "https://covers.openlibrary.org/b/id/$coverId-M.jpg"

            // PERBAIKAN: Menghilangkan nama parameter pada Glide
            Glide.with(this)
                .load(url)
                .into(binding.imageViewCover)
        } else {
            // PERBAIKAN: Pastikan 'book_not_found.png' ada di folder res/drawable
            binding.imageViewCover.setImageResource(
                R.drawable.book_not_found
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }
}

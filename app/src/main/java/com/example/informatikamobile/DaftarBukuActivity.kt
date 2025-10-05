package com.example.informatikamobile

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.informatikamobile.data.model.BookDoc
import com.example.informatikamobile.databinding.ActivityDaftarBukuBinding
import com.example.informatikamobile.ui.adapter.BookAdapter
import com.example.informatikamobile.ui.adapter.OnBookClickListener
import com.example.informatikamobile.ui.fragment.BookDetailFragment
import com.example.informatikamobile.viewmodel.MainViewModel

class DaftarBukuActivity : AppCompatActivity(), OnBookClickListener {

    private lateinit var binding: ActivityDaftarBukuBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // PERBAIKAN 1: Tambahkan 'this' sebagai parameter kedua untuk listener
        adapter = BookAdapter(emptyList(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.books.observe(this, Observer { books ->
            // Anda mungkin perlu mengubah cara setData jika konstruktor adapter berubah
            // Jika BookAdapter sekarang mengelola listnya sendiri, baris ini mungkin perlu diubah/dihapus
            // tergantung implementasi BookAdapter.kt Anda.
            adapter.setData(books)
        })

        viewModel.fetchBooks("kotlin programming")
    }

    override fun onBookClick(book: BookDoc) {
        book.let { b ->
            BookDetailFragment(
                title = b.title ?: "-",
                author = b.authorName?.joinToString(separator = ", ") ?: "Unknown Author",
                year = "${b.firstPublishYear}",
                coverId = b.coverId ?: 0
                // PERBAIKAN 2: Hilangkan nama parameter 'tag'
            ).show(supportFragmentManager, BookDetailFragment::class.java.simpleName)
        }
    }
}

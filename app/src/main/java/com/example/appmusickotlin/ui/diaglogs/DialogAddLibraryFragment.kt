package com.example.appmusickotlin.ui.diaglogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.appmusickotlin.R
import com.example.appmusickotlin.databinding.FragmentDialogLibraryAddBinding
import com.example.appmusickotlin.ui.home.Fragment.PlayListsFragment
import com.example.appmusickotlin.ui.home.HomeScreenActivity


class DialogAddLibraryFragment : DialogFragment(){

    private lateinit var binding: FragmentDialogLibraryAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogLibraryAddBinding.inflate(layoutInflater)

        binding.btnAddmusic.setOnClickListener {
            // Tạo một instance của Fragment mới (NewFragment)
            // Truy cập Activity chứa BottomNavigationView
            val activity = requireActivity()

            // Kiểm tra xem Activity có phải là MainActivity không (đặt tên Activity của bạn thích hợp)
            if (activity is HomeScreenActivity) {
                // Gọi phương thức trong MainActivity để chuyển Fragment trong BottomNavigationView
            }
            // Đóng DialogFragment sau khi chuyển sang Fragment mới (tuỳ chọn)
            dismiss()
        }
        return  binding.root

    }

}
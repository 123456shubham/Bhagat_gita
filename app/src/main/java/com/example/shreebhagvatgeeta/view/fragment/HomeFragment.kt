package com.example.shreebhagvatgeeta.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shreebhagvatgeeta.R
import com.example.shreebhagvatgeeta.adapter.ChapterAdapter
import com.example.shreebhagvatgeeta.databinding.FragmentHomeBinding
import com.example.shreebhagvatgeeta.model.ChapterResponseItem
import com.example.shreebhagvatgeeta.utils.CommonFunction.changeStatusBarColour
import com.example.shreebhagvatgeeta.viewModel.MainViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private val mainViewModel : MainViewModel by lazy {
        MainViewModel()
    }
    private  lateinit var  homeBinding : FragmentHomeBinding
    private lateinit var chapterAdapter: ChapterAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        changeStatusBarColour(requireActivity(), R.color.splash, isLightStatusBar = true)
        getAllChapters()

        return homeBinding.root
    }


    private fun getAllChapters(){
        homeBinding.homeShimer.visibility=View.VISIBLE
        homeBinding.homeRv.setHasFixedSize(false)
        homeBinding.homeRv.layoutManager=LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)

        lifecycleScope.launch {
            mainViewModel.getAllChapters().collect{chapterList->
                homeBinding.homeRv.visibility=View.VISIBLE

                chapterAdapter=ChapterAdapter(::onChapterClicked,requireActivity())
                homeBinding.homeRv.adapter=chapterAdapter
                chapterAdapter.differ.submitList(chapterList)
                homeBinding.homeShimer.visibility=View.GONE


            }
        }
    }
    fun onChapterClicked(chapterResponseItem: ChapterResponseItem){
        val bundle=Bundle()
        bundle.putInt("chapterNo", chapterResponseItem.chapter_number!!)
        bundle.putString("title",chapterResponseItem.name_translated)
        bundle.putString("des",chapterResponseItem.chapter_summary)
        bundle.putInt("count",chapterResponseItem.verses_count!!)
        findNavController().navigate(R.id.action_homeFragment_to_verseFragment,bundle)
   }
}
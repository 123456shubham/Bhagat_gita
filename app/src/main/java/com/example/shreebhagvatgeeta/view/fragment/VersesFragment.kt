package com.example.shreebhagvatgeeta.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shreebhagvatgeeta.R
import com.example.shreebhagvatgeeta.adapter.VersesAdapter
import com.example.shreebhagvatgeeta.databinding.FragmentVersesBinding
import com.example.shreebhagvatgeeta.utils.CommonFunction
import com.example.shreebhagvatgeeta.viewModel.MainViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class VersesFragment : Fragment() {
    private  var count=0;
    private val mainViewModel : MainViewModel by lazy {
        MainViewModel()
    }
    private lateinit var versesBinding: FragmentVersesBinding
    private lateinit var versesAdapter:VersesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        versesBinding = FragmentVersesBinding.inflate(inflater, container, false)
        CommonFunction.changeStatusBarColour(
            requireActivity(),
            R.color.splash,
            isLightStatusBar = true
        )

        getAndSet()
        getAllVerses()
        readMoreBtn()
        return versesBinding.root;
    }

    private fun getAndSet(){


        val bundle=arguments

        count=bundle!!.getInt("chapterNo")
        versesBinding.versesChapterCount.text= "Chapter "+bundle!!.getInt("chapterNo").toString()
        versesBinding.versesChapterTitle.text=bundle!!.getString("title")
        versesBinding.versesMsg.text=bundle.getString("des")
        versesBinding.chapterCountVerses.text=bundle.getInt("count").toString()

    }

    private fun getAllVerses(){
        versesBinding.verseRv.setHasFixedSize(false)
        versesBinding.verseRv.layoutManager=LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)

        versesBinding.verseShimer.visibility=View.VISIBLE


        lifecycleScope.launch {
            Log.d("VersesFragment", "Calling API with count: $count")

            try {
                mainViewModel.allVerses(count).collect {verses->
                    Log.d("VersesFragment", "Received response: $verses")

                    versesBinding.verseRv.visibility=View.VISIBLE
                    versesBinding.verseShimer.visibility=View.GONE
                    print("Verses List $count")

                    versesAdapter= VersesAdapter(::versesItemClicked)
                    versesBinding.verseRv.adapter=versesAdapter

                    val verseList= arrayListOf<String>()

                    for (currentVerse in verses){
                        for(verses in currentVerse.translations!!){
                            if (verses?.language=="english"){
                                verseList.add(verses.description.toString())
                                break
                            }
                        }
                    }

                    versesAdapter!!.differ.submitList(verseList)
                }

            }catch (e:Exception){
                print("Verses "+e.message)
            }




        }
    }

    fun readMoreBtn(){
        var isExpendable=false
        versesBinding.versesRemore.setOnClickListener{

            if (!isExpendable){
                versesBinding.versesMsg.maxLines=50
                isExpendable=true
            }else{
                versesBinding.versesMsg.maxLines=4
                isExpendable=false
            }
        }
    }

    fun versesItemClicked(verses:String,pos:Int){

        val bundle=Bundle()
        bundle.putInt("chapterNo",count)
        bundle.putInt("verseNo",pos)


        findNavController().navigate(R.id.action_verseFragment_to_verseDetailsFragment,bundle)
    }


}
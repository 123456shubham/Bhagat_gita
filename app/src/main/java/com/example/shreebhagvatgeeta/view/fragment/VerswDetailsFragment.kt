package com.example.shreebhagvatgeeta.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.shreebhagvatgeeta.R
import com.example.shreebhagvatgeeta.databinding.FragmentVerswDetailsBinding
import com.example.shreebhagvatgeeta.model.verse.Commentary
import com.example.shreebhagvatgeeta.model.verse.Translation
import com.example.shreebhagvatgeeta.utils.CommonFunction
import com.example.shreebhagvatgeeta.viewModel.MainViewModel
import kotlinx.coroutines.launch

class VerswDetailsFragment : Fragment() {


    private lateinit var binding:FragmentVerswDetailsBinding
    private  var chapterNumber=0;
    private  var versesNumber=0;
    private val mainViewModel : MainViewModel by lazy {
        MainViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentVerswDetailsBinding.inflate(inflater,container,false)
        CommonFunction.changeStatusBarColour(
            requireActivity(),
            R.color.splash,
            isLightStatusBar = true
        )

        getAndSetChapterVersion()
        versesDetails()
        return binding.root
    }

    fun getAndSetChapterVersion(){

        val bundle:Bundle?=arguments
        chapterNumber=bundle!!.getInt("chapterNo")
        versesNumber=bundle.getInt("verseNo")

        binding.versesNumber.setText("|| ${chapterNumber}.${versesNumber} ||")

    }

    fun versesDetails(){

        binding.verseDetailsShimer.visibility=View.VISIBLE

        lifecycleScope.launch{
            mainViewModel.versesDetails(chapterNumber,versesNumber).collect{verses->
                binding.verseDetailsShimer.visibility=View.GONE
                binding.viewseDetailScrool.visibility=View.VISIBLE

                binding.verseSlok.text=verses.text
                binding.verseMeaningOne.text=verses.transliteration
                binding.verseMeaningTwo.text=verses.word_meanings
                val englishList= arrayListOf<Translation>()

                for (i in verses.translations){

                    if (i?.language=="hindi"){
                        englishList.add(i)
                    }
                    val englishTranslationSize=englishList.size

                    if (englishList.isNotEmpty()){
                        binding.verseTranslationAuthor.text="Author : "+englishList[0].author_name
                        binding.verseTrasnlation.text=englishList[0].description

                        if (englishTranslationSize==1){
                            binding.verseForwardTranslation.visibility=View.GONE
                        }

                        var i=0;
                        binding.verseForwardTranslation.setOnClickListener {

                            if (i<englishTranslationSize-1){
                                i++
                                binding.verseTranslationAuthor.text="Author : "+englishList[i].author_name
                                binding.verseTrasnlation.text=englishList[i].description
                                binding.vereseBackwordTranslation.visibility=View.VISIBLE

                                if (i==englishTranslationSize-1){
                                    binding.verseForwardTranslation.visibility=View.GONE
                                }
                            }
                        }
                        binding.vereseBackwordTranslation.setOnClickListener {
                            if (i>0){
                                i--
                                binding.verseTranslationAuthor.text="Author : "+englishList[i].author_name
                                binding.verseTrasnlation.text=englishList[i].description
                                binding.verseForwardTranslation.visibility=View.VISIBLE
                                if (i==0){
                                    binding.vereseBackwordTranslation.visibility=View.GONE
                                }
                            }
                        }



                    }
                }


                val englishComentry= arrayListOf<Commentary>()

                for (j in verses.commentaries){
                    if (j?.language=="hindi"){
                        englishComentry.add(j)
                    }

                    val englishComentrySize=englishComentry.size
                    if (englishComentry.isNotEmpty()){
                        binding.versesComentryAuthor.text="Author : "+englishComentry[0].author_name
                        binding.versesComentry.text=englishComentry[0].description

                        if (englishComentrySize==1){
                            binding.verseForwardCommentry.visibility=View.GONE
                        }

                        var i=0;
                        binding.verseForwardCommentry.setOnClickListener {

                            if (i<englishComentrySize-1){
                                i++
                                binding.versesComentryAuthor.text="Author : "+englishComentry[i].author_name
                                binding.versesComentry.text=englishComentry[i].description
                                binding.verseBackwordCommentry.visibility=View.VISIBLE

                                if (i==englishComentrySize-1){
                                    binding.verseForwardCommentry.visibility=View.GONE
                                }
                            }
                        }
                        binding.verseBackwordCommentry.setOnClickListener {
                            if (i>0){
                                i--
                                binding.versesComentryAuthor.text="Author : "+englishComentry[i].author_name
                                binding.versesComentry.text=englishComentry[i].description
                                binding.verseForwardCommentry.visibility=View.VISIBLE
                                if (i==0){
                                    binding.verseBackwordCommentry.visibility=View.GONE
                                }
                            }
                        }



                    }
                }

            }

        }
    }

}
package com.example.app1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import com.example.app1.databinding.FragmentInputBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "number1"
//private const val ARG_PARAM2 = "number2"
//private const val ARG_PARAM3 = "number3"


/**
 * A simple [Fragment] subclass.
 * Use the [InputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class InputFragment : Fragment() {
    var binding: FragmentInputBinding? = null
    // TODO: Rename and change types of parameters
    private var number1: Int = 0
    private var number2: Int = 0
    private var number3: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            number1 = it.getInt("number1", 0)  //"number1"
            number2 = it.getInt("number2", 0)
            number3 = it.getInt("number3", 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding?.edtNumber1?.setText(number1.toString())   //maybe get number from frame
        binding?.edtNumber2?.setText(number2.toString())   //binding?.edtNumber2?.setText(number2)
        binding?.edtNumber3?.setText(number3.toString())

        binding?.edtNumber1?.setOnKeyListener { view, i, keyEvent ->    // need ramda parameter?
            setBundle()
            false
        }
        binding?.edtNumber2?.setOnKeyListener { view, i, keyEvent ->
            setBundle()
            false
        }
        binding?.edtNumber3?.setOnKeyListener { view, i, keyEvent ->
            setBundle()
            false
        }

        return binding?.root     //inflater.inflate(R.layout.fragment_input, container, false)
    }

    /*
    override fun onPause() {
        super.onPause()

        Bundle().let { bundle ->
            binding?.edtNumber1?.text.toString().toIntOrNull()?.let { number ->
                bundle.putInt("number1", number)
            }
            binding?.edtNumber2?.text.toString().toIntOrNull()?.let { number ->
                bundle.putInt("number2", number)
            }
            binding?.edtNumber3?.text.toString().toIntOrNull()?.let { number ->
                bundle.putInt("number3", number)
            }
            setFragmentResult("input", bundle)
        }
        //setFragmentResult("input", Bundle())
    }
    */

    fun setBundle() {
        Bundle().let { bundle ->
            binding?.edtNumber1?.text.toString().toIntOrNull()?.let { number ->
                bundle.putInt("number1", number)
            }
            binding?.edtNumber2?.text.toString().toIntOrNull()?.let { number ->
                bundle.putInt("number2", number)
            }
            binding?.edtNumber3?.text.toString().toIntOrNull()?.let { number ->
                bundle.putInt("number3", number)
            }
            setFragmentResult("input", bundle)      //frame에서 main으로? 보내는 결과값( bundle자료형. 아마 key:value값 ). 대충 "input"으로 간 자료형을 받기위한것.
                                                             //아마도, bundle은 frame에 있는 모든 inspector의 정보를 보냄. 여기서는 edtnumber의 number이려나?
                                                             //자료형의 구조를 보니, 스택에 쌓아서 보낸다고 봐도 과언은 아니네,
            //생각대로, key:value 형태이고, "input"은 입장권. result에 대한 특수 키
        }
        //setFragmentResult("input", Bundle())
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InputFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(number1: Int?, number2: Int?, number3: Int?) =
            InputFragment().apply {
                arguments = Bundle().apply {
                    number1?.let { putInt("number1", it) }
                    number2?.let { putInt("number2", it) }
                    number3?.let { putInt("number3", it) }
                }
            }
    }
}
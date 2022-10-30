package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.app1.databinding.ActivityMainBinding
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var number1: Int = 0
    private var number2: Int = 0
    private var number3: Int = 0

    fun aggregate(arr: IntArray, initValue: Int, op: (init: Int, elm: Int) -> Int): Int{
        var result = initValue

        for( elem in arr){
            result = op(result, elem)
        }

        return result
    }

    fun replaceFragment(fragment: Fragment) {   //fragment를 바꾸기위한 함수(.like scene변환)
        supportFragmentManager.beginTransaction().run { // fragment에게 transaction시작 명령.
            replace(binding.frmFragment.id, fragment)
            commit()
        }
    }

    fun replaceInputFragment(){
        val inputFragment = InputFragment.newInstance(number1, number2, number3)

        replaceFragment(inputFragment)

        inputFragment.setFragmentResultListener("input") { _, bundle -> //해당 fragment에서 resultlistener bundle.
            number1 = bundle.getInt("number1", 0)
            number2 = bundle.getInt("number2", 0)
            number3 = bundle.getInt("number3", 0)

        }

    }
/*
    fun setNumber(){
        inputFragment.setFragmentResultListener("input") { _, bundle ->
            number1 = bundle.getInt("number1", 0)
            number2 = bundle.getInt("number2", 0)
            number3 = bundle.getInt("number3", 0)

        }
    }
 */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)   //layout에 있는 객체들 전체로 가져오는것.
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        //replaceFragment(InputFragment.newInstance(0, 0, 0))
        replaceInputFragment()


        binding.btnInput.setOnClickListener {
            replaceInputFragment()
        //replaceFragment(InputFragment.newInstance(number1, number2, number3))
        }
        binding.btnSum.setOnClickListener {
            val n = aggregate(intArrayOf(number1, number2, number3), 0) {res, elem -> res + elem}
            replaceFragment(ResultFragment.newInstance(n))
        }
        binding.btnMax.setOnClickListener {
            //val arr: IntArray = intArrayOf(number1, number2, number3)
            val n = aggregate(intArrayOf(number1, number2, number3), Int.MIN_VALUE) {res, elem -> if( res > elem) res else elem}
            replaceFragment(ResultFragment.newInstance(n))
        }

    }
}

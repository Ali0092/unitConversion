package com.example.unitconversion.screens.menuScreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.unitconversion.R
import com.example.unitconversion.databinding.FragmentConvertorBinding

class ConvertorFragment : Fragment(R.layout.fragment_convertor),
    AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentConvertorBinding
    val args: ConvertorFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConvertorBinding.inflate(inflater, container, false)
        val type = args.convType
        spinnerAdapter(type)

        binding.basevalue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(str: CharSequence?, p1: Int, p2: Int, p3: Int) {
              val b_unit=binding.fromSpinner.selectedItem.toString()
              val c_unit=binding.tospinner.selectedItem.toString()
              val b_value=str.toString()

              convertor(b_unit,c_unit,b_value.toFloat(),type)

            }

            override fun afterTextChanged(p0: Editable?) = Unit
        })

        return binding.root
    }

    //Func. that will be called automatically whenever the item is selected from list
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        val baseValue = binding.basevalue.text.toString()
        if (baseValue.isNotEmpty()) {
            val b_unit=binding.fromSpinner.selectedItem.toString()
            val c_unit=binding.tospinner.selectedItem.toString()
            val Type=getType(b_unit)
           convertor(b_unit,c_unit,baseValue.toFloat(),Type)
        }
    }


    //Func. that sets the lists of the spinners( from and to )...
    private fun spinnerAdapter(type: String) {

        var listType = when (type) {
            "temp" -> R.array.temp_unit
            "length" -> R.array.length_unit
            "weight" -> R.array.weight_unit
            else -> R.array.time_unit
        }

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            listType,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.fromSpinner.adapter = adapter
        binding.tospinner.adapter = adapter
        binding.fromSpinner.onItemSelectedListener=this
        binding.tospinner.onItemSelectedListener=this

    }
    override fun onNothingSelected(p0: AdapterView<*>?) = Unit

    //**************************************//
    //        CONVERSION FUNCTIONS         //
    //************************************//

    private fun convertor(base_Unit:String,conv_Unit:String,base_Value:Float, type:String){
        when(type){
            "length"->   lenghtConv(base_Unit,conv_Unit,base_Value)
            "weight"->   weightConv(base_Unit,conv_Unit,base_Value)
            "temp"->     tempConv(base_Unit,conv_Unit,base_Value)
            else ->      timeConv(base_Unit,conv_Unit,base_Value)
        }
    }

    //**************************************//
    //      CONVERSION SUB FUNCTIONS       //
    //************************************//

    fun lenghtConv(base_Unit:String,conv_Unit:String,base_Value:Float){
       var res=0.0
        if(base_Unit=="cm"){
            res=when(conv_Unit){
                "mm" -> base_Value*10.0
                "m" ->  (base_Value)/100.0
                 else -> base_Value*1.0
            }
        }
        else if(base_Unit=="mm"){
            res=when(conv_Unit){
                "cm" -> (base_Value)/10.0
                 "m" -> (base_Value)/1000.0
                else -> base_Value*1.0
            }
        }
        else{
           res=when(conv_Unit){
                "mm" -> (base_Value)*1000.0
                "cm" -> (base_Value)*100.0
                else ->(base_Value)*1.0
            }
        }
       binding.resultvalue.text=res.toString()
    }

    fun weightConv(base_Unit:String,conv_Unit:String,base_Value:Float){
        var res=0.0
        if(base_Unit=="kg"){
            res=when(conv_Unit){
                "mg" -> (base_Value*1000.0)*1000.0
                "g" ->  (base_Value)*1000.0
                else -> base_Value*1.0
            }
        }
        else if(base_Unit=="mg"){
            res=when(conv_Unit){
                "g" -> (base_Value)/1000.0
                "kg" -> ((base_Value)/1000.0)/1000.0
                else -> base_Value*1.0
            }
        }
        else{
            res=when(conv_Unit){
                "mg" -> (base_Value)*1000.0
                "kg" -> (base_Value)/1000.0
                else ->(base_Value)*1.0
            }
        }
        binding.resultvalue.text=res.toString()
    }

    fun tempConv(base_Unit:String,conv_Unit:String,base_Value:Float){
        var res=0.0
        if(base_Unit=="F"){
            res=when(conv_Unit){
                "C" -> (base_Value-32.0)*(5.0/9)
                "K" ->  (base_Value-32.0)*(5.0/9) + 273.15
                else -> base_Value*1.0
            }
        }
        else if(base_Unit=="K"){
            res=when(conv_Unit){
                "C" -> base_Value-273.15
                "F" -> (base_Value-273.15)*(9/5) + 32
                else -> base_Value*1.0
            }
        }
        else{
            res=when(conv_Unit){
                "K" -> base_Value + 273.15
                "F" -> (base_Value * 9.0/5 ) + 32
                else ->(base_Value)*1.0
            }
        }
        binding.resultvalue.text=res.toString()
    }

    fun timeConv(base_Unit:String,conv_Unit:String,base_Value:Float){
        var res=0.0
        if(base_Unit=="msec"){
            res=when(conv_Unit){
                "sec" -> base_Value/1000.0
                "min" ->  ((base_Value)/60)/1000.0
                "hrs" ->  ((base_Value)/360)/1000.0
                else -> base_Value*1.0
            }
        }
        else if(base_Unit=="sec"){
            res=when(conv_Unit){
                "msec" -> (base_Value)*1000.0
                "min" -> (base_Value)/60.0
                "hrs" -> (base_Value)/3600.0
                else -> base_Value*1.0
            }
        }
        else if(base_Unit=="min"){
            res=when(conv_Unit){
                "msec" -> (base_Value)*60000.0
                "sec" -> (base_Value)*60.0
                "hrs" -> (base_Value)/60.0
                else -> base_Value*1.0
            }
        }
        else{
            res=when(conv_Unit){
                "msec" -> ((base_Value)*360)*1000.0
                "sec" -> (base_Value)*60000.0
                "min" -> (base_Value)*60.0
                else ->(base_Value)*1.0
            }
        }
        binding.resultvalue.text=res.toString()
    }
    fun getType(x: String):String{
        if(x=="cm" || x=="mm" || x=="m") return "length"
        else if(x=="kg" || x=="g" || x=="mg") return "weight"
        else if (x=="K" || x=="F" || x=="c") return "temp"
        else return "time"
    }
}


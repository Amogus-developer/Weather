package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.weather.databinding.ActivityMainBinding
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var doc: Document
    private lateinit var secThread: Thread
    private lateinit var runnable: Runnable

    lateinit var bindingClass: ActivityMainBinding
    var https = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        supportActionBar?.hide()
        ///GORODA
        bindingClass.textMoscow.setOnClickListener {
            https = "https://world-weather.ru/pogoda/russia/moscow/"
            bindingClass.textTgorod.text = "Город: Москва"
            init()
        }
        bindingClass.textTogiatty.setOnClickListener {
            https = "https://world-weather.ru/pogoda/russia/tolyatti/"
            bindingClass.textTgorod.text = "Город: Тольятти"
            init()
        }
        bindingClass.textSamara.setOnClickListener {
            https = "https://world-weather.ru/pogoda/russia/samara/"
            bindingClass.textTgorod.text = "Город: Самара"
            init()
        }
        bindingClass.textZhigulevsk.setOnClickListener {
            https = "https://world-weather.ru/pogoda/russia/zhigulevsk/"
            bindingClass.textTgorod.text = "Город: Жигулевск"
            init()
        }

    }
    private fun init(){

        runnable = Runnable {
            run(){
                getWeb()
            }
        }
        secThread = Thread(runnable)
        secThread.start()
    }

    private fun getWeb(){

        try {
            doc = Jsoup.connect(https).get()

            val tables: Elements = doc.getElementsByTag("tbody")

            val our_table: Element = tables.get(1)
            val element_from_table: Elements = our_table.children()
            val temperature: Element = element_from_table.get(0)
            val temperature_elements: Elements = temperature.children()

            bindingClass.text1.setText("Температура на ${temperature_elements.get(0).text()}: " + temperature_elements.get(1).text())

            val our_table2: Element = tables.get(1)
            val element_from_table2: Elements = our_table2.children()
            val temperature2: Element = element_from_table2.get(1)
            val temperature_elements2: Elements = temperature2.children()

            bindingClass.text2.setText("Температура на ${temperature_elements2.get(0).text()}: " + temperature_elements2.get(1).text())

            val our_table3: Element = tables.get(1)
            val element_from_table3: Elements = our_table3.children()
            val temperature3: Element = element_from_table3.get(2)
            val temperature_elements3: Elements = temperature3.children()

            bindingClass.text3.setText("Температура на ${temperature_elements3.get(0).text()}: " + temperature_elements3.get(1).text())

            val our_table4: Element = tables.get(1)
            val element_from_table4: Elements = our_table4.children()
            val temperature4: Element = element_from_table4.get(3)
            val temperature_elements4: Elements = temperature4.children()

            bindingClass.text4.setText("Температура на ${temperature_elements4.get(0).text()}: " + temperature_elements4.get(1).text())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
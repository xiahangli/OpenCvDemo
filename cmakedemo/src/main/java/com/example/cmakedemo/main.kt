package com.example.cmakedemo

class TestNestedClass{
    fun useNestedClass(bean : DateBean){
        var num = 1
        println("before$num")
        var nestclass = object : NestedClass() {
            override fun dosth(){
                num = 12
                println("num=$num")
            }
        }
        nestclass.dosth()
        println("after$num")
    }

    open class NestedClass{
        open fun dosth(){

        }
    }
    data class DateBean(var name:String)

}

fun main(args:Array<String>) {
var te = TestNestedClass()
    te.useNestedClass(TestNestedClass.DateBean("12"))
}

package expiriments.experiments

import org.junit.Test

import java.lang.reflect.Field

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    internal inner class student {
        private val name: String? = null
        var id: Int = 0
    }

    fun getStringLength(obj: Any?): Int? {
        if (obj is String) {
            // `obj` is automatically cast to `String` in this branch
            return obj.length
        }
        if (obj is Int){
            return obj.inc();
        }
        // `obj` is still of type `Any` outside of the type-checked branch
        return null
    }

     fun Int.Add( a:Int) =  a + this
    val <T> List<T>.lastIndex: Int
        get() = size - 1


    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        val c = student::class.java
        val fields = c.declaredFields
        for (f in fields) {
            println(f.name)
        }

        val  list : List<String> = listOf("Fowler", "orange", "evans")
        var x :String = "orange"
    when (x){
     in list -> println("Orange is there"+list.lastIndex)
     is String -> println("Im a string")

}




        var a: String? = "abc"
        a = null
        var int: Int = 45
      //  println(getStringLength(a))
        println(getStringLength(int))

        val nullableList: List<Int?> = listOf(1, 2, null, 4)
        val intList: List<Int> = nullableList.filterNotNull()
        println(1.Add(5))

        assertEquals( 1.Add(5) , 6);
        assertEquals(4, (2 + 2).toLong())
    }


}
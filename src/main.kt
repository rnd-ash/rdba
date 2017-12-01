import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    println("Enter mode")
    println("1. Live tracking mode")
    println("2. Historical data mode")
    val input= readLine()
    val is_in_track_mode=if(input=="1") true else if(input=="2") false else throw Exception("Invalid input \"$input\"")
    if(is_in_track_mode){
        println("Real time track mode")
        val now=Date()
        val format=SimpleDateFormat("yyyy-MM-dd").format(now)
        println("Tracking for $format")
        TODO("needs implementing")
    }
    else {
        println("Historical data mode")
        println("enter routes, separated by ','")
        val route = readLine().toString()
        println("enter dates in format YYYY-MM-DD, separated by ','")
        val date = readLine().toString()
        val current_route = route.split(',')
        val current_date = date.split(',')
        val thread_array = ArrayList<Thread>()
        //Launch each date and route combination as a separate Thread (+50% performance gain)
        //TODO launch AT MAXIMUM the number of system threads at any given time for best performance
        for (k in current_date) {
            for (i in current_route) {
                val url = "http://rtl2.ods-live.co.uk/api/trackingHistory?key=${File("api.key").readText()}&service=${i}&date=${k}&vehicle=&location="
                val thread = Thread(json_downloader(url, i, k))
                thread.start()
                thread_array.add(thread)
            }
        }
    }
}
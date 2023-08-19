import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun formatDate(input: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(input)
        return date?.let { outputFormat.format(it) } ?: ""
    }
}
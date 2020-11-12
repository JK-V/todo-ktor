import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.deser.std.DateDeserializers
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.LocalDate

data class TodoItem(
    val id: Int,
    val title: String,
    val details: String,
//    @JsonSerialize(using = ToStringSerializer::class)
//    @JsonDeserialize(using = DateDeserializers.DateDeserializer::class)
//    val dueDate: LocalDate,
    val assignedTo: String,
    val importance: String
)

enum class Importance{
    LOW, MEDIUM, HIGH
}
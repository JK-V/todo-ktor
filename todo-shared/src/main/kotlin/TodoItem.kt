import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.deser.Deserializers
import com.fasterxml.jackson.databind.deser.std.DateDeserializers
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.LocalDate

data class TodoItem(
    val title: String,
    val details: String,
    @JsonSerialize(using = ToStringSerializer::class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer::class)
    val dueDate: LocalDate,
    val assignedTo: String,
    val importance: Importance
)

enum class Importance{
    LOW, MEDIUM, HIGH
}
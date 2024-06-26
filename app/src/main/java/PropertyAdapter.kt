import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.fofoflix.R
import com.example.fofoflix.ui.MainProperty

class PropertyAdapter(
    private val properties: List<MainProperty.Property>,
    private val onAddPropertyClick: () -> Unit
) : RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    // ViewHolder que representa cada elemento en el RecyclerView
    class PropertyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)
        val location: TextView = view.findViewById(R.id.location)
        val priceNight: TextView = view.findViewById(R.id.priceNight)
        val maxGuests: TextView = view.findViewById(R.id.maxGuests)
        val addButton: Button = view.findViewById(R.id.addButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        // Inflar el layout de cada elemento
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_property, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        // Configurar cada elemento (ViewHolder) con los datos correspondientes
        val property = properties[position]
        holder.title.text = property.title
        holder.description.text = property.description
        holder.location.text = property.location
        holder.priceNight.text = "${property.priceNight} $"
        holder.maxGuests.text = "Max guests: ${property.maxGuests}"

        // Configurar el click listener del botón de agregar
        holder.addButton.setOnClickListener {
            onAddPropertyClick() // Llamar al método para mostrar el modal de agregar/editar
        }
    }

    override fun getItemCount() = properties.size
}

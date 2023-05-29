//package com.example.pasin_app.ui.history
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Card
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.recyclerview.widget.RecyclerView
//import com.example.pasin_app.R
//import com.example.pasin_app.databinding.ItemHistoryBinding
//import com.example.pasin_app.model.History
//
//class ListHistoryAdapter(private val listHistory: ArrayList<History>): RecyclerView.Adapter<ListHistoryAdapter.ListViewHolder>() {
//    class ListViewHolder(var binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)
//
//    private lateinit var onItemClickCallback: OnItemClickCallback
//
//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
//        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
//        return ListViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val (title, description, photo) = listHistory[position]
////        holder.binding.imgItemPhoto.setImageResource(photo)
////        holder.binding.tvItemTitle.text = title
////        holder.binding.tvItemDescription.text = description
////
////        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHistory[holder.adapterPosition]) }
//    }
//
//    override fun getItemCount(): Int = listHistory.size
//
//    interface OnItemClickCallback {
//        fun onItemClicked(data: History)
//    }
//
//}
//
//@Composable
//fun HistoryItem(
//    name: String,
//    waistValue: String,
//    hipValue: String,
//    chestValue: String,
//    heightValue: String,
//    recommendationValue: String,
//    onItemClick: () -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .clickable { onItemClick() }
//            .padding(vertical = 16.dp, horizontal = 20.dp)
//            .fillMaxWidth()
//    ) {
//        Box(
//            modifier = Modifier
//                .weight(0.3f)
//                .aspectRatio(1f)
//                .background(Color.Gray, RoundedCornerShape(8.dp))
//                .clickable { onItemClick() }
//        ) {
//            Card(
//                modifier = Modifier.fillMaxSize(),
//                elevation = 4.dp,
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.contoh_foto),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop
//                )
//            }
//        }
//
//        Column(
//            modifier = Modifier
//                .weight(0.7f)
//                .padding(start = 16.dp)
//        ) {
//            Text(
//                text = name,
//                fontFamily = FontFamily(Font(R.font.roboto_bold)),
//                color = Color(0xFF146C94),
//                fontSize = 16.sp,
//                modifier = Modifier.padding(bottom = 4.dp)
//            )
//
//            InfoRow(label = "Waist", value = waistValue)
//            InfoRow(label = "Hip", value = hipValue)
//            InfoRow(label = "Chest", value = chestValue)
//            InfoRow(label = "Height", value = heightValue)
//
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text(
//                    text = "Rekomendasi ukuran :",
//                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                    color = Color(0xFF146C94),
//                    fontSize = 13.sp
//                )
//                Text(
//                    text = recommendationValue,
//                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
//                    color = Color(0xFF146C94),
//                    fontSize = 13.sp,
//                    modifier = Modifier.padding(start = 15.dp)
//                )
//            }
//        }
//    }
//}
//
//
//
//
//@Composable
//fun InfoRow(label: String, value: String) {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier.padding(top = 10.dp)
//    ) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Text(
//                text = label,
//                fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                color = Color.White,
//                fontSize = 15.sp,
//                modifier = Modifier.width(45.dp)
//            )
//            Text(
//                text = ":",
//                fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                color = Color.White,
//                fontSize = 15.sp,
//                modifier = Modifier.padding(start = 15.dp)
//            )
//            Text(
//                text = value,
//                fontFamily = FontFamily(Font(R.font.roboto_bold)),
//                color = Color.White,
//                fontSize = 15.sp,
//                modifier = Modifier.padding(start = 15.dp)
//            )
//            Text(
//                text = "CM",
//                fontFamily = FontFamily(Font(R.font.roboto_regular)),
//                color = Color.White,
//                fontSize = 15.sp
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun HistoryItemPreview() {
//    MaterialTheme {
//        HistoryItem(
//            name = "John Doe",
//            waistValue = "32 inches",
//            hipValue = "40 inches",
//            chestValue = "38 inches",
//            heightValue = "6 feet",
//            recommendationValue = "M",
//            onItemClick = {}
//        )
//    }
//}

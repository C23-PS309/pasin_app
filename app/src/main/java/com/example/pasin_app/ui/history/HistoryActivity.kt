package com.example.pasin_app.ui.history

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.tween
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pasin_app.R
import com.example.pasin_app.databinding.ActivityHistoryBinding
import com.example.pasin_app.repository.ItemRepository
import com.example.pasin_app.utils.ViewModelFactory

class HistoryActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHistoryBinding.inflate(layoutInflater) }
//    private val list = ArrayList<History>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HistoryPage()
                }
            }
        }

//        binding.rvHistories.layoutManager = GridLayoutManager(this, 2)
//        list.addAll(dummyListHistory())
//        binding.rvHistories.adapter = ListHistoryAdapter(list)

    }

//    private fun dummyListHistory(): ArrayList<History> {
//        val dataTitle = arrayOf(
//            "Nama 1",
//            "Nama 2",
//            "Nama 3",
//            "Nama 4",
//            "Nama 5"
//        )
//        val dataDesc = arrayOf(
//            "Desc 1",
//            "Desc 2",
//            "Desc 3",
//            "Desc 4",
//            "Desc 5"
//        )
//
//        val listHistory = ArrayList<History>()
//        for (i in dataTitle.indices) {
//            val article = History(dataTitle[i], dataDesc[i], R.drawable.contoh_foto)
//            listHistory.add(article)
//        }
//        return listHistory
//    }
}

@Composable
fun HistoryPage(
    viewModel: HistoryViewModel = viewModel(factory = ViewModelFactory(ItemRepository())),
) {
    val groupedItems = viewModel.groupedItems.collectAsState()

    Box(
        modifier = Modifier
            .padding(15.dp, 5.dp, 15.dp, 10.dp)
    ) {
        val listState = rememberLazyListState()
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            state = listState,
        ) {
            groupedItems.value.forEach { (_, items) ->
                items(items, key = { it.historyID }) { item ->
                    HistoryContent(
                        image = item.photo,
                        name = item.title,
                        waistValue = item.measureData.waist,
                        hipValue = item.measureData.hip,
                        chestValue = item.measureData.chest,
                        heightValue = item.measureData.height,
                        recommendationValue = item.recommendation,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HistoryContent(
    image: Int,
    name: String,
    waistValue: Int,
    hipValue: Int,
    chestValue: Int,
    heightValue: Int,
    recommendationValue: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(
                shape = RoundedCornerShape(30.dp),
                color = Color(0xFFD9D9D9)
            )
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(end = 20.dp)
                .width(170.dp)
                .wrapContentSize()
        ) {
            Text(
                text = name,
                color = Color(0xFF146C94),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Column(
                modifier = modifier
                    .background(
                        shape = RoundedCornerShape(15.dp),
                        color = Color(0xFF146C94)
                    )
                    .padding(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Waist",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = ":",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = waistValue.toString(),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = "cm",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Hip",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = ":",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = hipValue.toString(),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = "cm",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Chest",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = ":",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = chestValue.toString(),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = "cm",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Height",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = ":",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = heightValue.toString(),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = "cm",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                    )
                }
            }
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
            ){
                Text(
                    text = "Rekomendasi Ukuran",
                    color = Color(0xFF146C94),
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    modifier = Modifier
                )
                Text(
                    text = ":",
                    color = Color(0xFF146C94),
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    modifier = Modifier
                )
                Text(
                    text = recommendationValue,
                    color = Color(0xFF146C94),
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    modifier = Modifier
                )
            }
        }
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(210.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PagePreview() {
    HistoryPage()
}

@Preview(showBackground = true)
@Composable
fun PreviewContent() {
    HistoryContent(
        image = R.drawable.contoh_foto,
        name = "Bob Sadino Hula Hula",
        waistValue = 65,
        hipValue = 102,
        chestValue = 23,
        heightValue = 160,
        recommendationValue = "XL",
    )
}
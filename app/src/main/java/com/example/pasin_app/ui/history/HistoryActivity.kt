package com.example.pasin_app.ui.history

import ViewModelFactoryItemRepo
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.AnimationState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.example.pasin_app.R
import com.example.pasin_app.model.UserPreference
import com.example.pasin_app.repository.ItemRepository
import com.example.pasin_app.ui.detail.DetailActivity
import com.example.pasin_app.utils.ViewModelFactory

//private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class HistoryActivity : AppCompatActivity() {

//    private val historyViewModel by viewModels<HistoryViewModel>(
//        factoryProducer = {
//            ViewModelFactory(
//                context = this,
//                pref = UserPreference.getInstance(dataStore)
//            )
//        }
//    )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Red)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.White)
                    ) {
                        TopAppBar(
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = colorResource(id = R.color.dark_blue),
                                titleContentColor = colorResource(id = R.color.white),
                                actionIconContentColor = colorResource(id = R.color.white),
                                navigationIconContentColor = colorResource(id = R.color.white)
                            ),
                            title = {
                                Text(
                                    text = "History Pasin",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_bold))
                                )
                            },
                        )
                        HistoryPage(
                            startDetailActivity = { historyId ->
                                startDetailActivity(historyId)
                            }
                        )
                    }
                }
            }
        }
    }

    private fun startDetailActivity(historyId: String) {
        Log.d("HistoryActivity", "startDetailActivity: $historyId")
        val intent = Intent(this, DetailActivity::class.java)
//        intent.putExtra(DetailActivity.EXTRA_ID, historyId)
        startActivity(intent)
    }
}

@Composable
fun HistoryPage(
    viewModel: HistoryViewModel = viewModel ( factory = ViewModelFactoryItemRepo (ItemRepository())),
    startDetailActivity: (String) -> Unit
) {
    val groupedItems = viewModel.groupedItems.collectAsState()
    Box(
        modifier = Modifier
            .padding(20.dp)
    ) {
        val listState = rememberLazyListState()
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            state = listState,
        ) {
            groupedItems.value.forEach { (_, items) ->
                items(items, key = {it.historyID}) { item ->
                    HistoryContent(
                        historyId = item.historyID,
                        image = item.photo,
                        name = item.title,
                        ageValue = item.age,
                        hipValue = item.measureData.hip,
                        shoulderValue = item.measureData.shoulder,
                        heightValue = item.measureData.height,
                        recommendationValue = item.recommendation,
                    ) {
                        startDetailActivity(item.historyID)
                    }
                }
            }
        }
    }
}

//@Composable
//fun HistoryPage(
//    viewModel: HistoryViewModel = viewModel(
//        factory = ViewModelFactory(
//            context = LocalContext.current,
//            pref = UserPreference.getInstance(dataStore = LocalContext.current.dataStore)
//        )
//    ),
//    startDetailActivity: (String) -> Unit
//) {
//    val groupedItems = viewModel.groupedItems.collectAsState()
//    Box(
//        modifier = Modifier
//            .padding(20.dp)
//    ) {
//        val listState = rememberLazyListState()
//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(5.dp),
//            state = listState,
//        ) {
//            groupedItems.value.forEach { (_, _) ->
//                items(groupedItems.value) { item ->
//                    HistoryContent(
//                        historyId = item.detailId,
//                        image = item.link_gambar,
//                        name = stringResource(id = R.string.app_name),
//                        ageValue = item.umur,
//                        hipValue = item.hipWidth,
//                        shoulderValue = item.shouldersWidth,
//                        heightValue = item.height,
//                        recommendationValue = item.result
//                    ) {
//
//                    }
//                }
//
//            }
//        }
//    }
//}

@Composable
fun HistoryContent(
    historyId: String,
    image: Int,
    name: String,
    ageValue: Int,
    hipValue: Float,
    shoulderValue: Float,
    heightValue: Int,
    recommendationValue: String,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    val umurTxt = "Umur"
    val pinggulTxt = "Pinggul"
    val tinggiTxt = "Tinggi"
    val bahuTxt = "Bahu"
    val tahunTxt = "Tahun"
    val cmTxt = "cm"

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onItemClick.invoke() }
            .background(
                shape = RoundedCornerShape(30.dp),
                color = Color(0xFFD9D9D9)
            )
            .padding(13.dp, 10.dp, 13.dp, 10.dp)
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
                        .padding(bottom = 3.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = umurTxt,
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
                            .weight(0.7f)
                    )
                    Text(
                        text = ageValue.toString(),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        modifier = Modifier
                            .weight(0.6f)
                    )
                    Text(
                        text = tahunTxt,
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier
                        .padding(bottom = 3.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = pinggulTxt,
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(1.1f)
                    )
                    Text(
                        text = ":",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(0.75f)
                    )
                    Text(
                        text = hipValue.toString(),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        modifier = Modifier
                            .weight(1.05f)
                    )
                    Text(
                        text = cmTxt,
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier
                        .padding(bottom = 3.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = bahuTxt,
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(1.1f)
                    )
                    Text(
                        text = ":",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(0.75f)
                    )
                    Text(
                        text = shoulderValue.toString(),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        modifier = Modifier
                            .weight(1.05f)
                    )
                    Text(
                        text = cmTxt,
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
                        text = tinggiTxt,
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(1.1f)
                    )
                    Text(
                        text = ":",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                            .weight(0.75f)
                    )
                    Text(
                        text = heightValue.toString(),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        modifier = Modifier
                            .weight(1.05f)
                    )
                    Text(
                        text = cmTxt,
                        color = Color.White,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        modifier = Modifier
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
            ) {
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
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .padding(top = 10.dp, bottom = 10.dp, end = 10.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(colorResource(id = R.color.gray))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PagePreview() {
    HistoryPage(startDetailActivity = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewContent() {
    HistoryContent(
        historyId = "123",
        image = R.drawable.contoh_foto,
        name = "Bob Sadino Hula Hula",
        ageValue = 65,
        hipValue = 102f,
        shoulderValue = 23f,
        heightValue = 160,
        recommendationValue = "XL",
        onItemClick = {}
    )
}
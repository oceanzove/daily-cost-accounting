package com.zove.money.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zove.money.CategoryViewModel
import com.zove.money.EntryViewModel


@Composable
fun EntryStatisticsScreen(categoryViewModel: CategoryViewModel, entryViewModel: EntryViewModel, navController: NavController){
    val categories = categoryViewModel.categories
    val entry = entryViewModel.entries

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(40.dp, 100.dp)
        ) {
            LazyColumn(modifier = Modifier
                .background(Color.Transparent)){
                items(categories) { category ->
                    Card (modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(top = 10.dp)) {
                        Column {
                            Text(text = category.name)
                            Text(text = entry.filter { it.category == category.name }.sumBy { it.cost }.toString())
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(horizontalArrangement = Arrangement.Center) {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    ,onClick = { navController.navigate("/entries")}) {
                    Text(text = "Вернуться обратно")
                }
            }
        }


    }
}

@Preview
@Composable
fun EntryStatisticsScreenPreview() {
    val categoryViewModel = viewModel<CategoryViewModel>()
    val entryViewModel = viewModel<EntryViewModel>()
    val navController = rememberNavController()

    EntryStatisticsScreen(categoryViewModel, entryViewModel, navController)
}
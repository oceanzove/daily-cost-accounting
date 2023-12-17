package com.zove.money.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zove.money.EntryViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun EntryListScreen(entryViewModel: EntryViewModel, navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)
    , contentAlignment = Alignment.Center)
    {
        Column (
            modifier = Modifier
                .fillMaxSize(0.9f)
                .verticalScroll(rememberScrollState())
        ) {
            entryViewModel.entries.forEach{
                entry ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(5.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp)){
                        Text(text = "Сумма: " + entry.cost.toString() + "₽ ")
                        Text(text = "  Категория: " + entry.category)
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row (modifier = Modifier, horizontalArrangement = Arrangement.Center) {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    ,onClick = { navController.navigate("/add-entry")}) {
                    Text(text = "Добавить запись")
                }
            }

        }
    }

}

@Preview
@Composable
fun EntryListScreenPreview(){
    val entryViewModel = viewModel<EntryViewModel>()
    val navController =  rememberNavController()

    EntryListScreen(entryViewModel = entryViewModel, navController = navController)
}
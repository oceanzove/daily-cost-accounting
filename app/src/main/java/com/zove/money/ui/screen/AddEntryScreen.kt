package com.zove.money.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zove.money.CategoryViewModel
import com.zove.money.EntryViewModel
import com.zove.money.data.Entry

@Composable
fun AddEntryScreen(entryViewModel: EntryViewModel, categoryViewModel: CategoryViewModel, navController: NavController){
    var cost by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedCategory by remember {
        mutableStateOf("")
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
            ,horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Добавить расход"
                ,modifier =  Modifier
                ,textAlign = TextAlign.Center
                ,color = Color.White
                ,fontSize = 24.sp
                ,fontWeight = FontWeight.Bold
            )
        }
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(60.dp, 180.dp)
        ) {
             TextField(value = cost, onValueChange = { cost = it}
             , placeholder = { Text(text = "Введите сумму",
                 modifier = Modifier
                     .padding(start = 45.dp)
                     .fillMaxWidth()
             )
             })
            Box (
                modifier = Modifier.padding(top = 20.dp)
            ) {
                TextButton(onClick = { expanded = true },
                    modifier = Modifier
                        .background(Color(red = 233, green = 224, blue = 237))
                        .fillMaxWidth()
                        ) {
                    Text(selectedCategory.ifEmpty { "Выберите категорию" }
                        , color = Color.Gray
                        , fontSize = 15.sp)
                }
                DropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}, modifier = Modifier
                    .fillMaxWidth(0.699f)) {
                    categoryViewModel.categories.forEach{
                        category ->

                            DropdownMenuItem(text = {
                                Row {
                                    Text(category.name)
                                    IconButton(onClick = { categoryViewModel.removeCategory(category)
                                    navController.navigate("/add-entry")})
                                    { Icon(Icons.Rounded.Delete, contentDescription = null) }
                                }
                            }, onClick = {
                                selectedCategory = category.name
                                expanded = false
                            })
                    }
                    TextButton(onClick = {
                        expanded = false
                        navController.navigate("/add-category")
                    }) {
                        Text(text = "Добавить категорию")
                    }
                }
            }
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                ,enabled = cost.isNotEmpty() && selectedCategory.isNotEmpty()
                ,onClick = {
                    entryViewModel.addEntry(Entry(cost.toInt(), selectedCategory))
                    navController.navigate("/entries")
                }
            ) {
                Text(text = "Добавить")
            }
        }
    }





}

@Preview
@Composable
fun AddEntryScreenPreview(){
    val entryViewModel = viewModel<EntryViewModel>()
    val categoryViewModel = viewModel<CategoryViewModel>()
    val navController = rememberNavController()
    AddEntryScreen(entryViewModel = entryViewModel, categoryViewModel = categoryViewModel, navController = navController)
}
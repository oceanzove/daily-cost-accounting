package com.zove.money

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zove.money.ui.screen.AddCategoryScreen
import com.zove.money.ui.screen.AddEntryScreen
import com.zove.money.ui.screen.EntryListScreen
import com.zove.money.ui.screen.EntryStatisticsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val entryViewModel = viewModel<EntryViewModel>()
            val categoryViewModel = viewModel<CategoryViewModel>()
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "/entries" ){
                composable("/entries") {
                    EntryListScreen(entryViewModel, navController)
                }
                composable("/add-entry") {
                    AddEntryScreen(entryViewModel, categoryViewModel, navController)
                }
                composable("/add-category"){
                    AddCategoryScreen(categoryViewModel, navController)
                }
                composable("/stats"){
                    EntryStatisticsScreen(categoryViewModel, entryViewModel, navController)
                }
            }
            }
        }
    }


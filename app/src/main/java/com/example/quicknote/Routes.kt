package com.example.quicknote

sealed class Routes(val route: String) {
    object Main : Routes("Main")
    object Add : Routes("Add")
    object Edit : Routes("Edit")
}
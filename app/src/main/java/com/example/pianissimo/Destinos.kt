package com.example.pianissimo

sealed class Destino(val route: String, val icon: Int, val title: String) {
    object Ecra01 : Destino(route = "ecra01", icon = R.drawable.pianissimologo, title = "Ecra01")
    object Ecra02 : Destino(route = "ecra02", icon = R.drawable.pianissimologo, title = "Ecra02")
    object Ecra03 : Destino(route = "ecra03", icon = R.drawable.pianissimologo, title = "Ecra03")
    object Ecra04 : Destino(route = "ecra04", icon = R.drawable.pianissimologo, title = "Ecra04")
    object Ecra05 : Destino(route = "ecra05", icon = R.drawable.pianissimologo, title = "Ecra05")
    companion object {
        val toList = listOf(Ecra01, Ecra02, Ecra03, Ecra04, Ecra05)
    }
}
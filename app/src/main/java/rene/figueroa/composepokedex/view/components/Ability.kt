package rene.figueroa.composepokedex.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Ability(type:String, label:String, value: String){
    if(type.lowercase() == "row"){
        Row {
            Label(label)
            Text(value)
        }
    }else{
        Column {
            Label(label)
            Text(value)
        }
    }
}

@Composable
fun Label(label:String){
    Text(label)
}
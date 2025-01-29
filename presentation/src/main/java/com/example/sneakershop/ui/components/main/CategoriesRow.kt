package com.example.sneakershop.ui.components.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.ProductCategory
import com.example.sneakershop.ui.theme.customAccentColor
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customTextColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily
import com.example.sneakershop.ui.theme.ralewayFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesRow(
    categoriesList: List<ProductCategory>,
    onCategoryCardClick: (ProductCategory) -> Unit,
    selectedCategory: ProductCategory? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Категории",
            fontSize = 16.sp,
            fontFamily = newPeninimMTFontFamily,
            color = customTextColor
        )
        Spacer(modifier = Modifier.height(19.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(categoriesList) { category ->
                Card(
                    onClick = { onCategoryCardClick(category) },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if(selectedCategory == category) customAccentColor else customBlockColor,
                        contentColor = if(selectedCategory == category) customBlockColor else customTextColor
                    ),
                    modifier = Modifier
                        .size(108.dp, 40.dp)
                ) {
                    Text(
                        text = category.value,
                        fontSize = 12.sp,
                        fontFamily = ralewayFontFamily,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize()
                    )
                }
            }
        }
    }
}
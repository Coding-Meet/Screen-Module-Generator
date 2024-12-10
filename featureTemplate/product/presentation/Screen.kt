package {{packageName}}.{{featureName}}.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import {{packageName}}.{{featureName}}.data.network.*
import {{packageName}}.{{featureName}}.domain.model.{{className}}
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun {{className}}ListScreen() {

    val viewModel: {{className}}ViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val {{featureName}}Service = Retrofit.Builder()
                    .baseUrl("https://fakestoreapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create({{className}}Service::class.java)

                val repository = {{className}}RepositoryImpl({{featureName}}Service)
                return {{className}}ViewModel(repository) as T
            }
        }
    )

    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = uiState.{{featureName}}s, key = {
                it.id
            }) { {{featureName}} ->
                {{className}}Card({{featureName}})
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun {{className}}Card({{featureName}}: {{className}}) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = {{featureName}}.image,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = {{featureName}}.title)
            Text(text = "Price: ${{{featureName}}.price}")
            Text(text = {{featureName}}.description)
        }
    }
}

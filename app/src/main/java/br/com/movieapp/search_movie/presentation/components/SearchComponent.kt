package br.com.movieapp.search_movie.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.R
import br.com.movieapp.search_movie.presentation.MovieSearchEvent
import br.com.movieapp.ui.theme.white

@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    query: String,
    onSearch: (String) -> Unit,
    onQueryChangeEvent: (MovieSearchEvent) -> Unit
) {
    OutlinedTextField(
        value = query,
        placeholder = {
            Text(
                text = stringResource(
                    id = R.string.search_movies
                )
            )
        },
        onValueChange = {
            onQueryChangeEvent(MovieSearchEvent.EnteredQuery(it))
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onSearch(query)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            // para quando abrir o teclado vir com as letras em maiusculo
            capitalization = KeyboardCapitalization.Sentences,
            // autocorrecao no teclado
            autoCorrect = true,
            // tipo do teclado
            keyboardType = KeyboardType.Text,
            // adicionar o botao de pesquisa no teclado
            imeAction = ImeAction.Search
        ),
        // setando acao de clicar no icone de pesquisar do teclado
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = white,
            cursorColor = white,
            placeholderColor = white,
            trailingIconColor = white,
        ),
        // para ter largura maxima
        // altura no minimo 60dp
        modifier = modifier
            .fillMaxWidth()
            .heightIn(60.dp)
    )
}

@Preview
@Composable
private fun SearchComponentPreview() {
    SearchComponent(
        query = "",
        onSearch = {},
        onQueryChangeEvent = {},
    )
}
package br.com.movieapp.movie_popular.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import br.com.movieapp.R
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MovieItem(
    modifier: Modifier,
    id: Int,
    voteAverage: Double,
    imageUrl: String,
    onClick: (id: Int) -> Unit,
) {

    Box(
        modifier = modifier
    ) {
        MovieRate(
            rate = voteAverage,
            modifier = modifier
                .align(Alignment.BottomStart)
                // zIndex -> Define que o MovieRate vai ficar na frente da imagem, (como se a imagem ficasse de fundo e o MovieRate na frente para mostrar os dois)
                .zIndex(1f)
                .padding(start = 6.dp, bottom = 8.dp)
                .background(Color.Black)
        )
        Card(
            // fillMaxSize -> ocupte toda a tela
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
                .padding(4.dp)
                .clickable {
                    onClick(id)
                },
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        // aqui passo a imagem q vai ser carregada
                        .data(imageUrl)
                        // aqui coloco um efeito/ animacao
                        .crossfade(true)
                        // aqui se der algum erro eu mostro isso
                        .error(R.drawable.ic_error_image)
                        // aqui em quanto ta carregando a imagem vai mostrar isso
                        .placeholder(R.drawable.ic_placeholder)
                        .build(),
                    contentDescription = null,
                    // para preencher toda a altura
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        // quero que a imagem preencha toda sua largura
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(Color.Black)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

        }


    }

}

@Preview
@Composable
private fun MovieItemPreview() {
    MovieItem(
        modifier = Modifier,
        id = 1,
        voteAverage = 7.2,
        imageUrl = "",
        onClick = {},
    )
}
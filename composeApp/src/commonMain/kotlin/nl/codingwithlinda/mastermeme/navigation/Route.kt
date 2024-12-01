package nl.codingwithlinda.mastermeme.navigation

sealed interface Route {
   val route: String

   data object MemeList: Route {
      override val route: String = "meme_list"
   }

   data object MemeCreator: Route {
       override val route: String
           get() = "meme_creator"
   }
}
import SwiftUI
import shared


struct ContentView: View {
    @State var repository = StarWarsRepository()
    @State var filmList: [Film] = []
    
    var body: some View {
        NavigationView {
            List(filmList, id: \.id) { film in
                FilmView(film: film)
            }
            .navigationTitle("Star Wars")
            .onAppear {
                repository.getFilms { filmList in
                    self.filmList = filmList
                }
            }
        }
    }
}
struct FilmView: View {
    var film: Film
    
    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text("\(film.title) - \(film.releaseDate)").font(.headline)
                Text(film.director).font(.subheadline)
            }
        }
    }
}


import SwiftUI
import shared
import KMPNativeCoroutinesAsync


struct ContentView: View {
    @State var repo = StarWarsRepository()
    @State var filmList: [FilmFragment] = []
    
    var body: some View {
        List(filmList) { film in
            VStack(alignment: .leading) {
                Text(film.title).font(.headline)
                Text(film.director).font(.subheadline)
            }
        }
        .task {
            await observeFilms()
        }
    }
    
    func observeFilms() async {
        do {
            let stream = asyncSequence(for: repo.films)
            for try await data in stream {
                self.filmList = data
            }
        } catch {
            print("Failed with error: \(error)")
        }
    }
}




struct PeopleView: View {
    @StateObject private var viewModel = StarWarsViewModel()
    
    var body: some View {
        NavigationView {
            List(viewModel.peopleList) { person in
                PersonView(person: person)
            }
            .navigationTitle("Star Wars")
            .task {
                await viewModel.startObservingPeople()
            }
        }
    }
}

struct PersonView: View {
    var person: PersonFragment
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(person.name).font(.headline)
            Text(person.homeworld.name).font(.subheadline)
        }
    }
}


struct FilmListView: View {
    @StateObject private var viewModel = StarWarsViewModel()
    
    var body: some View {
        
        NavigationView {
            List(viewModel.filmList) { film in
                FilmView(film: film)
            }
            .navigationTitle("Films")
            .task {
                await viewModel.startObservingFilms()
            }
        }
    }
}

struct FilmView: View {
    var film: FilmFragment
    
    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text(film.title).font(.headline)
                Text(film.director).font(.subheadline)
            }
        }
    }
}

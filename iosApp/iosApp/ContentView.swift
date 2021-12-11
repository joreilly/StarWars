import SwiftUI
import shared


struct ContentView: View {
    @StateObject private var viewModel = StarWarsViewModel()
    
    var body: some View {
        TabView {
            PeopleView()
                .tabItem {
                    Label("People", systemImage: "person")
                }
            FilmListView()
                .tabItem {
                    Label("Films", systemImage: "film")
                }
        }
    }
}



struct PeopleView: View {
    @StateObject private var viewModel = StarWarsViewModel()
    
    var body: some View {
        NavigationView {
            List(viewModel.peopleList, id: \.id) { person in
                PersonView(person: person)
            }
            .navigationTitle("Star Wars")
            .onAppear {
                viewModel.fetchPeople()
            }
        }
    }
}

struct PersonView: View {
    var person: Person
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(person.name).font(.headline)
            Text(person.homeWorld).font(.subheadline)
        }
    }
}

struct FilmListView: View {
    @StateObject private var viewModel = StarWarsViewModel()
    
    var body: some View {
        
        NavigationView {
            List(viewModel.filmList, id: \.id) { film in
                FilmView(film: film)
            }
            .navigationTitle("Films")
            .onAppear {
                viewModel.fetchFilms()
            }
        }
    }
}

struct FilmView: View {
    var film: Film
    
    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text(film.title).font(.headline)
                Text(film.director).font(.subheadline)
            }
        }
    }
}
